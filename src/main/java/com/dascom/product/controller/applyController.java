package com.dascom.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dascom.product.constant.LoginConstant;
import com.dascom.product.entity.CpResource;
import com.dascom.product.entity.CpResourceExample;
import com.dascom.product.entity.CpSoftware;
import com.dascom.product.entity.Solves;
import com.dascom.product.entity.WorkOrder;
import com.dascom.product.service.ResourceService;
import com.dascom.product.service.SolvesService;
import com.dascom.product.service.WorkOrderService;
import com.dascom.product.serviceImpl.WorkOrderServiceImpl;
import com.dascom.product.util.FileServer;
import com.dascom.product.util.JavaEmailSender;
import com.dascom.product.util.JsonTransform;
import com.dascom.product.util.PagedResult;

/**
 * 前端接口
 * @author DS-033
 *
 */
@RequestMapping("apply")
@Controller
public class applyController {
	
	@Autowired
	private SolvesService solvesServiceImpl;
	@Autowired
	private ResourceService resourceServiceImpl;
	@Autowired
	private WorkOrderService workOrderServiceImpl;
	
	//提交工单
	@RequestMapping(value="addWorkOrderSumbit",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public String addWorkOrderSumbit(HttpServletRequest request,WorkOrder workOrder, MultipartFile fileUrl){
		String error=null;
		try {
			//验证验证码是否通过.
			Subject sub =SecurityUtils.getSubject();
			Session session =sub.getSession();
			//系统的验证码
	        String code = (String) session.getAttribute("validateCode");
	        //用户输入的验证码
	        String submitCode = WebUtils.getCleanParam(request, "validateCode");
	        if (StringUtils.isEmpty(submitCode)  || !StringUtils.equals(code,submitCode.toLowerCase())) {
	            return JsonTransform.loginJsonTransform( LoginConstant.LOGIN_ERROR_CODE_100000, LoginConstant.LOGIN_ERROR_MESSAGE_VALIDATECODE, null);
	        }
	        //验证表单数据
			error=workOrderValidate(workOrder);
			if(error!=null){
				throw new Exception("提交工单,验证不通过.");
			}
			
			//上传文件到tomcat上 
			String path=FileServer.uploadSoftwareFile(fileUrl,FileServer.tomcatAddress(request.getSession().getServletContext().getRealPath("/"))+FileServer.workOrderContent);
			if(path==null&&("".equals(fileUrl.getOriginalFilename())||fileUrl.getOriginalFilename()==null)){
				error="文件上传失败.";
				throw new Exception();
			}
			workOrder.setAttachmentPath(path);
			workOrder.setAttachmentSuffix( path.substring(path.lastIndexOf(".")+1));
			if(workOrderServiceImpl.addworkOrder(workOrder)>0){
				JavaEmailSender.sendEmail("1005530584@qq.com", workOrder.getTheme(), workOrder.getContent(), null);
				return JsonTransform.loginJsonTransform(LoginConstant.SUCCEED_CODE, LoginConstant.SUCCEED_MESSAGE, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("提交工单时发生异常"+e);
		}
		return JsonTransform.loginJsonTransform(LoginConstant.ERROR_CODE, error==null?LoginConstant.ERROR_MESSAGE:error, null);
	}
	
	
	private String workOrderValidate(WorkOrder workOrder){
		if(workOrder.geteMail()==null||"".equals(workOrder.geteMail())){
			return "邮箱地址不能为空.";
		}
		//获取邮箱地址 
		String email = workOrder.geteMail();
		Integer fuhao2=email.indexOf("@");
		Integer fuhao1=email.indexOf(".");
		if(fuhao2==-1||fuhao1==-1||fuhao1<fuhao2){
			return "邮箱格式不正确.";
		}
		
		if(workOrder.getPhone()==null){
			return "电话格式不正确.";
		}else if(workOrder.getPhone().length()!=11){
			return "电话格式不正确.";
		}
		if(workOrder.getContent()==null||"".equals(workOrder.getContent())){
			return "内容不能为空.";
		}
		return null;
	}
	
	
	
	
	//置顶解决方案
	@RequestMapping(value="findTopperSolves",produces="application/json;charset=utf-8")
	@ResponseBody
	public String  findTopperSolves(HttpServletRequest request ){
		try {
			Solves solves=new Solves(); 
			solves.setTopper(1);
			List<Solves> solveList=solvesServiceImpl.seleteBySolves(solves);
			if(solveList!=null){
				return  JsonTransform.loginJsonTransform(LoginConstant.SUCCEED_CODE, LoginConstant.SUCCEED_MESSAGE, solveList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("出现异常:"+e.getMessage());
		}
		return JsonTransform.loginJsonTransform(LoginConstant.ERROR_CODE, LoginConstant.ERROR_MESSAGE, null);
	}
	
	//非置顶解决方案
	@RequestMapping(value="findNoTopperSolves",produces="application/json;charset=utf-8")
	@ResponseBody
	public String  findNOTopperSolves(HttpServletRequest request ){
		try {
			Solves solves=new Solves(); 
			solves.setTopper(0);
			List<Solves> solveList=solvesServiceImpl.seleteBySolves(solves);
			if(solveList!=null){
				return  JsonTransform.loginJsonTransform(LoginConstant.SUCCEED_CODE, LoginConstant.SUCCEED_MESSAGE, solveList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("出现异常:"+e.getMessage());
		}
		return JsonTransform.loginJsonTransform(LoginConstant.ERROR_CODE, LoginConstant.ERROR_MESSAGE, null);
	}
	
	//应用软件
	@RequestMapping(value="findsoft",produces="application/json;charset=utf-8")
	@ResponseBody
	public String  findsoft(HttpServletResponse response, @RequestParam(value="", defaultValue="1") Integer pageNumber,@RequestParam(value="",defaultValue="5")Integer pageSize){
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			PagedResult<CpResourceExample> listRes=resourceServiceImpl.findApp(pageNumber,pageSize);
			return JsonTransform.loginJsonTransform("1000", "成功", listRes);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("出现异常:"+e.getMessage());
		}
		return JsonTransform.loginJsonTransform(LoginConstant.ERROR_CODE, LoginConstant.ERROR_MESSAGE, null);
	}
	
	//查看对应版本 
	@RequestMapping(value="findVersion",produces="application/json;charset=utf-8")
	@ResponseBody
	public String findVersion(Integer id){
		try {
			List<CpSoftware> listsoft=resourceServiceImpl.findAppVersion(null, null, id).getDataList();
			if(listsoft!=null){
				return JsonTransform.loginJsonTransform(LoginConstant.SUCCEED_CODE, LoginConstant.SUCCEED_MESSAGE, listsoft);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查看对应版本的时候出错."+e);
		}
		return JsonTransform .loginJsonTransform(LoginConstant.ERROR_CODE, LoginConstant.ERROR_MESSAGE, null);
	}
	
	//查询对应资源
	@RequestMapping(value="findResource",produces="application/json;charset=utf-8")
	@ResponseBody
	public String findResource(Integer id){
		try {
			CpResource res= resourceServiceImpl.findByRid(id);
			if(res!=null){
				return JsonTransform.loginJsonTransform(LoginConstant.SUCCEED_CODE, LoginConstant.SUCCEED_MESSAGE,res );
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查询对应资源是出现异样:"+e);
		}
		return JsonTransform.loginJsonTransform(LoginConstant.ERROR_CODE, LoginConstant.ERROR_MESSAGE, null);
	}
	
	//二次开发包.
	@RequestMapping(value="findSDK",produces="application/json;charset=utf-8")
	@ResponseBody
	public String  findSDK(@RequestParam(value="", defaultValue="1") Integer pageNumber,@RequestParam(value="",defaultValue="5")Integer pageSize){
		try {
			PagedResult<CpResourceExample> listRes=resourceServiceImpl.findResource("二次开发包",pageNumber,pageSize);
			return JsonTransform.loginJsonTransform("1000", "成功", listRes);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("出现异常:"+e.getMessage());
		}
		return JsonTransform.loginJsonTransform(LoginConstant.ERROR_CODE, LoginConstant.ERROR_MESSAGE, null);
	}
	
	
	//查看的解决方案  
	@RequestMapping(value="findSolves",produces="application/json;charset=utf-8")
	@ResponseBody
	public String  findSolves(Integer id){
		try {
			Solves item =solvesServiceImpl.findSolvesByKey(id);
			if(item!=null){
				return JsonTransform.loginJsonTransform("1000","成功", item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("出现异常:"+e.getMessage());
		}
		return JsonTransform.loginJsonTransform(LoginConstant.ERROR_CODE, LoginConstant.ERROR_MESSAGE, null);
	}
	
	
	
}

package com.dascom.product.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dascom.product.constant.LoginConstant;
import com.dascom.product.entity.Solves;
import com.dascom.product.service.SolvesService;
import com.dascom.product.util.FileServer;
import com.dascom.product.util.JsonTransform;
import com.dascom.product.util.PagedResult;

@Controller
public class SolvesController {
	
	@Autowired
	private SolvesService  solvesServiceImpl;
	
	/**
	 * 查看解决方案 分页集合 带模糊查询
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @param title
	 * @return
	 */
	@RequestMapping(value="findsolveList")
	public String findsolveList(HttpServletRequest request ,@RequestParam(value="", defaultValue="1") Integer pageNumber,@RequestParam(value="",defaultValue="5")Integer pageSize,String title ){
		try {
			PagedResult<Solves>  solveList=solvesServiceImpl.findsolveList(pageNumber,pageSize,title);
			request.setAttribute("solveList", solveList);
			request .setAttribute("title", title);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查看解决方案集合的时候出现了异常:"+e);
		}
		return "Solves/solvesList";
	}
	
	
	
	/**
	 * 跳转至
	 * @return
	 */
	@RequestMapping(value="addSolves")
	public String addSolves(){
		return "Solves/solvesAdd";
	}
	
	@RequestMapping(value="addSolvesSubmit" ,produces="application/json;charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public String addSolvesSubmit(HttpServletRequest request ,MultipartFile imgFile,Solves solves){
		try {
			String error=solvesValiDate(solves);
			if(error!=null){
				throw new Exception(error);
			}
			String url=FileServer.uploadFile(imgFile, FileServer.tomcatAddress(request.getSession().getServletContext().getRealPath("/")) +FileServer.thumbnailPath);
			if(!"".equals(url)){
				solves.setCoverUrl(url);
			}
			int i= solvesServiceImpl.addSolves(solves);
			if(i==-1){
				throw new Exception("置顶数大于三");
			}
			if(i<=0){
				throw new Exception("很遗憾出现了意外的错误.");
			}
			return JsonTransform.loginJsonTransform("1000", "添加成功.", null);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return JsonTransform.loginJsonTransform("1001", e.getMessage()!=null?e.getMessage():"很遗憾,出现了意外的错误.", null);
		}
	}
	
	/**
	 * 删除解决方案
	 * @param id  格式  1,12,3,6
	 * @return
	 */
	@RequestMapping(value="delSolves",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public String delSolves(String id){
		try {
			int i =solvesServiceImpl.delSolves(id);
			if(i>0){
				return JsonTransform.loginJsonTransform(LoginConstant.SUCCEED_CODE, LoginConstant.SUCCEED_MESSAGE, null);
			}
			return JsonTransform.loginJsonTransform("1001","失败", null);
		} catch (Exception e) {
			return JsonTransform.loginJsonTransform("1001",e.getMessage()!=null?e.getMessage():LoginConstant.ERROR_MESSAGE, null);
		}
		
	}
	
	
	@RequestMapping(value="editSolves")
	public String editSolves(HttpServletRequest request,Integer id){
		try {
			Solves solves=solvesServiceImpl.findSolvesByKey(id);
			request.setAttribute("item", solves);
		} catch (Exception e) {
			System.out.println("出现异常:"+e.getMessage());
		}
		return "Solves/solvesEdit";
	}
	
	@RequestMapping(value="editSolvesSubmit" ,produces="application/json;charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public String editSolvesSubmit(HttpServletRequest request ,MultipartFile imgFile,Solves solves){
		String error="很遗憾,出现了意外的错误.";
		try {
			 error=solvesValiDate(solves);
			if(solves.getTopper()==null){
				solves.setTopper(0);
			}
			if(solves.getProf()==null){
				solves.setProf(1);
			}
			if(error!=null){
				throw new Exception(error);
			}
			String url=FileServer.uploadFile(imgFile, FileServer.tomcatAddress(request.getSession().getServletContext().getRealPath("/")) +FileServer.thumbnailPath);
			if(!"".equals(url))
				solves.setCoverUrl(url);
			int i= solvesServiceImpl.editSolves(solves);
			if(i==-1){
				error="置顶数大于三";
				throw new Exception("置顶数大于三");
			}
			if(i<=0){
				error="很遗憾出现了意外的错误.";
				throw new Exception("很遗憾出现了意外的错误.");
			}
			return JsonTransform.loginJsonTransform("1000", "修改成功.", null);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return JsonTransform.loginJsonTransform("1001", e.getMessage()!=null?e.getMessage():"很遗憾,出现了意外的错误.", null);
		}
	}
	
	
	
	
	/**
	 *  Solves数据校验 
	 * @param solves
	 * @return
	 */
	public String solvesValiDate(Solves solves){
		if(solves.getTitle()==null||solves.getTitle().equals("")){
			return "标题不能为空.";
		}
		if(solves.getTitle().length()>16||solves.getTitle().length()<1){
			return "请输入长度为2~16的标题";
		}
		if(solves .getContext()==null||solves.getContext().equals("")){
			return "内容不能为空.";
		}
		if(!(solves.getProf()==null||solves.getProf()==1||solves.getProf()==0)){
			return "出现了意外的错误。";
		}
		if(!(solves.getTopper()==null||solves.getTopper()==1||solves.getTopper()==0)){
			return "出现了意外的错误。";
		}
		return null;
	}
	
	
	
	
}

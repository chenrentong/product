package com.dascom.product.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dascom.product.util.FileServer;
import com.dascom.product.util.JsonTransform;
import com.dascom.product.util.PagedResult;
import com.dascom.product.entity.CpResource;
import com.dascom.product.entity.CpResourceExample;
import com.dascom.product.entity.CpSoftware;
import com.dascom.product.service.ResourceService;
import com.dascom.product.service.UploadService;

@Controller
public class ResourceController {
	
	@Autowired
	private ResourceService resourceServiceImpl;
	@Autowired
	private UploadService  uploadServiceimpl;
	
	//查看应用软件
	@RequestMapping(value="findApp",produces="application/json;charset=utf-8")
	@ResponseBody
	public String  findApp(@RequestParam(value="", defaultValue="1") Integer pageNumber,@RequestParam(value="",defaultValue="5")Integer pageSize){
		PagedResult<CpResourceExample> listRes=resourceServiceImpl.findApp(pageNumber,pageSize);
		JSONObject json =new JSONObject(listRes);
		return json.toString();
	}
	
	//查询应用软件
	@RequestMapping(value="findAppPage")
	public String  findAppPage( HttpServletRequest request,@RequestParam(value="", defaultValue="1") Integer pageNumber,@RequestParam(value="",defaultValue="5")Integer pageSize,String like ){
		try {
			PagedResult<CpResourceExample> listRes=resourceServiceImpl.findApp("应用软件",pageNumber,pageSize,like);
			request.setAttribute("listRes", listRes);
			if(like!=null&&!"".equals(like)){
				request.setAttribute("like", like);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Software/softList";	
		}
		return "Software/softList";
	}
	/**
	 * 查找二次开发包
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @param like
	 * @return
	 */
	@RequestMapping(value="findSDKPage")
	public String  findSDKPage( HttpServletRequest request,@RequestParam(value="", defaultValue="1") Integer pageNumber,@RequestParam(value="",defaultValue="5")Integer pageSize,String like ){
		try {
			PagedResult<CpResourceExample> listRes=resourceServiceImpl.findApp("二次开发包",pageNumber,pageSize,like);
			request.setAttribute("listRes", listRes);
			if(!(like==null&&"".equals(like))){
				request.setAttribute("like", like);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SDK/SDKList";
	}
	
	
	
	@RequestMapping (value="addSoft")
	public String addSoft(HttpServletRequest request){
		return  "Software/softAdd";
	}
	
	@RequestMapping (value="addSDK")
	public String addSDK(HttpServletRequest request){
		return  "SDK/Add";
	}
	
	
	/**
	 * 添加应用软件的提交方法
	 * @param request
	 * @param res
	 * @param imgFile
	 * @return
	 */
	@RequestMapping(value = "addSoftSubmit",produces="application/json;charset=utf-8")
	@ResponseBody
	public String addSoftSubmit(HttpServletRequest request, CpResource res, MultipartFile imgFile ){
		return addResource(request,"应用软件",res,imgFile);
	}
	
	
	/**
	 * 添加二次开发包的提交方法
	 * @param res
	 * @param imgFile
	 * @return
	 */
	@RequestMapping(value = "addSDKSubmit",produces="application/json;charset=utf-8")
	@ResponseBody
	public String addSDKSubmit(HttpServletRequest request, CpResource res, MultipartFile imgFile ){
		
		return addResource(request,"二次开发包",res,imgFile);
	}
	
	public String  addResource(HttpServletRequest request,String resourceTypeName, CpResource res, MultipartFile imgFile ){
		try {
			String valiDate=resValidate(res);
			if(valiDate!=null){
				return JsonTransform.loginJsonTransform("1001", valiDate, null);
			}
			//tomcat的根目录
			
			if(resourceServiceImpl.addresource(resourceTypeName,res.getTitle(),res.getName(),null,imgFile!=null?FileServer.uploadFile(imgFile, FileServer.tomcatAddress(request.getSession().getServletContext().getRealPath("/"))+FileServer.thumbnailPath):null,null,res.getDescribe())>=0){
				return JsonTransform.loginJsonTransform("1000", "成功", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonTransform.loginJsonTransform("1001", "失败 ,请联系管理员.", null);
	}
	
	/**
	 * 批量删除
	 * @param rid 格式   1,2,3,4  逗号分割
	 * @return
	 */
	@RequestMapping(value="delSoft" ,produces="application/json;charset=utf-8")
	@ResponseBody 
	public String delSoft(String rid){
		try {
			if(resourceServiceImpl.delByKeyList(rid)>=0){
				return JsonTransform .loginJsonTransform("1000", "删除成功", null);
			}
		} catch (Exception e) {
			System.out.println("出现删除资源异常:"+e);
		}
		return JsonTransform .loginJsonTransform("1001", "删除失败", null);
	}
	
	@RequestMapping(value="softEdit")
	public String softEdit(HttpServletRequest request,Integer id){
		try {
			CpResource res=resourceServiceImpl.findByRid(id);
			request.setAttribute("res", res);
		} catch (Exception e) {
			System.out.println("修改应用软件出现异常:"+e);	
		}
		return "Software/softEdit";
	}
	
	
	/**
	 * 提交修改资源
	 * @param request
	 * @param res
	 * @param imgFile
	 * @return
	 */
	@RequestMapping(value="softEditSubmit",produces="application/json;charset=utf-8")
	@ResponseBody
	public String softEditSubmit(HttpServletRequest request, CpResource res, MultipartFile imgFile){
		try {
 			String valiDate=resValidate(res);
			if(valiDate!=null){
				return JsonTransform.loginJsonTransform("1001", valiDate, null);
			}
			String imgFilePath=FileServer.uploadFile(imgFile, FileServer.tomcatAddress(request.getSession().getServletContext().getRealPath("/"))+FileServer.thumbnailPath);
			if(!imgFilePath.equals("")){
				res.setCoverUrl(imgFilePath);
			}
			if(resourceServiceImpl.updateresource(res)>0){
				return JsonTransform.loginJsonTransform("1000", "成功", null);
			}
		} catch (Exception e) {
			System.out.println("修改应用软件提交任务出现异常:"+e);
			e.printStackTrace();
			return JsonTransform.loginJsonTransform("1001", e.getMessage(), null);
		}
		return JsonTransform.loginJsonTransform("1001", "修改失败,请联系管理员", null);
	}
	
	@RequestMapping(value="versionList")
	public String versionList(HttpServletRequest request,Integer id,@RequestParam(value="", defaultValue="1") Integer pageNumber,@RequestParam(value="",defaultValue="5")Integer pageSize){
		try { 
			
			PagedResult<CpSoftware> sList=resourceServiceImpl.findAppVersion(pageNumber,pageSize,id);
			request.setAttribute("sList", sList);
			request.setAttribute("rid", id);
		} catch (Exception e) {
			System.out.println("查看软件版本出现了问题.");
		}
		return "Software/versionList";
	}
	
	/**
	 * 添加应用软件版本
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="addVersion")
	public String addVersion(HttpServletRequest request,Integer id){
		request.setAttribute("rid", id);
		return "Software/versionAdd";
	}
	
	@RequestMapping(value="editVersion")
	public String editVersion(HttpServletRequest request, Integer id){
		try {
			CpSoftware soft=resourceServiceImpl.findVersionBykey(id);
			request.setAttribute("soft", soft);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Software/versionEdit";
	}
	
	/**
	 * 添加应用软件版本提交
	 * @param request
	 * @param soft
	 * @return
	 */
	@RequestMapping(value="addVersionSubmit",produces="application/json;charset=utf-8")
	@ResponseBody
	public String addVersionSubmit(HttpServletRequest request,CpSoftware soft){
		String error="添加失败.";
		try {
			String valiDate=softValidate(soft);
			
			if(valiDate!=null){ 
				error =valiDate;
				throw new Exception(error);
			}
			int number=resourceServiceImpl.addsoft(soft);
			if(number ==-1){
				error="版本号重复."; 
			}else if(number==-2){
				error ="已经有最新版.";
			}else if(number>0){
				return JsonTransform.loginJsonTransform("1000","添加成功", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonTransform.loginJsonTransform("1001", error, null);
	}
	
	@RequestMapping(value="editVersionSubmit",produces="application/json;charset=utf-8")
	@ResponseBody
	public String editVersionSubmit(HttpServletRequest request,CpSoftware soft){
		String error="修改失败.";
		try {
			String valiDate=softValidate(soft);
			if(valiDate!=null){ 
				error =valiDate;
				throw new Exception(error);
			}
			int number=resourceServiceImpl.editSoft(soft);
			if(number ==-1){
				error="版本号重复."; 
			}else if(number==-2){
				error ="已经有最新版.";
			}else if(number>0){
				return JsonTransform.loginJsonTransform("1000","修改成功", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonTransform.loginJsonTransform("1001", error, null);
	}
	
	
	/**
	 * 上传文件到服务器
	 * @param file
	 * @return
	 */
	@RequestMapping(value="uploadSoftware",produces="application/json;charset=utf-8")
	@ResponseBody
	public String uploadSoftware(HttpServletRequest request,MultipartFile file){
		try {
			String path =FileServer.uploadSoftwareFile(file,FileServer.tomcatAddress(request.getSession().getServletContext().getRealPath("/"))+FileServer.software);
			Map<String , Object > map=new HashMap<String, Object>();
			map.put("path", path);
			map.put("size", file.getSize());
			map.put("suffix", file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1));
			if(path!=null){
				//保存缓存到数据库
				/*uploadServiceimpl.add(path, String.valueOf(file.getSize()), file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1));*/
				return JsonTransform.loginJsonTransform("1000", "上传成功.", map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonTransform.loginJsonTransform("1001", "上传失败.", null);
	}
	
	@RequestMapping(value="delVersion",produces="application/json;charset=utf-8")
	@ResponseBody
	public String delVersion(String id){
		String error="删除失败 .";
		try {
			int i= 	resourceServiceImpl.delVersion(id);
			if(i>0){
				return JsonTransform.loginJsonTransform("1000", "删除成功", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			error ="删除失败 ,出现了意外的错误.";
		}
		return JsonTransform.loginJsonTransform("1001", error, null);
	}
	
	
	
	 /**
     * ckeditor图片上传
     * 
     * @Title imageUpload
     * @param request
     * @param response
     */
    @RequestMapping("imageUpload")
    public void imageUpload(HttpServletRequest request, HttpServletResponse response) {
        try {
        	
        	String path = FileServer.upload(request,FileServer.resource,FileServer.tomcatAddress(request.getSession().getServletContext().getRealPath("/")));
        	// 结合ckeditor功能
            // imageContextPath为图片在服务器地址，如upload/123.jpg,非绝对路径
            String imageContextPath = /*request.getContextPath() + "/" + *//*"http://"+LoginConstant.HOSTNAME+":"+LoginConstant.PORT+"/product/"+*/path;
            response.setContentType("text/html;charset=UTF-8");
            String callback = request.getParameter("CKEditorFuncNum");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageContextPath + "',''" + ")");
            out.println("</script>");
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
	
	
	
	
	
	
	
	/**
	 * 应用软件的版本 . 
	 * @param soft
	 * @return 返回空表示没有错误
	 */
	public String softValidate(CpSoftware soft){
		if(soft==null){
			return "请正确填写数据";
		}
		if(soft.getRid()==null){
			return "数据有误 .请重新打开页面.";
		}
		if(soft.getVersion()==null ){
			return "版本名不能为空.";
		}else if(!(soft.getVersion().length()>=2&&soft.getVersion().length()<=16)){
			return "版本名长度应为2~16.";
		}
		if(soft.getVersionNum()==null ){
			return "版本号不能为空.";
		}else if(!(soft.getVersionNum().length()>=2&&soft.getVersionNum().length()<=16)){
			return "版本号名长度应为2~16.";
		}
		if(soft.getSystem()==null||"".equals(soft.getSystem())){
			return "支持系统不能为空.";
		}else if(!(soft.getSystem().equals("iPhone")||soft.getSystem().equals("Android")||soft.getSystem().equals("Windows"))){
			return "支持系统错误.";
		}
		
		if(soft.getIsNew()==null){
			soft.setIsNew(0);
		}else if(!(soft.getIsNew()==0||soft.getIsNew()==1)){
			return "该数据设为新版失败.";
		}
		return null;
	}
	
	/**
	 * 资源类数据校验
	 * @param res
	 * @return
	 */
	public String resValidate(CpResource res){
		if(res==null){
			return "请正确填写数据.";
		}
		if(res.getName()!=null){
			if(!((res.getName().length())>=2&&res.getName().length()<=50)){
				return "请输入2~50的名称.";
			}
		}else{
			return "名称不能为空.";
		}
		if(res.getTitle()!=null){
			if(!((res.getTitle().length())>=2&&res.getTitle().length()<=50)){
				return "请输入2~50的查询标题名称.";
			}
		}else{
			return "查询标题不能为空.";
		}
		return null;
	}
	
}

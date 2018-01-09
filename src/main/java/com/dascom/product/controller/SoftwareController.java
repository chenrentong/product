package com.dascom.product.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








































import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody; 

import com.dascom.product.util.JsonTransform;
import com.dascom.product.util.PagedResult;
import com.dascom.product.constant.LoginConstant;
import com.dascom.product.entity.CpResourceExample;
import com.dascom.product.entity.CpSoftware;
import com.dascom.product.service.ResourceService;
/**
 * 软件接口
 * @author DS-033
 *
 */
@RequestMapping("software")
@Controller
public class SoftwareController {
	
	
	
	@Autowired
	private ResourceService resourceServiceImpl;
	
	//查看应用软件
	@RequestMapping(value="findApp",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public String  findApp(@RequestParam(value="", defaultValue="1") Integer pageNumber,@RequestParam(value="",defaultValue="5")Integer pageSize){
		try {
			PagedResult<CpResourceExample> listRes=resourceServiceImpl.findApp(pageNumber,pageSize);
			return JsonTransform.loginJsonTransform("1000", "成功", listRes);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonTransform.loginJsonTransform("1001", "失败", null);
		}	
	}
	
	//查看应用软件的版本
	@RequestMapping(value="findAppEdition" ,produces="application/json;charset=utf-8")
	@ResponseBody
	public String findAppEdition(Integer rId){
		try {
			List<CpSoftware> sList=resourceServiceImpl.findAppEdition(rId);
			return JsonTransform.loginJsonTransform("1000", "成功.", sList);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonTransform.loginJsonTransform("1001", "失败.", null);
		}	
	}
	
	
	
	
	/**
	 * 查看最新软件
	 * @param request
	 * @param versionNum 版本号
	 * @param title 软件标题
	 * @param system 支持系统
	 * @return
	 */
	@RequestMapping(value="viewNewestAndroid",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public String viewNewestAndroid(HttpServletRequest request,@Param("title")String title){
		return viewNewest(title,"Android",request.getServerName(),request.getServerPort());
	}
	
	@RequestMapping(value="viewNewestWindows",produces="application/json;charset=utf-8"/*,method=RequestMethod.POST*/)
	@ResponseBody
	public String viewNewestWindows(HttpServletRequest request,@Param("title")String title){
		return viewNewest(title,"Windows",request.getServerName(),request.getServerPort());
	}

	@RequestMapping(value="viewNewestiPhone",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public String viewNewestiPhone(HttpServletRequest request,@Param("title")String title){
		return viewNewest(title,"iPhone",request.getServerName(),request.getServerPort());
	}
	
	
	
	public String viewNewest(String title,String systemType,String serverName,int i){
		String url="",version="",describe="";
		try{
			if(title==null){
				throw new Exception("标题为空.");
			}
			CpSoftware softTwo= resourceServiceImpl.findSoftwareByVersonInfo(title,systemType);
			if(softTwo==null){
				System.out.println("没有找到title为"+title+"的资源.");
				 throw new Exception("没有找到该资源.");
			}
			url="http://"+serverName+":"+i+"/product/software/updateSoftware/"+softTwo.getId();
			version=softTwo.getVersionNum();
			describe=softTwo.getDescribe();
			Map<String, String> map=new HashMap<String, String>();
			map.put("describe",describe );
			map.put("versionNum",version );
			map.put("url",url );
			System.out.println(new JSONObject(map).toString());
			return new JSONObject(map).toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("更新安卓软件的时候出现异常:"+e);
			return null;
		}
	}
	
	/**
	 * 下载软件  
	 * @param request
	 * @param response
	 * @param versionNum 版本号
	 * @param title 软件标题
	 * @param system 支持系统
	 * @return
	 */
	@RequestMapping(value="updateSoftware/{name}",produces="application/json;charset=utf-8" )
	@ResponseBody
	public String updateSoftware(HttpServletRequest request,HttpServletResponse response ,@PathVariable(value="name") String name ){
		try {  
	    	// 得到要下载的文件名  
	    	/*CpSoftware soft= resourceServiceImpl.findSoftwareByVersion(name);*/
			CpSoftware soft= resourceServiceImpl.findSoftwareByKey(name);
	    	if(soft==null){
	    		return JsonTransform.loginJsonTransform("1001", "没有该资源.", null);
	    	}
	    	String fileName = soft.getVersion()+"."+soft.getSuffix();
	        // 获取上传文件的目录  
	        // 上传位置  
	        String fileSaveRootPath = soft.getUrl();
	          
	        System.out.println(fileSaveRootPath  );  
	        // 得到要下载的文件  
	        File file = new File(fileSaveRootPath);  
	          
	        // 如果文件不存在  
	        if (!file.exists()) {  
	            return JsonTransform.loginJsonTransform("1001", "您要下载的资源已被删除！！", null);
	        }
	        System.out.println("开始下载 应用程序.");
	        // 处理文件名  
	        String realname = fileName.substring(fileName.indexOf("_") + 1);
	        // 设置响应头，控制浏览器下载该文件 
	        response.setHeader("content-disposition", "attachment;filename="  
	                + URLEncoder.encode(realname, "UTF-8"));  
	        response.setHeader("Content-Length",String.valueOf(file.length())); 
	        // 读取要下载的文件，保存到文件输入流  
	        FileInputStream in = new FileInputStream(fileSaveRootPath);
	        // 创建输出流  
	        OutputStream out = response.getOutputStream();  
	        // 创建缓冲区  
	        byte buffer[] = new byte[(int) file.length()];  
	        int len = 0;  
	        // 循环将输入流中的内容读取到缓冲区当中  
	        while ((len = in.read(buffer)) > 0) {
	            // 输出缓冲区的内容到浏览器，实现文件下载  
	            out.write(buffer, 0, len);
	        }
	        // 关闭文件输入流
	        in.close();
	        // 关闭输出流  
	        out.close();
	        
	        //下载数+1
	        
	        downnum( soft);
	    } catch (Exception e) {  
	    	e.printStackTrace();
	    	return JsonTransform.loginJsonTransform("1001", "出现了意外的事故.", null);
	    }
		return  JsonTransform.loginJsonTransform("1000", "成功.", null); 
	}
	
	//下载软件2
	@RequestMapping(value="updateSoftware2/{name}" )
	public String updateSoftware2(HttpServletRequest request,HttpServletResponse response ,@PathVariable(value="name") String name ){
		try {  
	    	// 得到要下载的文件名  
	    	/*CpSoftware soft= resourceServiceImpl.findSoftwareByVersion(name);*/
			CpSoftware soft= resourceServiceImpl.findSoftwareByKey(name);
	    	if(soft==null){
	    		request.setAttribute("msg", "没有该资源.");
	    		return "Software/updateSoftware2";
	    	}
	    	String fileName = soft.getVersion()+"."+soft.getSuffix();
	        // 获取上传文件的目录  
	        // 上传位置  
	        String fileSaveRootPath = soft.getUrl();
	          
	        System.out.println(fileSaveRootPath  );  
	        // 得到要下载的文件  
	        File file = new File(fileSaveRootPath);  
	          
	        // 如果文件不存在  
	        if (!file.exists()) {  
	        	request.setAttribute("msg", "您要下载的资源已被删除！！");
	    		return "Software/updateSoftware2";
	        }
	        System.out.println("开始下载 应用程序.");
	        // 处理文件名  
	        String realname = fileName.substring(fileName.indexOf("_") + 1);
	        // 设置响应头，控制浏览器下载该文件 
	        response.setHeader("content-disposition", "attachment;filename="  
	                + URLEncoder.encode(realname, "UTF-8"));  
	        response.setHeader("Content-Length",String.valueOf(file.length())); 
	        // 读取要下载的文件，保存到文件输入流  
	        FileInputStream in = new FileInputStream(fileSaveRootPath);
	        // 创建输出流  
	        OutputStream out = response.getOutputStream();  
	        // 创建缓冲区  
	        byte buffer[] = new byte[(int) file.length()];  
	        int len = 0;  
	        // 循环将输入流中的内容读取到缓冲区当中  
	        while ((len = in.read(buffer)) > 0) {
	            // 输出缓冲区的内容到浏览器，实现文件下载  
	            out.write(buffer, 0, len);
	        }
	        // 关闭文件输入流
	        in.close();
	        // 关闭输出流  
	        out.close();
	        
	        downnum( soft);
	        
	    } catch (Exception e) {  
	    	e.printStackTrace();
	    	request.setAttribute("msg", "出现了意外的事故.");
    		return "Software/updateSoftware2";
	    }
		return "Software/updateSoftware2";
	}
	
	
	
	//下载数+1 
	public void downnum(CpSoftware soft){
		
		resourceServiceImpl.editSoftDownloadNum(soft);
		
		
	}
	
	
	
}

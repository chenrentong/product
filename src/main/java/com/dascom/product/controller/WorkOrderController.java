package com.dascom.product.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import product.test;
import sun.util.logging.resources.logging;

import com.dascom.product.constant.LoginConstant;
import com.dascom.product.entity.CpSoftware;
import com.dascom.product.entity.WorkOrder;
import com.dascom.product.service.WorkOrderService;
import com.dascom.product.util.JavaEmailSender;
import com.dascom.product.util.JsonTransform;
import com.dascom.product.util.PagedResult;

@Controller
public class WorkOrderController {
	
	private Log log=LogFactory.getLog(WorkOrderController.class);
	
	@Autowired
	private WorkOrderService workOrderServiceImpl;
	
	//下载附件
	
	@RequestMapping(value="downloadOrderAttachment",produces="application/json;charset=utf-8")
	@ResponseBody
	public String downloadOrderAttachment(HttpServletRequest request,HttpServletResponse response,Integer id ){
		try {  
	    	// 得到要下载的文件名  
	    	/*CpSoftware soft= resourceServiceImpl.findSoftwareByVersion(name);*/
			WorkOrder o= workOrderServiceImpl.findByKey(id);
	    	if(o==null){
	    		return "没有该资源.";
	    	}
	    	String fileName = o.getTheme()+"."+o.getAttachmentSuffix();
	        // 获取上传文件的目录  
	        // 上传位置  
	        String fileSaveRootPath = o.getAttachmentPath();
	          
	        System.out.println(fileSaveRootPath  );  
	        // 得到要下载的文件  
	        File file = new File(fileSaveRootPath);  
	          
	        // 如果文件不存在  
	        if (!file.exists()) {  
	            return "您要下载的资源已被删除！";
	        }
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
	    } catch (Exception e) {  
	    	e.printStackTrace();
	    	return "出现了意外的事故.";
	    }
		return null;
	}
	
	
	
	
	@RequestMapping(value="workOrderEdit")
	public String workOrderEdit(HttpServletRequest request,Integer id){
		try {
			WorkOrder o= workOrderServiceImpl.findByKey(id);
			request.setAttribute("order",o );
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("进入查询工单页面出错.");
		}
		return "WorkOrder/workOrderEdit";
	}
	
	@RequestMapping(value="workOrderEditSubmit",produces="application/json;charset=utf-8")
	@ResponseBody
	public String workOrderEditSubmit(Integer id ,Integer isSolve,String solveProject){
		try {
			if(id==null||isSolve==null){
				log.warn("workOrder修改表单验证不通过.");
				throw new Exception();
			}
			if(workOrderServiceImpl.update( id , isSolve, solveProject)>0){
				return JsonTransform.loginJsonTransform(LoginConstant.SUCCEED_CODE, LoginConstant.SUCCEED_MESSAGE, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("workOrderEditSubmit方法出错.");
		}
		return JsonTransform.loginJsonTransform(LoginConstant.ERROR_CODE, LoginConstant.ERROR_MESSAGE, null);
	}
	
	
	@RequestMapping(value="findWorkOrderList")
	public String findWorkOrderList(HttpServletRequest request,Integer isSolve,String like,@RequestParam(value="", defaultValue="1") Integer pageNumber,@RequestParam(value="",defaultValue="5")Integer pageSize){
		try {
			PagedResult<WorkOrder> olist=workOrderServiceImpl.findWorkOrderPage(pageNumber,pageSize,isSolve,like);
			request.setAttribute("olist", olist);
			request.setAttribute("isSolve", isSolve);
			request.setAttribute("like", like);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("查询工单页面集合的时候出现了错误.");
		}
		return "WorkOrder/workOrderList";
	}

}

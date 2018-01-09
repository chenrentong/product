package com.dascom.product.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 上传文件改
 * @author DS-033
 *
 */
public class FileServer {
	// 图片类型
    private static List<String> fileTypes = new ArrayList<String>();
    
    static {
        fileTypes.add(".jpg");
        fileTypes.add(".jpeg");
        fileTypes.add(".bmp");
        fileTypes.add(".gif");
        fileTypes.add(".png");
    }
	
	
	
	
	
	//缩略图地址   
	public static String thumbnailPath="thumbnail";	
	//资源地址
	public static String resource="resource";
	//软件/二次开发包地址
	public static String software ="software";
	//缓存地址
	public static String cache ="cache";
	//工单资料
	public static String workOrderContent  ="workOrderContent";
	//网络地址前缀 
	public static String relativePath="/dcpath";   
	
	
	//上传图片
	
	//上传资源
	/**
	 * 上传文件  
	 * @param file 文件
	 * @param uploadPath  保存路径
	 * @return 本地tomcat模拟路径  date/下的的路径 
	 * @throws Exception 
	 */
	public static synchronized String uploadFile(MultipartFile file,String uploadPath) throws Exception  {
        try {
        	if(file.getOriginalFilename()==""||file.getOriginalFilename().equals("")||file.getOriginalFilename()==null){
        		return "";
        	}
        	String virtualPath=relativePath;
        	
        	//文件的名字
            String fileName = file.getOriginalFilename();
            //获取文件后缀名
            @SuppressWarnings("unused")
			String uploadContentType =fileName.substring(fileName.lastIndexOf(".")+1);
            
    		if (file.getSize() > 1024 * 1024*1024) {
    			throw new Exception("文件过大");
    		}
    		 fileName = new Date().getTime()+fileName; // 采用时间方式+原本名字 随即命名
            //准备保存参数
            File dir = new File(uploadPath,fileName);     
            //没有该路径就创建一个路径
            boolean b=dir.mkdirs();
            if(b){
            	file.transferTo(dir);
            }else{
            	throw new Exception("上传文件失败.");
            }
            
           /* if(!dir.exists()){  
                dir.mkdirs();  
            } 
            
        	//MultipartFile自带的解析方法   保存
			file.transferTo(dir); */
			String uploadPathEnd=null;
			//windows 和linux 不一样
			if(uploadPath.indexOf("\\")>0){
				uploadPathEnd=uploadPath.substring(uploadPath.lastIndexOf("\\")+1);
			}else{
				uploadPathEnd=uploadPath.substring(uploadPath.lastIndexOf("/")+1);
			}
			String ds =virtualPath+"/"+uploadPathEnd+"/"+fileName;
			System.out.println(ds);
			return ds;
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage().equals("上传文件失败.")){
				throw new Exception("上传文件失败.");
			}
			throw new Exception("出现了意外的错误.");
		}
    }
	
	//上传软件/二次开发包
	/**
	 * 上传应用软件 只能上传后缀名为  zip rar exe的文件 
	 * @param file
	 * @param uploadPath
	 * @param hostname
	 * @param prot
	 * @return  文件电脑完整路径  
	 * @throws Exception
	 */
	public static synchronized  String uploadSoftwareFile(MultipartFile file,String uploadPath) throws Exception {
        try {
        	if(file.getOriginalFilename()==null||"".equals(file.getOriginalFilename())){
        		return "";
        	}
        	
        	//文件的名字
            String fileName = file.getOriginalFilename();
            //获取文件后缀名
            @SuppressWarnings("unused")
			String uploadContentType =fileName.substring(fileName.lastIndexOf(".")+1);
            
            //暂时不做限制
    		/*if (file.getSize() > 1024 * 1024*1024) {
    			throw new Exception("文件过大");
    		}*/
    		fileName = new Date().getTime()+fileName; // 采用时间方式随即命名
    		
            //准备保存参数
            File dir = new File(uploadPath,fileName);     
            //没有该路径就创建一个路径
            if(!dir.exists()){  
                dir.mkdirs();  
            } 
        	//MultipartFile自带的解析方法   保存
			file.transferTo(dir); 
			String ds =uploadPath+"/"+fileName;
			System.out.println("保存的路径是 :"+ds);
			return ds;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("出现了意外的错误.");
		}
    }
	
	//request方式上传图片
	/**
     * 图片上传
     * 
     * @Title upload
     * @param request
     * @param DirectoryName
     *            文件上传目录：比如upload(无需带前面的/) upload/news ..
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
	public static String upload(HttpServletRequest request,String resource,String RealPath) throws IllegalStateException,
            IOException {
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession()
                .getServletContext());
        // 图片名称
        String relativePath = null;
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 记录上传过程起始时的时间，用来计算上传时间
                // int pre = (int) System.currentTimeMillis();
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        // 获得图片的原始名称
                        String originalFilename = file.getOriginalFilename();
                        // 获得图片后缀名称,如果后缀不为图片格式，则不上传
                        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                        if (!fileTypes.contains(suffix)) {
                            continue;
                        }
                        // 获得上传路径的绝对路径地址(/upload)-->
                        
                        String realPath = RealPath+"\\"+new Date().getYear()+"-"+new Date().getMonth()+"-"+new Date().getDay();
                        System.out.println(realPath);
                        // 如果路径不存在，则创建该路径
                        File realPathDirectory = new File(realPath);
                        if (realPathDirectory == null || !realPathDirectory.exists()) {
                            realPathDirectory.mkdirs();
                        }
                        // 重命名上传后的文件名 111112323.jpg
                        String fileName = new Date().getTime()+ suffix;
                        //得到相对路径 
                        relativePath =FileServer.relativePath+resource.substring(resource.lastIndexOf("\\")+1)+"/"+new Date().getYear()+"-"+new Date().getMonth()+"-"+new Date().getDay()+"/"+fileName;
                        File uploadFile = new File(realPathDirectory + "\\" + fileName);
                        System.out.println(uploadFile);
                        file.transferTo(uploadFile);
                    }
                }
                // 记录上传该文件后的时间
                // int finaltime = (int) System.currentTimeMillis();
                // System.out.println(finaltime - pre);
            }
        }
        
        return relativePath;
    }
	
	//剪切文件(剪切的地方 , 原来文件路径 )(如果没有剪切的地方 就删除原来的文件)
	
   
   
    /**
     *  得到tomcat根目录 
     * @param RealPath  request.getSession().getServletContext().getRealPath("/")
     * @return
     */
	public static String tomcatAddress(String RealPath){
		/*RealPath="/home/liujuan/";*/
		System.out.println("tomcat修改前的根目录是:"+RealPath);
		if(RealPath.lastIndexOf("\\")>0){
			//windows
			RealPath=RealPath.substring(0,RealPath.lastIndexOf("\\"));
			RealPath=RealPath.substring(0,RealPath.lastIndexOf("\\"));
			RealPath=RealPath.substring(0,RealPath.lastIndexOf("\\"));
			RealPath=RealPath+"\\"+"dcpath"+"\\";
		}else{
			//linux
			RealPath=RealPath.substring(0,RealPath.lastIndexOf("/"));
			RealPath=RealPath.substring(0,RealPath.lastIndexOf("/"));
			RealPath=RealPath.substring(0,RealPath.lastIndexOf("/"));
			RealPath=RealPath+"/"+"dcpath"+"/";
		}
		/*RealPath="/opt/dcpath/";*/
		System.out.println("tomcat的根目录是:"+RealPath);
		return RealPath;
	}
	
	
}

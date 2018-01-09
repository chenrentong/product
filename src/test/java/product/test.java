package product;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.dascom.product.controller.SoftwareController;
import com.dascom.product.util.JavaEmailSender;
import com.dascom.product.util.TimeFilter;

public class test {
	@Test
	public void sdfdfds() throws Exception{
		
	}
	
	
	@Test
	public void sdfsdf(){
		Log log=LogFactory.getLog(test.class);
		log.debug("你好.");
	}
	
	@Test
	public void sdfsdfd(){
		String sdf="sdfdsfds\\dfsdf";
		String f=sdf.substring(sdf.lastIndexOf("\\")+1);
		System.out.println(f);
	}
	
	
	@SuppressWarnings({ "resource", "deprecation" })
	@Test
	public void qwedf(){
		try {
			HttpClient httpClient=new DefaultHttpClient();
			String url="http://192.168.11.124:5656/product/software/viewNewestAndroid";
			HttpPost httpPost=new HttpPost(url);
			//建立HttpPost对象
			
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("title", "androidUpload.apk"));  
			UrlEncodedFormEntity uefEntity;
			uefEntity = new UrlEncodedFormEntity(formparams, "utf-8");
            httpPost.setEntity(uefEntity);
			//添加参数
			HttpResponse httpResponse=httpClient.execute(httpPost);
			String result=EntityUtils.toString(httpResponse.getEntity());
			System.out.println("json:"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sdfsdfds(){
		try {
			URL url=new URL("http://192.168.11.124:5656/product/software/updateSoftware/58");
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(4000);
			conn.setRequestMethod("GET");
			conn.addRequestProperty("Accept-Charset", "UTF-8");
			/*conn.addRequestProperty("Accept-Encoding", "identity");*/
			conn.setRequestProperty("Accept-Encoding", "identity");
			conn.connect();
			//要想个办法让他下载好 .再运行下一步. 
			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);

			int i = conn.getContentLength();
			System.out.println(i);
		
			
			
			/*RandomAccessFile file = new  RandomAccessFile("D:\\DASCOM\\software\\应用软件.apk","rw");*/
	        InputStream stream = conn.getInputStream();
	        byte buffer[] = new byte[1024*1024];
	        while (true) {
	            int len = stream.read(buffer);
	            if (len == -1) {
	                break;
	            }
	            
	            /*file.write(buffer, 0, len);*/
	        }
	        /*if (file != null) {
	            file.close();
	        }*/
	        if (stream != null) {
	            stream.close();
	        }
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	@Test
	public void sdsdf(){
		String  i="";
		
		if("".equals(i)){
			System.out.println("进入了?");
		}
		System.out.println("没有出错哈哈");
	}
	
	@Test
	public void sdfsf(){
		SoftwareController s=new SoftwareController();
	}
	@Test
	public void sfdsaf(){
		String code ="123456789012";
		String sub =code.substring(0,5);
		System.out.println(sub);
	}
	
	@Test
	public void dsfds(){
		String sdf="dsf.exe";
		String sdffd=sdf.substring(sdf.lastIndexOf(".")+1);
		System.out.println(sdffd);
	}
	@Test
	public void dfdf(){
		String fd="D:\\DASCOM\\thumbnail";
		String sd=fd.substring(fd.lastIndexOf("\\")+1);
		System.out.println(sd);
	}
	
	
	@Test
	public void sdf(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        
        //今天  
        System.out.println("今天"+sdf.format(new Date()));
        
        //昨天 
        c=Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,-1 );
        System.out.println("昨天 "+sdf.format(c.getTime()));
        Date yesterday =c.getTime();
        
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 1);//本周第一天，以星期日开始        
        System.out.println("本周第一天"+sdf.format(c.getTime()));
        Date startWeek=c.getTime();
        
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 7);//本周最后一天
        System.out.println("本周最后一天"+sdf.format(c.getTime()));
        Date lastWeek =c.getTime();
        
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);//本月第一天
        System.out.println("本月第一天"+sdf.format(c.getTime()));
        Date startMonth =c.getTime();
        
        c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);//本月最后一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println("本月最后一天"+sdf.format(c.getTime()));
        Date lastMonth=c.getTime();
        
        List<Date> listDate=new ArrayList<>();
        listDate.add(new Date());
        listDate.add(new Date());
        listDate.add(new Date());
        
        
        int i=TimeFilter.timeFilter(listDate, startMonth, lastMonth);
        System.out.println(i);

	}
	@Test
	public void setfs(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//今天的范围
		System.out.print("今天的范围是 :"+sdf.format(new Date())+" ~ ");
		System.out.println(sdf.format(TimeFilter.getTomorrow()));
		//昨天的范围
		System.out.print("昨天的范围是 :"+sdf.format(TimeFilter.getYesterday())+" ~ ");
		System.out.println(sdf.format(TimeFilter.getDay()));
		//本周的范围
		System.out.print("本周的范围是 :"+sdf.format(TimeFilter.getStartWeek())+" ~ ");
		System.out.println(sdf.format(TimeFilter.getLasttWeek()));
		//本月的范围
		System.out.print("本月的范围是 :"+sdf.format(TimeFilter.getStartMonth())+" ~ ");
		System.out.println(sdf.format(TimeFilter.getLastMonth()));
		
	}
	
	@Test
	public  void sendPostwithfile(){
		String url ="http://192.168.11.124:5656/product/Display/uploadFile";
		DataOutputStream out =null;
		BufferedReader in = null;
		String result ="";
		try {
			URL realUrl=new URL(url);
			//打开和URL之间的连接
			HttpURLConnection conn =(HttpURLConnection)realUrl.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			String BOUNDARY = "---------7d4a6d158c9";//定义数据分割线
			
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection","Keep-Alive");
			conn.setRequestProperty("Charset","UTF-8");
			conn.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);
			
			conn.setRequestProperty("user-agebt", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.connect();
			
			out =new DataOutputStream(conn.getOutputStream());
			byte[] end_data=("\r\n"+BOUNDARY+"--\r\n").getBytes();//定义最后数据分割线
			//添加参数Luid
			StringBuilder sb1=new StringBuilder();
			sb1.append("--");
			sb1.append(BOUNDARY);
			sb1.append("\r\n");
			sb1.append("Content-Disposition: form-data; name=\"luid\"");
			sb1.append("\r\n");
			sb1.append("\r\n");
			sb1.append("123");
			sb1.append("\r\n");
			out.write(sb1.toString().getBytes());
			//添加参数file
			File file =new File("D:\\DASCOM\\chatmodal\\ds-1505702352580.png");
			if(!file.exists()){
				System.out.println("该文件不存在.");
			}
			StringBuilder sb= new StringBuilder();
			sb.append("--");
			sb.append(BOUNDARY);
			sb.append("Content-Disposition: form-data; name=\"file\"; filename=\""+file.getName()+"\"");
			sb.append("\r\n");
			sb.append("Content-Type: application/octet-stream");
			sb.append("\r\n");
			sb.append("\r\n");
			out.write(sb.toString().getBytes());
			
			DataInputStream in1 =new DataInputStream(new FileInputStream(file));
			int bytes=0;
			byte[] bufferOut=new byte[(int) file.length()];
			
			while((bytes=in1.read(bufferOut))!= -1){
				out.write(bufferOut,0,bytes);
			}
			out.write("\r\n".getBytes());
			in1.close();
			out.write(end_data);
			
			//flush输出流的缓冲
			out.flush();	
			//定义BufferedReader输出流来读取URL的响应
			in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line=in.readLine())!=null){
				result +=line;
			}
			
		} catch (Exception e) {
			System.out.println("发送Post请求出现异常!"+e);
		}
		//使用finally块来关闭输出了流,输入流
		finally{
			try {
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			} catch ( Exception ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(result);
	}
	
	@Test
	public void newsendpostwithfile() throws Exception{
		String BOUNDARY ="----------HV2ymHFg03ehbqgZCaKO6jyH";
		 // 向服务器发送post请求
        URL url = new URL("http://192.168.11.124:5656/product/Display/uploadFile");
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        
        // 发送POST请求必须设置如下两行
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Connection","Keep-Alive");
        connection.setRequestProperty("Charset","UTF-8");
        connection.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);
        
        // 头
        String boundary = BOUNDARY;
        // 传输内容
        StringBuffer contentBody =new StringBuffer("--" + BOUNDARY);
        // 尾
        String endBoundary ="\r\n--" + boundary + "--\r\n";
       
        OutputStream out =connection.getOutputStream();
        
        //要上传的文件
        File file =new File("D:\\test\\ds-1505702352580.png");
		if(file.exists()){
			System.out.println(file.getName());
		}
        
        
        contentBody = new StringBuffer();
        contentBody.append("\r\n")
        .append("Content-Disposition:form-data; name=\"")
        .append("file" +"\"; ")   // form中field的名称
        .append("filename=\"")
        .append(file.getName() +"\"")   //上传文件的文件名，包括目录
        .append("\r\n")
        .append("Content-Type:application/octet-stream")
        .append("\r\n\r\n");
       
        String boundaryMessage2 = contentBody.toString();
        out.write(boundaryMessage2.getBytes("utf-8"));
       
        // 开始真正向服务器写文件
        /*File file = new File(ufi.getFileName());*/
        
        DataInputStream dis= new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut =new byte[(int) file.length()];
        bytes =dis.read(bufferOut);
        out.write(bufferOut,0, bytes);
        dis.close();
        contentBody.append("------------HV2ymHFg03ehbqgZCaKO6jyH");
       
        String boundaryMessage = contentBody.toString();
        out.write(boundaryMessage.getBytes("utf-8"));
        //System.out.println(boundaryMessage);
        out.write("------------HV2ymHFg03ehbqgZCaKO6jyH--\r\n".getBytes("UTF-8"));
        
        // 3. 写结尾
        out.write(endBoundary.getBytes("utf-8"));
        out.flush();
        out.close();
        
        // 4. 从服务器获得回答的内容
        String strLine="";
        String strResponse ="";
       
        InputStream in =connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        while((strLine =reader.readLine()) != null)
        {
                 strResponse +=strLine +"\n";
        }
        //System.out.print(strResponse);
        System.out.println(strResponse);
        
	}
    
    @Test
    public void s1df() throws IOException {  
        File dir1 = new File("D:\\test\\ds-1505702352580.png");  
        FileInputStream fis = null;  
        FileOutputStream fos = null;  
        //用字节输入输出流  
        if(dir1.exists()){
        	System.out.println("存在");
        }
        String path = dir1.getAbsolutePath();  
        String filename = path.substring(path.lastIndexOf("\\")+1, path.length());  
        File AfterFile = new File("d:\\DASCOM\\"+filename+"");  
        if(!AfterFile.exists()){  
            AfterFile.createNewFile();  
        }//如果文件不存在，则进行创建  
        fis = new FileInputStream(dir1);  
        fos = new FileOutputStream(AfterFile);  
        byte[] buffer = new byte[1024];  
        int length;  
        while((length=fis.read(buffer))!=-1){  
            fos.write(buffer,0,length);  
        }  
        fis.close();  
        fos.close();  
    }  
    
    
    

    @Test
    public void uploadToFarService() {  
    	Map<String,InputStream> files=new HashMap<String, InputStream>();
    	File file=new File("D:\\DASCOM\\chatmodal\\ds-1505702352580.png");
    	
    	
    	
        try {  
            String BOUNDARY = "---------7d4a6d158c9"; // 定义数据分隔线  
            URL url = new URL("http://192.168.11.124:5656/product/Display/uploadFile");  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setUseCaches(false);  
            conn.setRequestMethod("POST");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent",  
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
            conn.setRequestProperty("Charsert", "UTF-8");  
            conn.setRequestProperty("Content-Type",  
                    "multipart/form-data; boundary=" + BOUNDARY);  
  
            OutputStream out = new DataOutputStream(conn.getOutputStream());  
            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线  
            Iterator iter = files.entrySet().iterator();  
            int i=0;  
            while (iter.hasNext()) {  
                i++;  
                Map.Entry entry = (Map.Entry) iter.next();  
                String key = (String) entry.getKey();  
                InputStream val = (InputStream) entry.getValue();  
                String fname = key;  
                File file1 = new File(fname);  
                StringBuilder sb = new StringBuilder();  
                sb.append("--");  
                sb.append(BOUNDARY);  
                sb.append("\r\n");  
                sb.append("Content-Disposition: form-data;name=\"file" + i  
                        + "\";filename=\"" + key + "\"\r\n");  
                sb.append("Content-Type:application/octet-stream\r\n\r\n");  
  
                byte[] data = sb.toString().getBytes();  
                out.write(data);  
                DataInputStream in = new DataInputStream(val);  
                int bytes = 0;  
                byte[] bufferOut = new byte[1024];  
                while ((bytes = in.read(bufferOut)) != -1) {  
                    out.write(bufferOut, 0, bytes);  
                }  
                out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个  
                in.close();  
            }  
            out.write(end_data);  
            out.flush();  
            out.close();  
  
            // 定义BufferedReader输入流来读取URL的响应  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    conn.getInputStream(), "UTF-8"));  
            String line = null;  
            while ((line = reader.readLine()) != null) {  
                System.out.println(line);  
            }  
  
        } catch (Exception e) {  
            System.out.println("发送POST请求出现异常！" + e);  
            e.printStackTrace();  
        }  
    }  
    
}

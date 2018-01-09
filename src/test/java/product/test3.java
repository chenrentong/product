package product;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

public class test3 {
	
	@Test
	public void sdsdff(){
		HttpClient client = new HttpClient();
        GetMethod get = null;
        FileOutputStream output = null;
        try {
        	get = new GetMethod("http://192.168.11.124:5656/product/software/updateSoftware/58");
            int i=client.executeMethod(get);
            if(200==i){
            	File storeFile = new File("D:\\DASCOM\\software\\应用软件.apk");
                output = new FileOutputStream(storeFile);
                // 得到网络资源的字节数组,并写入文件
                output.write(get.getResponseBody());
            }else{
            	//读取错误信息 .
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出现异常");
        }
	}

}

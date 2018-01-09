package com.dascom.product.util;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class JsonTransform {
	/**
	 * 帮助转换json数据的方法 ,code 1000:成功  1001:失败 1006:未登录  1025:身份过期   ,100:寻找到最新的版本,101:寻找不到最新的版本
	 * @param code
	 * @param msg
	 * @param date
	 * @return
	 */
	public static String loginJsonTransform(String code, String msg ,Object date){
		Map<String, Object> map=new HashMap<String, Object>();
		//用户状态码 
		map.put("code", code);
		//登录消息
		map.put("msg", msg);
		//返回的数据.
		map.put("date", date);
		//转换成json数据
		JSONObject json=new JSONObject(map);
		System.out.println(json.toString());
		return  json.toString();
	}
	
	
}

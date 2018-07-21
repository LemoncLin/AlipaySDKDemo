package com.lqh.alipay.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;

import com.lqh.alipay.OrderBodyInfo;
import com.lqh.alipay.SignUtils;

import android.util.Log;

public class OrderInfoUtil2_0 {
	
	/**
	 * 构造授权参数列表
	 * 
	 * @param pid
	 * @param app_id
	 * @param target_id
	 * @return
	 */
	public static Map<String, String> buildAuthInfoMap(String pid, String app_id, String target_id, boolean rsa2) {
		Map<String, String> keyValues = new HashMap<String, String>();

		// 商户签约拿到的app_id，如：2013081700024223
		keyValues.put("app_id", app_id);

		// 商户签约拿到的pid，如：2088102123816631
		keyValues.put("pid", pid);

		// 服务接口名称， 固定值
		keyValues.put("apiname", "com.alipay.account.auth");

		// 商户类型标识， 固定值
		keyValues.put("app_name", "mc");

		// 业务类型， 固定值
		keyValues.put("biz_type", "openservice");

		// 产品码， 固定值
		keyValues.put("product_id", "APP_FAST_LOGIN");

		// 授权范围， 固定值
		keyValues.put("scope", "kuaijie");

		// 商户唯一标识，如：kkkkk091125
		keyValues.put("target_id", target_id);

		// 授权类型， 固定值
		keyValues.put("auth_type", "AUTHACCOUNT");

		// 签名类型
		keyValues.put("sign_type", rsa2 ? "RSA2" : "RSA");

		return keyValues;
	}

		/**
		 * 构造支付订单参数列表
		 * 就是说我这个支付订单是由这个map组成的，map中要有这些几个参数，并且给他们赋值
		 * @param pid
		 * @param productname
		 * @param productprice
		 * @param productid
		 * @return
		 */

	    public static Map<String, String> buildOrderParamMap(String app_id, String productname,String    productprice,String  productid,boolean rsa2) {

	        Map<String, String> keyValues = new HashMap<String, String>();

	        keyValues.put("app_id", app_id);

	        OrderBodyInfo sss=new  OrderBodyInfo("这是一个虚拟商品!!!!",productname,getOutTradeNo(),"30m",productprice,productid);
	        Gson gson=new   Gson();
	        String str = gson.toJson(sss);

	        keyValues.put("biz_content", str);
	        keyValues.put("charset", "utf-8");

	        keyValues.put("method", "alipay.trade.app.pay");

	        keyValues.put("sign_type", rsa2 ? "RSA2" : "RSA");

	        
	        SimpleDateFormat  simpleDateFormat=new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Log.d("DLog", simpleDateFormat.format(new Date()));
	        keyValues.put("timestamp", simpleDateFormat.format(new Date()));
	        
//	        keyValues.put("timestamp", "2016-07-29 16:55:53");
	       
	        keyValues.put("version", "1.0");

	        return keyValues;
	    }
	
	/**
	 * 构造支付订单参数信息
	 * 传入进来一个Map参数，就是说利用这个方法，我可以将map解析成一个String类型的支付订单
	 * @param map
	 * 支付订单参数
	 * @return
	 */
	public static String buildOrderParam(Map<String, String> map) {
		List<String> keys = new ArrayList<String>(map.keySet());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.size() - 1; i++) {
			String key = keys.get(i);
			String value = map.get(key);
			sb.append(buildKeyValue(key, value, true));
			sb.append("&");
		}

		String tailKey = keys.get(keys.size() - 1);
		String tailValue = map.get(tailKey);
		sb.append(buildKeyValue(tailKey, tailValue, true));

		return sb.toString();
	}
	
	/**
	 * 拼接键值对
	 * 将传入进来的两个字符串利用此方法拼接成key=value的形式
     * 该方法咨询是否转码（isEncode），如果需要对value进行转码的话，将会将其转为UTF—8格式；    
	 * @param key
	 * @param value
	 * @param isEncode
	 * @return
	 */
	private static String buildKeyValue(String key, String value, boolean isEncode) {
		StringBuilder sb = new StringBuilder();
		sb.append(key);
		sb.append("=");
		if (isEncode) {
			try {
				sb.append(URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				sb.append(value);
			}
		} else {
			sb.append(value);
		}
		return sb.toString();
	}
	
	/**
	 * 对支付参数信息进行签名
	 * 传入进来一个Map,一个rsaKey，并且询问是否是rsa2格式的私钥
     * 这个rsaKey是商户的私钥
     * 就是说通过此方法对商户的私钥进行“签名”处理，处理后就会生成（返回）一个sign=GKHJL%……&*的一大串字串  
	 * @param map
	 *            待签名授权信息
	 * 
	 * @return
	 */
	public static String getSign(Map<String, String> map, String rsaKey, boolean rsa2) {
				
		
		
		List<String> keys = new ArrayList<String>(map.keySet());
		// key排序
		Collections.sort(keys);
				
		StringBuilder authInfo = new StringBuilder();
		for (int i = 0; i < keys.size() - 1; i++) {
			String key = keys.get(i);
			String value = map.get(key);
			Log.i("DLog", "循环得到的key:   "+key+"循环拿到的value:  "+value);
			authInfo.append(buildKeyValue(key, value, false));
			authInfo.append("&");
			
		}

		String tailKey = keys.get(keys.size() - 1);
		String tailValue = map.get(tailKey);
		
		authInfo.append(buildKeyValue(tailKey, tailValue, false));
		
		String oriSign = SignUtils.sign(authInfo.toString(), rsaKey, rsa2);
		String encodedSign = "";
		
		
		try {

			encodedSign = URLEncoder.encode(oriSign, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			
			
			e.printStackTrace();
			
		}
		
		Log.i("DLog", "sign="+encodedSign);
		return "sign=" + encodedSign;
	}
	
	
	
	
	/**
	 * 要求外部订单号必须唯一。
	 * 利用此方法获取到一个外部订单号"OutTradeNo"
	 * @return
	 */
	private static String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

}

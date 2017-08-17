package com.weibo.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.tools.ant.taskdefs.Tstamp.CustomFormat;
import org.junit.Ignore;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mysql.fabric.xmlrpc.base.Data;
import com.weibo.global.HttpClientBase;
import com.weibo.global.JsonCommon;
import com.weibo.model.Status;


public class StatusCommon {
	Object status;
	Object statusCount;
	Object statuses;
	Object interest;
	Object lastTime;
	Object statusesIds;
	Object error;
	Object exposure;
	Object allowComment;
	Object userReadCount;
	Object darwinTags;
	HttpClientBase statusTest = new HttpClientBase();
	HttpCookie cookie=null;
	String relativeurl;
	String statusInfo;
	Object objectList;
	Object midObject;
	Object StatusBean;
	Object recomm;
	Object getExposureTrigger;
	Object getSimilarity;
	Object atTimeline;
	Object readMetas;
	Object phototags;
	boolean flag;



	public Object commonResult(String statusInfo) throws Exception {
//		if (statusInfo.contains("error_code")) {
//			error = (ErrorInfo) JsonCommon.getJavabean(statusInfo,
//					ErrorInfo.class);
//			MbLogger.error(statusInfo, new ExceptionCommon(statusInfo));
//			return error;
//		} else {
			status = (Status) JsonCommon.getJavabean(statusInfo, Status.class);
			
			return status;
//		}	
	}
	


	/*
	 * status feed (statuses/friends_timeline)
	 * 
	 * @param username
	 * 
	 * @param password
	 * 
	 * @param parameters required
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public Object friendsTimeline_status(String username, String password,
			String parameters) throws Exception {
		relativeurl = "/2/statuses/friends_timeline.json";
		statusInfo = statusTest.doGet(username, password, relativeurl, parameters);
		statuses = commonResult(statusInfo);
		return statuses;
	}

	
	public Object updateStatusPublic(String username, String password,
			String parameters) throws Exception {
		relativeurl = "/2/statuses/update.json";
		if (parameters.isEmpty()) {
			parameters = "visible=0&status=test public ";
		}
		statusInfo = statusTest.doPost(username, password, relativeurl,
				parameters);
		status = commonResult(statusInfo);
	
		return status;
	}

	public Object login(String userName,String password,String relativeUrl,String parameters) throws Exception
	{
		System.out.println("afd");
		statusInfo=statusTest.doPost(userName, password, relativeUrl, parameters);
		status=commonResult(statusInfo);
		return status;
	}

	public void loginCookie()
	{
		try {
			statusTest.getLoginCookie2();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void checkDenglu(String relativeUrl,String parameters)
	{
		try {
			statusTest.get1(relativeUrl, parameters);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void getList(String relativeUrl,String parameters)
	{
		try {
			statusTest.get1(relativeUrl, parameters);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void loginSaveCookie()
//	{
//		String loginUrl = "http://localhost/login.jsp";  
//        // 需登陆后访问的 Url  
//        String dataUrl = "http://localhost/applyopen/printApprize.jsp?iid=1516";  
//  
//        //HttpClientContext
//        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore);
//        HttpResponse httpResponse=null;
//        HttpEntity httpEntity=null;
//        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式  
//        
//        // 设置登陆时要求的信息，用户名和密码  
//        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//        nameValuePairs.add(new BasicNameValuePair("password", "123456"));
//        nameValuePairs.add (new BasicNameValuePair("password", "123456"));  
//       HttpPost httpPost=new HttpPost(loginUrl);
//       try {
//		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//	} catch (UnsupportedEncodingException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//      try {
//		httpResponse=httpClient.execute(httpPost);
//	} catch (ClientProtocolException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//      httpEntity=httpResponse.getEntity();
//      CookieStore cookieStore=httpClient.get
//	}
//	
	







	
	
	
	
	




	


	



}

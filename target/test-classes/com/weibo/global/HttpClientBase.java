
package com.weibo.global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.params.HttpParams;


public class HttpClientBase {
	private static final String HTTP = "http";
	private static final String  HTTPHEADER = "http://";
	static HttpClientContext context = null;
	static CookieStore cookieStore = null;
	private static CloseableHttpClient  httpClient=HttpClients.custom()
			.setDefaultCookieStore(cookieStore).build();
	private static String  JSESSIONID;
	private static String setCookie;
	private static String two_string;

	/**
	 * 
	 * @param username
	 * @param password
	 * @param relativeurl
	 * @return
	 * @throws Exception
	 */
	public String doGet(String username, String password, String relativeurl,
			String parameters)
			throws Exception {
		if(relativeurl == null || relativeurl.equals(""))
			return null;
		if(parameters.indexOf("source=") < 0) {
			//parameters += "&source=" + ParseProperties.getSystemProperty("source");
		parameters += "&source=*******";
		}
		String url = HTTPHEADER + ParseProperties.getSystemProperty("host") + ":" + ParseProperties.getSystemProperty("port") + relativeurl + "?" + parameters;
		
		HttpGet httpget = new HttpGet(url);
		HttpHost targetHost = new HttpHost(httpget.getURI().getHost(), httpget.getURI().getPort(), HTTP);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(targetHost.getHostName(),targetHost.getPort()), new UsernamePasswordCredentials(username, password));
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCredentialsProvider(credsProvider).build();
		JSONObject json = null;
		String jsonstring = "", line = null;
		try {
			// Create AuthCache instance
			AuthCache authCache = new BasicAuthCache();
			// Generate BASIC scheme object and add it to the local
			// auth cache
			BasicScheme basicAuth = new BasicScheme();
			authCache.put(targetHost, basicAuth);
			// Add AuthCache to the execution context
			HttpClientContext localContext = HttpClientContext.create();
			localContext.setAuthCache(authCache);
			for (int i = 0; i < 1; i++) {
				CloseableHttpResponse response = httpclient.execute(targetHost,
						httpget, localContext);
				try {
					HttpEntity entity = response.getEntity();

					if (entity != null) {
						InputStream in = (InputStream) entity.getContent();
						InputStreamReader ris = new InputStreamReader(in,
								"utf-8");
						BufferedReader br = new BufferedReader(ris);

						while ((line = br.readLine()) != null) {
							jsonstring += line;
						}
					}
					EntityUtils.consume(entity);
					System.out.println("curl -u \"" + username+ ":" + password + "\"  \"" + url + "\"");
					System.out.println(jsonstring);
					System.out.println("-----------------------------------------------");
				} finally {
					response.close();
				}
			}
		} finally {
			httpclient.close();
		}
		return jsonstring;
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param relativeurl
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String username, String password, String relativeurl,
			String parameters,String host)
			throws Exception {
		if(relativeurl == null || relativeurl.equals(""))
			return null;
		if(parameters.indexOf("source=") < 0) {
			//parameters += "&source=" + ParseProperties.getSystemProperty("source");
			parameters += "&source=";
		}
		String url = HTTPHEADER +host + relativeurl + "?" + parameters;
		
		HttpGet httpget = new HttpGet(url);
		HttpHost targetHost = new HttpHost(httpget.getURI().getHost(), httpget
				.getURI().getPort(), HTTP);

		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(targetHost.getHostName(),
				targetHost.getPort()), new UsernamePasswordCredentials(
				username, password));
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCredentialsProvider(credsProvider).build();
		JSONObject json = null;
		String jsonstring = "", line = null;
		try {
			// Create AuthCache instance
			AuthCache authCache = new BasicAuthCache();
			// Generate BASIC scheme object and add it to the local
			// auth cache
			BasicScheme basicAuth = new BasicScheme();
			authCache.put(targetHost, basicAuth);
			// Add AuthCache to the execution context
			HttpClientContext localContext = HttpClientContext.create();
			localContext.setAuthCache(authCache);
			for (int i = 0; i < 1; i++) {
				CloseableHttpResponse response = httpclient.execute(targetHost,
						httpget, localContext);
				try {
					HttpEntity entity = response.getEntity();

					if (entity != null) {
						InputStream in = (InputStream) entity.getContent();
						InputStreamReader ris = new InputStreamReader(in,
								"utf-8");
						BufferedReader br = new BufferedReader(ris);

						while ((line = br.readLine()) != null) {
							jsonstring += line;
						}
					}
					EntityUtils.consume(entity);
					System.out.println("curl -u \"" + username+ ":" + password + "\"  \"" + url + "\"");
					System.out.println(jsonstring);
					System.out.println("-----------------------------------------------");
				} finally {
					response.close();
				}
			}
		} finally {
			httpclient.close();
		}
		return jsonstring;
	}
	
	
	public  void getLoginCookie() throws ClientProtocolException, IOException
	{
		String loginUrl = "http://teacher.ets100.com/teacher/login";
		CloseableHttpResponse response = null;
		// 需登陆后访问的 Url
        //String dataUrl = "http://hi.mop.com/?";
       // CloseableHttpClient  httpClient =HttpClients.createDefault();
		
		CloseableHttpClient	httpClient =HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();
        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
        HttpPost httppost=new HttpPost(loginUrl);
		 // 设置登陆时要求的信息，用户名和密码
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("phone", "13012121212"));
        params.add(new BasicNameValuePair("password", "123456"));
		httppost.setEntity(new UrlEncodedFormEntity(params));
		response = httpClient.execute(httppost);
        HttpEntity entity=response.getEntity();
        String setCookie = response.getFirstHeader("Set-Cookie")
		        .getValue();
		     JSESSIONID = setCookie.substring("JSESSIONID=".length(),
		        setCookie.indexOf(";"));
		   System.out.println(JSESSIONID);
       // setCookieStore(response);
       // setContext();
       
        httppost.releaseConnection();
	}
	
	@SuppressWarnings("deprecation")
	public void get1(String relativeurl,String parameters) throws Exception
	{
		// httpClient.getState().addCookies(cookieStore);
		 
		
		httpClient =HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();
		String localurl = HTTPHEADER + ParseProperties.getSystemProperty("host") + ":" + ParseProperties.getSystemProperty("port") + relativeurl + "?" +parameters;
	     HttpPost httpPost2=new HttpPost(localurl); 
	     httpPost2.addHeader(new BasicHeader("token",two_string));
	       System.out.println("我查询session"+JSESSIONID);
	       CloseableHttpResponse   response=httpClient.execute(httpPost2);
	      //  httpClient.getParams().setParameter(JSESSIONID, JSESSIONID);
	        HttpEntity   entity=response.getEntity();
	        String jsonstring = "", line = null;
	        if (entity != null) {
				InputStream in = (InputStream) entity.getContent();
				InputStreamReader ris = new InputStreamReader(in,
						"utf-8");
				BufferedReader br = new BufferedReader(ris);

				while ((line = br.readLine()) != null) {
					jsonstring += line;
				}
				
	        }
			System.out.println(jsonstring);
	        System.out.println(response.getEntity());
	        
	}
	
	
	
	public static void getLoginCookie2() throws ClientProtocolException, IOException
	{
		String loginUrl = "http://teacher.ets100.com/teacher/login";
		CloseableHttpResponse response = null;
		// 需登陆后访问的 Url
        //String dataUrl = "http://hi.mop.com/?";
       // CloseableHttpClient  httpClient =HttpClients.createDefault();
		httpClient =HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();
        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
        HttpPost httppost=new HttpPost(loginUrl);
		 // 设置登陆时要求的信息，用户名和密码
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("phone", "18826499761"));
        params.add(new BasicNameValuePair("password", "123456"));
		httppost.setEntity(new UrlEncodedFormEntity(params));
		response = httpClient.execute(httppost);
        HttpEntity entity=response.getEntity();
        
        InputStream in1 = (InputStream) entity.getContent();
		InputStreamReader ris1 = new InputStreamReader(in1,
				"utf-8");
		BufferedReader br1 = new BufferedReader(ris1);
		 String jsonstring1 = "", line1 = null;
		while ((line1 = br1.readLine()) != null) {
			jsonstring1 += line1;
			JSONObject jsonObject = JSONObject.fromObject(jsonstring1);
           String one_string = jsonObject.getString("data");//
            JSONObject  jsonObject2=JSONObject.fromObject(one_string);
            two_string=jsonObject2.getString("token");
            System.out.println(two_string);
            System.out.println(one_string);
		}
        setCookieStore(response);
       // setContext();
        httppost.releaseConnection();
        //return httpClient; 
        HttpPost httpPost2=new HttpPost("http://teacher.ets100.com/teacher/center"); 
        response=httpClient.execute(httpPost2);
        entity=response.getEntity();
        String jsonstring = "", line = null;
        if (entity != null) {
			InputStream in = (InputStream) entity.getContent();
			InputStreamReader ris = new InputStreamReader(in,
					"utf-8");
			BufferedReader br = new BufferedReader(ris);

			while ((line = br.readLine()) != null) {
				jsonstring += line;
//				JSONObject jsonObject = JSONObject.fromObject(jsonstring);
//	           String one_string = jsonObject.getString("data");//
//	            JSONObject  jsonObject2=JSONObject.fromObject(one_string);
//	            two_string=jsonObject2.getString("token");
//	            System.out.println(two_string);
//	            System.out.println(one_string);
			}
        }
		System.out.println(jsonstring);
        System.out.println(response.getEntity());
        
	}
	
	 public static void setContext() {
		    System.out.println("----setContext");
		    context = HttpClientContext.create();
		    Registry<CookieSpecProvider> registry = RegistryBuilder
		        .<CookieSpecProvider> create()
		        .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
		        .register(CookieSpecs.BROWSER_COMPATIBILITY,
		            new BrowserCompatSpecFactory()).build();
		    context.setCookieSpecRegistry(registry);
		    context.setCookieStore(cookieStore);
		  }
	
	 public static CookieStore setCookieStore(HttpResponse httpResponse) {
		    System.out.println("----setCookieStore");
		    cookieStore = new BasicCookieStore();
		    // JSESSIONID
		     setCookie = httpResponse.getFirstHeader("Set-Cookie")
		        .getValue();
		     //JSESSIONID = setCookie.substring("token=".length(),
		     //   setCookie.indexOf(";"));
		     JSESSIONID=setCookie.substring(0, setCookie.indexOf(";"));
		     JSESSIONID="Hm_lvt_32dfd2e89ddd41da6a01cdb120001404=1502160025,1502337501,1502437131,1502443494; Hm_lpvt_32dfd2e89ddd41da6a01cdb120001404=1502446082;"
		    	        +JSESSIONID;
		    System.out.println("JSESSIONID:" + JSESSIONID);
		    // 新建一个Cookie
		    BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",
		        JSESSIONID);
		    cookie.setVersion(0);
		    cookie.setDomain("http://teacher.ets100.com");
		   // cookie.setPath("/teacher/login");
		    // cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");
		    // cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");
		    // cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");
		    // cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");
		    cookieStore.addCookie(cookie);
		    return cookieStore;
		  }
	 
	 
	
	
	
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param relativeurl
	 * @param parameters：post参数
	 * @return
	 * @throws Exception
	 */
	public String doPost(String username, String password, String relativeurl,
			String parameters) throws Exception {
		if(relativeurl == null || relativeurl.equals(""))
			return null;
		String source = null;
	
		if(parameters.indexOf("source=") < 0 ){
			source = "&source=" + ParseProperties.getSystemProperty("source");
		}
		 
		String localurl = HTTPHEADER + ParseProperties.getSystemProperty("host") + ":" + ParseProperties.getSystemProperty("port") + relativeurl + "?" + source;
		System.out.println("localurl"+localurl);
		HttpPost httppost = new HttpPost(localurl);
		HttpHost targetHost = new HttpHost(httppost.getURI().getHost(), httppost
				.getURI().getPort(), HTTP);
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(targetHost.getHostName(),
				targetHost.getPort()), new UsernamePasswordCredentials(
				username, password));
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCredentialsProvider(credsProvider).build();
		String jsonstring = "", line = null;
		StringEntity reqEntity = new StringEntity(parameters,"UTF-8");
		reqEntity.setContentType("application/x-www-form-urlencoded");
		httppost.setEntity(reqEntity);
		try {
			// Create AuthCache instance
			AuthCache authCache = new BasicAuthCache();
			// Generate BASIC scheme object and add it to the local
			// auth cache
			BasicScheme basicAuth = new BasicScheme();
			authCache.put(targetHost, basicAuth);
			// Add AuthCache to the execution context
			HttpClientContext localContext = HttpClientContext.create();
			localContext.setAuthCache(authCache);
			for (int i = 0; i < 1; i++) {
				CloseableHttpResponse response = httpclient.execute(targetHost,
						httppost, localContext);
				try {
					HttpEntity entity = response.getEntity();

					if (entity != null) {
						InputStream in = (InputStream) entity.getContent();
						InputStreamReader ris = new InputStreamReader(in,
								"utf-8");
						BufferedReader br = new BufferedReader(ris);

						while ((line = br.readLine()) != null) {
							jsonstring += line;
						}
						
					}
					EntityUtils.consume(entity);
					System.out.println("curl -u \"" + username+ ":" + password + "\"  \"" + localurl + "\" -d \"" + parameters + "\"");
					System.out.println(jsonstring);
					System.out
							.println("-----------------------------------------------");
				} finally {
					response.close();
				}
			}
		} finally {
			httpclient.close();
		}
		return jsonstring; 
	}
	/**
	 * 
	 * @param username
	 * @param password
	 * @param relativeurl
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public String doPost(String username, String password, String relativeurl,
			String parameters,String hosts) throws Exception {
		if(relativeurl == null || relativeurl.equals(""))
			return null;
		String source = null;
		if(parameters.indexOf("source=") < 0)
		 source = "&source=" + ParseProperties.getSystemProperty("source");
		
		String localurl = HTTPHEADER + hosts + relativeurl + "?" + source;
		HttpPost httppost = new HttpPost(localurl);
		HttpHost targetHost = new HttpHost(httppost.getURI().getHost(), httppost
				.getURI().getPort(), HTTP);
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(targetHost.getHostName(),
				targetHost.getPort()), new UsernamePasswordCredentials(
				username, password));
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCredentialsProvider(credsProvider).build();
		String jsonstring = "", line = null;
		StringEntity reqEntity = new StringEntity(parameters,"UTF-8");
		reqEntity.setContentType("application/x-www-form-urlencoded");
		httppost.setEntity(reqEntity);
	//	httppost.setHeader("cookie", cookieStore);
		try {
			// Create AuthCache instance
			AuthCache authCache = new BasicAuthCache();
			// Generate BASIC scheme object and add it to the local
			// auth cache
			BasicScheme basicAuth = new BasicScheme();
			authCache.put(targetHost, basicAuth);
			// Add AuthCache to the execution context
			HttpClientContext localContext = HttpClientContext.create();
			localContext.setAuthCache(authCache);
			for (int i = 0; i < 1; i++) {
				CloseableHttpResponse response = httpclient.execute(targetHost,
						httppost, localContext);
				try {
					HttpEntity entity = response.getEntity();

					if (entity != null) {
						InputStream in = (InputStream) entity.getContent();
						InputStreamReader ris = new InputStreamReader(in,
								"utf-8");
						BufferedReader br = new BufferedReader(ris);

						while ((line = br.readLine()) != null) {
							jsonstring += line;
						}
						
					}
					EntityUtils.consume(entity);
					System.out.println("curl -u \"" + username+ ":" + password + "\"  \"" + localurl + "\" -d \"" + parameters + "\"");
					System.out.println(jsonstring);
					System.out
							.println("-----------------------------------------------");
				} finally {
					response.close();
				}
			}
		} finally {
			httpclient.close();
		}
		return jsonstring; 
	}
}

package com.weibo.global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
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
import org.junit.Ignore;
import org.junit.Test;

public class HttpTest{

	private static final String HTTP = "http";
	private static final String  HTTPHEADER = "http://";
  // 创建CookieStore实例
  static CookieStore cookieStore = null;
  static HttpClientContext context = null;
  static  String loginUrl = "http://test.teacher.ets100.com/teacher/login";
  static  String testUrl = "http://test.teacher.ets100.com/column/choises";
  static  String testUrl2 = "http://test.teacher.ets100.com/teacher/center";
  String loginErrorUrl = "http://127.0.0.1:8080/CwlProClient/login/login.jsp";
  static String postCookie;
	private static CloseableHttpClient  client=HttpClients.custom()
			.setDefaultCookieStore(cookieStore).build();
  
//  static{
//	  try {
//		testLogin();
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//  }
  
  @Test
public  void testLogin() throws Exception {
    System.out.println("----testLogin");

    // // 创建HttpClientBuilder
    // HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
    // // HttpClient
    // CloseableHttpClient client = httpClientBuilder.build();
    // 直接创建client
    client =HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    HttpResponse httpResponse=null;
    HttpPost httpPost = new HttpPost(loginUrl);
   

 
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("phone", "13012121212"));
    params.add(new BasicNameValuePair("password", "654321"));
    httpPost.setEntity(new UrlEncodedFormEntity(params));
    System.out.println("request line:" + httpPost.getRequestLine());
    try {
      // 执行post请求
      httpResponse = client.execute(httpPost);
      
    //  System.out.println("client===="+client.getParams().getParameter("Cookie").toString());
     // postCookie=httpPost.getFirstHeader("Cookie").getValue()+";";
      //client.getParams().getParameter(arg0)
      printResponse(httpResponse);
     
      
      //String setCookie = httpResponse.getFirstHeader("Set-Cookie")
    	//        .getValue();
    	 //   String JSESSIONID = setCookie.substring("JSESSIONID=".length(),
    	 //       setCookie.indexOf(";"));
    	 //   System.out.println("JSESSIONID:" + JSESSIONID);

//      String location = httpResponse.getFirstHeader("Location")
//          .getValue();
//      if (location != null && location.startsWith(loginErrorUrl)) {
//        System.out.println("----loginError");
     // }
      
      // 执行get请求
    	    
    	    
    	    
    /*  System.out.println("----the same client");
      HttpGet httpGet = new HttpGet(testUrl);
      System.out.println("request line:" + httpGet.getRequestLine());
      HttpResponse httpResponse1 = client.execute(httpGet);
      printResponse(httpResponse1);*/

      

      // cookie store
      setCookieStore(httpResponse);
      // context
      setContext();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        // 关闭流并释放资源
        client.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public String  doPost(String relativeurl,String parameters) throws Exception
  {
	  System.out.println("------dopost");
	  if(relativeurl == null || relativeurl.equals(""))
			return null;
	  client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		String localurl =HTTPHEADER+ParseProperties.getSystemProperty("host")+ParseProperties.getSystemProperty("port")+relativeurl+"?";
		HttpPost httppost = new HttpPost(localurl);
		
		String jsonstring = "", line = null;
		StringEntity reqEntity = new StringEntity(parameters,"UTF-8");
		reqEntity.setContentType("application/x-www-form-urlencoded");
		httppost.setEntity(reqEntity);
	//	httppost.setHeader("cookie", cookieStore);
		try {
			// Create AuthCache instance
			//AuthCache authCache = new BasicAuthCache();
			// Generate BASIC scheme object and add it to the local
			// auth cache
			//BasicScheme basicAuth = new BasicScheme();
			//authCache.put(targetHost, basicAuth);
			// Add AuthCache to the execution context
		//	HttpClientContext localContext = HttpClientContext.create();
		//	localContext.setAuthCache(authCache);
			for (int i = 0; i < 1; i++) {
				CloseableHttpResponse response = client.execute(
						httppost);
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
					//System.out.println("curl -u \"" + username+ ":" + password + "\"  \"" + localurl + "\" -d \"" + parameters + "\"");
					System.out.println(jsonstring);
					System.out
							.println("-----------------------------------------------");
				} finally {
					response.close();
				}
			}
		} finally {
			client.close();
		}
		return jsonstring; 
	
  }
  
  public String doPost(String username, String password, String relativeurl,
			String parameters) throws Exception {
		if(relativeurl == null || relativeurl.equals(""))
			return null;
		
		String localurl = HTTPHEADER + ParseProperties.getSystemProperty("host") + ":" + ParseProperties.getSystemProperty("port") + relativeurl + "?" ;
		System.out.println("localurl"+localurl);
		HttpPost httppost = new HttpPost(localurl);
		HttpHost targetHost = new HttpHost(httppost.getURI().getHost(), httppost
				.getURI().getPort(), HTTP);
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(targetHost.getHostName(),
				targetHost.getPort()), new UsernamePasswordCredentials(
				username, password));
		client=HttpClients.custom().setDefaultCookieStore(cookieStore).build();
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
				CloseableHttpResponse response = client.execute(targetHost,
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
			client.close();
		}
		return jsonstring; 
	}
  
  
  
  @Test
  public void testContext() throws Exception {
    System.out.println("----testContext");
    // 使用context方式
    CloseableHttpClient client = HttpClients.createDefault();
    
    HttpGet httpGet = new HttpGet(testUrl);
 //   httpGet.addHeader(new BasicHeader("Cookie",cookieStore));
    System.out.println("request line:" + httpGet.getRequestLine());
    try {
      // 执行get请求
      HttpResponse httpResponse = client.execute(httpGet, context);
      System.out.println("context cookies:"
          + context.getCookieStore().getCookies());
      printResponse(httpResponse);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        // 关闭流并释放资源
        client.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void testCookieStore() throws Exception {
    System.out.println("----testCookieStore");
    // 使用cookieStore方式
    client = HttpClients.custom()
        .setDefaultCookieStore(cookieStore).build();
    HttpGet httpGet = new HttpGet(testUrl);
    System.out.println("request line:" + httpGet.getRequestLine());
    try {
      // 执行get请求
      HttpResponse httpResponse = client.execute(httpGet);
      System.out.println("cookie store:" + cookieStore.getCookies());
      printResponse(httpResponse);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        // 关闭流并释放资源
        client.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  
  @Test
  public void testCookieStore2(String url,String parameters) throws Exception {
    System.out.println("----testCookieStore");
    // 使用cookieStore方式
    client = HttpClients.custom()
        .setDefaultCookieStore(cookieStore).build();
    HttpGet httpGet = new HttpGet(testUrl2);
    System.out.println("request line:" + httpGet.getRequestLine());
    try {
      // 执行get请求
      HttpResponse httpResponse = client.execute(httpGet);
      System.out.println("cookie store:" + cookieStore.getCookies());
      printResponse(httpResponse);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        // 关闭流并释放资源
        client.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public static void printResponse(HttpResponse httpResponse)
      throws ParseException, IOException {
    // 获取响应消息实体
    HttpEntity entity = httpResponse.getEntity();
  
    // 响应状态
    System.out.println("status:" + httpResponse.getStatusLine());
    System.out.println("headers:");
    HeaderIterator iterator = httpResponse.headerIterator();
    while (iterator.hasNext()) {
      System.out.println("\t" + iterator.next());
    }
    // 判断响应实体是否为空
    if (entity != null) {
      String responseString = EntityUtils.toString(entity);
      System.out.println("response length:" + responseString.length());
      System.out.println("response content:"
          + responseString.replace("\r\n", ""));
    }
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

  public static void setCookieStore(HttpResponse httpResponse) {
    System.out.println("----setCookieStore");
    cookieStore = new BasicCookieStore();
    // JSESSIONID
    String setCookie = httpResponse.getFirstHeader("Set-Cookie")
        .getValue();
    String JSESSIONID = setCookie.substring(0,
        setCookie.indexOf(";"));
    JSESSIONID="Hm_lvt_32dfd2e89ddd41da6a01cdb120001404=1502160025,1502337501,1502437131,1502443494; Hm_lpvt_32dfd2e89ddd41da6a01cdb120001404=1502446082;"
        +JSESSIONID;
    System.out.println("JSESSIONID:" + JSESSIONID);
    // 新建一个Cookie
    BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",
        JSESSIONID);
    cookie.setVersion(0);
    cookie.setDomain("test.teacher.ets100.com");
  //  cookie.setPath("/CwlProClient");
    // cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");
    // cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");
    // cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");
    // cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");
    cookieStore.addCookie(cookie);
  }

  public static List<NameValuePair> getParam(Map parameterMap) {
    List<NameValuePair> param = new ArrayList<NameValuePair>();
    Iterator it = parameterMap.entrySet().iterator();
    while (it.hasNext()) {
      Entry parmEntry = (Entry) it.next();
      param.add(new BasicNameValuePair((String) parmEntry.getKey(),
          (String) parmEntry.getValue()));
    }
    return param;
  }
}
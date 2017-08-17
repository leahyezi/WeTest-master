package com.weibo.cases.example;

import org.apache.http.protocol.HTTP;
import org.junit.Assert;
import org.junit.Test;

import com.weibo.common.ChangeInfoCommon;
import com.weibo.global.HttpTest;
import com.weibo.model.ResponseBean;
import com.weibo.runner.Retry;

public class ExampleCookieTest {

	HttpTest HttpTest=new HttpTest();
	ChangeInfoCommon changeInfoCommon=new ChangeInfoCommon();
	
	
	@Test
	public void testCenter()
	{
		try {
			HttpTest.testCookieStore();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testlogin()
	{
		try {
			HttpTest.testLogin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Retry(value=2)
	@Test
	public void testClunmList()
	{
		try {
			HttpTest.testCookieStore();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	public void changePassword()
	{
		
		ResponseBean responseBean=changeInfoCommon.changePassword("old_passqord=123456&password=654321&secure_level=1");
		System.out.println("code"+responseBean.getCode()+"msg"+responseBean.getMsg()+"data"+responseBean.getData());
		Assert.assertEquals("对比返回code","0", responseBean.getCode());
		//Assert
	}
	
	@Test
	public void eqauls()
	{
		ResponseBean responseBean=new ResponseBean();
		responseBean.setCode("ss");
		ResponseBean responseBean2=new ResponseBean();
		responseBean2.setCode("ss");
		System.out.println((responseBean.toString()).equals(responseBean2.toString()));
	}
	
	
	
	
	
}

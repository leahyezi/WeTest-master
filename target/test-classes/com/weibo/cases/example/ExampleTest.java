package com.weibo.cases.example;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.weibo.common.StatusCommon;
import com.weibo.global.HttpClientBase;
import com.weibo.global.TestLog;
import com.weibo.model.Teacher;
import com.weibo.userpool.TeacherDao;
import com.weibo.userpool.TestDao;

import static org.hamcrest.Matchers.is;

public class ExampleTest {
  
	
	StatusCommon status=new StatusCommon();
	TeacherDao teacherDao=new TeacherDao();
    Teacher teacher =teacherDao.getAccount("13012121212");
	
	@Test
	public void testExampleOne() {
		System.out.println("one");
		try {
			// 构造场景
			
			assertThat("abc", is("abc"));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("testExampleOne excepiton");
		} finally {
			//  清数据
		}
	}

	@Test
	public void testExampleTwo() {
		System.out.println("two");
		try {

			assertThat("abc", is("abc"));
	

		} catch (Exception e) {
			e.printStackTrace();
			fail("testExampleTwo excepiton");
	} finally {

		}
	}
	
	
	@Test
	public void testLogin() 
	{
		    System.out.println("afad");
			try {
				TestLog.Comment("登录");
				status.login("111", "222", "/teacher/login", "phone="+teacher.getLogin()+"&password=123456");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(a.toString());
		
	}
	
	
	@Test
	public void testSave()
	{
		status.loginCookie();
	}
	
	@Test
	public void checkDeng()
	{
		status.checkDenglu("/teacher/check-phone", "phone=18826499761");
	}
	
	@Test
	public void getList()
	{
		status.getList("/column/choises", "");
	}
			
	@Test
	public void getCenter()
	{
		status.getList("/teacher/center", "");
	}
	
	
}

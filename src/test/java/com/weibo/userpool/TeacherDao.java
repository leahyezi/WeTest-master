package com.weibo.userpool;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.MediaName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.deser.impl.ManagedReferenceProperty;
import com.weibo.model.Teacher;


public class TeacherDao {

	public static void main(String[] args) {
		TeacherDao teacherDao=new TeacherDao();
		Teacher teacher= teacherDao.getAccount("18826499761");
		System.out.println(teacher.toString());
	}
	
	public  List<Teacher> getAccountList()
	{
		List<Teacher> teacherList=new ArrayList<Teacher>();
		Connection conn=JdbcUtil.getConnection();
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Teacher teacher=null;
		try {
			statement=conn.prepareStatement("select id,fdnumber,fdlogin,fdpassword from tbteacher ");
			resultSet=statement.executeQuery();
			while (resultSet.next()) {
				teacher=new Teacher();
				teacher.setId(resultSet.getInt("ID"));
				teacher.setNumber(resultSet.getInt("fdnumber"));
				teacher.setLogin(resultSet.getString("fdlogin"));
				teacher.setPassword(resultSet.getString("fdpassword"));
				
				teacherList.add(teacher);
			}
		return teacherList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			JdbcUtil.close(resultSet, statement, conn);
		}
		return null;
	}
	
	
	public  Teacher getAccount(String login )
	{
		
		Connection conn=JdbcUtil.getConnection();
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Teacher teacher=null;
		try {
			statement=conn.prepareStatement("select id,fdnumber,fdlogin,fdpassword from tbteacher where fdlogin='"+login+"';");
			resultSet=statement.executeQuery();
			while (resultSet.next()) {
				teacher=new Teacher();
				teacher.setId(resultSet.getInt("ID"));
				teacher.setNumber(resultSet.getInt("fdnumber"));
				teacher.setLogin(resultSet.getString("fdlogin"));
				teacher.setPassword(resultSet.getString("fdpassword"));
				
			
			}
		return teacher;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			JdbcUtil.close(resultSet, statement, conn);
		}
		return null;
	}
	
}

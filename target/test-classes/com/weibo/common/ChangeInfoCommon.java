package com.weibo.common;

import javax.swing.RepaintManager;

import com.weibo.global.HttpTest;
import com.weibo.global.JsonCommon;
import com.weibo.model.ResponseBean;
import com.weibo.model.Status;

public class ChangeInfoCommon {

	HttpTest httpTest=new HttpTest();
	ResponseBean responseBean=new ResponseBean();
	String  responseInfo;
	String relativeurl;
	
	public Object commonResult(String responseInfo) throws Exception {
//		if (statusInfo.contains("error_code")) {
//			error = (ErrorInfo) JsonCommon.getJavabean(statusInfo,
//					ErrorInfo.class);
//			MbLogger.error(statusInfo, new ExceptionCommon(statusInfo));
//			return error;
//		} else {
		responseBean =  (ResponseBean) JsonCommon.getJavabean(responseInfo, ResponseBean.class);
		return responseBean;
//		}	
	}
	
	public ResponseBean changePassword(String parameters)
	{
		relativeurl="/teacher/change-password";
		try {
			responseInfo=httpTest.doPost("HGF","111",relativeurl, parameters);
			responseBean=(ResponseBean) commonResult(responseInfo);
			//responseBean.getData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseBean;
	}
	
	
	
}

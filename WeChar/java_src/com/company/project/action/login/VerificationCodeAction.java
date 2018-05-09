package com.company.project.action.login;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import javacommon.util.VerificationCode;
import javacommon.util.VerificationCode.SecurityCodeLevel;

public class VerificationCodeAction extends ActionSupport implements SessionAware{
	//图片流
	private ByteArrayInputStream imageStream;
	//session域
	private Map<String, Object> session;
	
	public String getVerificationCode(){
		//如果开启Hard模式，可以不区分大小写
		String securityCode = VerificationCode.getSecurityCode(4,SecurityCodeLevel.Hard, false);
		imageStream = VerificationCode.getImageAsInputStream(securityCode);
		//放入session中
		session.put("securityCode", securityCode);
		return SUCCESS;
	}

	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}

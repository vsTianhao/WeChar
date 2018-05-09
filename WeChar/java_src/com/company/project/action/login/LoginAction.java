package com.company.project.action.login;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.company.project.model.UserInfo;
import com.company.project.service.LoginManager;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{
	private LoginManager loginManager;
	private UserInfo user;
	private Map<String, Object>session;
	/**回馈信息**/
	private String feedback;
	public String log(){
		UserInfo ui=loginManager.findUser(user);
		if(ui==null){
			feedback="账号或密码错误";
			return ERROR;
		}
		session.put("User", ui);
		return SUCCESS;
	}
	public LoginManager getLoginManager() {
		return loginManager;
	}
	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
}

package com.company.project.service;

import com.company.project.dao.LoginDao;
import com.company.project.model.UserInfo;

public class LoginManager {
	private LoginDao loginDao;
	
	public UserInfo findUser(UserInfo user){
		return loginDao.findUser(user);
	}
	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
	
}

package com.company.project.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.company.project.model.UserInfo;

import javacommon.base.BaseHibernateDao;

@Repository
public class LoginDao extends BaseHibernateDao<UserInfo,java.lang.Integer>{
	public UserInfo findUser(UserInfo user) {
		Query query= super.getSession().createQuery("from UserInfo where username=:username and password=:password");
		query.setString("username", user.getUsername());
		query.setString("password", user.getPassword());
		return (UserInfo) query.uniqueResult();
	}

	@Override
	public Class getEntityClass() {
		return UserInfo.class;
	}
}

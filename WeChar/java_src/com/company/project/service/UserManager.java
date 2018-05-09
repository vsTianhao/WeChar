/*
 * Powered By [zhanchaohan]
 * Since 2008 - 2018
 */

package com.company.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.company.project.model.*;
import com.company.project.dao.*;
import com.company.project.service.*;
import com.company.project.vo.query.*;

/**
 * @author zhanchaohan email:zhanchaohan@gmail.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class UserManager extends BaseManager<UserInfo,java.lang.String>{

	private UserDao userDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setUserDao(UserDao dao) {
		this.userDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.userDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(UserQuery query) {
		return userDao.findPage(query);
	}
	
}

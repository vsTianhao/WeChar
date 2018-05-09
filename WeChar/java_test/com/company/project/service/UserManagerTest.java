/*
 * Powered By [zhanchaohan]
 * Since 2008 - 2018
 */

package com.company.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;

import cn.org.rapid_framework.test.context.TestMethodContext;
import static junit.framework.Assert.*;

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


public class UserManagerTest extends BaseManagerTestCase{

	private UserManager manager;
	
	@Autowired
	public void setUserManager(UserManager manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/User.xml",
                            "classpath:testdata/User_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		UserInfo obj = newUser();
		manager.save(obj);
		manager.getEntityDao().flush();
		
		manager.update(obj);
		manager.getEntityDao().flush();
		
		assertNotNull(obj.getUsername());
		
		manager.removeById(obj.getUsername());
		manager.getEntityDao().flush();
	
	}
	
	public static UserInfo newUser() {
		UserInfo obj = new UserInfo();
		
	  	obj.setPassword(new java.lang.String("1"));
		return obj;
	}
}

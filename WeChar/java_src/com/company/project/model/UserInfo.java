/*
 * Powered By [zhanchaohan]
 * Since 2008 - 2018
 */

package com.company.project.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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


@Entity
@Table(name = "user")
public class UserInfo extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "User";
	public static final String ALIAS_USERNAME = "username";
	public static final String ALIAS_PASSWORD = "password";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * username       db_column: username 
     */ 	
	@Length(max=100)
	private java.lang.String username;
    /**
     * password       db_column: password 
     */ 	
	@Length(max=100)
	private java.lang.String password;
	//columns END


	public UserInfo(){
	}

	public UserInfo(
		java.lang.String username
	){
		this.username = username;
	}

	

	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "increment")
	@Column(name = "username", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	public java.lang.String getUsername() {
		return this.username;
	}
	
	@Column(name = "password", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Username",getUsername())
			.append("Password",getPassword())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUsername())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserInfo == false) return false;
		if(this == obj) return true;
		UserInfo other = (UserInfo)obj;
		return new EqualsBuilder()
			.append(getUsername(),other.getUsername())
			.isEquals();
	}
}


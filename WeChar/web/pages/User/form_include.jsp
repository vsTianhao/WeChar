<%@page import="com.company.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="username" name="username" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	<s:textfield label="%{@com.company.project.model.User@ALIAS_PASSWORD}" key="password" value="%{model.password}" cssClass="" required="false" />
	

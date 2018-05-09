<%@page import="com.company.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=User.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/pages/User/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/pages/User/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="username" id="username" value="%{model.username}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel"><%=User.ALIAS_PASSWORD%></td>	
				<td><s:property value="%{model.password}" /></td>
			</tr>
		</table>
	</s:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>
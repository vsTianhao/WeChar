package javacommon.struts2.interceptor;

import java.util.Map;

import com.company.project.model.UserInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/****
 * 登录全局拦截器
 * @author zhanchaohan
 *
 */
public class LoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
            Map<String, Object> session = ActionContext.getContext().getSession();  
            UserInfo user = (UserInfo) session.get("User");  
            if(user == null) {  
                return "login";  
            } else {  
                return invocation.invoke();  
        }  
	}
}

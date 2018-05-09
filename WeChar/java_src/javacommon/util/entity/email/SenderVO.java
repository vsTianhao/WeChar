package javacommon.util.entity.email;

import java.io.Serializable;
import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/****
 * 发送人
 * @author zhanchaohan
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SenderVO implements Serializable{
	private static final long serialVersionUID = 1L;
	/**发送人账号**/
	private String userName;
	/**发送人密码**/
	private String password;
	/**邮件编码**/
	private String emailEncoding;
	/**服务器协议**/
	private String emailServer;
}

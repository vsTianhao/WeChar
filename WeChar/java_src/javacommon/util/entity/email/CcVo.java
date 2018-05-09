package javacommon.util.entity.email;

import java.io.Serializable;
import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 抄送人地址
 * @author zhanchaohan
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CcVo implements Serializable{
	private static final long serialVersionUID = 1L;
	/**抄送人邮箱**/
	private String CcEmail;
	/**抄送人名称**/
	private String CcName;
	
}

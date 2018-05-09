package javacommon.util.entity.email;

import java.io.Serializable;
import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 密送人实体
 * @author zhanchaohan
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BccVo implements Serializable{
	private static final long serialVersionUID = 1L;
	/**密送人邮箱*/
	private String BccEmail;
	/**密送人名称**/
	private String BccName;
}

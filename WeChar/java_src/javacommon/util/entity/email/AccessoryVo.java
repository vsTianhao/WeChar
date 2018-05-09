package javacommon.util.entity.email;

import java.io.Serializable;
import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 附件实体对象
 * @author zhanchaohan
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessoryVo implements Serializable{
	private static final long serialVersionUID = 1L;
	/**附件地址**/
	private String accessoryPath;
	/**附件网络地址**/
	private URL accessoryUrl;
	/**附件描述**/
	private String accessoryDescribe;
	/**附件名称**/
	private String accessoryName;
	
}

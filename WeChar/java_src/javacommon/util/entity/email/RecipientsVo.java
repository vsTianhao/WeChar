package javacommon.util.entity.email;

import java.io.Serializable;
import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 收件人实体
 * @author zhanchaohan
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipientsVo implements Serializable{
	private static final long serialVersionUID = 1L;
	/** 收件人名称 **/
	private String emailAddresseeName;
	/** 收件人邮箱 **/
	private String emailAddresseeEmail;
}

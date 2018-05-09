package javacommon.util.entity.email;

import java.io.Serializable;
import java.net.URL;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 邮件实体对象
 * 
 * @author zhanchaohan
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailVo implements Serializable{
	private static final long serialVersionUID = 1L;
	/** 邮件标题 */
	private String emailHeadline;
	/** 发送人名称 **/
	private String emailSendUserName;
	/** 邮件正文 **/
	private String mainBody;
	/** 收件人 **/
	private Set<RecipientsVo> recipientsList;
	/** 附件 **/
	private Set<AccessoryVo> accessoryList;
	/** 抄送 **/
	private Set<CcVo> CcList;
	/** 密送 **/
	private Set<BccVo> BccList;
}

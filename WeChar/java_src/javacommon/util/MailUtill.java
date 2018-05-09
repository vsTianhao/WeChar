package javacommon.util;

import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import javacommon.util.entity.email.AccessoryVo;
import javacommon.util.entity.email.BccVo;
import javacommon.util.entity.email.CcVo;
import javacommon.util.entity.email.MailVo;
import javacommon.util.entity.email.RecipientsVo;
import javacommon.util.entity.email.SenderVO;

/****
 * 邮件发送工具类
 * 
 * @author zhanchaohan
 *
 */
public class MailUtill {
	/** 发送人 **/
	private SenderVO sender;

	/***
	 * 简单邮件发送
	 * 
	 * @param mail
	 *            邮件对象
	 */
	public void simpleEmail(MailVo mail) {
		try {
			SimpleEmail email = new SimpleEmail();
			email.setCharset(sender.getEmailEncoding()); // 邮件编码方式
			email.setHostName(sender.getEmailServer()); // 服务器名
			email.setAuthentication(sender.getUserName(), sender.getPassword()); // 用户名，密码
			if (mail.getRecipientsList() != null) {
				// 加入收件人
				for (RecipientsVo recipients : mail.getRecipientsList()) {
					if (StringUtils.isNotBlank(recipients.getEmailAddresseeEmail())
							&& StringUtils.isNotBlank(recipients.getEmailAddresseeName())) {
						email.addTo(recipients.getEmailAddresseeEmail(), recipients.getEmailAddresseeName(),
								sender.getEmailEncoding()); // 收信人
					} else {
						if (StringUtils.isNotBlank(recipients.getEmailAddresseeEmail())) {
							email.addTo(recipients.getEmailAddresseeEmail());
						}
					}
				}
			}
			if (mail.getCcList() != null) {
				// 加入抄送人
				for (CcVo Cc : mail.getCcList()) {
					if (StringUtils.isNotBlank(Cc.getCcEmail()) && StringUtils.isNotBlank(Cc.getCcName())) {
						email.addCc(Cc.getCcEmail(), Cc.getCcName(), sender.getEmailEncoding());
					} else {
						if (StringUtils.isNotBlank(Cc.getCcEmail())) {
							email.addCc(Cc.getCcEmail());
						}
					}
				}
			}
			if (mail.getBccList() != null) {
				// 加入密送人
				for (BccVo Bcc : mail.getBccList()) {
					if (StringUtils.isNotBlank(Bcc.getBccEmail()) && StringUtils.isNotBlank(Bcc.getBccName())) {
						email.addBcc(Bcc.getBccEmail(), Bcc.getBccName(), sender.getEmailEncoding());
					} else {
						if (StringUtils.isNotBlank(Bcc.getBccEmail())) {
							email.addBcc(Bcc.getBccEmail());
						}
					}
				}
			}
			email.setFrom(sender.getUserName(), mail.getEmailSendUserName(), sender.getEmailEncoding()); // 发信人
			email.setSubject(mail.getEmailHeadline()); // 标题
			email.setMsg(mail.getMainBody()); // 正文
			email.send(); // 发送
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 包含附件的邮件
	 */
	public void testMultiPartEmail(MailVo mail) {
		try {
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName(sender.getEmailServer());
			email.setAuthentication(sender.getUserName(), sender.getPassword());
			email.setCharset(sender.getEmailEncoding());
			if (mail.getRecipientsList() != null) {
				// 加入收件人
				for (RecipientsVo recipients : mail.getRecipientsList()) {
					if (StringUtils.isNotBlank(recipients.getEmailAddresseeEmail())
							&& StringUtils.isNotBlank(recipients.getEmailAddresseeName())) {
						email.addTo(recipients.getEmailAddresseeEmail(), recipients.getEmailAddresseeName(),
								sender.getEmailEncoding()); // 收信人
					} else {
						if (StringUtils.isNotBlank(recipients.getEmailAddresseeEmail())) {
							email.addTo(recipients.getEmailAddresseeEmail());
						}
					}
				}
			}
			if (mail.getCcList() != null) {
				// 加入抄送人
				for (CcVo Cc : mail.getCcList()) {
					if (StringUtils.isNotBlank(Cc.getCcEmail()) && StringUtils.isNotBlank(Cc.getCcName())) {
						email.addCc(Cc.getCcEmail(), Cc.getCcName(), sender.getEmailEncoding());
					} else {
						if (StringUtils.isNotBlank(Cc.getCcEmail())) {
							email.addCc(Cc.getCcEmail());
						}
					}
				}
			}
			if (mail.getBccList() != null) {
				// 加入密送人
				for (BccVo Bcc : mail.getBccList()) {
					if (StringUtils.isNotBlank(Bcc.getBccEmail()) && StringUtils.isNotBlank(Bcc.getBccName())) {
						email.addBcc(Bcc.getBccEmail(), Bcc.getBccName(), sender.getEmailEncoding());
					} else {
						if (StringUtils.isNotBlank(Bcc.getBccEmail())) {
							email.addBcc(Bcc.getBccEmail());
						}
					}
				}
			}
			email.setFrom(sender.getUserName(), mail.getEmailSendUserName(), sender.getEmailEncoding()); // 发信人
			EmailAttachment att = null;
			for (AccessoryVo accessory : mail.getAccessoryList()) {
				if (StringUtils.isNotBlank(accessory.getAccessoryPath())) {
					att = new EmailAttachment();
					att.setPath(accessory.getAccessoryPath()); // 附件原始路径
					att.setDisposition(EmailAttachment.ATTACHMENT);
					// 是否乱码无法测试，若乱码可参考下一句解决方法
					att.setDescription(accessory.getAccessoryDescribe()); // 附件描述
					// 防止附件名乱码
					att.setName(MimeUtility.encodeText(accessory.getAccessoryName())); // 附件名称
					email.attach(att);
				}
				if (accessory.getAccessoryUrl() != null) {
					att = new EmailAttachment();
					att.setURL(accessory.getAccessoryUrl()); // 附件网络路径
					att.setDisposition(EmailAttachment.ATTACHMENT);
					// 是否乱码无法测试，若乱码可参考下一句解决方法
					att.setDescription(accessory.getAccessoryDescribe()); // 附件描述
					// 防止附件名乱码
					att.setName(MimeUtility.encodeText(accessory.getAccessoryName())); // 附件名称
					email.attach(att);
				}
			}
			email.setSubject(mail.getEmailHeadline()); // 标题
			email.setMsg(mail.getMainBody()); // 正文
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/****
	 * html邮件发送
	 * 
	 * @param mail
	 */
	public void sendHtmlEmail(MailVo mail) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName(sender.getEmailServer());
			email.setAuthentication(sender.getUserName(), sender.getPassword());
			email.setCharset(sender.getEmailEncoding());
			if (mail.getRecipientsList() != null) {
				// 加入收件人
				for (RecipientsVo recipients : mail.getRecipientsList()) {
					if (StringUtils.isNotBlank(recipients.getEmailAddresseeEmail())
							&& StringUtils.isNotBlank(recipients.getEmailAddresseeName())) {
						email.addTo(recipients.getEmailAddresseeEmail(), recipients.getEmailAddresseeName(),
								sender.getEmailEncoding()); // 收信人
					} else {
						if (StringUtils.isNotBlank(recipients.getEmailAddresseeEmail())) {
							email.addTo(recipients.getEmailAddresseeEmail());
						}
					}
				}
			}
			if (mail.getCcList() != null) {
				// 加入抄送人
				for (CcVo Cc : mail.getCcList()) {
					if (StringUtils.isNotBlank(Cc.getCcEmail()) && StringUtils.isNotBlank(Cc.getCcName())) {
						email.addCc(Cc.getCcEmail(), Cc.getCcName(), sender.getEmailEncoding());
					} else {
						if (StringUtils.isNotBlank(Cc.getCcEmail())) {
							email.addCc(Cc.getCcEmail());
						}
					}
				}
			}
			if (mail.getBccList() != null) {
				// 加入密送人
				for (BccVo Bcc : mail.getBccList()) {
					if (StringUtils.isNotBlank(Bcc.getBccEmail()) && StringUtils.isNotBlank(Bcc.getBccName())) {
						email.addBcc(Bcc.getBccEmail(), Bcc.getBccName(), sender.getEmailEncoding());
					} else {
						if (StringUtils.isNotBlank(Bcc.getBccEmail())) {
							email.addBcc(Bcc.getBccEmail());
						}
					}
				}
			}
			email.setFrom(sender.getUserName(), mail.getEmailSendUserName(), sender.getEmailEncoding()); // 发信人
			EmailAttachment att = null;
			for (AccessoryVo accessory : mail.getAccessoryList()) {
				if (StringUtils.isNotBlank(accessory.getAccessoryPath())) {
					att = new EmailAttachment();
					att.setPath(accessory.getAccessoryPath()); // 附件原始路径
					att.setDisposition(EmailAttachment.ATTACHMENT);
					// 是否乱码无法测试，若乱码可参考下一句解决方法
					att.setDescription(accessory.getAccessoryDescribe()); // 附件描述
					// 防止附件名乱码
					att.setName(MimeUtility.encodeText(accessory.getAccessoryName())); // 附件名称
					email.attach(att);
				}
				if (accessory.getAccessoryUrl() != null) {
					att = new EmailAttachment();
					att.setURL(accessory.getAccessoryUrl()); // 附件网络路径
					att.setDisposition(EmailAttachment.ATTACHMENT);
					// 是否乱码无法测试，若乱码可参考下一句解决方法
					att.setDescription(accessory.getAccessoryDescribe()); // 附件描述
					// 防止附件名乱码
					att.setName(MimeUtility.encodeText(accessory.getAccessoryName())); // 附件名称
					email.attach(att);
				}
			}
			email.setSubject(mail.getEmailHeadline()); // 标题
			email.setHtmlMsg(mail.getMainBody()); // 正文
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

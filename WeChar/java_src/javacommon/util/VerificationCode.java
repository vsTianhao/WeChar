package javacommon.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

/****
 * 生成验证码
 * 
 * @author zhanchaohan
 *
 */
public class VerificationCode {
	/**
	 * 验证码难度级别，Simple只包含数字，Medium包含数字和小写英文，Hard包含数字和大小写英文
	 */
	public enum SecurityCodeLevel {
		Simple, Medium, Hard
	};

	/**
	 * 产生默认验证码，4位中等难度
	 * 
	 * @return String 验证码
	 */
	public static String getSecurityCode() {
		return getSecurityCode(4, SecurityCodeLevel.Medium, false);
	}

	/**
	 * 产生长度和难度任意的验证码
	 * 
	 * @param length
	 *            长度
	 * @param level
	 *            难度级别
	 * @param isCanRepeat
	 *            是否能够出现重复的字符，如果为true，则可能出现 5578这样包含两个5,如 果为false，则不可能出现这种情况
	 * @return String 验证码
	 */
	public static String getSecurityCode(int length, SecurityCodeLevel level, boolean isCanRepeat) {
		// 随机抽取len个字符
		int len = length;
		// 字符集合(除去易混淆的数字0、数字1、字母l、字母o、字母O)
		char[] codes = { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		// 根据不同的难度截取字符数组
		if (level == SecurityCodeLevel.Simple) {
			codes = Arrays.copyOfRange(codes, 0, 9);
		} else if (level == SecurityCodeLevel.Medium) {
			codes = Arrays.copyOfRange(codes, 0, 33);
		}
		// 字符集合长度
		int n = codes.length;
		// 抛出运行时异常
		if (len > n && isCanRepeat == false) {
			throw new RuntimeException(String.format(
					"调用SecurityCode.getSecurityCode(%1$s,%2$s,%3$s)出现异常，当isCanRepeat为%3$s时，传入参数%1$s不能大于%4$s", len,
					level, isCanRepeat, n));
		}
		// 存放抽取出来的字符
		char[] result = new char[len];
		// 判断能否出现重复的字符
		if (isCanRepeat) {
			for (int i = 0; i < result.length; i++) {
				// 索引 0 and n-1
				int r = (int) (Math.random() * n);
				// 将result中的第i个元素设置为codes[r]存放的数值
				result[i] = codes[r];
			}
		} else {
			for (int i = 0; i < result.length; i++) {
				// 索引 0 and n-1
				int r = (int) (Math.random() * n);
				// 将result中的第i个元素设置为codes[r]存放的数值
				result[i] = codes[r];
				// 必须确保不会再次抽取到那个字符，因为所有抽取的字符必须不相同。
				// 因此，这里用数组中的最后一个字符改写codes[r]，并将n减1
				codes[r] = codes[n - 1];
				n--;
			}
		}
		return String.valueOf(result);
	}

	/*
	 * 第一步已经完成，有了上面SecurityCode类提供的验证码，就应该考虑怎么在图片上 写字符串了。
	 * 在Java中操作图片，需要使用BufferedImage类，它代表内存中的图片。
	 * 写字符串，就需要从图片BufferedImage上得到绘图图面Graphics，然后在图面上 drawString。
	 * 为了使验证码有一定的干扰性，也绘制了一些噪点。调用Graphics类的drawRect绘 制1*1大小的方块就可以了。
	 * 特别说明一下，由于后面要与Strtus2结合使用， 而在Struts2中向前台返回图片数据使用的是数据流的形式。所以提供了从图片向流 的转换方法
	 * 生成验证码图片
	 * 
	 * @param securityCode
	 *            验证码字符
	 * @return BufferedImage 图片
	 */
	public static BufferedImage createImage(String securityCode) {
		// 验证码长度
		int codeLength = securityCode.length();
		// 字体大小
		int fSize = 15;
		int fWidth = fSize + 1;
		// 图片宽度
		int width = codeLength * fWidth + 6;
		// 图片高度
		int height = fSize * 2 - 5;
		// 图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.createGraphics();
		// 设置背景色
		g.setColor(Color.WHITE);
		// 填充背景,用白色填充
		g.fillRect(0, 0, width, height);
		// 设置边框颜色
		g.setColor(Color.LIGHT_GRAY);
		// 边框字体样式
		g.setFont(new Font("Arial", Font.BOLD, height - 2));
		// 绘制边框
		g.drawRect(0, 0, width - 1, height - 1);
		// 绘制噪点
		Random rand = new Random();
		// 设置噪点颜色
		// g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < codeLength * 7; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
			// 绘制1*1大小的矩形
			g.drawRect(x, y, 1, 1);
			// g.setColor(Color.LIGHT_GRAY);
		}
		// 绘制验证码
		int codeY = height - 10;
		// 设置字体颜色和样式
		// g.setColor(new Color(19,148,246));
		g.setFont(new Font("Georgia", Font.BOLD, fSize + 2));
		for (int i = 0; i < codeLength; i++) {
			g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
			g.drawString(String.valueOf(securityCode.charAt(i)), i * 16 + 5, codeY);
		}
		// 关闭资源
		g.dispose();
		return image;
	}

	/**
	 * 返回验证码图片的流格式
	 * 
	 * @param securityCode
	 *            验证码
	 * @return ByteArrayInputStream 图片流
	 */
	public static ByteArrayInputStream getImageAsInputStream(String securityCode) {
		BufferedImage image = createImage(securityCode);
		return convertImageToStream(image);
	}

	/**
	 * 将BufferedImage转换成ByteArrayInputStream
	 * 
	 * @param image
	 *            图片
	 * @return ByteArrayInputStream 流
	 */
	private static ByteArrayInputStream convertImageToStream(BufferedImage image) {
		ByteArrayInputStream inputStream = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] bimage = null;
		try {
			ImageIO.write(image, "jpeg", bos);
			bimage = bos.toByteArray();
			inputStream = new ByteArrayInputStream(bimage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 返回输入流
		return inputStream;
	}
}

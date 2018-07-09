package com.github.fish.common.utils;

import com.github.fish.common.constant.IConstInfo;
import com.github.fish.common.enums.Module;
import com.github.fish.common.exceptions.BaseBizException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * 编码工具类
 */
public class CoderUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoderUtil.class);

	/**
	 * 加密
	 *
	 * @param content  需要加密的内容
	 * @param password 加密密码
	 * @return
	 */
	private static byte[] encrypt(String content, String password) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		//kgen.init(128, new SecureRandom(password.getBytes()));
		//防止linux下 随机生成key
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(password.getBytes());
		kgen.init(128, random);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");        // 创建密码器
		byte[] byteContent = content.getBytes("UTF-8");
		cipher.init(Cipher.ENCRYPT_MODE, key);            // 初始化
		return cipher.doFinal(byteContent);               // 加密
	}

	/**
	 * 解密
	 *
	 * @param content  待解密内容
	 * @param password 解密密钥
	 * @return
	 */
	private static byte[] decrypt(byte[] content, String password) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		//kgen.init(128, new SecureRandom(password.getBytes()));
		//防止linux下 随机生成key
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(password.getBytes());
		kgen.init(128, random);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");    // 创建密码器
		cipher.init(Cipher.DECRYPT_MODE, key);        // 初始化
		return cipher.doFinal(content);               // 加密
	}

	/**
	 * 将二进制转换成16进制
	 *
	 * @param buf
	 * @return
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}


	/**
	 * 将16进制转换为二进制
	 *
	 * @param hexStr
	 * @return
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * @param resource
	 * @return
	 */
	public static String encrypt(String resource) {
		if (StringUtils.isNotBlank(resource)) {
			try {
				byte[] bytes = encrypt(resource, IConstInfo.SECRET_KEY);
				return parseByte2HexStr(bytes);
			} catch (Exception e) {
				LOGGER.error("加密失败,{}", e.getMessage());
				if (e.getCause() instanceof BaseBizException) {
					throw (BaseBizException) e.getCause();
				}
				throw new BaseBizException(IConstInfo.EXCEPTION_TYPE_GENERAL, "加密失败.", Module.SYSTEM);
			}
		} else {
			return null;
		}
	}

	public static String decrypt(String resource) {
		if (StringUtils.isNotBlank(resource)) {
			try {
				byte[] bytes = parseHexStr2Byte(resource);
				byte[] decrypt = decrypt(bytes, IConstInfo.SECRET_KEY);
				return new String(decrypt);
			} catch (Exception e) {
				LOGGER.error("解码失败,{}", e.getMessage());
				if (e.getCause() instanceof BaseBizException) {
					throw (BaseBizException) e.getCause();
				}
				throw new BaseBizException(IConstInfo.EXCEPTION_TYPE_GENERAL, "解码失败.", Module.SYSTEM);
			}
		} else {
			return null;

		}
	}


	public static void main(String[] args) throws Exception {
//        String content = "你好";
//        String password = "12345678";
//        //加密
//        System.out.println("加密前：" + content);
//        byte[] encryptResult = encrypt(content, password);
//        System.out.println(encryptResult);
//        //解密
//        byte[] decryptResult = decrypt(encryptResult, password);
//        System.out.println("解密后：" + new String(decryptResult));
//
//
//        String content = "test";
//        String password = "12345678";
//        //加密
//        System.out.println("加密前：" + content);
//        byte[] encryptResult = encrypt(content, password);
//        String encryptResultStr = parseByte2HexStr(encryptResult);
//        System.out.println("加密后：" + encryptResultStr);
//        //解密
//        byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);
//        byte[] decryptResult = decrypt(decryptFrom, password);
//        System.out.println("解密后：" + new String(decryptResult));

		//String content = "我爱你";
		String content = "18611899696";
		String password = "12345678";
		//加密
		System.out.println("加密前：" + content);
		String encrypt = encrypt(content);
		System.out.println("加密后：" + encrypt);
		//解密

		String decrypt = decrypt(encrypt);
		System.out.println("解密后：" + decrypt);
	}

}
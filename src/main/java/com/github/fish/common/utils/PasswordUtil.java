package com.github.fish.common.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.HashMap;
import java.util.Map;

/**
 * 密码加密工具
 * 
 */
public class PasswordUtil {
	
	/**
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	public static Map<String, String> encrypt(String loginName, String password) {
		Map<String, String> map = new HashMap<>();
		String algorithmName = "md5";
		String salt1 = loginName;
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		int hashIterations = 2;

		SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
		String encodedPassword = hash.toHex();
		String salt = salt1 + salt2;
		map.put("encodedPassword", encodedPassword);
		map.put("salt", salt);
		return map;
	}
	/**
	 * 
	 * @param loginName
	 * @param password
	 * @param salt
	 * @return
	 */
	public static Map<String, String> encrypt(String loginName, String password, String salt) {
		Map<String, String> map = new HashMap<>();
		String algorithmName = "md5";
		int hashIterations = 2;

		SimpleHash hash = new SimpleHash(algorithmName, password, salt, hashIterations);
		String encodedPassword = hash.toHex();
		map.put("encodedPassword", encodedPassword);
		map.put("salt", salt);
		return map;
	}
}

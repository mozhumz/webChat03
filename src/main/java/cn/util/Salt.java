package cn.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *封装密码加密加盐算法
 * @author asus1
 *
 */
public class Salt {
	private static final String SALT="我是东坡人";
	public static String md5Salt(String password){
		return DigestUtils.md5Hex(password+SALT);
	}
}

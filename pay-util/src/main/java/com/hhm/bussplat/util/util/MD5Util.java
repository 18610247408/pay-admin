package com.hhm.bussplat.util.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * Created by jianwei.zhao on 2018年12月6日.
 */
public class MD5Util {
	
	/**
	 * * 使用md5的算法进行加密
	 * 
	 * @param plainText
	 *            加密字段
	 * @param charset
	 *            字符集
	 * @param isUpper
	 *            是否大写
	 * @return 加密后字段
	 * @throws UnsupportedEncodingException
	 */
	public static String md5(String plainText, String charset, boolean isUpper) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes(charset));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("没有字符集！");
		}
		if (isUpper) {
			return byteToHex(secretBytes).toUpperCase();
		}
		return byteToHex(secretBytes);
	}

	public static String md5(Map<String, String> requestParam, String key, String charset, boolean isUpper)
			throws UnsupportedEncodingException {
		StringBuffer strbuff = new StringBuffer();
		List<String> list = new ArrayList<String>(requestParam.keySet());
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			String paramName = list.get(i);
			if ("sign".equals(paramName)) {
				continue;
			}
			String paramValue = requestParam.get(paramName);
			if (paramName.equals("data"))
				paramValue = URLEncoder.encode(paramValue, "UTF-8");

			strbuff.append(paramName).append("=").append(paramValue);
		}
		return md5(strbuff.append(key).toString(), charset, isUpper);
	}

	private static String byteToHex(byte[] inbuf) {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < inbuf.length; i++) {
			String byteStr = Integer.toHexString(inbuf[i] & 0xFF);
			if (byteStr.length() != 2)
				strBuf.append('0').append(byteStr);
			else {
				strBuf.append(byteStr);
			}
		}
		return new String(strBuf);
	}

}

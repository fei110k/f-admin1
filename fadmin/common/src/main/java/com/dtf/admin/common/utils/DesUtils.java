package com.dtf.admin.common.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES加密\解密工具类
 */
public class DesUtils {
	
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;

		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; ++i) {
			int intTmp = arrB[i];

			while (intTmp < 0) {
				intTmp += 256;
			}

			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i += 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[(i / 2)] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
	/**
	 * 解密
	 * @param source
	 * @param key
	 * @return
	 */
	public static String decrypt(String source, String key) {
		if ((source == null) || (key == null)) {
			return source;
		}
		String rtn = null;
		try {
			Key secKey = getKey(key.getBytes());
			Cipher decryptCipher = Cipher.getInstance("DES");
			decryptCipher.init(2, secKey);
			rtn = new String(decryptCipher.doFinal(hexStr2ByteArr(source)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	/**
	 * 加密
	 * @param source 需要加密的内容
	 * @param key
	 * @return
	 */
	public static String encrypt(String source, String key) {
		if ((source == null) || (key == null)) {
			return source;
		}
		String rtn = null;
		try {
			Key secKey = getKey(key.getBytes());
			Cipher encryptCipher = Cipher.getInstance("DES");
			encryptCipher.init(1, secKey);
			rtn = byteArr2HexStr(encryptCipher.doFinal(source.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}
	
	public static Key getKey(byte[] arrBTmp) throws Exception {
		byte[] arrB = new byte[8];

		for (int i = 0; (i < arrBTmp.length) && (i < arrB.length); ++i) {
			arrB[i] = arrBTmp[i];
		}

		Key key = new SecretKeySpec(arrB, "DES");
		return key;
	}

	public static void main(String[] args) {
		String key = "12345";
		String originString = "12345";
		String encryptString = encrypt(originString, key);
		System.out.println("原始串：" + originString);
		System.out.println("加密后：" + encryptString);
		System.out.println("解密后：" + decrypt(encryptString, key));
	}
}

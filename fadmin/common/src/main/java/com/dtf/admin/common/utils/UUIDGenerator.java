package com.dtf.admin.common.utils;

import java.net.InetAddress;
import java.util.UUID;

/**
 * 提供生成唯一ID的工具类
 * 
 * @author Administrator
 *
 */
public class UUIDGenerator {
	private static UUIDGenerator uuidGenerator = new UUIDGenerator();

	public static UUIDGenerator getInst() {
		return uuidGenerator;
	}

	private static final int IP;

	private static int IptoInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}

	static {
		int ipadd;
		try {
			ipadd = IptoInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}
	private static short counter = (short) 0;
	private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

	public UUIDGenerator() {
	}

	/**
	 * Unique across JVMs on this machine (unless they load this class in the
	 * same quater second - very unlikely)
	 */
	private int getJVM() {
		return JVM;
	}

	/**
	 * Unique in a millisecond for this JVM instance (unless there are >
	 * Short.MAX_VALUE instances created in a millisecond)
	 */
	private short getCount() {
		synchronized (UUIDGenerator.class) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}

	/**
	 * Unique in a local network
	 */
	private int getIP() {
		return IP;
	}

	/**
	 * Unique down to millisecond
	 */
	private short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}

	private int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	private final static String sep = "";

	private String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	private String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	/**
	 * 根据
	 * 
	 * @return
	 */
	public String getGeneratorID() {
		return new StringBuffer(36).append(format(getIP())).append(sep)
				.append(format(getJVM())).append(sep)
				.append(format(getHiTime())).append(sep)
				.append(format(getLoTime())).append(sep)
				.append(format(getCount())).toString();
	}

	/**
	 * 获取java内部提供的UUID，36位 4255c27c-a444-4d65-8ffb-b04379321b35
	 * 
	 * @return
	 */
	public String getUUID36() {
		return UUID.randomUUID() + "";
	}

	/**
	 * 获取java内部提供的UUID，32位，把中间的-去除了 4255c27ca4444d658ffbb04379321b35
	 * 
	 * @return
	 */
	public String getUUID32() {
		return getUUID36().replaceAll("-", "");
	}
}

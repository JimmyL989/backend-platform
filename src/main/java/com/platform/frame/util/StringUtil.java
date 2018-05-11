package com.platform.frame.util;

import java.security.MessageDigest;

/**
 * @Description: 字符串处理工具类
 * @author: WangZhenzhong
 */
public class StringUtil {
	/**
	 * 字符串首字母小写
	 * @param str
	 * @return
	 */
	public static String toLowerCaseFirstOne(String str) {
		if (str == null || "".equals(str))
			return str;
		if (Character.isLowerCase(str.charAt(0)))
			return str;
		else
			return (new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1))
					.toString();
	}

	/**
	 * 字符串首字母大写
	 * @param str
	 * @return
	 */
	public static String toUpperCaseFirstOne(String str) {
		if (str == null || "".equals(str))
			return str;
		if (Character.isUpperCase(str.charAt(0)))
			return str;
		else
			return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1))
					.toString();
	}
	
	public static boolean isNullString(String str) {
		if (str == null || "".equals(str))
			return true;
		return false;
	}
	
	/**
	 * MD5加密
	 * @param s 
	 * @return String
	 */
	public static String MD5(String s)
	{
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try
		{
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str).toUpperCase();
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	/**
	 * 字符串左边开始填充字符处理
	 * @param strPara    原字符串
	 * @param cPara      要填充的字符
	 * @param intLength  填充后字符达到的总长度
	 * @return String
	 */
	public static String addChar(String strPara, char cPara, int nLength)
	{

		if (strPara.length() >= nLength)
		{
			return strPara;
		}
		else
		{
			String temp = "";
			for (int i = 0; i < (nLength - strPara.length()); i++)
			{
				temp = temp + String.valueOf(cPara);
			}
			return temp + strPara;
		}
	}
}

package com.allen.common.comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	// �����ʼ�
	private static final String V_EMAIL = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
	// �ֻ�
	private static final String V_MOBILE = "(\\+\\d+)?1[3458]\\d{9}$";
	// �̶��绰
	private static final String V_PHONE = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
	// ��ĸ
	private static final String V_LETTER = "^[A-Za-z]+$";
	// ����
	private static String regex = "(\\d+).*";

	/**
	 * �жϸ����ַ����Ƿ�հ״��� �հ״���ָ�ɿո��Ʊ�����س��������з���ɵ��ַ��� �������ַ���Ϊnull����ַ���������true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.length() == 0)
			return true;
		else
			return false;
	}

	/**
	 * �ж��ǲ���һ���Ϸ��ĵ����ʼ���ַ
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (isEmpty(email))
			return false;
		return Pattern.matches(V_EMAIL, email);
	}

	/**
	 * �ж��ǲ�����ĸ
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isLetter(String letter) {
		if (isEmpty(letter))
			return false;
		return Pattern.matches(V_LETTER, letter);
	}

	/**
	 * �ж��ǲ���һ���Ϸ����ֻ��� ֧�ֹ��ʸ�ʽ��+86135xxxx...���й��ڵأ���+00852137xxxx...���й���ۣ�
	 * �ƶ��ĺŶΣ�134(0-8)��135��136��137��138��139��147��Ԥ������TD��������
	 * 150��151��152��157��TDר�ã���158��159��187��δ���ã���188��TDר�ã�
	 * ��ͨ�ĺŶΣ�130��131��132��155��156�������ר�ã���185��δ���ã���186��3g��
	 * ���ŵĺŶΣ�133��153��180��δ���ã���189
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		if (isEmpty(mobile))
			return false;
		return Pattern.matches(V_MOBILE, mobile);
	}

	/**
	 * ��֤�̶��绰����
	 * 
	 * @param phone
	 *            �绰���룬��ʽ�����ң��������绰���� + ���ţ����д��룩 + �绰���룬�磺+8602085588447 ���ң�������
	 *            ���� ����ʶ�绰����Ĺ��ң��������ı�׼���ң����������롣�������� 0 �� 9 ��һλ���λ���֣�
	 *            ����֮���ǿո�ָ��Ĺ��ң����������롣 ���ţ����д��룩������ܰ���һ�������� 0 �� 9
	 *            �����֣���������д������Բ���š��� �Բ�ʹ�õ�������д���Ĺ��ң�����������ʡ�Ը������ �绰���룺������� 0 �� 9
	 *            ��һ����������
	 * @return ��֤�ɹ�����true����֤ʧ�ܷ���false
	 */
	public static boolean isPhone(String phone) {
		if (isEmpty(phone))
			return false;
		return Pattern.matches(V_PHONE, phone);
	}

	/**
	 * �ַ���ת����
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * ����ת����
	 * 
	 * @param obj
	 * @return ת���쳣���� 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * ����ת����
	 * 
	 * @param obj
	 * @return ת���쳣���� 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * �ַ���ת����ֵ
	 * 
	 * @param b
	 * @return ת���쳣���� false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * ��һ��InputStream��ת�����ַ���
	 * 
	 * @param is
	 * @return
	 */
	public static String toConvertString(InputStream is) {
		StringBuffer res = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader read = new BufferedReader(isr);
		try {
			String line;
			line = read.readLine();
			while (line != null) {
				res.append(line);
				line = read.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != isr) {
					isr.close();
					isr.close();
				}
				if (null != read) {
					read.close();
					read = null;
				}
				if (null != is) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
			}
		}
		return res.toString();
	}

	public static String getNumber(String str) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if (m.find()) {
			return m.group(1);
		}
		return "";
	}

	/**
	 * ������ַ���
	 * 
	 * @param str
	 * @return String
	 */
	public static String doEmpty(String str) {
		return doEmpty(str, "");
	}

	/**
	 * ������ַ���
	 * 
	 * @param str
	 * @param defaultValue
	 * @return String
	 */
	public static String doEmpty(String str, String defaultValue) {
		if (str == null || str.equalsIgnoreCase("null")
				|| str.trim().equals("") || str.trim().equals("����ѡ��")) {
			str = defaultValue;
		} else if (str.startsWith("null")) {
			str = str.substring(4, str.length());
		}
		return str.trim();
	}

	/**
	 * ��ѡ��
	 */
	final static String PLEASE_SELECT = "��ѡ��...";

	public static boolean notEmpty(Object o) {
		return o != null && !"".equals(o.toString().trim())
				&& !"null".equalsIgnoreCase(o.toString().trim())
				&& !"undefined".equalsIgnoreCase(o.toString().trim())
				&& !PLEASE_SELECT.equals(o.toString().trim());
	}

	public static boolean empty(Object o) {
		return o == null || "".equals(o.toString().trim())
				|| "null".equalsIgnoreCase(o.toString().trim())
				|| "undefined".equalsIgnoreCase(o.toString().trim())
				|| PLEASE_SELECT.equals(o.toString().trim());
	}

	public static boolean num(Object o) {
		int n = 0;
		try {
			n = Integer.parseInt(o.toString().trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if (n > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean decimal(Object o) {
		double n = 0;
		try {
			n = Double.parseDouble(o.toString().trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if (n > 0.0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ��JID�����û���
	 * 
	 * @param Jid
	 * @return
	 */
	public static String getUserNameByJid(String Jid) {
		if (empty(Jid)) {
			return null;
		}
		if (!Jid.contains("@")) {
			return Jid;
		}
		return Jid.split("@")[0];
	}

	/**
	 * ���û�������JID
	 * 
	 * @param jidFor
	 *            ����//ysj-pc
	 * @param userName
	 * @return
	 */
	public static String getJidByName(String userName, String jidFor) {
		if (empty(jidFor) || empty(jidFor)) {
			return null;
		}
		return userName + "@" + jidFor;
	}

	/**
	 * ���ݸ�����ʱ���ַ����������� �� ʱ �� ��
	 * 
	 * @param allDate
	 *            like "yyyy-MM-dd hh:mm:ss SSS"
	 * @return
	 */
	public static String getMonthTomTime(String allDate) {
		return allDate.substring(5, 19);
	}

	/**
	 * ���ݸ�����ʱ���ַ����������� �� ʱ �� �µ�����
	 * 
	 * @param allDate
	 *            like "yyyy-MM-dd hh:mm:ss SSS"
	 * @return
	 */
	public static String getMonthTime(String allDate) {
		return allDate.substring(5, 16);
	}

	/**
	 * Phone���ж�
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNum(String mobiles) {
		String regExp = "^[1]([3][0-9]{1}|([5][0-9]{1})|([8][0-9]{1})|([7][0-9]{1}))[0-9]{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(mobiles);
		return m.find();// boolean
	}

	/**
	 * �õ�ͼƬ����
	 * 
	 * @param url
	 * @return
	 */
	public static String getIconName(String url) {
		String str = url.toString();
		int index = str.lastIndexOf("/") + 1;
		String iconName = str.substring(index);
		return iconName;
	}

	/**
	 * �õ�ͼƬ��·���������ļ�����
	 * 
	 * @param url
	 * @return
	 */
	public static String getIconUrl(String url) {
		String str = url.toString();
		int end = str.lastIndexOf("/");
		String uri = str.substring(0, end);
		return uri;
	}

	/**
	 * ���ת��Ϊȫ�ǵķ���
	 * 
	 * @param input
	 * @return
	 */
	public static String ToSBC(String input) {
		// ���תȫ�ǣ�
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127 && c[i] > 32)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}

	/**
	 * ȫ��ת��Ϊ��ǵķ���
	 * 
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (isChinese(c[i])) {
				if (c[i] == 12288) {
					c[i] = (char) 32;
					continue;
				}
				if (c[i] > 65280 && c[i] < 65375)
					c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);
	}

	/**
	 * ���ñ���ķ�ʽ�ж��ַ��Ƿ�Ϊ���ֵķ���
	 * 
	 * @param c
	 * @return
	 */
	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

}

package com.jcode.utils;


public abstract class BytesUtils
{
	private static final char[] DIGITS_HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };


    public static int checksum(byte[] buff, int offset, int size)
    {
        int sum = buff[offset];

        for (int i = offset + 1, n = 1; n < size; i++, n++) {
            sum ^= buff[i];
        }

        return sum & 0xff;
    }
    


    public static long toInt64(byte[] bytes)
    {    	
    	 int ch1 = bytes[0] & 0xff;
         int ch2 = bytes[1] & 0xff;
         int ch3 = bytes[2] & 0xff;
         int ch4 = bytes[3] & 0xff;
    	 int ch5 = bytes[4] & 0xff;
         int ch6 = bytes[5] & 0xff;
         int ch7 = bytes[6] & 0xff;
         int ch8 = bytes[7] & 0xff;
         long ival =  ((ch1 << 56)|(ch2 << 48)|(ch3 << 40)|(ch4 << 32)|(ch5 << 24) | (ch6 << 16) | (ch7 << 8) | (ch8 << 0));
         return ival;

    }
    

    public static int toInt32(byte[] bytes)
    {
        return toInt32(bytes, 0);
    }

    public static int toInt32(byte[] bytes, int offset)
    {    	
    	 int ch1 = bytes[0] & 0xff;
         int ch2 = bytes[1] & 0xff;
         int ch3 = bytes[2] & 0xff;
         int ch4 = bytes[3] & 0xff;
         int ival =  ((ch1 << 24) | (ch2 << 16) | (ch3 << 8) | (ch4 << 0));
         return ival;

    }

    public static short toInt16(byte[] bytes)
    {
    	 int ch1 = bytes[0] & 0xff;
         int ch2 = bytes[1] & 0xff;
         return (short) ((ch1 << 8) | (ch2 << 0));
    }
    
	protected static char[] encodeHex(byte[] data) {
		int l = data.length;
		char[] out = new char[l << 1];
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS_HEX[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS_HEX[0x0F & data[i]];
		}
		return out;
	}

	protected static byte[] decodeHex(char[] data) {
		int len = data.length;
		if ((len & 0x01) != 0) {
			throw new RuntimeException("字符个数应该为偶数");
		}
		byte[] out = new byte[len >> 1];
		for (int i = 0, j = 0; j < len; i++) {
			int f = toDigit(data[j], j) << 4;
			j++;
			f |= toDigit(data[j], j);
			j++;
			out[i] = (byte) (f & 0xFF);
		}
		return out;
	}

	protected static int toDigit(char ch, int index) {
		int digit = Character.digit(ch, 16);
		if (digit == -1) {
			throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
		}
		return digit;
	}

	/**
	 * 十六进制字节数组转字符串
	 * @param bs
	 * @return
	 */
	public static String toHexString(byte[] bs) {
		return new String(encodeHex(bs));
	}

	/**
	 * 字节数组转十六进制字符串
	 * @param bs
	 * @return
	 */
	public static byte[] hexString2Bytes(String hex) {
		return decodeHex(hex.toCharArray());
	}

	public static byte[] chars2Bytes(char[] bs) {
		return decodeHex(bs);
	}
}

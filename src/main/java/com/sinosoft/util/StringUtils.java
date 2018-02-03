package com.sinosoft.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StringUtils {

    private static final String hexString = "0123456789ABCDEF";
    private static final NumberFormat cardformat = new DecimalFormat("00000000");
    private static final NumberFormat cardformat1 = new DecimalFormat("00");
    private static final Random cardrandom = new Random();

    /**
     * Creates a new instance of StringUtils
     */
    private StringUtils() {
    }

    /**
     *
     * @return
     */
    public static String getCardNumber() {
        return cardformat.format(Math.abs(System.currentTimeMillis()) % 1000000L)
                + cardformat.format(Math.abs(cardrandom.nextLong()) % 1000000L);
    }

    /**
     *
     * @param sValue
     * @return
     */
    public static String encodeXML(String sValue) {

        if (sValue == null) {
            return null;
        } else {
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < sValue.length(); i++) {
                char charToCompare = sValue.charAt(i);
                if (charToCompare == '&') {
                    buffer.append("&amp;");
                } else if (charToCompare == '<') {
                    buffer.append("&lt;");
                } else if (charToCompare == '>') {
                    buffer.append("&gt;");
                } else if (charToCompare == '\"') {
                    buffer.append("&quot;");
                } else if (charToCompare == '\'') {
                    buffer.append("&apos;");
                } else {
                    buffer.append(charToCompare);
                }
            }
            return buffer.toString();
        }
    }

    /**
     *
     * @param binput
     * @return
     */
    public static String byte2hex(byte[] binput) {

        StringBuilder sb = new StringBuilder(binput.length * 2);
        for (int i = 0; i < binput.length; i++) {
            int high = ((binput[i] & 0xF0) >> 4);
            int low = (binput[i] & 0x0F);
            sb.append(hexString.charAt(high));
            sb.append(hexString.charAt(low));
        }
        return sb.toString();
    }

    public static String string2Hex(String str) {
        return byte2hex(str.getBytes());
    }
//    public static String encode(String str) {
////根据默认编码获取字节数组 
//        byte[] bytes = str.getBytes();
//        StringBuilder sb = new StringBuilder(bytes.length * 2);
////将字节数组中每个字节拆解成2位16进制整数 
//        for (int i = 0; i < bytes.length; i++) {
//            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
//            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
//        }
//        return sb.toString();
//    }

    public static byte[] subByte(byte[] bparams, int s, int len) {
        byte[] sub = new byte[len];
        for (int i = 0; i < len; i++) {
            sub[i] = bparams[s + i];
        }
        return sub;
    }

    public static int byte2Int(byte[] bparams, int s, int len) {
        int num = 0;
        try {
            for (int i = 0; i < len; i++) {
                num = (num << 8) + byte2Int(bparams[s + i]);
            }
        } catch (Exception ex) {
        }
        return num;
    }

    public static int byte2Int(byte paramb) {
        if (paramb >= 0) {
            return paramb;
        }
        return paramb + 256;
    }

    public static byte byteXOR(byte[] sinput) {
        byte bxor = sinput[0];
        for (byte b : sinput) {
            bxor = (byte) (bxor ^ b);
        }
        return bxor;
    }

    public static byte[] intToByte2(int i) {
        byte[] targets = new byte[2];
        targets[1] = (byte) (i & 0xFF);
        targets[0] = (byte) (i >> 8 & 0xFF);
        return targets;
    }

    public static byte[] intToByte(int i) {
        byte[] targets = new byte[1];
        targets[0] = (byte) (i & 0xFF);
        return targets;
    }

    /**
     *
     * @param sinput
     * @return
     */
    public static byte[] hex2byte(String sinput) {
        int length = sinput.length();

        if ((length & 0x01) != 0) {
            throw new IllegalArgumentException("odd number of characters.");
        }

        byte[] out = new byte[length >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < length; i++) {
            int f = Character.digit(sinput.charAt(j++), 16) << 4;
            f = f | Character.digit(sinput.charAt(j++), 16);
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }

    /**
     *
     * @param resource
     * @return
     * @throws IOException
     */
    public static String readResource(String resource) throws IOException {

        InputStream in = StringUtils.class.getResourceAsStream(resource);
        if (in == null) {
            throw new FileNotFoundException(resource);
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        byte[] data = out.toByteArray();

        return new String(data, "UTF-8");
    }

    /**
     *
     * @param sCardNumber
     * @return
     */
    public static boolean isNumber(String sCardNumber) {

        if ((sCardNumber == null) || (sCardNumber.equals(""))) {
            return false;
        }

        for (int i = 0; i < sCardNumber.length(); i++) {
            char c = sCardNumber.charAt(i);
            if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9') {
                return false;
            }
        }

        return true;
    }

    public static byte[] concatAll(byte[] first, byte[]... rest) {
        int totalLength = first.length;
        for (byte[] array : rest) {
            totalLength += array.length;
        }
        byte[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (byte[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    public static String hex2String(String bytes) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        for (int i = 0; i < bytes.length(); i += 2) {
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
                    .indexOf(bytes.charAt(i + 1))));
        }
        String s;
        try {
            s = new String(baos.toByteArray(), "GBK");
        } catch (UnsupportedEncodingException ex) {
            s = "";
//             Logger.getLogger(StringUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public static String double2String(double d) {
        return cardformat.format(d * 100).substring(0, 8);
    }

    public static String double2String3(double d) {
        return cardformat.format(d * 1000).substring(0, 8);
    }

    public static String double2String2(double d) {
        return cardformat1.format(d).substring(0, 2);
    }

    public static String fixString(String paramString, int paramInt) {
        if (paramString == null) {
            paramString = "";
        } 
        if (paramString.length() > paramInt) {
            return paramString.substring(0, paramInt + 1);
        }
        return  String.format("%-" + paramInt + "s", paramString); 
       
    }
    
    public static String fixString2(String paramString, int paramInt) {
        if (paramString == null) {
            paramString = "";
        }
        String paramString1;
        try {
            byte[] buff = paramString.getBytes("GBK");
            paramString1 = new String(buff, "ISO-8859-1");
        } catch (UnsupportedEncodingException ex) {
            paramString1 = paramString;
            // Logger.getLogger(StringUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (paramString1.length() > paramInt) {
            return paramString1.substring(0, paramInt + 1);
        }
        String parString = String.format("%-" + paramInt + "s", paramString1);
        try {
            String reString = new String(parString.getBytes("ISO-8859-1"), "GBK");
            return reString;
        } catch (UnsupportedEncodingException ex) {
            return String.format("%-" + paramInt + "s", "");
            // Logger.getLogger(StringUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static String fixDecimal3(double para) {
        BigDecimal paramBigDecimal = new BigDecimal(para);
        DecimalFormat localDecimalFormat = new DecimalFormat("000");
        return localDecimalFormat.format(paramBigDecimal);
    }
}

package com.hhm.bussplat.util.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author q
 * @time 2020/5/20 5:39 下午
 */
public class DigestUtil {
    public static final String ENCODE = "UTF-8";
    private Key key;

    private DigestUtil() {
    }

    public static byte[] getMd5Bytes(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte[] b = md.digest();
            return b;
        } catch (NoSuchAlgorithmException var3) {
            throw new RuntimeException("没有MD5这个加密算法");
        }
    }

    public static String getMd5Str(String source) {
        byte[] b = getMd5Bytes(source);
        StringBuffer buf = new StringBuffer("");

        for(int offset = 0; offset < b.length; ++offset) {
            int i = b[offset];
            if (i < 0) {
                i += 256;
            }

            if (i < 16) {
                buf.append("0");
            }

            buf.append(Integer.toHexString(i));
        }

        return buf.toString();
    }

    public static final String md5(String res) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] e = res.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(e);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int dd = 0; dd < j; ++dd) {
                byte byte0 = md[dd];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            String var11 = new String(str);
            return var11;
        } catch (Exception var10) {
            return null;
        }
    }

    public static final String md5UpperCase(String res) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] e = res.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(e);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int dd = 0; dd < j; ++dd) {
                byte byte0 = md[dd];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            String var11 = new String(str);
            return var11;
        } catch (Exception var10) {
            return null;
        }
    }

    public void getKey(String strKey) {
        try {
            KeyGenerator e = KeyGenerator.getInstance("DES");
            e.init(new SecureRandom(strKey.getBytes()));
            this.key = e.generateKey();
            e = null;
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public String getEncString(String strMing) {
        Object byteMi = null;
        Object byteMing = null;
        String strMi = "";
        BASE64Encoder base64en = new BASE64Encoder();

        try {
            byte[] byteMing1 = strMing.getBytes("UTF-8");
            byte[] byteMi1 = this.getEncCode(byteMing1);
            strMi = base64en.encode(byteMi1);
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            base64en = null;
            byteMing = null;
            byteMi = null;
        }

        return strMi;
    }

    public String getDesString(String strMi) {
        BASE64Decoder base64De = new BASE64Decoder();
        Object byteMing = null;
        Object byteMi = null;
        String strMing = "";

        try {
            byte[] byteMi1 = base64De.decodeBuffer(strMi);
            byte[] byteMing1 = this.getDesCode(byteMi1);
            strMing = new String(byteMing1, "UTF-8");
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            base64De = null;
            byteMing = null;
            byteMi = null;
        }

        return strMing;
    }

    private byte[] getEncCode(byte[] byteS) {
        byte[] byteFina = null;

        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(1, this.key);
            byteFina = cipher.doFinal(byteS);
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            cipher = null;
        }

        return byteFina;
    }

    private byte[] getDesCode(byte[] byteD) {
        byte[] byteFina = null;

        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(2, this.key);
            byteFina = cipher.doFinal(byteD);
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            cipher = null;
        }

        return byteFina;
    }

    public static byte[] getSha1Bytes(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("sha-1");
            md.update(source.getBytes());
            byte[] b = md.digest();
            return b;
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static String getSha1Str(String source) {
        byte[] b = getSha1Bytes(source);
        String result = "";

        for(int i = 0; i < b.length; ++i) {
            result = result + Integer.toString((b[i] & 255) + 256, 16).substring(1);
        }

        return result;
    }

    public static void main(String[] args) {
        DigestUtil des = new DigestUtil();
        des.getKey("1qazXsw2");
        String strEnc = des.getEncString("中文加密,有没有问题");
        System.out.println(strEnc);
        String strDes = des.getDesString(strEnc);
        System.out.println(strDes);
        System.out.println(md5("1234qwer"));
    }
}

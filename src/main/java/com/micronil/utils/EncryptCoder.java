package com.micronil.utils;

/**
 * Created by apoptoxin on 2018/3/29.
 */


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES Coder<br/>
 * secret key length:   128bit, default:    128 bit<br/>
 * mode:    ECB/CBC/PCBC/CTR/CTS/CFB/CFB8 to CFB128/OFB/OBF8 to OFB128<br/>
 * padding: Nopadding/PKCS5Padding/ISO10126Padding/
 * @author Aub
 *
 */
public class EncryptCoder {

    private static final String encryptKey = "1234567890";

    public static String md5Encrypt(String content) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 计算md5函数
        md.update(content.getBytes());
        try {
            return byteArr2HexStr(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String aesEncrypt(String content) throws Exception {
        return aesEncrypt(content, encryptKey);
    }

    public static String aesDecrypt(String encrypt) throws Exception {
        return aesDecrypt(hexStr2ByteArr(encrypt), encryptKey);
    }

    private static String aesEncrypt(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(encryptKey.getBytes());
        kgen.init(128, random);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));

        return byteArr2HexStr(cipher.doFinal(content.getBytes("utf-8")));
    }

    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    private static String aesDecrypt(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(decryptKey.getBytes());
        kgen.init(128, random);


        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes,"utf-8");
    }

    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
     * hexStr2ByteArr(String strIn) 互为可逆的转换过程

     *
     * @param arrB
     *            需要转换的byte数组

     * @return 转换后的字符串
     * @throws Exception
     *             本方法不处理任何异常，所有异常全部抛出
     */
    private static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍

        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
     * 互为可逆的转换过程

     *
     * @param strIn
     *            需要转换的字符串
     * @return 转换后的byte数组

     * @throws Exception
     *             本方法不处理任何异常，所有异常全部抛出
     */
    private static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }


    //随机生成密钥
    private static String getKey() throws Exception{
        //       DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 为我们选择的DES算法生成一个KeyGenerator对象
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(sr);
        // 生成密匙
        SecretKey key = kg.generateKey();
        // 获取密匙数据
        byte rawKeyData[] = key.getEncoded();

        //return new String(rawKeyData);

        return byteArr2HexStr(rawKeyData);
    }

    public static void main(String[] args) throws Exception {
        // TODO 自动生成的方法存根
        String content = "我爱你";
        System.out.println("加密前：" + content);

        String key = encryptKey; // key=getKey();
        System.out.println("加密密钥和解密密钥：" + key);

        String encrypt = aesEncrypt(content);
        System.out.println("加密后：" + encrypt);

        String decrypt = aesDecrypt(encrypt);
        System.out.println("解密后：" + decrypt);
    }
}
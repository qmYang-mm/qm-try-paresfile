package com.quanmin.paresfile.common;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.quanmin.paresfile.util.JacksonUtils;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;

/**
 * 微信小程序
 * 数据解密算法
 *
 * @author zhonghongqiang
 */

public class WXBizDataCrypt {

    private String appId;

    private String sessionKey;

    public WXBizDataCrypt(String appId, String sessionKey) {
        this.appId = appId;
        this.sessionKey = sessionKey;
    }

    /**
     * 1.对称解密使用的算法为 WXBizDataCrypt-128-CBC，数据采用PKCS#7填充。
     * 2.对称解密的目标密文为 Base64_Decode(encryptedData)。
     * 3.对称解密秘钥 aeskey = Base64_Decode(session_key), aeskey 是16字节。
     * 4.对称解密算法初始向量 为Base64_Decode(iv)，其中iv由数据接口返回。
     */
    public ObjectNode decrypt(String encryptedData, String iv) throws Exception {

        /*
         * 小程序加密数据解密算法
         * https://developers.weixin.qq.com/miniprogram/dev/api/signature.html#wxchecksessionobject
         * 1.对称解密的目标密文为 Base64_Decode(encryptedData)。
         * 2.对称解密秘钥 aeskey = Base64_Decode(session_key), aeskey 是16字节。
         * 3.对称解密算法初始向量 为Base64_Decode(iv)，其中iv由数据接口返回。
         */
        Decoder decoder = Base64.getDecoder();
        byte[] encryptedByte = decoder.decode(encryptedData);
        byte[] sessionKeyByte = decoder.decode(this.sessionKey);
        byte[] ivByte =decoder.decode(iv);
        /*
         * 以下为AES-128-CBC解密算法
         */
        SecretKeySpec skeySpec = new SecretKeySpec(sessionKeyByte, "AES");
        System.out.println(skeySpec);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivByte);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] original = cipher.doFinal(encryptedByte);

        String jsonStr = new String(original);
        return JacksonUtils.jsonToBean(jsonStr, ObjectNode.class);
    }
}
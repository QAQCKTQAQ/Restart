package com.fhzn.demo.util;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class RSAUtils {

    // 后端使用的私钥（需要与前端公钥配对）
    private static final String PRIVATE_KEY_PEM =
        "-----BEGIN PRIVATE KEY-----\n" +
            "MIIBVwIBADANBgkqhkiG9w0BAQEFAASCAUEwggE9AgEAAkEAwI/OiI/2l4LUWkzE\n" +
            "Sm+Jtq33zRfnMcyxPmRA/xWBcfPleoakV6LXIM9tj5humSwzgh7LLWBVOT/5CpoC\n" +
            "ZlnX5QIDAQABAkEApDU+C8iwoW2IxuR184AAMQ9TpMsAdcA3WZxGrQBa3Z9/psdQ\n" +
            "QgSba/CEGs3y7cBmAAyHP7M54ZSIqK6gJSyo4QIhAP912YAfAadHAj6vHQhq6I33\n" +
            "pDts/3vm71wK50Gz5MNtAiEAwPfxNclcdN2B7n+n0bMaK3fzvLy5sDmCN13Q5h9C\n" +
            "I1kCIQD2Cvq2VKbSBylZWG29GsLrYXWzqrKTEoS9gN7lQC94LQIhALCA2a/x+9Cl\n" +
            "X5hwSWdE5FYIRPhIw6wOHi5YcKBczRkZAiEA8aYfZf/GxhcvZYQL4qPd57bQiflf\n" +
            "/OQyGGCLhCV5bgc=\n" +
            "-----END PRIVATE KEY-----";

    /**
     * 解密 RSA 加密的数据
     *
     * @param encryptedData 需要解密的数据，Base64 编码
     * @return 解密后的字符串
     * @throws Exception 可能的解密异常
     */
    public static String decrypt(String encryptedData) throws Exception {
        // 去除密钥头尾标记并进行 Base64 解码
        String privateKeyPEM = PRIVATE_KEY_PEM
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replaceAll("\\s", "");
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyPEM);

        // 生成私钥
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        // 解密数据
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes, "UTF-8");
    }
}

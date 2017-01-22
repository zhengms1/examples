package com.pahlsoft.examples.encryption;

import java.util.Arrays;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
 
public class MakeHash {
    private static final byte[] keyBytes =
    {
        (byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef
    };
    private static final IvParameterSpec ips = new IvParameterSpec(new byte[8]);
 
    public static void main(String[] args) throws Exception
    {
        System.out.println(MakeHash.generateHash("j004063", "dudes"));
    }
 
    public static String generateHash(String user, String password) throws Exception
    {
        // instructions see http://www.sans.org/reading_room/special/?id=oracle_pass&ref=911
        // 1. Concatenate the username and the password to produce a plaintext string;
        // 2. Convert the plaintext string to uppercase characters;
        // 3. Convert the plaintext string to multi-byte storage format; ASCII characters have the high byte set to 0x00;
        byte[] input = (user + password).toUpperCase().getBytes("utf-16be");
 
        //4.Encrypt the plaintext string (padded with 0s if necessary to the next even block length)
        //using the DES algorithm in cipher block chaining (CBC) mode with a fixed key value of
        //0x0123456789ABCDEF;
        input = Arrays.copyOf(input, ((input.length + 7) / 8) * 8); // Pad with zeros
     
        Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKey key = new SecretKeySpec(keyBytes, "DES");
        cipher.init(Cipher.ENCRYPT_MODE, key, ips);
        byte[] encryptedBytes = cipher.doFinal(input);
 
        //5.Encrypt the plaintext string again with DES-CBC, but using the last block of the output
        //of the previous step (ignoring parity bits) as the encryption key.
        // Don't need to set parity - done behind the scenes by the JCE
        Key encryptedKey = new SecretKeySpec(encryptedBytes, encryptedBytes.length - 8, 8, "DES");
        cipher.init(Cipher.ENCRYPT_MODE, encryptedKey, ips);
        byte[] encryptedPw = cipher.doFinal(input);
 
        //The last block of the output is converted into a printable string to produce the password hash value.
        return bytesToHexString(encryptedPw, encryptedPw.length - 8, 8);
    }
 
    private static String bytesToHexString(byte[] bytes, int offset, int length)
    {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++)
        {
            sb.append(String.format("%02X", bytes[offset++]));
        }
        return sb.toString();
    }
}
 

package com.pahlsoft.examples.encryption.signfactory;

import java.io.FileInputStream;
import java.security.cert.Certificate;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

public class ICEKeyStore {

	public KeyPair getKeyPair() throws Exception {
	
	KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());

	String s_passwd = new String("czfqnr20");
	char[] password = s_passwd.toCharArray();
	
    FileInputStream fis = null;
    
    fis = new java.io.FileInputStream("C:\\Documents and Settings\\J004063\\.keystore");
    ks.load(fis, password);
    Certificate cert = ks.getCertificate("iceKey");
    Key key = ks.getKey("iceKey", password);

   PublicKey publicKey = cert.getPublicKey();
        
        if (fis != null) {
            fis.close();
        }
        
        
    return new KeyPair(publicKey, (PrivateKey)key);
    }
    
  }
	
  


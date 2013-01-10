package com.pahlsoft.aws;

import org.apache.axis.encoding.Base64;


public class Encoder {
	/**
	* Performs base64-encoding of input bytes.
	*
	* @param rawData * Array of bytes to be encoded.
	* @return * The base64 encoded string representation of rawData.
	*/
	public static String EncodeBase64(byte[] rawData) {
		return Base64.encode(rawData);
	}
}

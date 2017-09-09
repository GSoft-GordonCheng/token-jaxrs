package gordon.tokens.core;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import gordon.tokens.crypto.DESCipher;
import gordon.tokens.crypto.DESSecretKey;

/**
* @author Gordon
*/
public class XToken extends Token {

	public XToken(String owner) {
		super(owner);
	}
	@Override
	public String createToken() throws NoSuchAlgorithmException {
		try {
			String owner = super.getOwner();
			SecretKey key = super.getKey();
			return encode(owner, key);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoSuchAlgorithmException(e.getMessage());
		}
	}
	@Override
	public void getKeyValue() {
		// TODO Auto-generated method stub
		
	}
	private String encode(String encryptContext, SecretKey secretKey) throws Exception {
		// 1. 加密器(返回密文)
		Cipher cipher = DESCipher.getInstance(Cipher.ENCRYPT_MODE, secretKey);
		byte[] context = cipher.doFinal(encryptContext.getBytes());  
		return Base64.getEncoder().encodeToString(context);  
	}
	public static String decode(String encryptString, String secretKey) throws Exception {
		// 1. rebuild key
		SecretKey key = DESSecretKey.rebuildKey(secretKey);
		// 2. decode the base64 string
        byte[] cipherText = Base64.getDecoder().decode(encryptString);
		// 3. 解密器(返回明文)
		Cipher cipher = DESCipher.getInstance(Cipher.DECRYPT_MODE, key);
        byte[] bys = cipher.doFinal(cipherText);  
        return new String(bys);
	}
	
}

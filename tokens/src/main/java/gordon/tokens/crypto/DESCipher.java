package gordon.tokens.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
* @author Gordon
*/
public class DESCipher {
	public static Cipher getInstance(int encryptMode, SecretKey secretKey) throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(encryptMode, secretKey, new IvParameterSpec(new byte[8]));
		return cipher; 
	}
}

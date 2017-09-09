package gordon.tokens.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
* @author Gordon
*/
public class DESSecretKey {
	public static SecretKey generateKey() throws NoSuchAlgorithmException 
	{
		return generateKey("Gordon.C.Cheng");
	}
	public static SecretKey generateKey(String encryptKey) throws NoSuchAlgorithmException 
	{
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		keyGen.init(new SecureRandom((encryptKey).getBytes()));
		return keyGen.generateKey();
		//return Base64.getEncoder().encodeToString(secretKey.getEncoded());
	}
	public static SecretKey rebuildKey(String base64Key) {
		byte[] decodedKey = Base64.getDecoder().decode(base64Key);
		return new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
	}	
}

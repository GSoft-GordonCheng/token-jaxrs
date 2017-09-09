package gordon.tokens.core;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import gordon.tokens.WebApplication;

/**
* @author Gordon
*/
public abstract class Token {
	static final String ISSUER = "Gordon.C.Cheng";
	private String token;
	private String owner;
	private SecretKey key;
	public Token(String owner) {
		this.owner = owner;
	}
	public String getOwner() {
		return this.owner;
	}
	public String generate() throws NoSuchAlgorithmException {
		// Step1. 動態建立一把私鑰 (必須集中存放否則無法反解)
		this.key = generateKey();
		// Step2. 產生令牌(由實體物件建立)
		this.token = createToken();
		return this.token;
	}
	public SecretKey getKey() throws NoSuchAlgorithmException {
		if (null == this.key) {
			throw new NoSuchAlgorithmException();
		}
		return this.key;
	}
	public String getKeyString() throws NoSuchAlgorithmException {
		// 把私鑰轉成Base64字串格式,可用來儲存
		return Base64.getEncoder().encodeToString(getKey().getEncoded());
	}
	public SecretKey generateKey() throws NoSuchAlgorithmException 
	{
		byte[] key = (this.getClass().getName() + ISSUER).getBytes();
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		keyGen.init(new SecureRandom(key));
		return keyGen.generateKey();
	}
	public static String findByToken(String token) {
		return WebApplication.jedis.get(token);
	}
	public static void deleteByToken(String token) {
		WebApplication.jedis.del(token);
	}
	public abstract String createToken() throws NoSuchAlgorithmException;
	public abstract void getKeyValue();
	public void save(String key, String value) {
		int seconds = 24 * 60 * 60;
		WebApplication.jedis.setex(key, seconds, value);
	}
}

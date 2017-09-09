package gordon.tokens.core;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
* @author Gordon
*/
public class JWTToken extends Token {
	public JWTToken(String owner) {
		super(owner);
	}
	@Override
	public String createToken() throws NoSuchAlgorithmException {
		String owner = super.getOwner();
		SecretKey key = super.getKey();
		return Jwts.builder()
                .setSubject(owner)
                .setIssuer(ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusYears(1)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
	}
	private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
	@Override
	public void getKeyValue() {
		// TODO Auto-generated method stub
		
	}
}

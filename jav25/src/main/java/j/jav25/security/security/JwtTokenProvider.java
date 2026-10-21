package j.jav25.security.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-milliseconds}")
    private long jwtExpirationMilliseconds;

    // Create generate token utility method
    public String generateToken(Authentication authentication){
    	
    	String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(System.currentTimeMillis() + jwtExpirationMilliseconds);
        
    	return Jwts.builder()
                .subject(username)    //set username
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith(key())         //sign with decoder secret key
                .compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // Get username from token
    public String getUsernameFromToken(String token){

        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // validate token
    public boolean validateToken(String token){
        Jwts.parser()
            .verifyWith((SecretKey) key())
            .build()
            .parse(token);  //parse will throw exception if token is invalid
        
        return true;
    }
}

package net.engineeringdigest.journalApp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import net.engineeringdigest.journalApp.entity.UserEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthUtil {

    @Value("${jwt.secret}")
    private String jwtToken;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtToken.getBytes(StandardCharsets.UTF_8));
    }
    public String generateToken(UserEntry userEntry){
      return Jwts.builder()
              .setSubject(userEntry.getUsername())
              .claim("userId",userEntry.getId().toString())
              .setIssuedAt(new Date())
              .setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
              .signWith(getSecretKey())
              .compact();
    }

}

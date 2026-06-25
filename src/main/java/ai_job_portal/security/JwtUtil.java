package ai_job_portal.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {


    private final String SECRET_KEY =
            "mySecretKeyForAiJobPortal2026SecureKey";


    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis()
                                + 1000 * 60 * 60 * 24)
                )
                .signWith(
                        Keys.hmacShaKeyFor(
                                SECRET_KEY.getBytes())
                )
                .compact();
    }



    // Token se email nikalna
    public String extractEmail(String token) {

        return Jwts.parser()
                .verifyWith(
                        Keys.hmacShaKeyFor(
                                SECRET_KEY.getBytes())
                )
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }



    // Token valid hai ya nahi
    public boolean validateToken(String token) {

        try {

            Jwts.parser()
                    .verifyWith(
                            Keys.hmacShaKeyFor(
                                    SECRET_KEY.getBytes())
                    )
                    .build()
                    .parseSignedClaims(token);
            System.out.println("TOKEN VALID");

            return true;


        } catch(Exception e){

            return false;
        }
    }
}
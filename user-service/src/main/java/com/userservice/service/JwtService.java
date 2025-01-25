package com.userservice.service;

import com.userservice.dto.UserLoginRequestDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String secret = "secretadjkfhdufahiefhjsadjkfhadjkfhsecretsdffsdf";
    private static final SecretKey secretKey = createSecretKey(secret);
//    private static final Logger log = LoggerFactory.getLogger(JwtService.class);

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        try {
            return Jwts
                    .parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
//            log.error("Invalid Key.");
            return null;
        }
    }

    public static SecretKey createSecretKey(String keyString) {
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    }

    public static boolean validateToken(String token, String username) {
        final String extractedUsernameFromToken = extractUsername(token);
        return extractedUsernameFromToken.equals(username) && !isTokenExpired(token);
    }

    private static boolean isTokenExpired(String token) {
        return etractExpiration(token).before(new Date());
    }

    private static Date etractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(UserLoginRequestDTO user) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .and()
                .signWith(secretKey)
                .compact();
    }
}

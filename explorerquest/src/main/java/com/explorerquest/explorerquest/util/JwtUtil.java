package com.explorerquest.explorerquest.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.logging.Logger;
import java.util.Base64;

@Component
public class JwtUtil {

    private static final Logger logger = Logger.getLogger(JwtUtil.class.getName());

    private SecretKey secretKey;

    @Value("${jwt.expiration.ms}")
    private long jwtExpirationMs;

    @Value("${jwt.secret}")
    public void setJwtSecret(String jwtSecret) {
        // Decodifica la chiave Base64 per ottenere la chiave valida
        byte[] decodedKey = Base64.getDecoder().decode(jwtSecret);
        this.secretKey = Keys.hmacShaKeyFor(decodedKey);
        logger.info("JWT Util initialized with secret: [PROTECTED]");
        logger.info("JWT Expiration (ms): " + jwtExpirationMs);
    }

    public String generateToken(String username) {
        try {
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                    .signWith(secretKey, SignatureAlgorithm.HS512)
                    .compact();
        } catch (JwtException e) {
            logger.severe("Error generating JWT token: " + e.getMessage());
            throw e;
        }
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            logger.severe("Error extracting username from token: " + e.getMessage());
            throw e;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.warning("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.warning("JWT token is unsupported: " + e.getMessage());
        } catch (MalformedJwtException e) {
            logger.warning("JWT token is malformed: " + e.getMessage());
        } catch (SignatureException e) {
            logger.warning("JWT token signature validation failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.warning("JWT token is empty or null: " + e.getMessage());
        }
        return false;
    }
}

package com.mgnt.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Base64;

import javax.crypto.SecretKey;

@Service
public class TokenService {
  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private long expiration;

  public String generateToken(Long userId) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + expiration);
    SecretKey secretKey = generalKey();

    return Jwts.builder()
    .setSubject(Long.toString(userId))
    .setIssuedAt(now)
    .setExpiration(expiryDate)
    .signWith(secretKey)
    .compact();
  }

  public Long getUserIdFromToken(String token) {
    Claims claims = Jwts.parserBuilder()
    .setSigningKey(secret)
    .build()
    .parseClaimsJws(token)
    .getBody();
    return Long.parseLong(claims.getSubject());
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
      .setSigningKey(secret)
      .build()
      .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private SecretKey generalKey(){
    byte[] encodeKey = Base64.getDecoder().decode(secret);
    return Keys.hmacShaKeyFor(encodeKey);
  }
}

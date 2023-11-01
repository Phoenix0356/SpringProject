package com.demo.Util;

import com.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private  static  final String CLAIM_KEY_CREATED = "createdTime";

    @Value("${jwt.expiration}")
    private long  expiration;
    @Value ("${jwt.secret}")
    private String secret;
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
//    private SecretKey getSecretKey(){
//        return Jwts.SIG.HS256.key().build();
//    }


    private String createToken(HashMap<String,Object> claims){
        return Jwts.builder()
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }
    public String createToken(String userId){
        HashMap<String,Object> claims=new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userId);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return createToken(claims);
    }
    private Claims getClaimsFromToken(String token) {
        Claims claims=null;
        try {
            claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        }catch (JwtException ex){
            ex.printStackTrace();
        }
        return claims;
    }

    public Integer getUserIdFromToken(String tokenWithBearer){
        String token = tokenWithBearer.replace("Bearer ", "");
        String userId;
        try {
            Claims claims = getClaimsFromToken(token);
            userId = claims.getSubject();
        }catch (Exception e){
            userId = null;
        }
        return Integer.parseInt(userId);
    }

}

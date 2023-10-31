package com.demo.Util;

import com.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import static java.security.KeyRep.Type.SECRET;
@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private  static  final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.expiration}")
    private long  expiration;
    @Value ("${jwt.secret}")
    private String secret;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(User user){
        HashMap<String,Object> claims=new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,user.getUserName());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return createToken(claims);
    }
    private String createToken(HashMap<String,Object> claims){
        return Jwts.builder()
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }


}

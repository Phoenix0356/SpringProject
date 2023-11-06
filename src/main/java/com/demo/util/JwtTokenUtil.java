package com.demo.util;

import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenUtil {
    @Autowired
    UserMapper userMapper;

    @Value("${jwt.expiration}")
    private long expiration;
    @Value ("${jwt.secret}")
    private String secret;


    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    private String createToken(HashMap<String,Object> claims){
        return Jwts.builder()
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + expiration*1000))
                .signWith(getSigningKey())
                .compact();
    }
    public String createToken(User user){
        HashMap<String,Object> claims=new HashMap<>();
        claims.put("sub",String.valueOf(user.getUserId()));
        claims.put("iss",user.getAccount());
        claims.put("exp",new Date());
        return createToken(claims);
    }
    public Claims validateToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }catch (JwtException ex){
            return null;
        }
        return claims;
    }


    public Integer getUserIdFromToken(String token){
        String userId;
        Claims claims = validateToken(token);
        if (claims==null) return null;
        userId = claims.get("sub").toString();
        return Integer.parseInt(userId);
    }


}

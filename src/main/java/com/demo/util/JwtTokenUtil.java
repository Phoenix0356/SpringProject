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
    private static final String CLAIM_KEY_USERId = "userId";
    private static final String CLAIM_KEY_USERNAME = "account";
    private  static  final String CLAIM_KEY_CREATED = "createdTime";
    private static UserDetailsService userDetailsService;

    @Value("${jwt.expiration}")
    private static long expiration;
    @Value ("${jwt.secret}")
    private static String secret;


    private static SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    private static String createToken(HashMap<String,Object> claims){
        return Jwts.builder()
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + expiration*1000))
                .signWith(getSigningKey())
                .compact();
    }
    public static String createToken(User user){
        HashMap<String,Object> claims=new HashMap<>();
        claims.put(CLAIM_KEY_USERId,String.valueOf(user.getUserId()));
        claims.put(CLAIM_KEY_USERNAME,user.getAccount());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return createToken(claims);
    }
    public static Claims getClaimsFromToken(String token) {
        Claims claims=null;
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


    public static Integer getUserIdFromToken(String token){
        String userId;
        Claims claims = getClaimsFromToken(token);
        if (claims==null) return null;
        userId = claims.get("userId").toString();
        return Integer.parseInt(userId);
    }

   public boolean isValidateToken(String token){
        Claims claims = getClaimsFromToken(token);
        String account = userMapper.selectById(getUserIdFromToken(token)).getAccount();
        if (claims==null||account==null) return false;
        String accountOfToken = claims.get("account").toString();
        return account.equals(accountOfToken);
    }
}

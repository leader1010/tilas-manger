package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "itheima";
    private static long expire = 43200000L;


    public static String generateJwt(Map<String, Object> claims) {
        String compact = Jwts.builder().addClaims(claims).signWith(SignatureAlgorithm.HS256, signKey).
                setExpiration(new Date(System.currentTimeMillis() + expire)).compact();
        return compact;
    }

    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser().setSigningKey(signKey).parseClaimsJws(jwt).getBody();
        return claims;
    }


}

package com.example.numble_insta.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean { //빈 초기화
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";
    private final String secret;
    private Key key;

    private long tokenValidityInMillIseconds;


    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}")long tokenValidityInMillIseconds
            ) {
        this.secret = secret;
        this.tokenValidityInMillIseconds = tokenValidityInMillIseconds;
    }

    //빈이 생성되고 주입을 받은 후에 시크릿 값을 base64 decode해서 key 할당
    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Authentication authentication) {
        String authorites = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMillIseconds);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorites) //정보 저장
                .signWith(key, SignatureAlgorithm.HS512)  //사용할 암호화 알고리즘
                .setExpiration(validity)  //만료를 위해서
                .compact();
    }

    public Authentication getAuthentication(String token) {

        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User user = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(user, token, authorities);
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch (SecurityException | MalformedJwtException e){
            logger.info("잘못된 JWT 서명입니다.");
        }
        catch (ExpiredJwtException e){
            logger.info("만료된 JWT 서명입니다.");
        }
        catch (UnsupportedJwtException e){
            logger.info("지원되지 않는 JWT 서명입니다.");
        }
        catch (IllegalArgumentException e){
            logger.info(" JWT 토큰이 잘못되었습니다.");
        }

        return false;
    }
}
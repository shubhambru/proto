package com.ecom.proto.service;

import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.proto.model.AuthDTO;
import com.ecom.proto.repository.AuthRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthService {
    
    @Autowired
    AuthRepository repository;

    public ResponseEntity<?> login(AuthDTO user){
        if(user.getEmail()==null || user.getPassword()==null){
            return new ResponseEntity<String>("Send body data",HttpStatus.BAD_REQUEST);
        }
        try {
            String uid = repository.checkCredentials(user.getEmail(), getHash(user.getPassword()));
            if(uid==null){
                return new ResponseEntity<String>("Wrong credentials",HttpStatus.BAD_REQUEST);
            }
            else{
                return new ResponseEntity<String>(generateToken(uid),HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Error fetching data"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> signup(AuthDTO user) {
        if(user.getEmail()==null || user.getPassword()==null){
            return new ResponseEntity<String>("Send body data",HttpStatus.BAD_REQUEST);
        }
        try {
            if(repository.countByEmail(user.getEmail())==0){
                user.setPassword(getHash(user.getPassword()));
                repository.save(user);
                return new ResponseEntity<String>(generateToken(String.valueOf(user.getUid())),HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<String>("User already exists",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Error creating user"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> all() {
        return new ResponseEntity<List<AuthDTO>>(repository.findAll(),HttpStatus.OK);
    }

    private String getHash(String passowrd)  {
        String key = "DKYGKTUQGTWKOVALC95t9";
        passowrd = passowrd + key;
        try {
            MessageDigest msgDst = MessageDigest.getInstance("MD5");  
            
            byte[] msgArr = msgDst.digest(passowrd.getBytes());  
            
            BigInteger bi = new BigInteger(1, msgArr);
            
            return bi.toString(16);  
        }  
        catch (NoSuchAlgorithmException abc)   
        {  
            throw new RuntimeException(abc);  
        }  
    }

    private final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    public Long getUidFromToken(String token) {
        return Long.parseLong(extractClaim(token, Claims::getSubject));
    }

    private Date extractExpiration(String token) throws Exception {
        try {
            return extractClaim(token, Claims::getExpiration);
        } catch (Exception e) {
            throw new Exception();
        }
        
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }


    private String generateToken(String uid){

        Map<String,Object> claims=new HashMap<>();
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(uid)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+86400000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}

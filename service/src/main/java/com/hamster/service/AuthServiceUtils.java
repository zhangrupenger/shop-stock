package com.hamster.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hamster.service.exception.BusinessException;
import com.hamster.service.exception.CodeEnum;
import com.hamster.service.mode.UserLoginInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AuthServiceUtils {
    private static final long EXPIRE_DATE = 30*60*100000;
    private static final String TOKEN_SECRET = "YWWHXXTWKK5L2OVE0PP";
    public static String getToken (String userId) {
        try {
            String token = "";
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            Algorithm algorithm = Algorithm.HMAC512(TOKEN_SECRET);
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            token = JWT.create().withHeader(header).withClaim("userId",userId).withExpiresAt(date).sign(algorithm);
            return token;
        } catch (Exception e) {
            log.error("get token error",e);
            return null;
        }

    }

    public static String getToken (String userId,String poiId, String role) {
        try {
            String token = "";
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            Algorithm algorithm = Algorithm.HMAC512(TOKEN_SECRET);
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            token = JWT.create().withHeader(header).withClaim("userId",userId).withClaim("poiId",poiId).withClaim("role",role).withExpiresAt(date).sign(algorithm);
            return token;
        } catch (Exception e) {
            log.error("get token error",e);
            return null;
        }

    }

    public static UserLoginInfo verity(String token) throws BusinessException{
        try {
            Algorithm algorithm = Algorithm.HMAC512(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String userId = jwt.getClaim("userId").asString();
            String poiId = jwt.getClaim("poiId").asString();
            UserLoginInfo loginInfo = new UserLoginInfo();
            loginInfo.setUserId(Long.valueOf(userId));
            loginInfo.setRole(1);
            loginInfo.setPoiId(Long.valueOf(poiId));
            return loginInfo;
        } catch (Exception e) {
           throw new BusinessException(CodeEnum.LOG_ERROR.getCode(), CodeEnum.LOG_ERROR.getMsg());
        }
    }
}

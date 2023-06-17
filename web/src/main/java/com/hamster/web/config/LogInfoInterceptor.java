package com.hamster.web.config;

import com.hamster.service.AuthServiceUtils;
import com.hamster.service.exception.BusinessException;
import com.hamster.service.exception.CodeEnum;
import com.hamster.service.mode.UserLoginInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.Objects;
import java.util.UUID;

@Component
@Slf4j
public class LogInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String t_token = request.getHeader("t_token");
        if (t_token == null || "".equals(t_token)) {
            log.info("被拦截的uri:{}",request.getRequestURI());
            throw new BusinessException(CodeEnum.LOG_ERROR.getCode(), CodeEnum.LOG_ERROR.getMsg());
        }
        //String t_token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJwb2lJZCI6IjAiLCJleHAiOjE2NjMwOTE5MDIsInVzZXJJZCI6IjQifQ.zR97BAri1KXopDSAUagNg0RVr6b-TggMduFXEEtk4ve2eF1ACjNDpeX-Dm35aqtg8BzjKGePA4JrjuZ0ts0N9w";
        UserLoginInfo verity = AuthServiceUtils.verity(t_token);
        if (Objects.isNull(verity)) {
            throw new BusinessException(CodeEnum.LOG_ERROR.getCode(), CodeEnum.LOG_ERROR.getMsg());
        }

        request.setAttribute("userId",verity.getUserId());
        request.setAttribute("poiId",verity.getPoiId());
        request.setAttribute("role",verity.getRole());
        return true;
    }


}

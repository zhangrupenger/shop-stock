package com.hamster.web.config;

import com.hamster.service.exception.BusinessException;
import com.hamster.service.exception.CodeEnum;
import com.hamster.web.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ControllerAdvice
@Slf4j
public class ControllerException {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleValidException(MethodArgumentNotValidException e) {
        log.error("校验参数异常"+ Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return new ResultVo(CodeEnum.PARAM_ERROR.getCode(), Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public Object handleValidException(BusinessException e) {
        log.error("BusinessException异常", e);
        return new ResultVo(e.getCode(), e.getMsg());
    }
}

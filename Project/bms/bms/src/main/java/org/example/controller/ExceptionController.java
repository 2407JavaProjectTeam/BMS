package org.example.controller;

import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import cn.dev33.satoken.exception.NotLoginException;
@RestControllerAdvice
public class ExceptionController {

    /**
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NotLoginException.class)
    public SaResult catchNotLoginException(NotLoginException e){
        return SaResult.get(11011,e.getMessage(),null);
    }


}

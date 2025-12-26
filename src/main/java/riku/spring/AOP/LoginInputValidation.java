package riku.spring.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import riku.spring.models.Admin;

@Component
@Aspect
@ControllerAdvice
public class LoginInputValidation {

   @ExceptionHandler(MethodArgumentNotValidException.class)
    public String inputHandler(){
       return "AdminNotFound";
   }
}

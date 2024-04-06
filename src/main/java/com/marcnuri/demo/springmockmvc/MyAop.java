package com.marcnuri.demo.springmockmvc;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAop {

    @Autowired
    UserManager userManager;

    @Around( "execution(* com.marcnuri.demo.springmockmvc.SpringMockMvcWebController.*(..))")
    public Object run(ProceedingJoinPoint joinPoint) throws Throwable{


        SpringMockMvcWebController springMockMvcWebController = (SpringMockMvcWebController)joinPoint.getTarget();

        System.out.println("========before========");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("arg: "+arg);
            if (arg instanceof User) {
                User user = (User) arg;
//                HttpServletRequest request = (HttpServletRequest) arg;
                userManager.setUser(user);
                // Here you can access request parameters and convert them to your object
                // For example, using ObjectMapper for JSON or any other way you prefer
            }
        }

        Object result = joinPoint.proceed(args);
        System.out.println("========after========");
        userManager.unsetUser();
        return result;
    }
}

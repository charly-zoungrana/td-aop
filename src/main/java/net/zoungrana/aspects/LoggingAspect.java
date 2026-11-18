package net.zoungrana.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Aspect
public class LoggingAspect {

    Logger logger=Logger.getLogger(LoggingAspect.class.getName());

    public LoggingAspect() throws IOException {
        logger.addHandler(new FileHandler("log.xml"));
        logger.setUseParentHandlers(false);
    }

    @Pointcut("execution(* net.zoungrana.metier.MetierBanqueImpl.*(..))")
    public void pc1(){}

//    @Before("pc1()")
//    public void before(JoinPoint joinPoint){
//        t1=System.currentTimeMillis();
//        logger.info("--------------------------------------------------------------");
//        logger.info("Début d'exécution de la méthode: "+joinPoint.getSignature());
//    }
//
//    @After("pc1()")
//    public void after(JoinPoint joinPoint){
//        logger.info("Fin d'exécution de la méthode: "+joinPoint.getSignature());
//        t2=System.currentTimeMillis();
//        logger.info("Durée : " + (t2-t1) + " ms");
//        logger.info("--------------------------------------------------------------");
//    }

    @Around("pc1()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint,JoinPoint joinPoint) throws Throwable {
        long t1,t2;
        t1=System.currentTimeMillis();
        logger.info("--------------------------------------------------------------");
        logger.info("Début d'exécution de la méthode: "+joinPoint.getSignature());
        Object result=proceedingJoinPoint.proceed();
        logger.info("Fin d'exécution de la méthode: "+joinPoint.getSignature());
        t2=System.currentTimeMillis();
        logger.info("Durée : " + (t2-t1) + " ms");
        logger.info("--------------------------------------------------------------");

        return result;
    }


}

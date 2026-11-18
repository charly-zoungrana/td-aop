package net.zoungrana.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.logging.Logger;

@Aspect
public class LoggingAspect {

    Long t1,t2;
    Logger logger=Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("execution(* net.zoungrana.metier.MetierBanqueImpl.*(..))")
    public void pc1(){}

    @Before("pc1()")
    public void before(JoinPoint joinPoint){
        t1=System.currentTimeMillis();
        logger.info("--------------------------------------------------------------");
        logger.info("Début d'exécution de la méthode: "+joinPoint.getSignature());
    }

    @After("pc1()")
    public void after(JoinPoint joinPoint){
        logger.info("Fin d'exécution de la méthode: "+joinPoint.getSignature());
        t2=System.currentTimeMillis();
        logger.info("Durée : " + (t2-t1) + " ms");
        logger.info("--------------------------------------------------------------");
    }


}

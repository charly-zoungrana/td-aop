package net.zoungrana.aspects;

import net.zoungrana.metier.Compte;
import net.zoungrana.metier.MetierBanqueImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PatchRetraitAspect {

    @Pointcut("execution(* net.zoungrana.metier.MetierBanqueImpl.retirer(Long, double) )")
    public void pc1(){}

    @Around("pc1() && args(code,montant)")
    public Object aroundRetirer(ProceedingJoinPoint proceedingJoinPoint, JoinPoint joinPoint, Long code, double montant) throws Throwable {
        MetierBanqueImpl metierBanque=(MetierBanqueImpl) joinPoint.getTarget();
        Compte compte=metierBanque.consulterCompte(code);
        if(compte.getSolde()<montant){
            throw new IllegalArgumentException("Solde insuffisant");
        }
        return proceedingJoinPoint.proceed();
    }
}

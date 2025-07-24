package ir.maktabsharif.cw31.aspect;

import ir.maktabsharif.cw31.model.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Aspect
@Slf4j
public class SimpleAspect {

    @Pointcut("execution(* ir.maktabsharif.cw31.controller.AuthorController.saveAuthor(..))")
    public void savePointCut() {

    }

    @Around("savePointCut()")
    public Object aroundSave(ProceedingJoinPoint joinPoint) {
        return logAction( joinPoint, "save");
    }

    @Pointcut("execution(* ir.maktabsharif.cw31.controller.AuthorController.updateAuthor(..))")
    public void updatePointCut() {

    }

    @Around("updatePointCut()")
    public Object aroundUpdate(ProceedingJoinPoint joinPoint) {
        return logAction( joinPoint, "update");
    }

    @Pointcut("execution(* ir.maktabsharif.cw31.controller.AuthorController.deleteAuthor(..))")
    public void deletePointCut() {

    }

    @Around("deletePointCut()")
    public Object aroundDelete(ProceedingJoinPoint joinPoint) {
        return logAction( joinPoint, "delete");
    }

    private static Object logAction(ProceedingJoinPoint joinPoint, String action) {
        Object proceed = null;
        String textBefore="User {} - request for "+action+" with id {}";
        String textDone="User {} - request for "+action+" with id {} (Done)";
        String textFailed="User {} - request for "+action+" with id {} (Failed)";
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = UUID.randomUUID().toString();
        log.info(textBefore, principal.getAuthor().getId(), id);
        try {
            proceed = joinPoint.proceed();
            log.info(textDone, principal.getAuthor().getId(), id);

        } catch (Throwable e) {
            log.info(textFailed, principal.getAuthor().getId(), id);
        }
        return proceed;
    }
}

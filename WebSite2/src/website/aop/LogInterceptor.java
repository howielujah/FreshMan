package website.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class LogInterceptor {
    // 宣告並取得logger
    private Logger logger = Logger.getLogger(LogInterceptor.class);

    // 程式在進入website下的方法前皆會執行此段程式
    @Before("execution(* website..*(..))")
    public void invoke() throws Throwable {
        // 將訊息寫至logger
        logger.info("進來了!!!");
    }
    
 // 程式在進出website.model下的方法皆會執行此段程式
    @Around("execution(* website.model..*(..))")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        // 將訊息寫至logger
    	logger.info(joinPoint.getSignature().getName()+"前");
    	Object retVal =  joinPoint.proceed();
    	logger.info(joinPoint.getSignature().getName()+"後");
		return retVal;
    }
    
    @AfterThrowing(pointcut = "execution(* website.model..*(..))",throwing = "throwable")
    public void invoke(JoinPoint joinPoint,Throwable throwable) throws Throwable {
        // 將訊息寫至logger
    	logger.info(joinPoint.getSignature().getName()+" Exception的型態為:"+throwable);		
    }
}

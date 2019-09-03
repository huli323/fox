package aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AspectJService {
    @Before("execution(* show(..))")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg.getClass() + " "+ arg.toString());
        }
    }

    @After("execution(* show(..))")
    public void after(){
        System.out.println("after method");
    }
}

package aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AspectJService {
    @Before("execution(* show(..))")
    public void before(){
        System.out.println("before method");
    }
}

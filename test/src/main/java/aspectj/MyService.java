package aspectj;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

class MyService implements MethodBeforeAdvice, AfterReturningAdvice, MethodInterceptor {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("-------------before method-----------");
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("-------------after method------------");
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("round advice");
        invocation.proceed();
        return null;
    }
}
package proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {
    private Object obj;
    public Object newProxy(Object obj){
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("print".equals(method.getName())){
            if(args == null || args.length == 0 || args[0] == null){
                System.out.println("名字为空，已被拦截");
                return null;
            }
        }
        return method.invoke(obj, args);
    }
}

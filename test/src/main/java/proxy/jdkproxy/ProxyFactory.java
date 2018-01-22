package proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory implements InvocationHandler {
    private Object stu;
    public Object createStudentProxy(Object stu){
        this.stu = stu;
        return Proxy.newProxyInstance(stu.getClass().getClassLoader(), stu.getClass().getInterfaces(), this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Student s = (Student) stu;
        Object obj = new Object();
        if(s != null){
            if(s.getName() != null){
                obj = method.invoke(s, args);
            } else {
                System.out.println("名字为空，已被拦截");
            }
        }
        return obj;
    }
}

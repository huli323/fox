package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class c = Ref.class;
//        Ref r = (Ref) c.newInstance();
//        r.show("fox");

        Method[] methods = c.getDeclaredMethods();
        for (Method m :
                methods) {
            if ("show".equals(m.getName())){
                Object o = c.newInstance();
                Method method = c.getMethod("show", new Class[]{ String.class, Integer.class});
                method.invoke(o, new Object[]{ "fox", 22});
            }
        }
    }
}

class Ref{
    public void show(String name, Integer age){
        System.out.println(name + "-" + age);
    }
}

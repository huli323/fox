package spel;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Test1 {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine javascript = manager.getEngineByName("JavaScript");
        javascript.eval("function sum(a, b){return a + b;}");
        Invocable invocable = (Invocable) javascript;
        Object sum = invocable.invokeFunction("sum", 10, 1);
        System.out.println(sum);
    }
}

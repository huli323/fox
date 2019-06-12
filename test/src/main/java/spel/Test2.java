package spel;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class Test2 {
    public static void main(String[] args) {

        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("'abc'.concat('dec')");
        System.out.println(expression.getValue());


    }
}

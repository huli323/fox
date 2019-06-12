package leetCode;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 */
public class LongestValidParentheses {
    public static void main(String[] args) {
        String str = "(()";
        System.out.println(handle1(str));
    }

    public static int handle1(String s){
        if(s == null || s.length() == 0) return 0;

        int res = 0;

        // 将左括号( 当成 1，右括号)当成 -1
        int[] array = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') array[i] = 1;
            else if(s.charAt(i) == ')') array[i] = -1;
        }

        for (int i = 0; i < array.length - 1; i++) {
            int sum = 0;
            for (int j = i; j < array.length; j++) {
                sum += array[j];
                if(sum == 0){   // 有效的括号串和一定为0，和为0了继续判断后续字符
                    res = Math.max(j + 1 - i, res);
                } else if(sum < 0) break;
            }
        }

        return res;
    }

    public static int handle2(String s){
        if(s == null || s.length() == 0) return 0;

        int res = 0;
        int[] array = new int[s.length()];
        for (int i = 1; i < array.length; i++) {
            if(')' != s.charAt(i)) continue;

            int left = i - array[i-1] - 1;
            if(left >= 0 && s.charAt(left) == '('){ // 一组
                array[i] = array[i-1] + 2;
            }

            if(left - 1 >= 0){  // 当前括号组左边的括号组个数
                array[i] += array[left - 1];
            }

            res = Math.max(res, array[i]);
        }


        return res;
    }
}

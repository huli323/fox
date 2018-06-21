package leetCode;

/**
 * Validate if a given string is numeric.
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 *
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 */
public class ValidNumber {
    public static void main(String[] args) throws Exception {
        double a = Double.parseDouble("959440.94f");
        String n = "12312.1345e42";
        long s1 = System.nanoTime();
        System.out.println(isNumber(n) + "  " + (System.nanoTime() - s1));

        long s2 = System.nanoTime();
        System.out.println(ff2(n) + "  " + (System.nanoTime() - s2));

    }

    /**
     * 方法1：
     * @param str
     * @return
     */
    public static boolean isNumber(String str){
        try {
            double a = Double.parseDouble(str);
            System.out.println(a);
            str = str.trim();
            return !(str.length() > 1 && "FfDd".indexOf(str.substring(str.length() - 1, str.length())) != -1);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 方法2：
     * @param str
     * @return
     */
    public static boolean ff2(String str){
        return str.trim().matches("[-+]?((\\d*\\.?\\d+)|(\\d+\\.?\\d*))(([eE][-+]?)\\d+)?");
    }
}

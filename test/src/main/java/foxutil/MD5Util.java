package foxutil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String encode(String s){
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(s.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseToHexString(byte[] array){
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            String hex = Integer.toHexString(b & 0xff);
            if(hex.length() == 1){
                hex = "0" + hex;
            }
            sb.append(hex);
        }

        return sb.toString();
    }

}

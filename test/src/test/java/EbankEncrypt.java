import org.apache.axiom.util.base64.Base64Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * 光大加密
 */
public class EbankEncrypt {

    /**
     * 测试环境key
     */
    public static final String signKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK05+UVe5PPn8p6z9gGZE4yAqeeY3n9TDmbZ2NgwNHefvtYeAAH1OSVIkoghjmJrtYRRxyxWM/xPOPKbN4RwBBQmqtNBr2lLPSljDE8pjSoLU5FIBPU3de+1+WlNy7FB4oxx+pFIZuuJb/NaI58S3Pqjfq/vkdnK5f6nx+7XZrDjAgMBAAECgYAh75SU8YU6/zq5upm8MGfzA5XFiF42ZWgR4CWqHK96q/Hv8oqf15Gtay1BvAkv4Nf7pyW02uJuUHrglGXo7STvFy0Rp/t/EDVWfZHr1hl8JQ8czyS8+12/TNTaxeSsYVYHsFQ9CTju82AD5BSHy82D4dGI/IGlWXN3tJLRtbII8QJBANP2dPX4DVeah7e06Ee+e8Me4aDSE+VivYOBypIAI1i2nHENGh5GgdfThUYtQrntLlKczMCN+jw+N92faW7sP6sCQQDRN0XZZsilfEElLpQBENB8qSyB8Pp7Wsjd0nOCo+36WeCGCyC3/rZLUsn++lTDi7B+eaEGPyfQrYktNLz9DPupAkBSd38F+95lCRBonxAIacoJHki2o0VuZ75IHgVMTJVz/IhdS7Wvtcrqx7cAmLdvUyJErhDTkemb6rEdntoMeApFAkAwczVo0EW9FRZdDvlVedheVispeG35bR/BRwBbC62L4Y5+XEtacNE0hlqjAsPoeQwJbg9dS/jjeZyf7GurynrpAkEApRaKqNnzLedPnOhPr9ewmUBoLqoM9s25qosAXLFCmshMIzkaeCrcl4dx6XPwC9AeuYWMCqHacO7FB7Q0aeXFmg==";

    public static void main(String[] args) throws Exception {
        //String signKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK05+UVe5PPn8p6z9gGZE4yAqeeY3n9TDmbZ2NgwNHefvtYeAAH1OSVIkoghjmJrtYRRxyxWM/xPOPKbN4RwBBQmqtNBr2lLPSljDE8pjSoLU5FIBPU3de+1+WlNy7FB4oxx+pFIZuuJb/NaI58S3Pqjfq/vkdnK5f6nx+7XZrDjAgMBAAECgYAh75SU8YU6/zq5upm8MGfzA5XFiF42ZWgR4CWqHK96q/Hv8oqf15Gtay1BvAkv4Nf7pyW02uJuUHrglGXo7STvFy0Rp/t/EDVWfZHr1hl8JQ8czyS8+12/TNTaxeSsYVYHsFQ9CTju82AD5BSHy82D4dGI/IGlWXN3tJLRtbII8QJBANP2dPX4DVeah7e06Ee+e8Me4aDSE+VivYOBypIAI1i2nHENGh5GgdfThUYtQrntLlKczMCN+jw+N92faW7sP6sCQQDRN0XZZsilfEElLpQBENB8qSyB8Pp7Wsjd0nOCo+36WeCGCyC3/rZLUsn++lTDi7B+eaEGPyfQrYktNLz9DPupAkBSd38F+95lCRBonxAIacoJHki2o0VuZ75IHgVMTJVz/IhdS7Wvtcrqx7cAmLdvUyJErhDTkemb6rEdntoMeApFAkAwczVo0EW9FRZdDvlVedheVispeG35bR/BRwBbC62L4Y5+XEtacNE0hlqjAsPoeQwJbg9dS/jjeZyf7GurynrpAkEApRaKqNnzLedPnOhPr9ewmUBoLqoM9s25qosAXLFCmshMIzkaeCrcl4dx6XPwC9AeuYWMCqHacO7FB7Q0aeXFmg==";
        byte[] byteArray = toByteArray(new File("C:\\Users\\Administrator\\Desktop\\TrsIn_20180510_784.txt"));
        System.out.println(new String(byteArray, "utf-8"));

        byte[] keyBytes = Base64Utils.decode(signKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);

        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateK);
        signature.update(byteArray);

        String sign = Base64Utils.encode(signature.sign());
        System.out.println("加密后:\n" + sign);

    }

    private static byte[] toByteArray(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024 * 2];
            int n = 0;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            return out.toByteArray();
        } finally {
            out.close();
            in.close();
        }
    }
}

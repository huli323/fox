import foxutil.DateUtil;
import org.apache.axiom.util.base64.Base64Utils;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

public class EbankTransferFileEncrypt {
    /**
     * 测试环境key
     */
    public static final String signKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK05+UVe5PPn8p6z9gGZE4yAqeeY3n9TDmbZ2NgwNHefvtYeAAH1OSVIkoghjmJrtYRRxyxWM/xPOPKbN4RwBBQmqtNBr2lLPSljDE8pjSoLU5FIBPU3de+1+WlNy7FB4oxx+pFIZuuJb/NaI58S3Pqjfq/vkdnK5f6nx+7XZrDjAgMBAAECgYAh75SU8YU6/zq5upm8MGfzA5XFiF42ZWgR4CWqHK96q/Hv8oqf15Gtay1BvAkv4Nf7pyW02uJuUHrglGXo7STvFy0Rp/t/EDVWfZHr1hl8JQ8czyS8+12/TNTaxeSsYVYHsFQ9CTju82AD5BSHy82D4dGI/IGlWXN3tJLRtbII8QJBANP2dPX4DVeah7e06Ee+e8Me4aDSE+VivYOBypIAI1i2nHENGh5GgdfThUYtQrntLlKczMCN+jw+N92faW7sP6sCQQDRN0XZZsilfEElLpQBENB8qSyB8Pp7Wsjd0nOCo+36WeCGCyC3/rZLUsn++lTDi7B+eaEGPyfQrYktNLz9DPupAkBSd38F+95lCRBonxAIacoJHki2o0VuZ75IHgVMTJVz/IhdS7Wvtcrqx7cAmLdvUyJErhDTkemb6rEdntoMeApFAkAwczVo0EW9FRZdDvlVedheVispeG35bR/BRwBbC62L4Y5+XEtacNE0hlqjAsPoeQwJbg9dS/jjeZyf7GurynrpAkEApRaKqNnzLedPnOhPr9ewmUBoLqoM9s25qosAXLFCmshMIzkaeCrcl4dx6XPwC9AeuYWMCqHacO7FB7Q0aeXFmg==";

    public static void main(String[] args) throws InvalidKeySpecException, SignatureException, NoSuchAlgorithmException, InvalidKeyException, IOException {

        generateEncrptFile("C:\\Users\\Administrator\\Desktop\\TrsIn_20180509_748.txt");

    }

    public static void generateEncrptFile(String filePath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        File file = new File(filePath);

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String str = br.readLine();
        String tail = "|+|" + str;
        StringBuilder builder = new StringBuilder();

        while(str != null){
            builder.append(str + "\n");
            str = br.readLine();
        }
        br.close();
        System.out.println(builder.substring(0, builder.length()));

        // 私钥
        byte[] keyBytes = Base64Utils.decode(signKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);

        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateK);
        signature.update(builder.toString().getBytes());

        // 加密后
        String sign = Base64Utils.encode(signature.sign());
        System.out.println("加密后：\n" + sign);

        // 拼接内容
        String content = sign + tail;

        File newFile = new File(file.getParent() + File.separator + "TrsIn_wycf_" + DateUtil.formatDate(new Date()) + ".ok");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile)));
        writer.write(content);
        writer.flush();
        writer.close();
    }


}

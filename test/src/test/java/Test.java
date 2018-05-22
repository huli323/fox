import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

public class Test {
    public static void main(String[] args) throws Exception {

//        String urlStr = "ftp://root:ifox94323@ftp.foxme.vip/usr/local/test";
//        URL url = new URL(urlStr);
//        URLConnection connection = url.openConnection();
//        connection.connect();
//        System.out.println(connection.getContentLength());
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        System.out.println(reader.readLine());

//        String str = "123456374180507010189";
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        md5.update(str.getBytes("GBK"));
//        System.out.println(byte2HexStr(md5.digest()));



//        CloseableHttpClient client = HttpClients.custom().setDefaultConnectionConfig(ConnectionConfig.DEFAULT).build();
//        HttpPost post = new HttpPost("http://www.foxme1.vip");
//        post.setHeader("Content-Type", "application/xml;charset=gbk");
//
//        HttpResponse response = client.execute(post);
//        String message = EntityUtils.toString(response.getEntity(), "GBK");
//        System.out.println(message);


        try{
            System.out.println("try clause");
            int a = 1;
            if(a == 1)
                throw new Exception("eee");
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        System.out.println("end");

    }

    public static String byte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toLowerCase());
        }
        return sb.toString();
    }

}
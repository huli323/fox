package test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadTest {
    public static void main(String[] args) throws URISyntaxException {
        String url = "http://cn.bing.com/";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            Pattern p = Pattern.compile("(g_img=\\{url: \")(.*\\.jpg)\"");
            String str = EntityUtils.toString(response.getEntity());
//            System.out.println(str);
            Matcher m = p.matcher(str);
//            System.out.println("匹配数："+m.groupCount());
            String address = null;
            if (m.find()) {
                address = url + m.group(2);
            } else {
                System.exit(0);
            }
            System.out.println("图片地址:" + address);
            System.out.println("正在下载……");
//            address = "https://cn.bing.com/az/hprichbg/rb/FDNY343_EN-US8191512432_1920x1080.jpg";
            HttpGet getImage = new HttpGet(address);
            CloseableHttpResponse responseImg = httpClient.execute(getImage);
            HttpEntity entity = responseImg.getEntity();
            File file = new File(address);
            System.out.println("file path " + file.getAbsolutePath());
            // writeImgEntityToFile(entity,"C:\\Users\\Public\\Pictures\\Sample Pictures\\" + dateFormat.format(new Date()) + ".jpg");
            createFile(address, "C:\\Users\\Public\\Pictures\\Sample Pictures\\" + dateFormat.format(new Date()) + ".jpg");
            writeImgEntityToFile(entity, "C:\\Users\\Public\\Pictures\\Sample Pictures\\" + dateFormat.format(new Date()) + ".png");
            System.out.println("下载完毕.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void createFile(String address, String toPath) throws IOException, URISyntaxException {
        URL url1 = new URL(address);
        HttpURLConnection httpConnection = (HttpURLConnection) url1.openConnection();
        InputStream inputStream = httpConnection.getInputStream();

//        BufferedInputStream inputStream = new BufferedInputStream(url1.openStream());

        FileOutputStream fileOutputStream = new FileOutputStream(new File(toPath));
        byte[] b = new byte[1024];
        int flag, i = 0;
        //read方法可能读不到1024个字节，友谊调用write方法是要给定偏移量以防造成写入的数据有问题
        while ((flag = inputStream.read(b)) != -1) {
            System.out.println("第" + ++i + "次===" + flag);
            fileOutputStream.write(b, 0, flag);
        }
        fileOutputStream.close();
        inputStream.close();


    }

    public static void writeImgEntityToFile(HttpEntity imgEntity, String fileAddress) {
        File storeFile = new File(fileAddress);
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(storeFile);

            if (imgEntity != null) {
                InputStream instream;
                instream = imgEntity.getContent();
                byte b[] = new byte[8 * 1024];
                int count;
                while ((count = instream.read(b)) != -1) {
                    output.write(b, 0, count);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

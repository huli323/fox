package test;

import com.google.common.collect.Lists;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlingPicture {
    public static void main(String[] args) throws IOException {
        crawlingPic(Paths.get("/Users/huli/.pic/site/site.txt"), "/Users/huli/.pic/tmp");

    }

    public static void crawlingPic(Path sourceFile, String destDir) throws IOException {
        List<String> pages = Files.readAllLines(sourceFile);
//        List<String> pages = Lists.newArrayList("https://t66y.com/htm_data/2202/7/4899187.html");
        List<List<String>> picUrls = Lists.newArrayList();
        for (String url : pages) {
            picUrls.add(searchResource(url));
        }
        if(pages.size() > 1) {
            Files.move(sourceFile, sourceFile.resolveSibling(System.currentTimeMillis() + ""));
        }

        for (int i = 0; i < picUrls.size(); i++) {
            int index = i;
            new Thread(() -> {
                List<String> urls = picUrls.get(index);
                for (int j = 0; j < urls.size(); j++) {
                    String picUrl = urls.get(j);
                    System.out.println("start to download pic: " + picUrl);
                    int k = picUrl.lastIndexOf(".");

                    if(k == -1) continue;
                    String suffix = picUrl.substring(k);
                    if(suffix.contains("/")) continue;

                    try {
                        createFile(picUrl, destDir + index + "_" + j + "_" + System.currentTimeMillis() + suffix);
                    } catch (IOException e) {
                        System.out.println("download pic failed: " + picUrl);
                    }
                }
            }).start();
        }

    }

    public static List<String> searchResource(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response;
        List<String> picSrcs = Lists.newArrayList();
        try {
            response = httpClient.execute(httpGet);
            Pattern p = Pattern.compile("iyl-data=.*? ess-data='(.*?)'");
            String str = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            Matcher m = p.matcher(str);
            while (m.find()) {
                picSrcs.add(m.group(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picSrcs;
    }

    private static void createFile(String address, String toPath) throws IOException {
        URL url1 = new URL(address);
        HttpURLConnection httpConnection = (HttpURLConnection) url1.openConnection();
        httpConnection.setConnectTimeout(10000);
        InputStream inputStream = httpConnection.getInputStream();

        File file = new File(toPath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            throw new RuntimeException("create directory failure!");
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] b = new byte[1024];
        int count;
        while ((count = inputStream.read(b)) != -1) {
            fileOutputStream.write(b, 0, count);
        }
        fileOutputStream.close();
        inputStream.close();

        System.out.println("write to " + file.getAbsolutePath());
    }

}

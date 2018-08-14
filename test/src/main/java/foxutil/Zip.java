package foxutil;

import java.io.*;
import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            zipCompress(new File("D:\\IT电子书"), "C:/Users/Administrator/Desktop/aa.zip");
            System.out.println("用时："+(System.currentTimeMillis() - start) / 1000 + "s");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param sourceFile    要压缩的文件
     * @param compressedFile    压缩后的文件
     * @throws IOException
     */
    public static void zipCompress(File sourceFile, String compressedFile) throws IOException {
        // 创建zip输出流
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(compressedFile));

        // 创建缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(zos);

        System.out.println("压缩中...");
        long start = System.currentTimeMillis();
        compress(zos, bos, sourceFile, sourceFile.getName());
        System.out.println("压缩结束，用时："+(System.currentTimeMillis() - start) / 1000 + "s");

        if(bos != null)
            bos.close();

        if(zos != null)
            zos.close();
    }

    private static void compress(ZipOutputStream zos, BufferedOutputStream bos, File sourceFile, String base) throws IOException {
        // 处理文件夹
        if(sourceFile.isDirectory()){
            File[] files = sourceFile.listFiles();
            if(files.length == 0){
                zos.putNextEntry(new ZipEntry(base + "/"));
            } else {
                for (File file :
                        files) {
                    compress(zos, bos, file, base + "/" + file.getName());
                }
            }
        } else {
            System.out.println("压缩:" + base);
            zos.putNextEntry(new ZipEntry(base));
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
            byte[] bytes = new byte[1024];
            int t;
            while((t = bis.read(bytes)) != -1){
                zos.write(bytes, 0, t);
            }
            bis.close();
        }
    }
}

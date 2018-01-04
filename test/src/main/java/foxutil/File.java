package foxutil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class File {
    public static void nioCopy(String file1, String file2) throws IOException {
        FileChannel in = new FileInputStream(file1).getChannel();
        FileChannel out = new FileOutputStream(file2).getChannel();

        long count = (64 * 1024 * 1024) - (32 * 1024);
        long position = 0;
        long size = (int) in.size();
        while (position < size) {
            position += in.transferTo(position, count, out);
        }
    }
}

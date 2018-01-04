package foxutil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screen {
    /**
     *
     * @param fileName  保存文件名
     */
    public static void shot(String fileName){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rect = new Rectangle(dimension);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        BufferedImage bufferedImage = robot.createScreenCapture(rect);
        try {
            ImageIO.write(bufferedImage, "png", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

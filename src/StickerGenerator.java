import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class StickerGenerator {
    public void generate(InputStream imageStream, File output) throws Exception {
        var originalImage = ImageIO.read(imageStream);

        var width = originalImage.getWidth();
        var height = originalImage.getHeight();
        var newHeight = height + 200;

        var newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        var font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        var text = "Oh my friends";
        var textLength = text.length();
        var textPositionX = (width / 2) - ((textLength * 64) / 4); // + (textLength % 2 == 0 ? 6 : 32);
        var textPositionY = (height + 100) + 32;

        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);
        graphics.drawString(text, textPositionX, textPositionY);
        ImageIO.write(newImage, "png", output);
    }
}

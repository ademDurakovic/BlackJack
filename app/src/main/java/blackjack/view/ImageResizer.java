package blackjack.view;

import javax.swing.ImageIcon;
import java.awt.Image;
/*Class made to resize icons throughout our classes and helps avoid code repetion. */
public class ImageResizer {
    public static void resize(double scale, ImageIcon icon) {
        int height = icon.getIconHeight();
        int width = icon.getIconWidth();

        height = (int) (height * scale);
        width = (int) (width * scale);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        icon.setImage(image);
    }
}


package jp.xdomain.html.yoctomns.util;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import jp.xdomain.html.yoctomns.graphics.Animation;

public class ImageUtil {
    public static BufferedImage readImage(String path) throws IOException, IllegalArgumentException {
        BufferedImage readImage = ImageIO.read(ImageUtil.class.getResourceAsStream(path));
        BufferedImage compatibleImage =createCompatibleImage(readImage.getWidth(), readImage.getHeight(), Transparency.BITMASK);
        Graphics2D graphics2D = compatibleImage.createGraphics();
        graphics2D.drawImage(readImage, 0, 0, null);
        graphics2D.dispose();
        return compatibleImage;
    }

    public static BufferedImage createCompatibleImage(int width, int height, int transparency) {
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        return graphicsConfiguration.createCompatibleImage(width, height, transparency);
    }

    public static List<Animation> convertToAnimationFromFile(String path, int width, int height, int frame) throws IOException, IllegalArgumentException {
        List<Animation> animations = new ArrayList<>();
        BufferedImage image = readImage(path);
        for (int y = 0; y < image.getHeight(); y += height) {
            List<BufferedImage> assets = new ArrayList<>();
            for (int x = 0; x < image.getWidth(); x += width) {
                assets.add(image.getSubimage(x, y, width, height));
            }
            animations.add(new Animation(assets, frame));
        }
        return animations;
    }

    public static List<BufferedImage> convertToTileAssetsFromFile(String path, int width, int height) throws IOException, IllegalArgumentException {
        List<BufferedImage> assets = new ArrayList<>();
        BufferedImage image = readImage(path);
        for (int y = 0; y < image.getHeight(); y += height) {
            for (int x = 0; x < image.getWidth(); x += width) {
                assets.add(image.getSubimage(x, y, width, height));
            }
        }
        return assets;
    }
}

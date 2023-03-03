package jp.xdomain.html.yoctomns.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.IOException;

import jp.xdomain.html.yoctomns.core.Padding;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.util.LoggingUtil;
import jp.xdomain.html.yoctomns.state.State;

public class UIText extends UIComponent {
    private static final String DEFAULT_FONT_NAME = "PixelMplus-20130602/PixelMplus12-Regular.ttf";
    private String text;
    private int fontSize;
    private int fontStyle;
    private String fontFamily;
    private Color color;
    private Font font;
    private String fileName;
    private String filePath;

    public UIText(String text, int fontSize) {
        this(text, fontSize, Font.PLAIN, Font.MONOSPACED, new Padding(5));
    }

    public UIText(String text, int fontSize, int fontStyle, String fontFamily, Padding padding) {
        this(text, fontSize, fontStyle, fontFamily, Color.WHITE, DEFAULT_FONT_NAME, padding);
    }

    public UIText(String text, int fontSize, int fontStyle, String fontFamily, Color color, String fileName, Padding padding) {
        this.text = text;
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
        this.fontFamily = fontFamily;
        this.color = color;
        this.fileName = fileName;
        this.filePath = "/font/" + fileName;
        this.padding = padding;
        this.font = new Font(fontFamily, fontStyle, fontSize);
    }

    private void createFont() {
        try {
            InputStream inputStream = UIText.class.getResourceAsStream(filePath);
            font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (IOException e) {
            LoggingUtil.severePrint("An exception occurred during the font file loading process. fileName: " + fileName, e);
        } catch (FontFormatException e) {
            LoggingUtil.severePrint("The specified font is invalid. fileName: " + fileName, e);
        }
    }

    private void calculateSize() {
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
        int width = fontMetrics.stringWidth(text) + padding.getHorizontal();
        int height = fontMetrics.getHeight() + padding.getVertical();
        size = new Size(width, height);
    }

    @Override
    public void update(State state) {
        // TODO
        // createFont();
        calculateSize();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.setFont(font);
        int drawX = 0;
        int drawY = 0;
        if (parent != null) {
            drawX = absolute.getX() + padding.getLeft();
            drawY = absolute.getY() + fontSize + padding.getTop();
        } else {
            drawX = relative.getX() + padding.getLeft();
            drawY = relative.getY() + fontSize + padding.getTop();
        }
        graphics2D.drawString(text, drawX, drawY);
    }

    public void setText(String text) {
        this.text = text;
    }
}

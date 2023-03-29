package jp.xdomain.html.yoctomns.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLayeredPane;

import jp.xdomain.html.yoctomns.ui.UIDebug;

public class GamePanel extends JLayeredPane {
    private Display parent;
    private UIDebug uIDebug;

    public GamePanel(Display parent, int width, int height) {
        this.parent = parent;
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        this.uIDebug = new UIDebug();
        this.uIDebug.setBounds(width - 100, height - 100, 100, 100);
        add(uIDebug);
    }

    public void update() {
        uIDebug.update();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;
        parent.draw(graphics2D);
    }
}

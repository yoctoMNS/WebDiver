package jp.xdomain.html.yoctomns.ui;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JComponent;

import jp.xdomain.html.yoctomns.game.GameLoop;

public class UIDebug extends JComponent {
    private JLabel fpsCounter;

    public UIDebug() {
        super();
        fpsCounter = new JLabel();
        fpsCounter.setForeground(Color.BLACK);
        fpsCounter.setBounds(0, 0, 500, 500);
        setFont(new Font(Font.MONOSPACED, Font.PLAIN, 100));
        add(fpsCounter);
        setBackground(Color.RED);
        // setBackground(new Color(1.0F, 1.0F, 1.0F, 0.7F));
    }

    public void update() {
        fpsCounter.setText(String.valueOf(GameLoop.dispCounter));
    }
}

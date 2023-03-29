package jp.xdomain.html.yoctomns.display;

import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import jp.xdomain.html.yoctomns.state.State;
import jp.xdomain.html.yoctomns.util.LoggingUtil;

public class Display extends JFrame implements WindowListener {
    private GamePanel gamePanel;
    private State state;

    public Display(State state, String title, int width, int height) {
        super(title);
        this.state = state;
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.gamePanel = new GamePanel(this, width, height);
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener(this);
    }

    public void update() throws RuntimeException, Error {
        gamePanel.update();
    }

    public void draw(Graphics2D graphics2D) throws RuntimeException, Error {
        graphics2D.clearRect(0, 0, getWidth(), getHeight());
        state.draw(graphics2D);
        graphics2D.dispose();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        LoggingUtil.infoPrint("The game is closed.");
        LoggingUtil.close();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}

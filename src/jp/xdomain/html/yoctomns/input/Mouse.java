package jp.xdomain.html.yoctomns.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import jp.xdomain.html.yoctomns.core.Position;

public class Mouse implements MouseListener, MouseMotionListener {
    private Position position;
    private boolean mouseClicked;
    private boolean mousePressed;

    public Mouse() {
        this.position = new Position();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClicked = true;
        mousePressed = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position.setX(e.getX());
        position.setY(e.getY());
    }

    @Override
    public String toString() {
        return "X: " + position.getX() + " Y: " + position.getY();
    }

    public boolean isMouseClicked() {
        return mouseClicked;
    }

    public boolean isMousePresed() {
        return mousePressed;
    }
}

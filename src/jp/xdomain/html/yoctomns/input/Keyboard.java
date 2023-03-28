package jp.xdomain.html.yoctomns.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private boolean[] isPressed;
    private byte[] pressTime;

    public Keyboard() {
        this.isPressed = new boolean[KeyEvent.KEY_LAST];
        this.pressTime = new byte[KeyEvent.KEY_LAST];
    }

    public void update() {
        updateKeyState(KeyEvent.VK_W);
        updateKeyState(KeyEvent.VK_A);
        updateKeyState(KeyEvent.VK_S);
        updateKeyState(KeyEvent.VK_D);
        updateKeyState(KeyEvent.VK_E);
        updateKeyState(KeyEvent.VK_P);
        updateKeyState(KeyEvent.VK_SPACE);
        updateKeyState(KeyEvent.VK_ENTER);
    }

    private void updateKeyState(int keyCode) {
        if (isPressed[keyCode]) {
            if (pressTime[keyCode] < Byte.MAX_VALUE) {
                pressTime[keyCode]++;
            }
        } else {
            pressTime[keyCode] = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        isPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        isPressed[e.getKeyCode()] = false;
    }

    public boolean isPressedKey(int keyCode) {
        return pressTime[keyCode] > 0;
    }

    public boolean isJustPressedKey(int keyCode) {
        return pressTime[keyCode] == 1;
    }

    public int isPressedTime(int keyCode) {
        return pressTime[keyCode];
    }
}

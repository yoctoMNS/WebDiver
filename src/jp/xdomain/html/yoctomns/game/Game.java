package jp.xdomain.html.yoctomns.game;

import jp.xdomain.html.yoctomns.display.Display;
import jp.xdomain.html.yoctomns.input.Keyboard;
import jp.xdomain.html.yoctomns.input.Mouse;
import jp.xdomain.html.yoctomns.state.PlayState;
import jp.xdomain.html.yoctomns.state.State;

public class Game {
    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;
    public static final String TITLE = "Web Diver Pre-Alpha";
    private boolean debugMode;
    private Keyboard keyboard;
    private Display display;
    private State state;
    private Mouse mouse;
    // TODO
    // private MouseHandler mouseHandler;
    // private UIDebugInfo uiDebugInfo;

    public Game() {
        this.debugMode = true;
        this.keyboard = new Keyboard();
        this.mouse = new Mouse();
        this.state = new PlayState(this);
        // TODO
        // this.mouseHandler = new MouseHandler();
        // this.state = new StartState(this);
        this.display = new Display(state, TITLE, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.display.getContentPane().addKeyListener(keyboard);
        this.display.getContentPane().requestFocus();
        this.display.getContentPane().addMouseListener(mouse);
        this.display.getContentPane().addMouseMotionListener(mouse);
    }

    public void update() throws RuntimeException, Error {
        keyboard.update();
        // TODO
        // mouseHandler.update(state);
        state.update();
    }

    public void draw() throws RuntimeException, Error {
        display.getContentPane().repaint();
    }

    public void sendState(State state) {
        this.state = state;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public State getState() {
        return state;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Mouse getMouse() {
        return mouse;
    }
}

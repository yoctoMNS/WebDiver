package jp.xdomain.html.yoctomns.game;

import jp.xdomain.html.yoctomns.display.Display;
import jp.xdomain.html.yoctomns.display.GameCamera;
import jp.xdomain.html.yoctomns.input.Keyboard;
import jp.xdomain.html.yoctomns.state.PlayState;
import jp.xdomain.html.yoctomns.state.State;

public class Game {
    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;
    public static final String TITLE = "Web Diver Pre-Alpha";

    private boolean debugMode;
    // TODO
    private Keyboard keyboard;
    private Display display;
    private State state;
    private GameCamera gameCamera;
    // private Mouse mouse;
    // private MouseHandler mouseHandler;
    // private UIDebugInfo uiDebugInfo;

    public Game() {
        this.debugMode = true;
        // TODO
        this.keyboard = new Keyboard();
        // this.mouse = new Mouse();
        // this.mouseHandler = new MouseHandler();
        this.display = new Display(TITLE, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.display.getCanvas().addKeyListener(keyboard);
        // this.display.getCanvas().addMouseListener(mouse);
        // this.display.getCanvas().addMouseMotionListener(mouse);
        this.display.getCanvas().requestFocus();
        this.state = new PlayState(this);
        // this.state = new StartState(this);
        this.gameCamera = new GameCamera();
    }

    public void update() throws RuntimeException, Error {
        // TODO
        keyboard.update();
        // mouseHandler.update(state);
        state.update();
    }

    public void draw() throws RuntimeException, Error {
        display.draw(state);
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

    public Keyboard getKeyboard() {
        return keyboard;
    }
}

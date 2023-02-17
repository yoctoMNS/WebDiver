package jp.xdomain.html.yoctomns.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.display.GameCamera;
import jp.xdomain.html.yoctomns.input.Keyboard;
import jp.xdomain.html.yoctomns.state.State;

public abstract class Entity {
    protected State state;
    protected GameCamera gameCamera;
    protected Keyboard keyboard;
    protected Position position;
    protected Size size;
    protected String name;
    protected int scale;

    public Entity(State state, Position position, Size size, String name, int scale) {
        this.state = state;
        this.gameCamera = new GameCamera();
        this.keyboard = state.getKeyboard();
        this.position = position;
        this.size = size;
        this.name = name;
        this.scale = scale;
    }
    
    public abstract void update();

    public abstract void draw(Graphics2D graphics2D);

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public int getWidth() {
        return size.getWidth();
    }

    public int getHeight() {
        return size.getHeight();
    }

    public String getName() {
        return name;
    }

    public int getScale() {
        return scale;
    }
}

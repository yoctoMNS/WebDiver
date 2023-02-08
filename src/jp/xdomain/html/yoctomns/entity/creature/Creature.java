package jp.xdomain.html.yoctomns.entity.creature;

import java.util.List;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.entity.Entity;
import jp.xdomain.html.yoctomns.graphics.Animation;
import jp.xdomain.html.yoctomns.state.State;

public abstract class Creature extends Entity {
    protected int vx;
    protected int vy;
    protected int speed;
    protected Direction direction;
    protected List<Animation> animations;

    public Creature(State state, Position position, Size size, String name, int scale) {
        super(state, position, size, name, scale);
        this.direction = Direction.DOWN;
    }

    @Override
    public void update() {
        position.addX(vx);
        position.addY(vy);
        bounds.x += vx;
        bounds.y += vy;
    }
}

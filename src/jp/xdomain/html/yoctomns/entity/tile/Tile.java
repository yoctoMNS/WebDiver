package jp.xdomain.html.yoctomns.entity.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.entity.Entity;
import jp.xdomain.html.yoctomns.state.State;

public abstract class Tile extends Entity {
    // TODO
    public static final List<Integer> NORMAL_TILE_ID = Arrays.asList(2);
    public static final List<Integer> HOLE_TILE_ID = Arrays.asList(602,603,2499);
    public static final List<Integer> OBJECT_TILE_ID = Arrays.asList(48,321,85,137,66,67,71,371);
    public static final List<Integer> EVENT_TILE_ID = Arrays.asList(685,720);
    protected int id;
    protected BufferedImage texture;

    public Tile(int id, State state, Position position, Size size, String name, int scale, BufferedImage texture) {
        super(state, position, size, name, scale);
        this.id = id;
        this.texture = texture;
    }

    public abstract boolean canWalk();

    public abstract void draw(Graphics2D graphics2D);

    public int getId() {
        return id;
    }
}

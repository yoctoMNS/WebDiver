package jp.xdomain.html.yoctomns.entity.tile;

import java.awt.image.BufferedImage;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.state.State;

public class WarpEventTile extends EventTile {
    private int nextMapId;

    public WarpEventTile(int id, State state, Position position, Size size, String name, int scale, BufferedImage texture, int nextMapId) {
        super(id, state, position, size, name, scale, texture);
        this.nextMapId = nextMapId;
    }

    @Override
    public void action() {
        System.out.println("player is on the warp event tile");
    }
}

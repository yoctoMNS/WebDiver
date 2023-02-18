package jp.xdomain.html.yoctomns.entity.tile;

import java.awt.image.BufferedImage;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.event.Effect;
import jp.xdomain.html.yoctomns.state.State;

public class EffectEventTile extends EventTile implements Effect {
    public EffectEventTile(int id, State state, Position position, Size size, String name, int scale, BufferedImage texture) {
        super(id, state, position, size, name, scale, texture);
    }

    @Override
    public void action() {
        invoke();
    }

    @Override
    public void invoke() {
        System.out.println("player is on the effect event tile");
    }
}

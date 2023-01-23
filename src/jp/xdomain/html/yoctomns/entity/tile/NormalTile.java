package jp.xdomain.html.yoctomns.entity.tile;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.game.Game;
import jp.xdomain.html.yoctomns.input.Keyboard;

public class NormalTile extends Tile {
    public NormalTile(Game game, Position position, Size size, String name, int scale) {
        super(game, position, size, name, scale);
    }

    public boolean canWalk() {
        return true;
    }
}

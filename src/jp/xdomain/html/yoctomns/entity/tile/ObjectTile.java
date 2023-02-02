package jp.xdomain.html.yoctomns.entity.tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.state.PlayState;
import jp.xdomain.html.yoctomns.state.State;

public class ObjectTile extends Tile {
    public ObjectTile(State state, Position position, Size size, String name, int scale, BufferedImage texture) {
        super(state, position, size, name, scale, texture);
    }

    @Override
    public boolean canWalk() {
        return false;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if (state instanceof PlayState playState) {
            Position slideTilePos = gameCamera.getSlideTilesPos(playState.getPlayer());
            graphics2D.drawImage(
                    texture,
                    getX() * getWidth() * scale + slideTilePos.getX(),
                    getY() * getHeight() * scale + slideTilePos.getY(),
                    getWidth() * scale,
                    getHeight() * scale,
                    null);

            if (state.getGame().isDebugMode()) {
                graphics2D.setColor(Color.ORANGE);
                graphics2D.drawRect(
                        getX() * getWidth() * scale + slideTilePos.getX(),
                        getY() * getHeight() * scale + slideTilePos.getY(),
                        getWidth() * scale,
                        getHeight() * scale);
            }
        }
    }
}

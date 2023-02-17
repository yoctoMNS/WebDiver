package jp.xdomain.html.yoctomns.display;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.entity.Entity;
import jp.xdomain.html.yoctomns.entity.creature.Player;
import jp.xdomain.html.yoctomns.game.Game;

public class GameCamera {
    private int screenWidth;
    private int screenHeight;

    public GameCamera() {
        this.screenWidth = Game.SCREEN_WIDTH;
        this.screenHeight = Game.SCREEN_HEIGHT;
    }

    public Position getScreenCenterPos(Entity entity) {
        int centerX = screenWidth / 2 - (entity.getWidth() * entity.getScale()) / 2;
        int centerY = screenHeight / 2 - (entity.getHeight() * entity.getScale()) / 2;
        return new Position(centerX, centerY);
    }

    public Position getSlideTilesPos(Entity entity) {
        int moveX = getScreenCenterPos(entity).getX() * entity.getScale() - entity.getX() * entity.getScale();
        int moveY = getScreenCenterPos(entity).getY() * entity.getScale() - entity.getY() * entity.getScale();
        return new Position(moveX, moveY);
    }

    public Position getSlideTilePosFromPlayer(Player player) {
        int moveX = getScreenCenterPos(player).getX() + (Player.BOUNDS_OFFSET_X * player.getScale()) - player.getX() * player.getScale();
        int moveY = getScreenCenterPos(player).getY() + (Player.BOUNDS_OFFSET_Y * player.getScale()) - player.getY() * player.getScale();
        return new Position(moveX, moveY);
    }
}

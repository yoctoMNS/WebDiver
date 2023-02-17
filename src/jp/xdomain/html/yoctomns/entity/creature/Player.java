package jp.xdomain.html.yoctomns.entity.creature;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.entity.Entity;
import jp.xdomain.html.yoctomns.entity.tile.EventTile;
import jp.xdomain.html.yoctomns.entity.tile.Tile;
import jp.xdomain.html.yoctomns.state.State;
import jp.xdomain.html.yoctomns.state.PlayState;
import jp.xdomain.html.yoctomns.util.ImageUtil;
import jp.xdomain.html.yoctomns.util.LoggingUtil;
import jp.xdomain.html.yoctomns.world.WorldData;

public class Player extends Creature {
    public static final String ASSET_FILE_NAME = "/img/creature/player.png";
    public static final int BOUNDS_OFFSET_X = 4;
    public static final int BOUNDS_OFFSET_Y = 6;
    public static final int BOUNDS_WIDTH = 7;
    public static final int BOUNDS_HEIGHT = 10;
    private WorldData worldData;
    private List<Tile[][]> layers;

    public Player(WorldData worldData, State state, Position position, Size size, String name, int scale) {
        super(state, position, size, name, scale);
        this.worldData = worldData;
        this.direction = Direction.DOWN;
        this.speed = 1;
        try {
            this.animations = ImageUtil.convertToAnimationFromFile(
                    ASSET_FILE_NAME, worldData.getTileWidth(), worldData.getTileHeight(), 10);
        } catch (IOException e) {
            LoggingUtil.severePrint("Failed load animation file.", e);
        }
        if (state instanceof PlayState playState) {
            this.layers = playState.getLayers();
        }
    }
 
    private void handleInput() {
        vx = vy = 0;
        if (keyboard.isPressedKey(KeyEvent.VK_W)) {
            vy = -speed;
            direction = Direction.UP;
            animations.get(direction.getValue()).start();
        } else if (keyboard.isPressedKey(KeyEvent.VK_S)) {
            vy = speed;
            direction = Direction.DOWN;
            animations.get(direction.getValue()).start();
        } else if (keyboard.isPressedKey(KeyEvent.VK_A)) {
            vx = -speed;
            direction = Direction.LEFT;
            animations.get(direction.getValue()).start();
        } else if (keyboard.isPressedKey(KeyEvent.VK_D)) {
            vx = speed;
            direction = Direction.RIGHT;
            animations.get(direction.getValue()).start();
        } else {
            animations.get(direction.getValue()).stop();
        }
    }

    private List<Position> getDirTilePosList(Direction direction) {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        int tileSize = worldData.getTileWidth();
        switch (direction) {
        case UP:
            x1 = getX() / tileSize;
            x2 = (getX() + getWidth()) / tileSize;
            y1 = y2 = (getY() + vy) / tileSize;
            break;
        case DOWN:
            x1 = getX() / tileSize;
            x2 = (getX() + getWidth()) / tileSize;
            y1 = y2 = (getY() + getHeight() + vy) / tileSize;
            break;
        case LEFT:
            y1 = getY() / tileSize;
            y2 = (getY() + getHeight()) / tileSize;
            x1 = x2 = (getX() + vx) / tileSize;
            break;
        case RIGHT:
            y1 = getY() / tileSize;
            y2 = (getY() + getHeight()) / tileSize;
            x1 = x2 = (getX() + getWidth() + vx) / tileSize;
            break;
        default:
            /* NONE */
        }
        return Arrays.asList(new Position(x1, y1), new Position(x2, y2));
    }

    private boolean isOutOfMap() {
        List<Position> positions = getDirTilePosList(direction);
        int x1 = positions.get(0).getX();
        int y1 = positions.get(0).getY();
        int x2 = positions.get(1).getX();
        int y2 = positions.get(1).getY();
        if (x1 <= 0 || y1 <= 0 || x1 >= worldData.getWidth() - 1 || y1 >= worldData.getHeight() - 1 ||
            x2 <= 0 || y2 <= 0 || x2 >= worldData.getWidth() - 1 || y2 >= worldData.getHeight() - 1) {
            return true;
        }
        return false;
    }

    private boolean isWalkableTile() {
        List<Position> positions = getDirTilePosList(direction);
        int x1 = positions.get(0).getX();
        int y1 = positions.get(0).getY();
        int x2 = positions.get(1).getX();
        int y2 = positions.get(1).getY();
        boolean canWalkFlg = true;
        for (Tile[][] tiles : layers) {
            if (tiles[y1][x1] != null) {
                if (!tiles[y1][x1].canWalk()) {
                    canWalkFlg = false;
                    break;
                }
            }
            if (tiles[y2][x2] != null) {
                if (!tiles[y2][x2].canWalk()) {
                    canWalkFlg = false;
                    break;
                }
            }
        }
        return canWalkFlg;
    }

    private void handleEventTile() {
        List<Position> positions = getDirTilePosList(direction);
        int x1 = positions.get(0).getX();
        int y1 = positions.get(0).getY();
        int x2 = positions.get(1).getX();
        int y2 = positions.get(1).getY();
        boolean isEventTile1 = false;
        boolean isEventTile2 = false;
        for (Tile[][] tiles : layers) {
            Tile tile1 = tiles[y1][x1];
            Tile tile2 = tiles[y2][x2];
            if (tile1 != null) {
                if (tile1 instanceof EventTile eventTile) {
                    isEventTile1 = true;
                }
            }
            if (tile2 != null) {
                if (tile2 instanceof EventTile eventTile) {
                    isEventTile2 = true;
                }
            }
            if (isEventTile1 && isEventTile2) {
                if (tile1 instanceof EventTile eventTile) {
                    eventTile.action(state);
                }
                break;
            }
        }
    }

    private boolean canWalk() {
        if (isOutOfMap()) {
            return false;
        }
        if (!isWalkableTile()) {
            return false;
        }
        return true;
    }
    
    @Override
    public void update() {
        handleInput();
        if (canWalk()) {
            super.update();
            handleEventTile();
        }
        animations.get(direction.getValue()).update();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        BufferedImage image = animations.get(direction.getValue()).getImage();
        Position screenCenterPos = gameCamera.getScreenCenterPos(this);
        graphics2D.drawImage(
            image,
            screenCenterPos.getX(), screenCenterPos.getY(),
            worldData.getTileWidth() * scale, worldData.getTileHeight() * scale, null
        );
        if (state.getGame().isDebugMode()) {
            graphics2D.setColor(Color.RED);
            graphics2D.drawRect(
                screenCenterPos.getX() + (BOUNDS_OFFSET_X * scale),
                screenCenterPos.getY() + (BOUNDS_OFFSET_Y * scale),
                getWidth() * scale, getHeight() * scale
            );
        }
        graphics2D.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        graphics2D.drawString(getX() + ", " + getY(), 10, 70);
        graphics2D.drawString(vx + ", " + vy, 10, 110);
    }
}

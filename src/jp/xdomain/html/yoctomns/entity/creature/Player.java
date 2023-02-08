package jp.xdomain.html.yoctomns.entity.creature;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.entity.Entity;
import jp.xdomain.html.yoctomns.state.State;
import jp.xdomain.html.yoctomns.util.ImageUtil;

public class Player extends Creature {
    public static final String ASSET_FILE_NAME = "/img/creature/player.png";
    private static final int BOUNDS_OFFSET_X = 4;
    private static final int BOUNDS_OFFSET_Y = 6;
    private static final int BOUNDS_WIDTH = 7;
    private static final int BOUNDS_HEIGHT = 10;

    public Player(State state, Position position, Size size, String name, int scale) {
        super(state, position, size, name, scale);
        this.bounds = new Rectangle(getX() + BOUNDS_OFFSET_X, getY() + BOUNDS_OFFSET_Y, BOUNDS_WIDTH, BOUNDS_HEIGHT);
        this.direction = Direction.DOWN;
        this.speed = 1;
        try {
            this.animations = ImageUtil.convertToAnimationFromFile(ASSET_FILE_NAME, size.getWidth(), size.getHeight(), 10);
        } catch (IOException e) {
            LoggingUtil.severePrint("Failed load animation file.", e);
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

    @Override
    public void update() {
        handleInput();
        super.update();
        animations.get(direction.getValue()).update();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        BufferedImage image = animations.get(direction.getValue()).getImage();
        Position screenCenterPos = gameCamera.getScreenCenterPos(this);
        graphics2D.drawImage(image, screenCenterPos.getX(), screenCenterPos.getY(), getWidth() * scale, getHeight() * scale, null);
    }
}

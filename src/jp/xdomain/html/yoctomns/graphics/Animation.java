package jp.xdomain.html.yoctomns.graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import jp.xdomain.html.yoctomns.util.ImageUtil;

public class Animation {
    private List<BufferedImage> images;
    private int idx;
    private int nextFrame;
    private int currentFrame;
    private boolean stop;

    public Animation(List<BufferedImage> images, int frame) {
        this.images = images;
        this.nextFrame = frame;
        this.currentFrame = nextFrame;
    }

    public void update() {
        if (currentFrame == 0) {
            idx++;
            if (idx == images.size()) {
                idx = 0;
            }
            currentFrame = nextFrame;
        }
        if (!stop) {
            --currentFrame;
        }
    }

    public void start() {
        stop = false;
    }

    public void stop() {
        stop = true;
    }

    public BufferedImage getImage() {
        return images.get(idx);
    }
}

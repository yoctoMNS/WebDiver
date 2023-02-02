package jp.xdomain.html.yoctomns.entity.tile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.xdomain.html.yoctomns.util.ImageUtil;

public class TileAssets {
    private List<BufferedImage> assets;

    public TileAssets(String path, int width, int height) {
        try {
            this.assets = ImageUtil.convertToTileAssetsFromFile(path, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(int id) {
        return assets.get(id);
    }
}

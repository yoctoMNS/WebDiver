package jp.xdomain.html.yoctomns.world;

import java.util.List;

import jp.xdomain.html.yoctomns.entity.tile.Tile;

public class WorldData {
    private int width;
    private int height;
    private int tileWidth;
    private int tileHeight;
    private int tileScale;
    private List<Integer> mapData;
    private String fileName;

    public WorldData(int width, int height, int tileWidth, int tileHeight, int tileScale, List<Integer> mapData, String fileName) {
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileScale = tileScale;
        this.mapData = mapData;
        this.fileName = fileName;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileScale() {
        return tileScale;
    }

    public int getMapLength() {
        return mapData.size();
    }

    public List<Integer> getMapData() {
        return mapData;
    }

    public String getFileName() {
        return fileName;
    }
}

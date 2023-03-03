package jp.xdomain.html.yoctomns.core;

public class Size {
    private int width;
    private int height;

    public Size() {
        this.width = 0;
        this.height = 0;
    }

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "WIDTH: " + width + " HEIGHT: " + height;
    }
}

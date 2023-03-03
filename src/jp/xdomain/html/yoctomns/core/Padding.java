package jp.xdomain.html.yoctomns.core;

public class Padding {
    private int top;
    private int bottom;
    private int left;
    private int right;

    public Padding(int space) {
        this(space, space);
    }

    public Padding(int horizontal, int vertical) {
        this(vertical, vertical, horizontal, horizontal);
    }

    public Padding(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getVertical() {
        return top + bottom;
    }

    public int getHorizontal() {
        return left + right;
    }
}

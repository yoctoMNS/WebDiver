package jp.xdomain.html.yoctomns.ui;

public class Alignment {
    private final Alignment.Position horizontal;
    private final Alignment.Position vertical;

    public enum Position {
        START, CENTER, END;
    }

    public Alignment(Alignment.Position horizontal, Alignment.Position vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Alignment.Position getHorizontal() {
        return horizontal;
    }

    public Alignment.Position getVertical() {
        return vertical;
    }
}

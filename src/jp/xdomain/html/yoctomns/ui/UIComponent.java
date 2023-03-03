package jp.xdomain.html.yoctomns.ui;

import java.awt.Graphics2D;

import jp.xdomain.html.yoctomns.core.Margin;
import jp.xdomain.html.yoctomns.core.Padding;
import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.state.State;

public abstract class UIComponent {
    protected Position relative;
    protected Position absolute;
    protected Size size;
    protected Margin margin;
    protected Padding padding;
    protected UIComponent parent;

    public UIComponent() {
        this.relative = new Position(0, 0);
        this.absolute = new Position(0, 0);
        this.size = new Size(1, 1);
        this.margin = new Margin(0);
        this.padding = new Padding(0);
    }

    public abstract void draw(Graphics2D graphics2D);

    public abstract void update(State state);

    public void setParent(UIComponent parent) {
        this.parent = parent;
    }

    public void setRelative(Position relative) {
        this.relative = relative;
    }

    public void setAbsolute(Position absolute) {
        this.absolute = absolute;
    }

    public Margin getMargin() {
        return margin;
    }

    public Padding getPadding() {
        return padding;
    }

    public Size getSize() {
        return size;
    }
}

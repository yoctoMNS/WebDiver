package jp.xdomain.html.yoctomns.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import jp.xdomain.html.yoctomns.core.Margin;
import jp.xdomain.html.yoctomns.core.Padding;
import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.state.State;

public abstract class UIContainer extends UIComponent {
    protected Size windowSize;
    protected boolean isCenter;
    protected Color backgroundColor;
    protected Alignment alignment;
    protected List<UIComponent> children;

    public UIContainer(Size windowSize) {
        this(windowSize, new Alignment(Alignment.Position.START, Alignment.Position.START));
    }

    public UIContainer(Size windowSize, Alignment alignment) {
        this(windowSize, alignment, new Color(0, 0, 0, 0));
    }

    public UIContainer(Size windowSize, Alignment alignment, Color backgroundColor) {
        super();
        this.windowSize = windowSize;
        this.isCenter = false;
        this.backgroundColor = backgroundColor;
        this.alignment = alignment;
        this.margin = new Margin(5);
        this.padding = new Padding(5);
        this.children = new ArrayList<>();
        calculateSize();
        calculatePosition();
    }

    private void calculateSize() {
        Size calculateContentSize = calculateContentSize();
        int width = padding.getHorizontal() + calculateContentSize.getWidth();
        int height = padding.getVertical() + calculateContentSize.getHeight();
        size = new Size(width, height);
    }

    private void calculatePosition() {
        int x = padding.getLeft();
        if (alignment.getHorizontal() == Alignment.Position.CENTER) {
            x = windowSize.getWidth() / 2 - size.getWidth() / 2;
        }
        if (alignment.getHorizontal() == Alignment.Position.END) {
            x = windowSize.getWidth() - size.getWidth() - margin.getRight();
        }
        int y = padding.getTop();
        if (alignment.getVertical() == Alignment.Position.CENTER) {
            y = windowSize.getHeight() / 2 - size.getHeight() / 2;
        }
        if (alignment.getVertical() == Alignment.Position.END) {
            y = windowSize.getHeight() - size.getHeight() - margin.getBottom();
        }
        relative = new Position(x, y);
        if (parent == null) {
            absolute = new Position(x, y);
        }
        calculateContentPosition();
    }

    protected abstract Size calculateContentSize();

    protected abstract void calculateContentPosition();

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(backgroundColor);
        graphics2D.fillRect(relative.getX(), relative.getY(), size.getWidth(), size.getHeight());
        children.forEach(child -> child.draw(graphics2D));
    }

    @Override
    public void update(State state) {
        children.forEach(child -> child.update(state));
        calculateSize();
        calculatePosition();
    }

    public void addUIComponent(UIComponent uiComponent) {
        children.add(uiComponent);
        uiComponent.setParent(this);
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }
}

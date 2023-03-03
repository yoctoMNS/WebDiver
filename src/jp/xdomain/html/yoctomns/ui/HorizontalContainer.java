package jp.xdomain.html.yoctomns.ui;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;

public class HorizontalContainer extends UIContainer {
    public HorizontalContainer(Size windowSize) {
        super(windowSize);
    }

    public HorizontalContainer(Size windowSize, Alignment alignment) {
        super(windowSize, alignment);
    }

    @Override
    protected Size calculateContentSize() {
        int combinedChildWidth = 0;
        int tallestChildHeight = 0;
        for (UIComponent child : children) {
            combinedChildWidth += child.getWidth() + child.getMargin().getHorizontal();
            if (child.getHeight() > tallestChildHeight) {
                tallestChildHeight = child.getSize().getHeight();
            }
        }
        return new Size(combinedChildWidth, tallestChildHeight);
    }

    @Override
    public void calculateContentPosition() {
        int currentX = padding.getLeft();
        int currentY = padding.getTop();
        for (UIComponent child : children) {
            if (isCenter) {
                currentY = size.getHeight() / 2 - child.getSize().getHeight() / 2;
            }
            currentX += child.getMargin().getLeft();
            child.setRelative(new Position(currentX, currentY));
            child.setAbsolute(new Position(currentX + absolute.getX(), currentY + absolute.getY()));
            currentX += child.getSize().getWidth();
            currentX += child.getMargin.getRight();
        }
    }
}

package jp.xdomain.html.yoctomns.ui;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;

public class VerticalContainer extends UIContainer {
    public VerticalContainer(Size windowSize) {
        super(windowSize);
    }

    public VerticalContainer(Size windowSize, Alignment alignment) {
        super(windowSize, alignment);
    }

    @Override
    protected Size calculateContentSize() {
        int combinedChildHeight = 0;
        int widestChildWidth = 0;
        for (UIComponent child : children) {
            combinedChildHeight += child.getSize().getHeight() + child.getMargin().getVertical();
            if (child.getSize().getWidth() > widestChildWidth) {
                widestChildWidth = child.getSize().getWidth();
            }
        }
        return new Size(widestChildWidth, combinedChildHeight);
    }

    @Override
    public void calculateContentPosition() {
        int currentX = padding.getLeft();
        int currentY = padding.getTop();
        for (UIComponent child : children) {
            if (isCenter) {
                currentX = size.getWidth() / 2 - child.getSize().getWidth() / 2;
            }
            currentY += child.getMargin().getTop();
            child.setRelative(new Position(currentX, currentY));
            child.setAbsolute(new Position(currentX + absolute.getX(), currentY + absolute.getY()));
            currentY += child.getSize().getHeight();
            currentY += child.getMargin().getBottom();
        }
    }
}

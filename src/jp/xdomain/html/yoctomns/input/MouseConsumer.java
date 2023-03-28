package jp.xdomain.html.yoctomns.input;

import jp.xdomain.html.yoctomns.state.State;

public interface MouseConsumer {
    void onClick(State state);
    void onDrag(State state);
}

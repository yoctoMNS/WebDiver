package jp.xdomain.html.yoctomns.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.entity.creature.Player;
import jp.xdomain.html.yoctomns.game.Game;
import jp.xdomain.html.yoctomns.game.GameLoop;
import jp.xdomain.html.yoctomns.state.PlayState;
import jp.xdomain.html.yoctomns.state.State;

public class UIDebugInfo extends VerticalContainer {
    private UIText frameCount;
    private UIText playerPos;

    public UIDebugInfo(Size windowSize) {
        super(windowSize, new Alignment(Alignment.Position.END, Alignment.Position.START));
        this.frameCount = new UIText("", 30);
        this.playerPos = new UIText("", 30);
        this.backgroundColor = new Color(100, 100, 100, 100);
        addUIComponent(frameCount);
        addUIComponent(playerPos);
    }

    @Override
    public void update(State state) {
        super.update(state);
        if (state instanceof PlayState playState) {
            Player player = playState.getPlayer();
            Game game = playState.getGame();
            frameCount.setText(String.valueOf(GameLoop.dispCounter));
            playerPos.setText(player.toString());
        }
    }
}

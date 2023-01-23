package jp.xdomain.html.yoctomns.state;

import java.awt.Graphics2D;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.entity.creature.Player;
import jp.xdomain.html.yoctomns.entity.tile.Tile;
import jp.xdomain.html.yoctomns.game.Game;

public class PlayState extends State {
    private Player player;
    private Tile tile;

    public PlayState(Game game) {
        super(game);

        this.player = new Player(game, new Position(0, 0), new Size(16, 16), "yocto", 4);
    }

    @Override
    public void update() {
        super.update();
        player.update();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        player.draw(graphics2D);
    }
}

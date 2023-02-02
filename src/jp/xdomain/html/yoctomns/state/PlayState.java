package jp.xdomain.html.yoctomns.state;

import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.entity.creature.Player;
import jp.xdomain.html.yoctomns.entity.tile.NormalTile;
import jp.xdomain.html.yoctomns.entity.tile.HoleTile;
import jp.xdomain.html.yoctomns.entity.tile.ObjectTile;
import jp.xdomain.html.yoctomns.entity.tile.Tile;
import jp.xdomain.html.yoctomns.entity.tile.TileAssets;
import jp.xdomain.html.yoctomns.game.Game;
import jp.xdomain.html.yoctomns.util.FileUtil;
import jp.xdomain.html.yoctomns.world.WorldData;

public class PlayState extends State {
    private TileAssets tileAssets;
    private List<Tile[][]> layers;
    private Player player;

    public PlayState(Game game) {
        super(game);

        this.layers = new ArrayList<>();
        try {
            WorldData worldData = FileUtil.buildWorldDataForTextData("/img/tile/world1.map");
            buildTiles(worldData);
            this.player = new Player(this, new Position(0, 0), new Size(worldData.getTileWidth(), worldData.getTileHeight()), "yocto", worldData.getTileScale());
        } catch (IOException e) {
            System.err.println("Failed build world data.");
            e.printStackTrace();
        }
    }

    public void buildTiles(WorldData worldData) {
        tileAssets = new TileAssets("/img/tile/" + worldData.getFileName(), worldData.getTileWidth(), worldData.getTileHeight());
        List<Integer> mapData = worldData.getMapData();
        Tile[][] tiles = null;
        int x = 0;
        int y = 0;

        for (int i = 0; i < worldData.getMapLength(); i++) {
            if (i % (worldData.getWidth() * worldData.getHeight()) == 0) {
                if (tiles == null) {
                    tiles = new Tile[worldData.getHeight()][worldData.getWidth()];
                } else {
                    layers.add(tiles);
                    tiles = new Tile[worldData.getHeight()][worldData.getWidth()];
                }
            }

            Position pos = new Position(x, y);
            Size size = new Size(worldData.getTileWidth(), worldData.getTileHeight());
            String name = "Normal Tile";
            int scale = worldData.getTileScale();
            int id = mapData.get(i) - 1;
            if (id != -1) {
                BufferedImage image = tileAssets.getImage(id);
                if (Tile.NORMAL_TILE_ID.contains(id)) {
                    tiles[y][x] = new NormalTile(this, pos, size, name, scale, image);
                } else if (Tile.HOLE_TILE_ID.contains(id)) {
                    tiles[y][x] = new HoleTile(this, pos, size, name, scale, image);
                } else if (Tile.OBJECT_TILE_ID.contains(id)) {
                    tiles[y][x] = new ObjectTile(this, pos, size, name, scale, image);
                }
            }
            x++;
            if (x == worldData.getWidth()) {
                x = 0;
                y++;
            }
            if (y == worldData.getHeight()) {
                y = 0;
            }
        }
    }

    @Override
    public void update() {
        super.update();

        player.update();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        layers.stream().forEach(tiles -> {
            for (Tile[] line : tiles) {
                for (Tile tile : line) {
                    if (tile != null) {
                        tile.draw(graphics2D);
                    }
                }
            }
        });

        player.draw(graphics2D);
    }

    public Player getPlayer() {
        return player;
    }
}

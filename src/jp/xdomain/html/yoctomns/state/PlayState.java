package jp.xdomain.html.yoctomns.state;

import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.xdomain.html.yoctomns.core.Position;
import jp.xdomain.html.yoctomns.core.Size;
import jp.xdomain.html.yoctomns.entity.creature.Player;
import jp.xdomain.html.yoctomns.entity.tile.EffectEventTile;
import jp.xdomain.html.yoctomns.entity.tile.NormalTile;
import jp.xdomain.html.yoctomns.entity.tile.HoleTile;
import jp.xdomain.html.yoctomns.entity.tile.ObjectTile;
import jp.xdomain.html.yoctomns.entity.tile.Tile;
import jp.xdomain.html.yoctomns.entity.tile.TileAssets;
import jp.xdomain.html.yoctomns.entity.tile.WarpEventTile;
import jp.xdomain.html.yoctomns.game.Game;
import jp.xdomain.html.yoctomns.util.FileUtil;
import jp.xdomain.html.yoctomns.util.LoggingUtil;
import jp.xdomain.html.yoctomns.world.WorldData;

public class PlayState extends State {
    private List<Tile[][]> layers;
    private Player player;

    public PlayState(Game game) {
        super(game);
        this.layers = new ArrayList<>();
        try {
            WorldData worldData = FileUtil.buildWorldDataForTextData("/map/world1.map");
            buildTiles(worldData);
            this.player = new Player(
                worldData, this,
                new Position(worldData.getTileWidth() * 4 + Player.BOUNDS_OFFSET_X, worldData.getTileHeight() * 4 + Player.BOUNDS_OFFSET_Y),
                new Size(Player.BOUNDS_WIDTH, Player.BOUNDS_HEIGHT),
                "yocto", worldData.getTileScale()
            );
        } catch (IOException e) {
            LoggingUtil.severePrint("Failed build world data.", e);
        }
    }

    public void buildTiles(WorldData worldData) {
        TileAssets tileAssets = new TileAssets("/img/tile/" + worldData.getFileName(), worldData.getTileWidth(), worldData.getTileHeight());
        List<Integer> mapData = worldData.getMapData();
        for (int i = 0; i < 3; i++) {
            Tile[][] tiles = new Tile[worldData.getHeight()][worldData.getWidth()];
            for (int y = 0; y < worldData.getHeight(); y++) {
                for (int x = 0; x < worldData.getWidth(); x++) {
                    // TODO
                    Position pos = new Position(x, y);
                    Size size = new Size(worldData.getTileWidth(), worldData.getTileHeight());
                    String name = "Normal Tile";
                    int scale = worldData.getTileScale();
                    int id = mapData.get((i * (worldData.getWidth() * worldData.getHeight())) + y * worldData.getWidth() + x) - 1;
                    if (id != -1) {
                        BufferedImage image = tileAssets.getImage(id);
                        if (Tile.NORMAL_TILE_ID.contains(id)) {
                            tiles[y][x] = new NormalTile(id, this, pos, size, name, scale, image);
                        } else if (Tile.HOLE_TILE_ID.contains(id)) {
                            name = "Hole Tile";
                            tiles[y][x] = new HoleTile(id, this, pos, size, name, scale, image);
                        } else if (Tile.OBJECT_TILE_ID.contains(id)) {
                            name = "Object Tile";
                            tiles[y][x] = new ObjectTile(id, this, pos, size, name, scale, image);
                        } else if (Tile.WARP_EVENT_TILE_ID.contains(id)) {
                            name = "Warp Event Tile";
                            // TODO
                            tiles[y][x] = new WarpEventTile(id, this, pos, size, name, scale, image, 1);
                        } else if (Tile.EFFECT_EVENT_TILE_ID.contains(id)) {
                            name = "Effect Event Tile";
                            tiles[y][x] = new EffectEventTile(id, this, pos, size, name, scale, image);
                        }
                    }
                }
            }
            layers.add(tiles);
        }
        LoggingUtil.infoPrint("World building is complete.");
    }

    @Override
    public void update() {
        super.update();
        player.update();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        layers.stream().forEach(tiles ->  {
            for (Tile[] line : tiles) {
                for (Tile tile : line) {
                    if (tile != null && tile.getId() != 2499) {
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

    public List<Tile[][]> getLayers() {
        return layers;
    }
}

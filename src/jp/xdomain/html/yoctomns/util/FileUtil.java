package jp.xdomain.html.yoctomns.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.xdomain.html.yoctomns.world.WorldData;

public class FileUtil {
    private static final int WORLD_RES_FILE_NAME    = 0;
    private static final int WORLD_WIDTH            = 1;
    private static final int WORLD_HEIGHT           = 2;
    private static final int WORLD_TILE_WIDTH       = 3;
    private static final int WORLD_TILE_HEIGHT      = 4;
    private static final int WORLD_TILE_SCALE       = 5;

    public static WorldData buildWorldDataForTextData(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(FileUtil.class.getResourceAsStream(path)))) {
            String line = "";
            String fileName = "";
            int width = 0;
            int height = 0;
            int tileWidth = 0;
            int tileHeight = 0;
            int tileScale = 0;
            List<Integer> mapData = new ArrayList<>();
            int idx = 0;

            while ((line = reader.readLine()) != null) {
                if      (idx == WORLD_RES_FILE_NAME) fileName = line;
                else if (idx == WORLD_WIDTH)         width = Integer.parseInt(line);
                else if (idx == WORLD_HEIGHT)        height = Integer.parseInt(line);
                else if (idx == WORLD_TILE_WIDTH)    tileWidth = Integer.parseInt(line);
                else if (idx == WORLD_TILE_HEIGHT)   tileHeight = Integer.parseInt(line);
                else if (idx == WORLD_TILE_SCALE)    tileScale = Integer.parseInt(line);
                else {
                    String[] splitData = line.split(",");
                    for (String id : splitData) {
                        mapData.add(Integer.parseInt(id));
                    }
                }
                idx++;
            }

            return new WorldData(
                    width,
                    height,
                    tileWidth,
                    tileHeight,
                    tileScale,
                    mapData,
                    fileName
            );
        }
    }
}

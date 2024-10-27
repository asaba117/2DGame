package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int[][] map = {
            {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 1, 1, 2, 2, 1, 1, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 2, 2, 0, 0, 0, 1, 1, 0, 0},
            {1, 1, 0, 0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 1, 1, 0},
            {1, 0, 0, 2, 2, 1, 1, 1, 1, 2, 2, 0, 0, 0, 1, 1},
            {1, 0, 0, 2, 1, 1, 0, 0, 1, 1, 2, 0, 0, 1, 1, 0},
            {1, 0, 0, 2, 1, 0, 0, 0, 0, 1, 2, 0, 0, 1, 1, 0},
            {1, 0, 0, 2, 2, 0, 0, 0, 0, 2, 2, 0, 0, 1, 1, 0},
            {1, 1, 0, 0, 2, 2, 2, 2, 2, 2, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}
    };

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];

        getTileImage();
    }
    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stone.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2d){
//        g2d.drawImage(tile[0].image,0,0,gamePanel.tileSize,gamePanel.tileSize,null);
//        g2d.drawImage(tile[0].image,0,0,gamePanel.tileSize,gamePanel.tileSize,null);
//        g2d.drawImage(tile[0].image,0,0,gamePanel.tileSize,gamePanel.tileSize,null);
        int tileSize = gamePanel.tileSize;

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                int tileType = map[row][col];
                BufferedImage tileImage = tile[tileType].image;
                g2d.drawImage(tileImage, col * tileSize, row * tileSize, tileSize, tileSize, null);
            }
        }
    }

}

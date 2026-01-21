
package world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import graphics.SpriteSheetTile;
import main.GamePanel;

public class World {

	public static Tile[] tiles;
	public static int WIDTH;
	public static int HEIGHT;
	public int TILE_SIZE = GamePanel.tileSize;
	
	public World(String path) {

		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));

			int[] pixel = new int[map.getWidth() * map.getHeight()];

			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();

			tiles = new Tile[map.getWidth() * map.getHeight()];

			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixel, 0, map.getWidth());

			for (int xx = 0; xx < map.getWidth(); xx++) {
				for (int yy = 0; yy < map.getHeight(); yy++) {
					int pixelAtual = pixel[xx + (yy * map.getWidth())];

					tiles[xx + (yy * WIDTH)] = new TileFloor(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_GRASS);
					if (pixelAtual == 0XFF009bdb) {
						tiles[xx + (yy * WIDTH)] = new TileWall(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_WATER);
					} else if (pixelAtual == 0xFF9e9e9e) {
						tiles[xx + (yy * WIDTH)] = new TileWall(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_WALL);
					}else if (pixelAtual == 0xFF78540a) {
						tiles[xx + (yy * WIDTH)] = new TileFloor(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_EARTH);
					}else if (pixelAtual == 0xFFfef69f) {
						tiles[xx + (yy * WIDTH)] = new TileFloor(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_SAND);
					}else if (pixelAtual == 0xFF006b33) {
						tiles[xx + (yy * WIDTH)] = new TileWall(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_TREE);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isSolid(int tileX, int tileY) {
		if (tileX < 0 || tileY < 0 || tileX >= WIDTH || tileY >= HEIGHT) {
			return true;
		}

		Tile t = tiles[tileX + tileY * WIDTH];
		return t.collision;
	}

	public void draw(Graphics2D g2, GamePanel gp) {

		int xStart = 0;
		int yStart = 0;
		int xEnd = WIDTH;
		int yEnd = HEIGHT;

		for (int x = xStart; x < xEnd; x++) {
			for (int y = yStart; y < yEnd; y++) {

				Tile t = tiles[x + (y * WIDTH)];
				t.draw(g2, gp);
			}
		}
	}
}
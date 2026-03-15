package world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class World {

	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;

	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));

			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();

			tiles = new Tile[WIDTH * HEIGHT];
			int[] pixels = new int[WIDTH * HEIGHT];

			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);

			for (int x = 0; x < WIDTH; x++) {
				for (int y = 0; y < HEIGHT; y++) {
					int pixel = pixels[x + (y * WIDTH)];
					int tileX = x * GamePanel.tileSize;
					int tileY = y * GamePanel.tileSize;

					tiles[x + (y * WIDTH)] = createTile(pixel, tileX, tileY);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Tile createTile(int pixel, int x, int y) {

		switch (pixel) {

		case 0xFF009045:
			return new TileFloor(x, y, Tile.TILE_GRASS);
		case 0xFF9e9e9e:
			return new TileWall(x, y, Tile.TILE_WALL);
		case 0xFF009bdb:
			return new TileWall(x, y, Tile.TILE_WATER);
		case 0xFF78540a:
			return new TileFloor(x, y, Tile.TILE_EARTH);
		case 0xFFfef69f:
			return new TileFloor(x, y, Tile.TILE_SAND);
		case 0xFF006b33:
			return new TileWall(x, y, Tile.TILE_TREE);
		case 0xFF888888:
			return new TileTall(x, y, Tile.TILE_TALL);
		}
		return new TileFloor(x, y, Tile.TILE_GRASS);
	}

	public static boolean isSolid(int tileX, int tileY) {
		if (tileX < 0 || tileY < 0 || tileX >= WIDTH || tileY >= HEIGHT) {
			return true;
		}

		Tile t = tiles[tileX + tileY * WIDTH];
		return t.collision;
	}

	public void renderComPlayer(Graphics2D g2, Player player) {

		List<TileTall> tallList = new ArrayList<>();

		for (int i = 0; i < tiles.length; i++) {
			Tile t = tiles[i];

			if (t instanceof TileTall) {
				tallList.add((TileTall) t);
			} else {
				t.draw(g2, player);
			}
		}

		tallList.sort((a, b) -> Integer.compare(a.y + a.getHeight(), b.y + b.getHeight()));

		int playerBaseY = player.worldY + 14 + player.solidArea.y + player.solidArea.height;

		for (TileTall tile : tallList) {
			int tileBaseY = tile.y + tile.getHeight();

			if (playerBaseY < tileBaseY) {
				player.draw(g2);
				playerBaseY = Integer.MAX_VALUE;
			}

			tile.draw(g2, player);
		}

		if (playerBaseY != Integer.MAX_VALUE) {
			player.draw(g2);
		}
	}

}
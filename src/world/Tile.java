package world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Player;
import main.GamePanel;

public class Tile {

	public static BufferedImage TILE_WALL = GamePanel.spriteSheetTile.getSprite(32, 0, 16, 16);
	public static BufferedImage TILE_TALL = GamePanel.spriteSheetTile.getSprite(48, 0, 16, 48);
	public static BufferedImage TILE_GRASS = GamePanel.spriteSheetTile.getSprite(0, 0, 16, 16);
	public static BufferedImage TILE_WATER = GamePanel.spriteSheetTile.getSprite(16, 0, 16, 16);
	public static BufferedImage TILE_EARTH = GamePanel.spriteSheetTile.getSprite(0, 16, 16, 16);
	public static BufferedImage TILE_SAND = GamePanel.spriteSheetTile.getSprite(16, 16, 16, 16);
	public static BufferedImage TILE_TREE = GamePanel.spriteSheetTile.getSprite(32, 16, 16, 16);

	public BufferedImage sprite;
	public int x, y;
	public boolean collision = false;

	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public int getWidth() {
		return GamePanel.tileSize;
	}

	public int getHeight() {
		return GamePanel.tileSize;
	}

	public void draw(Graphics2D g2, Player player) {

		int screenX = x - player.worldX + player.screenX;
		int screenY = y - player.worldY + player.screenY;

		if (x + GamePanel.tileSize > player.worldX - player.screenX
				&& x - GamePanel.tileSize < player.worldX + player.screenX
				&& y + GamePanel.tileSize > player.worldY - player.screenY
				&& y - GamePanel.tileSize < player.worldY + player.screenY) {

			g2.drawImage(sprite, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
		}
	}
}
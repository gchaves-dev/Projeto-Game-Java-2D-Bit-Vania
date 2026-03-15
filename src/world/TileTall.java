package world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Player;
import main.GamePanel;

public class TileTall extends Tile {

	public TileTall(int x, int y, BufferedImage sprite) {
		super(x, y - (sprite.getHeight() * GamePanel.scale - GamePanel.tileSize), sprite);
		this.collision = true;
	}

	@Override
	public int getHeight() {
		return sprite.getHeight() * GamePanel.scale;
	}

	@Override
	public int getWidth() {
		return sprite.getWidth() * GamePanel.scale;
	}
	
	@Override
	public void draw(Graphics2D g2, Player player) {

		int screenX = x - player.worldX + player.screenX;
		int screenY = y - player.worldY + player.screenY;

		if (x + getWidth() > player.worldX - player.screenX 
		 && x - getWidth() < player.worldX + player.screenX
		 && y + getHeight() > player.worldY - player.screenY
		 && y - getHeight() < player.worldY + player.screenY) {

			g2.drawImage(sprite, screenX, screenY, getWidth(), getHeight(), null);
		}
	}
}
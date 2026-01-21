package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheetPlayer {

	private BufferedImage spriteSheetPlayer;

	public SpriteSheetPlayer(String path) {
		try {
			spriteSheetPlayer = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {			
			e.printStackTrace();

		}
	}

	public BufferedImage getSprite(int x, int y, int width, int height) {
		return spriteSheetPlayer.getSubimage(x, y, width, height);

	}

}

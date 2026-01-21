package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheetMap {

	private BufferedImage spriteSheetMap;

	public SpriteSheetMap(String path) {
		try {
			spriteSheetMap = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {			
			e.printStackTrace();

		}
	}

	public BufferedImage getSprite(int x, int y, int width, int height) {
		return spriteSheetMap.getSubimage(x, y, width, height);

	}

}

package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheetObject {

	private BufferedImage spriteSheetObject;

	public SpriteSheetObject(String path) {
		try {
			spriteSheetObject = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {			
			e.printStackTrace();

		}
	}

	public BufferedImage getSprite(int x, int y, int width, int height) {
		return spriteSheetObject.getSubimage(x, y, width, height);

	}

}

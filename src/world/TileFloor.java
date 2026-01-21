package world;

import java.awt.image.BufferedImage;

public class TileFloor extends Tile {

	public TileFloor(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
		// TODO Auto-generated constructor stub
		this.collision = false;
	}

}
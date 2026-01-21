package world;

import java.awt.image.BufferedImage;

public class TileWall extends Tile {

    public TileWall(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
        // A linha mais importante!
        this.collision = true;
    }
}
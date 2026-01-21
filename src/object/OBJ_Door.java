package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Door extends SuperObject{
	
	public OBJ_Door() {
		
		name = "Door";
		
		image = GamePanel.spriteSheetObject.getSprite(0, 0, 16, 16);
		
		collision = true;		
	}
}

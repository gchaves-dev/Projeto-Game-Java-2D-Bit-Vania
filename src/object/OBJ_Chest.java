package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Chest extends SuperObject{
	
	public OBJ_Chest() {
		
		name = "Chest";
		
		image = GamePanel.spriteSheetObject.getSprite(32, 0, 16, 16);
		
		collision = false;		
	}
}

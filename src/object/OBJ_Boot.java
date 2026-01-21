package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Boot extends SuperObject{

	public OBJ_Boot() {	
	
		name = "Boot";
	
		image = GamePanel.spriteSheetObject.getSprite(48, 0, 16, 16);
	
		collision = false;		
	}
}

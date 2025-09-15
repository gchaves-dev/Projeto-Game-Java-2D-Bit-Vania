package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boot extends SuperObject{

public OBJ_Boot() {	
	
	name = "Boot";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

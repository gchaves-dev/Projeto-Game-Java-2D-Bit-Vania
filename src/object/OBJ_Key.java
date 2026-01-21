package object;

import main.GamePanel;

public class OBJ_Key extends SuperObject {

    public OBJ_Key() {

        name = "Key";
        
        image = GamePanel.spriteSheetObject.getSprite(16, 0, 16, 16);

        collision = false;
    }
}

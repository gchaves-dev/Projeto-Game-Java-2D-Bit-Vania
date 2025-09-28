package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import graphics.Sprite;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    
    public int right_dir = 0, left_dir = 1;    
    public int lastDir = right_dir;

    public Sprite sprite;

    private BufferedImage[] rightPlayer;
    private BufferedImage[] leftPlayer;

    private int frames = 0, maxFrames = 6, index = 0, maxIndex = 3;
    private boolean moved = false;

    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        sprite = new Sprite("/player/playerAnimation.png");

        rightPlayer = new BufferedImage[4];
        leftPlayer = new BufferedImage[4];
        
        for (int i = 0; i < 4; i++) {
            rightPlayer[i] = sprite.getSprite(i * 16, 0, 16, 16);
        }

        for (int i = 0; i < 4; i++) {
            leftPlayer[i] = sprite.getSprite(i * 16, 16, 16, 16);
        }

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = gp.tileSize / 4;
        solidArea.y = gp.tileSize / 4;         
        solidArea.width = gp.tileSize / 2;
        solidArea.height = gp.tileSize / 2;
        
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        

        this.setDefaultValues();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 31;
        worldY = gp.tileSize * 24;
        speed = 3;
        direction = "left";
    }

    public void update() {

        moved = false; 

        int moveX = 0;
        int moveY = 0;

        if (keyH.upPressed) {
            moveY = -speed;
            direction = "up";
            moved = true;
        }
        if (keyH.downPressed) {
            moveY = speed;
            direction = "down";
            moved = true;
        }
        if (keyH.leftPressed) {
            moveX = -speed;
            direction = "left";
            moved = true;
            lastDir = left_dir; 
        }
        if (keyH.rightPressed) {
            moveX = speed;
            direction = "right";
            moved = true;
            lastDir = right_dir; 
        }
        
        collisionOn = false;
        gp.cChecker.checkTile(this);
        int objIndex = gp.cChecker.checkObect(this, true);
        pickUpObject(objIndex);
        
        if (!collisionOn) {

            collisionOn = false;
            int oldWorldX = worldX;
            worldX += moveX;
            gp.cChecker.checkTile(this);
            if (collisionOn) {
                worldX = oldWorldX;
            }

            collisionOn = false;
            int oldWorldY = worldY;
            worldY += moveY;
            gp.cChecker.checkTile(this);
            if (collisionOn) {
                worldY = oldWorldY;
            }
        }
        
        if (moved) {
            frames++;
            if (frames >= maxFrames) {
                frames = 0;
                index++;
                if (index > maxIndex)
                    index = 0;
            }
        } else {
            index = 0; 
        }
    }

    public void pickUpObject(int i) {

        if (i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Key: " + hasKey);
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    System.out.println("Key: " + hasKey);
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image;
        
        if (lastDir == right_dir) {
            image = rightPlayer[index];
        } else {
            image = leftPlayer[index];
        }
        
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}

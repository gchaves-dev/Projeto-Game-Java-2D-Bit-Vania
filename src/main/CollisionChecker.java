package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;

	}

	public void checkTile(Entity entity) {
        int leftX = entity.worldX + entity.solidArea.x;
        int rightX = leftX + entity.solidArea.width;
        int topY = entity.worldY + entity.solidArea.y;
        int bottomY = topY + entity.solidArea.height;

        int dx = 0, dy = 0;
        if (entity.direction.equals("up")) dy = -entity.speed;
        else if (entity.direction.equals("down")) dy = entity.speed;
        else if (entity.direction.equals("left")) dx = -entity.speed;
        else if (entity.direction.equals("right")) dx = entity.speed;

        int col1 = (leftX + dx) / gp.tileSize;
        int col2 = (rightX + dx) / gp.tileSize;
        int row1 = (topY + dy) / gp.tileSize;
        int row2 = (bottomY + dy) / gp.tileSize;

        int tile1 = gp.tileM.mapTileNum[col1][row1];
        int tile2 = gp.tileM.mapTileNum[col2][row2];

        if (gp.tileM.tile[tile1].collision || gp.tileM.tile[tile2].collision)
            entity.collisionOn = true;
    }
	
	public int checkObect(Entity entity, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < gp.obj.length; i++) {
			
			if(gp.obj[i] != null) {
				
				// Get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// Get the object's solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						//System.out.println("up collision!");
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						//System.out.println("down collision!");
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						//System.out.println("left collision!");
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						//System.out.println("right collision!");
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;				
			
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
										
			}
			
		}
		
		return index;
		
	}
}

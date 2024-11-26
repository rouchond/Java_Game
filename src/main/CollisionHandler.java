package main;

import entity.Entity;

public class CollisionHandler {

    GamePanel gp;

    /**
     * Creates a CollisionHandler
     * @param gp The Game Panel
     */
    public CollisionHandler(GamePanel gp){
        this.gp = gp;
    }

    /**
     * Checks adjacent tiles in the direction entity is moving in to see if there's a
     * valid collision
     * @param entity The entity that is being checked for a collision
     */
    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.solidAreaWorldX;
        int entityRightWorldX = entity.solidAreaWorldX + entity.solidArea.width;
        int entityTopWorldY = entity.solidAreaWorldY;
        int entityBottomWorldY = entity.solidAreaWorldY + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;
        boolean isColliding;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                isColliding = (gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum1].collision) || (gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum2].collision);

                if (isColliding){
                    entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                isColliding = (gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum1].collision) || (gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum2].collision);
                if (isColliding){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                isColliding = (gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum1].collision) || (gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum2].collision);
                if (isColliding){
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                isColliding = (gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum1].collision) || (gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum2].collision);
                if (isColliding){
                    entity.collisionOn = true;
                }

                break;
        }
    }

}

package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    /**
     * X,Y coordinates of the Entity
     */
    public int worldX,worldY;
    /**
     * Units the entity moves
     */
    public int speed;

    /**
     * Entity Sprites (will change the implementation soon)
     */
    public BufferedImage img;

    /**
     * Direction the entity is facing
     */
    public String direction;

    /**
     * Timer until sprite changes
     */
    public int spriteCounter = 0;

    /**
     * Current sprite in the animation rotation
     */
    public int spriteNum = 1;

    /**
     * The area that represents the collision box
     */
    public Rectangle solidArea;

    /**
     * The area of an entity collider without collision logic
     */
    public int solidAreaDefaultX, solidAreaDefaultY;

    /**
     * A state representing if an entity is currently in a collision
     */
    public boolean collisionOn = false;
}

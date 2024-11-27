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
     * The cap of the entity movement
     */
    public int maxSpeed;

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
     * The area that represents the collision box relative to the screen
     */
    public Rectangle solidArea;

    /**
     * The area of an entity collider relative to the world
     */
    public int solidAreaWorldX, solidAreaWorldY;

    /**
     * A state representing if an entity is currently in a collision
     */
    public boolean collisionOn = false;

    /**
     * A state representing if an entity can move
     */
    public boolean canMove = false;

    public int bumpPos = 0;
}

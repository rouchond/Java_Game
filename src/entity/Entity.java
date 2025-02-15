package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    /**
     * X,Y coordinates of the Entity
     */
    public int worldX,worldY;

    /**
     * Entity Sprites (will change the implementation soon)
     */
    public BufferedImage img;

    /**
     * Direction the entity is facing
     */
    public Direction direction;

    /**
     * The current pixels the player is moving per frame
     */
    public double xSpeed, ySpeed = 0;

    /**
     * The cap of the x and y speeds.
     */
    public double maxXSpeed, maxYSpeed;

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
     * States representing the type of tile collision an entity is in.
     */
    public boolean isGrounded, touchedCeiling, touchedWall;

    public int bumpPos = 0;

    /**
     * Sets all collision related variables to false
     */
    public void resetCollisions () {
        isGrounded = false;
        touchedCeiling = false;
        touchedWall = false;
        collisionOn = false;
    }
}

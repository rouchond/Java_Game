package entity;

import main.CollisionHandler;
import main.GamePanel;
import main.KeyHandler;
import main.PlayerController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    /**
     * The X position of the screen
     */
    public final int screenX;

    /**
     * The Y position of the screen
     */
    public final int screenY;

    /**
     * The current speed the player is falling
     */
    public int fallSpeed;

    /**
     * The max speed the player can fall
     */
    public int maxFallSpeed;

    GamePanel gp;
    KeyHandler keyH;
    CollisionHandler colHandler;
    PlayerController pController = new PlayerController(this);

    /**
     * The offset applied on the Player's collider rect world position
     */
    public int colliderOffsetX, colliderOffsetY;


    /**
     * Create the player
     * @param gp The Game Panel
     * @param keyH The Key Handler
     */
    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;
        this.colHandler = new CollisionHandler(this.gp);

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();

        int xOffset = (gp.tileSize / 8);
        int yOffset = (gp.tileSize / 4);
        colliderOffsetX = xOffset;
        colliderOffsetY = yOffset;

        solidArea.x = screenX + xOffset;
        solidArea.y = screenY + yOffset;
        solidArea.width = gp.tileSize - yOffset;
        solidArea.height = gp.tileSize - yOffset;

        setDefaultValues();
        getPlayerImage();
    }

    /**
     * Set initial position, speed, and direction of the player
     */
    private void setDefaultValues(){
        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 2;
        solidAreaWorldX = worldX + colliderOffsetY;
        solidAreaWorldY = worldY + colliderOffsetY;
        speed = 2;
        maxSpeed = 7;
        fallSpeed = 2;
        maxFallSpeed = 10;
        direction = "down";
    }

    /**
     * Load the sprites for the player
     */
    private void getPlayerImage(){
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Ronin.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * All the logic for the player that will be updated by the FPS factor
     */
    public void update () {

        collisionOn = false;
        colHandler.checkTile(this);

        pController.updateMovement();
    }

    /**
     * Draw the player based on the direction it's facing
     * Handles the offset of the camera
     * @param g2 2D Graphics object from a panel
     */
    public void draw (Graphics2D g2) {

        g2.drawImage(img, screenX, screenY, gp.tileSize, gp.tileSize, null);

        //Debug Drawings
        g2.setColor(Color.red);
        g2.drawRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);
    }
}

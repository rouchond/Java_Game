package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    /**
     * The X position of the screen
     */
    //public final int screenX;

    /**
     * The Y position of the screen
     */
    //public final int screenY;

    GamePanel gp;
    KeyHandler keyH;

    /**
     * The amount of keys the player holds
     */
    public int playerKeys = 0;


    /**
     * Create the player
     * @param gp The Game Panel
     * @param keyH The Key Handler
     */
    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        //screenX = gp.screenWidth/2 - (gp.tileSize/2);
       // screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    /**
     * Set initial position, speed, and direction of the player
     */
    private void setDefaultValues(){
        worldX = 100;
        worldY = 250;
        speed = 5;
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

    private void updateMovement() {
        if (keyH.leftPressed){
            worldX -= 1;
        }
        else if (keyH.rightPressed) {
            worldX += 1;
        }
    }

    /**
     * All the logic for the player that will be updated by the FPS factor
     */
    public void update () {
        updateMovement();
    }

    /**
     * Draw the player based on the direction it's facing
     * Handles the offset of the camera
     * @param g2 2D Graphics object from a panel
     */
    public void draw (Graphics2D g2) {
        g2.drawImage(img, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}

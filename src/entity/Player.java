package entity;

import main.GamePanel;

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

    GamePanel gp;
    //KeyHandler keyH;

    /**
     * The amount of keys the player holds
     */
    public int playerKeys = 0;


    /**
     * Create the player
     * @param gp The Game Panel
     */
    public Player(GamePanel gp){

        this.gp = gp;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

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
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
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


    /**
     * All the logic for the player that will be updated by the FPS factor
     */
    public void update () {

    }

    /**
     * Draw the player based on the direction it's facing
     * Handles the offset of the camera
     * @param g2 2D Graphics object from a panel
     */
    public void draw (Graphics2D g2) {
        g2.drawImage(img, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}

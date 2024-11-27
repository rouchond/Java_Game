package entity;

import main.CollisionHandler;
import main.GamePanel;
import main.KeyHandler;
import main.controllers.PlayerController;
import main.states.PlayerFalling;
import main.states.PlayerIdle;
import main.states.PlayerJumping;
import main.states.PlayerMoving;

import javax.imageio.ImageIO;
import java.awt.*;
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
     * The value fall speed gets reset to.
     */
    public float minFallSpeed;

    /**
     * The current speed the player is falling
     */
    public float fallSpeed;

    /**
     * The max speed the player can fall
     */
    public int maxFallSpeed;

    GamePanel gp;
    KeyHandler keyH;
    CollisionHandler colHandler;
    PlayerController pController;

    public PlayerIdle idle;
    public PlayerMoving moving;
    public PlayerJumping jumping;
    public PlayerFalling falling = new PlayerFalling();


    /**
     * The offset applied on the Player's collider rect world position
     */
    public int colliderOffset;


    /**
     * Create the player
     * @param gp The Game Panel
     * @param keyH The Key Handler
     */
    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;
        this.colHandler = new CollisionHandler(this.gp);
        this.pController = new PlayerController(this, this.keyH);

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();

        int xOffset = (gp.tileSize / 8);
        int yOffset = (gp.tileSize / 6);
        int heightOffset = (gp.tileSize / 6);
        int widthOffset = (gp.tileSize / 3);
        colliderOffset = yOffset;

        solidArea.x = screenX + xOffset;
        solidArea.y = screenY + yOffset;
        solidArea.width = gp.tileSize - widthOffset;
        solidArea.height = gp.tileSize - heightOffset;

        this.moving = new PlayerMoving(this.keyH);
        this.jumping = new PlayerJumping(this.keyH);
        this.idle = new PlayerIdle(this.keyH);

        setDefaultValues();
        getPlayerImage();
        pController.setupState(falling);
    }

    /**
     * Set initial position, speed, and direction of the player
     */
    private void setDefaultValues(){
        worldX = gp.tileSize * 5;
        worldY = 0;
        solidAreaWorldX = worldX + colliderOffset;
        solidAreaWorldY = worldY + colliderOffset;
        speed = 2;
        minFallSpeed = 2;
        maxSpeed = 7;
        fallSpeed = minFallSpeed;
        maxFallSpeed = 10;
        direction = Direction.DOWN;
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

        // if colliding with wall, push off wall
        if (touchedWall) {
            if (direction == Direction.RIGHT){
                worldX -= speed;
                solidAreaWorldX -= speed;
            } else if (direction == Direction.LEFT) {
                worldX += speed;
                solidAreaWorldX += speed;
            }
        }

        resetCollisions();

        colHandler.checkTile(this);

        pController.update();
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

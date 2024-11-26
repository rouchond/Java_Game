package main;

import entity.Player;

public class PlayerController {

    boolean isGrounded;
    KeyHandler keyH = new KeyHandler();
    Player player;

    public PlayerController (Player player) {
        isGrounded = false;
        this.player = player;

    }

    /**
     * Updates the position of the player based on inputs
     */
    public void updateMovement() {
        // TODO: Implement playerMovement

        if (player.collisionOn && player.direction.equals("down")) {
            isGrounded = true;
        } else if (!player.collisionOn) {
            isGrounded = false;
        }

        //Gravity
        if (!isGrounded) {
            player.direction = "down";
            player.worldY += player.fallSpeed;
            player.solidAreaWorldY = player.worldY + player.colliderOffsetY;
        }

    }

}

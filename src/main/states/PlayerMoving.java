package main.states;

import entity.Direction;
import main.KeyHandler;
import main.controllers.PlayerController;

public class PlayerMoving implements State<PlayerController> {

    /**
     * A representation for the state's completeness
     */
    private boolean isComplete = false;
    KeyHandler keyH;

    public PlayerMoving (KeyHandler keyH) {this.keyH = keyH;}

    @Override
    public boolean isComplete() {return this.isComplete;}

    @Override
    public void setIsComplete(boolean status) {isComplete = status;}

    @Override
    public void enterState(PlayerController controller) {
        // Animations go here
    }

    @Override
    public void updateState(PlayerController controller) {
//        if (!controller.isGrounded) {
//            controller.changeState(controller.player.falling);
//        }
        if (keyH.jumpPressed) {
            controller.changeState(controller.player.jumping);
        }
        else if (keyH.leftPressed) {
            if (controller.player.direction == Direction.RIGHT && controller.player.xSpeed != 0) {
                slowDown(controller);
            }
            else {
                moveLeft(controller);
            }
        }
        else if (keyH.rightPressed) {
            if (controller.player.direction == Direction.LEFT && controller.player.xSpeed != 0) {
                slowDown(controller);
            }
            else {
                moveRight(controller);
            }
        }
        else {
            // Only go into idle if the player's horizontal speed is 0
            if (controller.player.xSpeed == 0) {
                controller.changeState(controller.player.idle);
            }
            // Slow down the player since no key is being pressed
            else {
                slowDown(controller);
            }
        }
    }

    private void moveLeft(PlayerController controller) {
        if (!controller.player.touchedWall) {
            controller.player.direction = Direction.LEFT;
            controller.player.worldX -= getSpeed(true, controller);
            controller.player.solidAreaWorldX = controller.player.solidAreaWorldX - getSpeed(true, controller);
        }
    }

    private void moveRight(PlayerController controller) {
        if (!controller.player.touchedWall) {
            controller.player.direction = Direction.RIGHT;
            controller.player.worldX += getSpeed(true, controller);
            controller.player.solidAreaWorldX = controller.player.solidAreaWorldX + getSpeed(true, controller);
        }
    }

    private void slowDown(PlayerController controller) {
        if (!controller.player.touchedWall){
        if (controller.player.direction == Direction.RIGHT) {
            controller.player.worldX += getSpeed(false, controller);
            controller.player.solidAreaWorldX = controller.player.solidAreaWorldX + getSpeed(false, controller);
        } else if (controller.player.direction == Direction.LEFT) {
            controller.player.worldX -= getSpeed(false, controller);
            controller.player.solidAreaWorldX = controller.player.solidAreaWorldX - getSpeed(false, controller);
        }
        }
    }

    private int getSpeed(boolean accel, PlayerController controller) {
        double deltaTime = controller.player.gp.deltaTime;
        double quickAccel = 0.1 * deltaTime;
        double slowAccel = 0.05 * deltaTime;
        double quickDeccel = 0.2 * deltaTime;
        double speed;

        // Speed up player
        if (accel) {
            //Quick burst of movement
            if (controller.player.xSpeed < (controller.player.maxXSpeed / 3)) {
                speed = controller.player.xSpeed + quickAccel;
            }
            // Slow Acceleration to top speed
            else{
                speed = Math.min(controller.player.xSpeed + slowAccel, controller.player.maxXSpeed);
            }
        }

        // Slow down player
        else {
            speed = Math.max(controller.player.xSpeed - quickDeccel, 0);
        }

        controller.player.xSpeed = speed;
        return (int)(speed * deltaTime);
    }

    @Override
    public void exitState(PlayerController controller) {setIsComplete(true);}
}

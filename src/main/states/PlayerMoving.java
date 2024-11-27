package main.states;

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
            moveLeft(controller);
        }
        else if (keyH.rightPressed) {
            moveRight(controller);
        }
        else {
            controller.changeState(controller.player.idle);
        }
    }

    private void moveLeft(PlayerController controller) {
        if (controller.player.canMove) {
            controller.player.direction = "left";
            controller.player.worldX -= controller.player.speed;
            controller.player.solidAreaWorldX = controller.player.solidAreaWorldX - controller.player.speed;
        }
    }

    private void moveRight(PlayerController controller) {
        if (controller.player.canMove) {
            controller.player.direction = "right";
            controller.player.worldX += controller.player.speed;
            controller.player.solidAreaWorldX = controller.player.solidAreaWorldX + controller.player.speed;
        }
    }

    @Override
    public void exitState(PlayerController controller) {setIsComplete(true);}
}

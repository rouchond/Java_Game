package main.states;

import entity.Direction;
import main.controllers.PlayerController;

public class PlayerFalling implements State<PlayerController> {

    private boolean isComplete = false;

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public void setIsComplete(boolean status) {
        this.isComplete = status;
    }

    @Override
    public void enterState(PlayerController controller) {
        // Play Anim
    }

    @Override
    public void updateState(PlayerController controller) {
        double deltaTime = controller.player.gp.deltaTime;
        // Just made contact with ground
        if (controller.player.collisionOn && controller.player.direction == Direction.DOWN) {
            controller.player.isGrounded = true;
            controller.player.worldY = controller.player.bumpPos;
            controller.player.ySpeed = 0;
            controller.changeState(controller.player.idle);
        } else if (!controller.player.collisionOn) {
            controller.player.isGrounded = false;
        }

        //Gravity
        if (!controller.player.isGrounded) {
            controller.player.direction = Direction.DOWN;
            controller.player.worldY += (int) (controller.player.ySpeed * deltaTime);

            if (controller.player.ySpeed < controller.player.maxYSpeed) {
                controller.player.ySpeed += controller.gravity * deltaTime;
            }
            controller.player.solidAreaWorldY = controller.player.worldY + controller.player.colliderOffset;
        }
    }

    @Override
    public void exitState(PlayerController controller) {
        setIsComplete(true);
    }
}

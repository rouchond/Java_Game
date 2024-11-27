package main.states;

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
        if (controller.player.collisionOn && controller.player.direction.equals("down")) {
            controller.isGrounded = true;
            controller.player.worldY = controller.player.bumpPos;
            controller.player.fallSpeed = controller.player.minFallSpeed;
            controller.changeState(controller.player.idle);
        } else if (!controller.player.collisionOn) {
            controller.isGrounded = false;
        }

        //Gravity
        if (!controller.isGrounded) {
            controller.player.direction = "down";
            controller.player.worldY += (int) controller.player.fallSpeed;

            if (controller.player.fallSpeed < controller.player.maxFallSpeed) {
                controller.player.fallSpeed += controller.gravity;
                System.out.println(controller.player.fallSpeed);
            }
            controller.player.solidAreaWorldY = controller.player.worldY + controller.player.colliderOffset;
        }
    }

    @Override
    public void exitState(PlayerController controller) {
        setIsComplete(true);
    }
}

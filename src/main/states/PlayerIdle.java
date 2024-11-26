package main.states;

import main.KeyHandler;
import main.controllers.PlayerController;


public class PlayerIdle implements State<PlayerController> {

    /**
     * A representation for the state's completeness
     */
    private boolean isComplete = false;

    KeyHandler keyH;

    public PlayerIdle (KeyHandler keyH) {
        this.keyH = keyH;
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public void setIsComplete(boolean status) {
        isComplete = status;
    }

    @Override
    public void enterState(PlayerController controller) {
        // Play Anim
    }

    @Override
    public void updateState(PlayerController controller) {
        if (keyH.leftPressed || keyH.rightPressed) {
            controller.changeState(controller.player.moving);
        } else if (!controller.isGrounded) {
            controller.changeState(controller.player.falling);
        } else if (keyH.jumpPressed) {
            controller.changeState(controller.player.jumping);
        }
    }

    @Override
    public void exitState(PlayerController controller) {
        setIsComplete(true);
    }
}

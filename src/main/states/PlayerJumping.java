package main.states;

import main.KeyHandler;
import main.controllers.PlayerController;

public class PlayerJumping implements State<PlayerController> {

    /**
     * A representation for the state's completeness
     */
    private boolean isComplete = false;

    KeyHandler keyH;

    public PlayerJumping (KeyHandler keyH) {this.keyH = keyH;}

    @Override
    public boolean isComplete() {return isComplete;}

    @Override
    public void setIsComplete(boolean status) {isComplete = status;}

    @Override
    public void enterState(PlayerController controller) {
        // Animation Here
    }

    @Override
    public void updateState(PlayerController controller) {
        // Jump Logic
    }

    @Override
    public void exitState(PlayerController controller) {setIsComplete(true);}
}

package main.controllers;

import entity.Player;
import main.KeyHandler;
import main.states.*;

public class PlayerController implements EntityController<PlayerController> {

    /**
     * Gravity
     */
    public final float gravity = 0.25f;

    public Player player;
    KeyHandler keyH;
    public State<PlayerController> currentState;

    public PlayerController (Player player, KeyHandler keyH) {
        this.player = player;
        this.keyH = keyH;
    }


    @Override
    public void setupState(State<PlayerController> startState) {
        currentState = startState;
        currentState.enterState(this);
    }

    @Override
    public void changeState(State<PlayerController> newState) {
        currentState.exitState(this);
        currentState = newState;
        currentState.enterState(this);
    }

    public void update() {currentState.updateState(this);}

}

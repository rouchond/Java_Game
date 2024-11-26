package main.states;

import main.controllers.EntityController;

public interface State<T extends EntityController<T>> {

    /**
     * A getter for the state's completeness
     */
    boolean isComplete();

    /**
     * A setter for the isComplete variable
     * @param status What the status of isComplete will be changed to
     */
    void setIsComplete(boolean status);

    /**
     * Sets up the state to then call update continuously
     * @param controller The state controller of the entity.
     */
    void enterState(T controller);

    /**
     * Updates the state of the entity. This is where the bulk of the entity's logic lies
     * @param controller The state controller of the entity.
     */
    void updateState(T controller);

    /**
     * Cleans up the state before transitioning out into another state
     * @param controller The state controller of the entity.
     */
    void exitState(T controller);


}

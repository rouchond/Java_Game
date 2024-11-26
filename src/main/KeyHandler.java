package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; //Look over these docs to understand more

public class KeyHandler implements KeyListener {

    public boolean keyDown, leftPressed, rightPressed, jumpPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // Will not implement
    }

    /**
     * Checks if valid key was pressed down
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Handling AD (horizontal) input

        if (code == KeyEvent.VK_A){
            leftPressed = true;
            keyDown = true;
        }

        if (code == KeyEvent.VK_D){
            rightPressed = true;
            keyDown = true;
        }

        if (code == KeyEvent.VK_SPACE) {
            jumpPressed = true;
            keyDown = true;
        }
    }

    /**
     * Check's if a valid key was released
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }

        if (code == KeyEvent.VK_SPACE) {
            jumpPressed = false;
        }

        if (!rightPressed && !leftPressed && !jumpPressed) {
            keyDown = false;
        }

    }
}

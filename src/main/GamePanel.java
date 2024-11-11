package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen settings

    /**
     * The size of a tile before rescaling
     */
    final int originalTileSize = 16; // 16x16 tile

    /**
     * Scaling factor of the game
     */
    final int scale = 3;

    /**
     * Size of the tile after scaling is applied
     */
    public final int tileSize = originalTileSize * scale; // 48x48 tile

    /**
     * Width of the screen (unit is per tile)
     */
    public final int maxScreenCol = 16; // 16 tiles

    /**
     * Height of the screen (unit is per tile)
     */
    public final int maxScreenRow = 12; // 12 tiles

    /**
     * Width of screen scaled to tileSize
     */
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels

    /**
     * Height of Screen scaled to tileSize
     */
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    /**
     * An empty black game window that can listen for key inputs
     * Default Dimensions: 768 Pixels x 576 Pixels
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Can improve game's rendering performance
        //this.addKeyListener(keyH);
        this.setFocusable(true); // Game panel can be "focused" to receive key input
    }

    /**
     * When the thread is started, run is called. run handles the time (FPS) in
     * the game.
     */
    @Override
    public void run() {

    }
}

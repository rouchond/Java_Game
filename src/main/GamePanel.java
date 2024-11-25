package main;

import entity.Player;
import tile.TileManager;

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

    // World Settings

    /**
     * The width of the world (unit is per tile)
     */
    public final int maxWorldCol = 20;

    /**
     * The height of the world (unit is per tile)
     */
    public final int maxWorldRow = 20;


    //FPS

    /**
     * Frames per second of our game
     */
    int fps = 60;

    /**
     * The current FPS of the user
     */
    public int currentFPS;

    // System

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    TileManager tileM = new TileManager(this);

    // Entities

    public Player player = new Player(this, keyH);

    /**
     * An empty black game window that can listen for key inputs
     * Default Dimensions: 768 Pixels x 576 Pixels
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true); // Can improve game's rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true); // Game panel can be "focused" to receive key input
    }



    /**
     * Instantiating a new game thread with the panel and starting the thread
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Update the state of the game based on the FPS
     */
    public void update()
    {
        player.update();
    }

    /**
     * When the thread is started, run is called. run handles the time (FPS) in
     * the game.
     */
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / fps; //converts FPS to nanoseconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        //int drawCount = 0;

        while (gameThread != null) {

            /*
            Check the current time, then determine how much time
            has passed and divide it by the interval, then add
            it to delta. Then update lastTime to currentTime

            Once delta reaches the drawInterval (1), update, and
            repaint. Then reset delta.

             */

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime); // Using to track FPS
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint(); // This is how you call paintComponent
                delta--;
                //drawCount++;
            }

            // Update FPS count
            if (timer >= 1000000000) {
                //currentFPS = drawCount;
                //drawCount = 0;
                timer = 0;
            }
        }
    }
    /**
     *
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Tile Manager
        tileM.draw(g2);

        // Player
        player.draw(g2);


    }
}

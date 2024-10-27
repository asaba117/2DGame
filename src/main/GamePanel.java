package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel  extends JPanel implements Runnable {
    // Screen Settings
    final int originalTileSize = 16; //16x16 Tiles (Objects and Characters)
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 Tiles (This is to help scale up image on modern resolutions)
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60; // Game FPS

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this, keyHandler);



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread.isAlive()) {


            update(); // Updates the information depending on character position.

            repaint(); // Draws the screen with updated information.

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000000;

                if(remainingTime < 0) {
                    remainingTime = 0; // To check if allocated time is used
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //System.out.println("Game is looping");
        }
    }
    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileManager.draw(g2d);

        player.draw(g2d);

        g2d.dispose(); // Save memory.
    }
}

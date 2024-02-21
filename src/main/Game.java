package main;

import entities.Player;

import java.awt.*;

public class Game implements Runnable {
   private GameWindow gameWindow;
   private GamePanel gamePanel;
   private Thread gameThread;
   private final int FPS_SET = 120;
   private final int UPS_SET = 200;

   private Player player;

   public Game() {
      initClasses();
      gamePanel = new GamePanel(this);
      gameWindow = new GameWindow(gamePanel);
      gamePanel.requestFocus();


      startGameLoop();

   }

   private void initClasses() {
      player = new Player(200, 200);
   }


   private void startGameLoop() {
      gameThread = new Thread(this);
      gameThread.start();
   }

   public void update() {
      player.update();

   }

   public void render(Graphics g) {
      player.render(g);
   }

   @Override
   public void run() {
      double timePerUpdate = 1000000000.0 / UPS_SET; //time between updates
      double timePerFrame = 1000000000.0 / FPS_SET; //nanoseconds

      long previousTime = System.nanoTime();

      int frames = 0;
      int updates = 0;
      long lastCheck = System.currentTimeMillis();

      double deltaU = 0;
      double deltaF = 0;

      while (true) {

         long currentTime = System.nanoTime();

         deltaU += (currentTime - previousTime) / timePerUpdate;
         deltaF += (currentTime - previousTime) / timePerFrame;
         previousTime = currentTime;

         //update
         if (deltaU >= 1) {
            update();
            updates++;
            deltaU--;
         }

         //render
         if (deltaF >= 1) {
            gamePanel.repaint();
            frames++;
            deltaF--;

         }

         if (System.currentTimeMillis() - lastCheck >= 1000) {
            lastCheck = System.currentTimeMillis();
            System.out.println("fps: " + frames + " | UPS: " + updates);
            frames = 0;
            updates = 0;
         }

      }

   }

   public void windowFocusLost() {
      player.resetDirBooleans();
   }

   public Player getPlayer() {
      return player;
   }


}

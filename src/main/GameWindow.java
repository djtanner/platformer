package main;

import javax.swing.*;

public class GameWindow {
   private JFrame jframe;
   private final int WIDTH = 400;
   private final int HEIGHT = 400;

   public GameWindow(GamePanel gamePanel) {
      jframe = new JFrame();

      jframe.setSize(WIDTH, HEIGHT);
      jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      jframe.add(gamePanel);
      jframe.setLocationRelativeTo(null); //spawns window in center of screen
      jframe.setVisible(true);
   }
}

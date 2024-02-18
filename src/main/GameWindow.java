package main;

import javax.swing.*;

public class GameWindow {
   private JFrame jframe;
   public static final int WIDTH = 1280;
   public static final int HEIGHT = 800;

   public GameWindow(GamePanel gamePanel) {
      jframe = new JFrame();

      //jframe.setSize(WIDTH, HEIGHT);
      jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      jframe.add(gamePanel);
      jframe.setLocationRelativeTo(null); //spawns window in center of screen

      jframe.setResizable(false);
      jframe.pack();
      jframe.setVisible(true);

   }
}

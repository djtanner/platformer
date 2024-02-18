package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
   private MouseInputs mouseInputs;
   private float xDelta = 100;
   private float yDelta = 100;

   private float xDir = .01f;
   private float yDir = .01f;
   private int frames;
   private long lastCheck = 0;

   private Color color;

   public GamePanel() {
      mouseInputs = new MouseInputs(this);
      addKeyListener(new KeyboardInputs(this));
      addMouseListener(mouseInputs);
      addMouseMotionListener(mouseInputs);

      frames = 0;

      xDelta = 0;
      yDelta = 0;

      color = Color.BLUE;
   }

   public void changeXDelta(int value) {
      this.xDelta += value;

   }

   public void changeYDelta(int value) {
      this.yDelta += value;

   }

   public void setRectPosition(int x, int y) {
      this.xDelta = x;
      this.yDelta = y;

   }

   public void paintComponent(Graphics g) {
      //super.paintComponent(g);

      updateRectangle();
      frames++;
      if (System.currentTimeMillis() - lastCheck >= 1000) {
         lastCheck = System.currentTimeMillis();
         System.out.println("fps: " + frames);
         frames = 0;
      }
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(color);
      g2.fillRect((int) xDelta, (int) yDelta, 200, 50);
      repaint();


   }

   public void updateRectangle() {
      xDelta += xDir;
      yDelta += yDir;

      if (xDelta > GameWindow.WIDTH || xDelta < 0) {
         xDir *= -1;
         color = getRandomColor();
      }
      if (yDelta > GameWindow.HEIGHT || yDelta < 0) {
         yDir *= -1;
         color = getRandomColor();
      }
   }

   private Color getRandomColor() {
      int r = (int) (Math.random() * 255);
      int b = (int) (Math.random() * 255);
      int g = (int) (Math.random() * 255);


      return new Color(r, g, b);
   }


}



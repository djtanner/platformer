package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
   private MouseInputs mouseInputs;
   private float xDelta;
   private float yDelta;

   private float xDir = 1f;
   private float yDir = 1f;

   private long lastCheck = 0;

   private Color color;
   private Random random;

   private ArrayList<MyRect> listRects;

   public GamePanel() {
      mouseInputs = new MouseInputs(this);
      addKeyListener(new KeyboardInputs(this));
      addMouseListener(mouseInputs);
      addMouseMotionListener(mouseInputs);

      xDelta = 0;
      yDelta = 0;

      listRects = new ArrayList<>();
      color = Color.BLUE;

      random = new Random();
   }

   public void changeXDelta(int value) {
      this.xDelta += value;

   }

   public void changeYDelta(int value) {
      this.yDelta += value;

   }

  /* public void setRectPosition(int x, int y) {
      this.xDelta = x;
      this.yDelta = y;

   }*/

   public void spawnRect() {
      MyRect rect = new MyRect();
      listRects.add(rect);
   }

   public void paintComponent(Graphics g) {
      //super.paintComponent(g);
      for (MyRect rect : listRects) {
         rect.updateRectangle();
         rect.draw(g);
      }

      updateRectangle();

      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(color);
      g2.fillRect((int) xDelta, (int) yDelta, 200, 50);


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

   public class MyRect {
      private float xDelta;
      private float yDelta;

      private float xDir = 1f;
      private float yDir = 1f;
      private Color color;

      public MyRect() {
         xDelta = (float) (Math.random() * GameWindow.WIDTH);
         yDelta = (float) (Math.random() * GameWindow.HEIGHT);


      }

      public void updateRectangle() {
         xDelta += xDir;
         yDelta += yDir;

         if (xDelta > GameWindow.WIDTH || xDelta < 0) {
            xDir *= -1;
            color = newColor();
         }
         if (yDelta > GameWindow.HEIGHT || yDelta < 0) {
            yDir *= -1;
            color = newColor();
         }
      }

      private Color newColor() {
         return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
      }

      public void draw(Graphics g) {
         Graphics2D g2 = (Graphics2D) g;
         g2.setColor(this.color);
         g2.fillRect((int) xDelta, (int) yDelta, 50, 50);
      }


   }


   private Color getRandomColor() {
      int r = (int) (Math.random() * 255);
      int g = (int) (Math.random() * 255);
      int b = (int) (Math.random() * 255);


      return new Color(r, g, b);
   }


}



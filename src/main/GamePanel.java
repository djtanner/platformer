package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
   private MouseInputs mouseInputs;
   private int xDelta = 100;
   private int yDelta = 100;

   public GamePanel() {
      mouseInputs = new MouseInputs(this);
      addKeyListener(new KeyboardInputs(this));
      addMouseListener(mouseInputs);
      addMouseMotionListener(mouseInputs);

      xDelta = 0;
      yDelta = 0;
   }

   public void changeXDelta(int value) {
      this.xDelta += value;
      repaint();
   }

   public void changeYDelta(int value) {
      this.yDelta += value;
      repaint();
   }

   public void setRectPosition(int x, int y) {
      this.xDelta = x;
      this.yDelta = y;
      repaint();
   }

   public void paintComponent(Graphics g) {
      //super.paintComponent(g);

      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(Color.BLUE);
      g2.fillRect(xDelta, yDelta, 200, 50);

   }

}

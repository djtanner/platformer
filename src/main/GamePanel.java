package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
   private MouseInputs mouseInputs;

   public GamePanel() {
      mouseInputs = new MouseInputs();
      addKeyListener(new KeyboardInputs());
      addMouseListener(mouseInputs);
      addMouseMotionListener(mouseInputs);

   }

   public void paintComponent(Graphics g) {
      //super.paintComponent(g);

      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(Color.BLUE);
      g2.fillRect(100, 100, 200, 50);

   }

}

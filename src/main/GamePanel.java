package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {

   public GamePanel() {
      addKeyListener(new KeyListener() {
         public void keyTyped(KeyEvent e) {
            
         }

         public void keyPressed(KeyEvent e) {

         }

         public void keyReleased(KeyEvent e) {

         }
      });
   }

   public void paintComponent(Graphics g) {
      //super.paintComponent(g);

      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(Color.BLUE);
      g2.fillRect(100, 100, 200, 50);

   }

}

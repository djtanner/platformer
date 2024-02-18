package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {
   private MouseInputs mouseInputs;
   private float xDelta;
   private float yDelta;
   private BufferedImage img;


   public GamePanel() {

      importImg();
      mouseInputs = new MouseInputs(this);
      addKeyListener(new KeyboardInputs(this));
      addMouseListener(mouseInputs);
      addMouseMotionListener(mouseInputs);

      setPanelSize();

      xDelta = 0;
      yDelta = 0;

   }

   public void changeXDelta(int value) {
      this.xDelta += value;

   }

   public void changeYDelta(int value) {
      this.yDelta += value;

   }

   private void importImg() {
      InputStream is = getClass().getResourceAsStream("/player_sprites.png");
      try {
         img = ImageIO.read(is);
      } catch (IOException e) {
         e.printStackTrace();

      }
   }

   private void setPanelSize() {
      Dimension size = new Dimension(1280, 720);

      setPreferredSize(size);

   }

   public void paintComponent(Graphics g) {
      //super.paintComponent(g);

      Graphics2D g2 = (Graphics2D) g;

      g2.drawImage(img, 0, 0, null);


   }


}



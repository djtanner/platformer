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
   private BufferedImage img, subImg;


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

   public void setRectPos(int x, int y) {
      this.xDelta = x;
      this.yDelta = y;
   }

   public void paintComponent(Graphics g) {
      //super.paintComponent(g);

      Graphics2D g2 = (Graphics2D) g;

      subImg = img.getSubimage(1 * 64, 8 * 40, 64, 40);
      g2.drawImage(subImg, (int) xDelta, (int) yDelta, 128, 80, null);


   }


}



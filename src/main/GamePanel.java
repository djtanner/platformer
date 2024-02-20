package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.Directions.DOWN;
import static utils.Constants.Directions.LEFT;
import static utils.Constants.Directions.RIGHT;
import static utils.Constants.Directions.UP;
import static utils.Constants.PlayerConstants.IDLE;
import static utils.Constants.PlayerConstants.RUNNING;
import static utils.Constants.PlayerConstants.getSpriteAmounts;

public class GamePanel extends JPanel {
   private MouseInputs mouseInputs;
   private float xDelta;
   private float yDelta;
   private BufferedImage img;
   private BufferedImage[][] animations;
   private int animationTick;
   private int animationIndex;
   private int animationSpeed;
   private int playerAction = IDLE;
   private int playerDir = -1;
   private boolean moving = false;


   public GamePanel() {

      importImg();
      loadAnimations();
      mouseInputs = new MouseInputs(this);
      addKeyListener(new KeyboardInputs(this));
      addMouseListener(mouseInputs);
      addMouseMotionListener(mouseInputs);

      setPanelSize();

      xDelta = 0;
      yDelta = 0;

      animationSpeed = 15;

   }

   private void loadAnimations() {
      animations = new BufferedImage[9][6];

      for (int j = 0; j < animations.length; j++)
         for (int i = 0; i < animations[j].length; i++) {
            animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
         }

   }


   public void setDirection(int direction) {
      this.playerDir = direction;
      moving = true;

   }

   public void setMoving(boolean moving) {
      this.moving = moving;
   }


   private void importImg() {
      InputStream is = getClass().getResourceAsStream("/player_sprites.png");
      try {
         img = ImageIO.read(is);
      } catch (IOException e) {
         e.printStackTrace();

      } finally {
         try {
            is.close();
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }
   }

   private void setPanelSize() {
      Dimension size = new Dimension(1280, 720);

      setPreferredSize(size);

   }

   private void updateAnimationTick() {
      animationTick++;

      if (animationTick >= animationSpeed) {
         animationTick = 0;
         animationIndex++;

         //restart animation loop for number of sprites
         if (animationIndex >= getSpriteAmounts(playerAction)) {
            animationIndex = 0;
         }
      }


   }

   private void setAnimation() {
      if (moving) {
         playerAction = RUNNING;
      } else {
         playerAction = IDLE;
      }
   }

   private void updatePos() {
      if (moving) {
         switch (playerDir) {
            case LEFT:
               xDelta -= 5;
               break;
            case UP:
               yDelta -= 5;
               break;
            case RIGHT:
               xDelta += 5;
               break;
            case DOWN:
               yDelta += 5;
               break;
         }
      }
   }

   public void paintComponent(Graphics g) {
      //super.paintComponent(g);

      Graphics2D g2 = (Graphics2D) g;

      updateAnimationTick();
      setAnimation();
      updatePos();

      g2.drawImage(animations[playerAction][animationIndex], (int) xDelta, (int) yDelta, 256, 160, null);


   }


}



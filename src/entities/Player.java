package entities;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.PlayerConstants.ATTACK_1;
import static utils.Constants.PlayerConstants.IDLE;
import static utils.Constants.PlayerConstants.RUNNING;
import static utils.Constants.PlayerConstants.getSpriteAmounts;

public class Player extends Entity {
   private BufferedImage[][] animations;
   private int animationTick;
   private int animationIndex;
   private int animationSpeed = 25;
   private int playerAction = IDLE;
   private int playerDir = -1;
   private boolean moving = false;
   private boolean attacking = false;
   private boolean left, right, up, down;
   private float playerSpeed = 2.0f;

   public Player(float x, float y) {
      super(x, y);
      loadAnimations();
   }

   public void update() {
      updatePos();
      updateAnimationTick();
      setAnimation();

   }

   public void render(Graphics g) {

      Graphics2D g2 = (Graphics2D) g;

      g2.drawImage(animations[playerAction][animationIndex], (int) x, (int) y, 128, 80, null);

   }


   private void updateAnimationTick() {
      animationTick++;

      if (animationTick >= animationSpeed) {
         animationTick = 0;
         animationIndex++;

         //restart animation loop for number of sprites
         if (animationIndex >= getSpriteAmounts(playerAction)) {
            animationIndex = 0;
            attacking = false;
         }
      }


   }

   private void setAnimation() {

      int startAnimation = playerAction;

      if (moving) {
         playerAction = RUNNING;
      } else {
         playerAction = IDLE;
      }

      if (attacking) {
         playerAction = ATTACK_1;
      }

      if (startAnimation != playerAction) {
         resetAnimationTick();
      }
   }


   private void resetAnimationTick() {
      animationTick = 0;
      animationIndex = 0;
   }

   private void updatePos() {

      moving = false;
      if (left && !right) {
         x -= playerSpeed;
         moving = true;
      } else if (right && !left) {
         x += playerSpeed;
         moving = true;
      }
      if (up && !down) {
         y -= playerSpeed;
         moving = true;
      } else if (down && !up) {
         y += playerSpeed;
         moving = true;
      }
   }


   private void loadAnimations() {


      BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
      animations = new BufferedImage[9][6];

      for (int j = 0; j < animations.length; j++)
         for (int i = 0; i < animations[j].length; i++) {
            animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
         }


   }

   public void setAttacking(boolean attacking) {
      this.attacking = attacking;
   }

   public void resetDirBooleans() {

      left = false;
      right = false;
      up = false;
      down = false;

   }

   public boolean isLeft() {
      return left;
   }

   public void setLeft(boolean left) {
      this.left = left;
   }

   public boolean isRight() {
      return right;
   }

   public void setRight(boolean right) {
      this.right = right;
   }

   public boolean isUp() {
      return up;
   }

   public void setUp(boolean up) {
      this.up = up;
   }

   public boolean isDown() {
      return down;
   }

   public void setDown(boolean down) {
      this.down = down;
   }

}
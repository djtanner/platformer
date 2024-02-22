package entities;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.PlayerConstants.ATTACK_1;
import static utils.Constants.PlayerConstants.FALLING;
import static utils.Constants.PlayerConstants.IDLE;
import static utils.Constants.PlayerConstants.JUMPING;
import static utils.Constants.PlayerConstants.RUNNING;
import static utils.Constants.PlayerConstants.getSpriteAmounts;
import static utils.HelpMethods.GetEntityXPosNextToWall;
import static utils.HelpMethods.canMoveHere;
import static utils.HelpMethods.getEntityYPosUnderRoofOrAboveFloor;
import static utils.HelpMethods.isEntityOnFloor;

public class Player extends Entity {
   private BufferedImage[][] animations;
   private int animationTick;
   private int animationIndex;
   private int animationSpeed = 25;
   private int playerAction = IDLE;
   private int playerDir = -1;
   private boolean moving = false;
   private boolean attacking = false;
   private boolean left, right, up, down, jump;
   private float playerSpeed = 2.0f;
   private int[][] levelData;
   private float xDrawOffset = 21 * Game.SCALE;
   private float yDrawOffset = 4 * Game.SCALE;

   //jumping and gravity
   private float airSpeed = 0f;
   private float gravity = 0.04f * Game.SCALE;
   private float jumpSpeed = -2.25f * Game.SCALE;
   private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
   private boolean inAir = false;


   public Player(float x, float y, int width, int height) {
      super(x, y, width, height);
      loadAnimations();
      initHitbox(x, y, 20 * Game.SCALE, 28 * Game.SCALE); //height of hitbox
   }

   public void update() {
      updatePos();
      updateAnimationTick();
      setAnimation();

   }

   public void render(Graphics g) {

      Graphics2D g2 = (Graphics2D) g;

      g2.drawImage(animations[playerAction][animationIndex], (int) (hitBox.x - xDrawOffset), (int) (hitBox.y - yDrawOffset), width, height, null);
      drawHitbox(g2);
   }

   public void loadLevelData(int[][] levelData) {
      this.levelData = levelData;
      if (!isEntityOnFloor(hitBox, levelData)) {
         inAir = true;
      }
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

      if (inAir) {
         if (airSpeed < 0) {
            playerAction = JUMPING;
         } else {
            playerAction = FALLING;
         }
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

      if (jump && !inAir) {
         jump();
      }
      if (!left && !right && !inAir) {
         return;
      }

      float xSpeed = 0;


      moving = false;
      if (left) {
         xSpeed -= playerSpeed;

      }

      if (right) {
         xSpeed += playerSpeed;

      }

      if (!inAir) {
         if (!isEntityOnFloor(hitBox, levelData)) {
            inAir = true;
         }
      }


      if (inAir) {
         if (canMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, levelData)) {
            hitBox.y += airSpeed;
            airSpeed += gravity;
            updateXPos(xSpeed);
         } else {
            hitBox.y = getEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
            if (airSpeed > 0) {
               resetInAir();
            } else {
               //hit the roof
               airSpeed = fallSpeedAfterCollision;
            }
            updateXPos(xSpeed);
         }
      } else {
         updateXPos(xSpeed);
      }
      moving = true;

   }


   private void jump() {
      if (inAir) {
         return;
      }

      inAir = true;
      airSpeed = jumpSpeed;
   }

   private void resetInAir() {
      inAir = false;
      airSpeed = 0;
   }

   private void updateXPos(float xSpeed) {
      if (canMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, levelData)) {
         hitBox.x += xSpeed;
      } else {
         //move player next to wall
         hitBox.x = GetEntityXPosNextToWall(hitBox, xSpeed);
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

   public void setJump(boolean jump) {
      this.jump = jump;
   }

}

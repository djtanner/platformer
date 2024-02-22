package utils;

import main.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods {

   //checks if position of hitbox overlaps a tile
   public static boolean canMoveHere(float x, float y, float width, float height, int[][] levelData) {

      //hitbox overlap with top left and bottom right
      if (!isSolid(x, y, levelData)) {
         if (!isSolid(x + width, y + height, levelData)) {
            //hitbox overlap with bottom left and top right
            if (!isSolid(x + width, y, levelData)) {
               if (!isSolid(x, y + height, levelData)) {

                  return true;
               }
            }
         }
      }
      return false;

   }

   public static float GetEntityXPosNextToWall(Rectangle2D.Float hitBox, float xSpeed) {
      int currentTile = (int) (hitBox.x / Game.TILES_SIZE);
      //check if colliding to the left or right
      if (xSpeed > 0) {
         //Right
         int tileXPos = currentTile * Game.TILES_SIZE;
         int xOffset = (int) (Game.TILES_SIZE - hitBox.width);
         return tileXPos + xOffset - 1;
      } else {
         //Left
         return currentTile * Game.TILES_SIZE;
      }
   }

   public static float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitBox, float airSpeed) {
      int currentTile = (int) (hitBox.y / Game.TILES_SIZE);

      if (airSpeed > 0) {
         //falling down
         int tileYPos = currentTile * Game.TILES_SIZE;
         int yOffset = (int) (Game.TILES_SIZE - hitBox.height);
         return tileYPos + yOffset - 1;

      } else {
         //jumping
         return currentTile * Game.TILES_SIZE;

      }
   }

   private static boolean isSolid(float x, float y, int[][] levelData) {
      //check if inside the game window
      if (x < 0 || x >= Game.GAME_WIDTH) {
         return true;
      }

      if (y < 0 || y >= Game.GAME_HEIGHT) {
         return true;
      }

      float xIndex = x / Game.TILES_SIZE;
      float yIndex = y / Game.TILES_SIZE;

      int value = levelData[(int) yIndex][(int) xIndex];

      //48 is number of sprites, 11 is transparent tile on tilemap
      if (value >= 48 || value < 0 || value != 11) {
         return true;
      }

      return false;

   }


   public static boolean isEntityOnFloor(Rectangle2D.Float hitBox, int[][] levelData) {
      //check the pixel below bottom left and bottom rt corners of hitBox
      if (!isSolid(hitBox.x, hitBox.y + hitBox.height + 1, levelData)) {
         if (!isSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, levelData)) {
            return false;
         }
      }

      return true;
   }

}

package utils;

import main.Game;

public class HelpMethods {

   //checks if position overlaps a tile
   public static boolean canMoveHere(float x, float y, int width, int height, int[][] levelData) {

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


}

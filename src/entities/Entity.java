package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

   //protected so extended classes can use
   protected float x, y;
   protected int width, height;
   protected Rectangle2D.Float hitBox;

   public Entity(float x, float y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;

   }

   protected void initHitbox(float x, float y, int width, int height) {
      hitBox = new Rectangle2D.Float(x, y, width, height);
   }

   /**
    * protected void updateHitbox() {
    * hitBox.x = (int) x;
    * hitBox.y = (int) y;
    * }
    */

   public Rectangle2D.Float getHitBox() {
      return hitBox;
   }

   protected void drawHitbox(Graphics g) {
      //for debugging the hitbox

      g.setColor(Color.magenta);
      g.drawRect((int) hitBox.x, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);

   }

}

package entities;

import java.awt.*;

public abstract class Entity {

   //protected so extended classes can use
   protected float x, y;
   protected int width, height;
   protected Rectangle hitBox;

   public Entity(float x, float y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      initHitbox();
   }

   private void initHitbox() {
      hitBox = new Rectangle((int) x, (int) y, width, height);
   }

   protected void updateHitbox() {
      hitBox.x = (int) x;
      hitBox.y = (int) y;
   }

   public Rectangle getHitBox() {
      return hitBox;
   }

   protected void drawHitbox(Graphics g) {
      //for debugging the hitbox

      g.setColor(Color.magenta);
      g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);

   }

}

package entities;

public abstract class Entity {

   //protected so extended classes can use
   protected float x, y;

   public Entity(float x, float y) {
      this.x = x;
      this.y = y;
   }
}

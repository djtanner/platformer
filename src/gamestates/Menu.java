package gamestates;

import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menu extends State implements StateMethods {


   public Menu(Game game) {
      super(game);
   }

   public void update() {

   }

   public void draw(Graphics g) {
      g.setColor(Color.black);
      g.drawString("MENU", Game.GAME_WIDTH / 2, 200);
   }

   public void mouseClicked(MouseEvent e) {

   }

   public void mouseMoved(MouseEvent e) {

   }

   public void mousePressed(MouseEvent e) {

   }

   public void mouseReleased(MouseEvent e) {

   }

   public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_ENTER)
         Gamestate.state = Gamestate.PLAYING;

   }

   public void keyReleased(KeyEvent e) {

   }
}

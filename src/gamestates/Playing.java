package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static main.Game.SCALE;

public class Playing extends State implements StateMethods {
   private Player player;
   private LevelManager levelManager;

   public Playing(Game game) {
      super(game);
      initClasses();
   }


   private void initClasses() {
      levelManager = new LevelManager(game);
      player = new Player(200, 200, (int) (64 * SCALE), (int) (40 * SCALE));
      player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
   }

   public void windowFocusLost() {
      player.resetDirBooleans();
   }

   public Player getPlayer() {
      return player;
   }

   public void update() {
      levelManager.update();
      player.update();
   }

   public void draw(Graphics g) {
      levelManager.draw(g);
      player.render(g);
   }

   public void mouseClicked(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON1) {
         player.setAttacking(true);
      }
   }

   public void mouseMoved(MouseEvent e) {

   }

   public void mousePressed(MouseEvent e) {

   }

   public void mouseReleased(MouseEvent e) {

   }

   public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {

         case KeyEvent.VK_A:
            player.setLeft(true);
            break;
         case KeyEvent.VK_D:
            player.setRight(true);
            break;
         case KeyEvent.VK_SPACE:
            player.setJump(true);
            break;
         case KeyEvent.VK_BACK_SPACE:
            Gamestate.state = Gamestate.MENU;
            break;
      }
   }

   public void keyReleased(KeyEvent e) {
      switch (e.getKeyCode()) {
         case KeyEvent.VK_SPACE:
            player.setJump(false);
            break;

         case KeyEvent.VK_A:
            player.setLeft(false);
            break;

         case KeyEvent.VK_D:
            player.setRight(false);
            break;
      }
   }
}

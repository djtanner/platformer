package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static main.Game.SCALE;

public class Playing extends State implements StateMethods {
   private Player player;
   private LevelManager levelManager;
   private boolean didJump = false;
   private boolean isPaused = false;
   private PauseOverlay pauseOverlay;

   public Playing(Game game) {
      super(game);
      initClasses();
   }


   private void initClasses() {
      levelManager = new LevelManager(game);
      player = new Player(200, 200, (int) (64 * SCALE), (int) (40 * SCALE));
      player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
      pauseOverlay = new PauseOverlay(this);

   }

   public void windowFocusLost() {
      player.resetDirBooleans();
   }

   public Player getPlayer() {
      return player;
   }

   public void update() {
      if (!isPaused) {
         levelManager.update();
         player.update();
      } else {
         pauseOverlay.update();
      }
   }

   public void draw(Graphics g) {
      levelManager.draw(g);
      player.render(g);

      if (isPaused)
         pauseOverlay.draw(g);
   }

   public void mouseClicked(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON1) {
         player.setAttacking(true);
      }
   }

   public void mouseMoved(MouseEvent e) {
      if (isPaused)
         pauseOverlay.mouseMoved(e);
   }

   public void mousePressed(MouseEvent e) {
      if (isPaused)
         pauseOverlay.mousePressed(e);
   }

   public void mouseReleased(MouseEvent e) {
      if (isPaused)
         pauseOverlay.mouseReleased(e);

   }

   public void mouseDragged(MouseEvent e) {
      if (isPaused)
         pauseOverlay.mouseDragged(e);
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
            if (!didJump) {
               player.setJump(true);
               didJump = true;
            }
            break;
         case KeyEvent.VK_BACK_SPACE:
            Gamestate.state = Gamestate.MENU;
            break;

         case KeyEvent.VK_ESCAPE:
            Gamestate.state = Gamestate.QUIT;
            break;

         case KeyEvent.VK_P:
            isPaused = !isPaused;
            break;

         default:
            break;
      }
   }

   public void unpauseGame() {
      isPaused = false;
   }

   public void keyReleased(KeyEvent e) {
      switch (e.getKeyCode()) {
         case KeyEvent.VK_SPACE:
            player.setJump(false);
            didJump = false;
            break;

         case KeyEvent.VK_A:
            player.setLeft(false);
            break;

         case KeyEvent.VK_D:
            player.setRight(false);
            break;

         default:
            break;
      }
   }
}


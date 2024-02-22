package inputs;

import gamestates.Gamestate;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

   private GamePanel gamePanel;

   public KeyboardInputs(GamePanel gamePanel) {
      this.gamePanel = gamePanel;
   }

   public void keyTyped(KeyEvent e) {

   }

   public void keyPressed(KeyEvent e) {
      switch (Gamestate.state) {
         case MENU:
            gamePanel.getGame().getMenu().keyPressed(e);
            break;

         case PLAYING:
            gamePanel.getGame().getPlaying().keyPressed(e);
            break;

         default:
            break;
      }
   }

   public void keyReleased(KeyEvent e) {
      switch (Gamestate.state) {
         case MENU:
            gamePanel.getGame().getMenu().keyReleased(e);
            break;

         case PLAYING:
            gamePanel.getGame().getPlaying().keyReleased(e);
            break;

         default:
            break;
      }
   }
}

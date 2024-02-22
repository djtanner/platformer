package inputs;

import gamestates.Gamestate;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
   private GamePanel gamePanel;

   public MouseInputs(GamePanel gamePanel) {
      this.gamePanel = gamePanel;
   }

   public void mouseClicked(MouseEvent e) {
      // System.out.println("clicked mouse");
      switch (Gamestate.state) {
         case MENU:
            gamePanel.getGame().getMenu().mouseClicked(e);
            break;

         case PLAYING:
            gamePanel.getGame().getPlaying().mouseClicked(e);
            break;
      }

   }

   public void mousePressed(MouseEvent e) {

   }

   public void mouseReleased(MouseEvent e) {

   }

   public void mouseEntered(MouseEvent e) {

   }

   public void mouseExited(MouseEvent e) {

   }

   public void mouseDragged(MouseEvent e) {

   }

   public void mouseMoved(MouseEvent e) {


   }
}

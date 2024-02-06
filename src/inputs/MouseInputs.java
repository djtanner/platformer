package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

   public void mouseClicked(MouseEvent e) {
      System.out.println("clicked mouse");
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
      System.out.println("moved mouse");
   }
}

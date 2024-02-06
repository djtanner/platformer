package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {


   public void keyTyped(KeyEvent e) {

   }

   public void keyPressed(KeyEvent e) {

   }

   public void keyReleased(KeyEvent e) {
      switch (e.getKeyCode()) {
         case KeyEvent.VK_W:
            System.out.println("W was pressed");
            break;

         case KeyEvent.VK_A:
            System.out.println("A was pressed");
            break;

         case KeyEvent.VK_S:
            System.out.println("S was pressed");
            break;

         case KeyEvent.VK_D:
            System.out.println("D was pressed");
            break;
      }
   }
}

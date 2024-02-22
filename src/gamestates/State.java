package gamestates;

import main.Game;

//superclass for game states
public class State {

   protected Game game;


   public State(Game game) {
      this.game = game;

   }

   public Game getGame() {
      return game;
   }

}

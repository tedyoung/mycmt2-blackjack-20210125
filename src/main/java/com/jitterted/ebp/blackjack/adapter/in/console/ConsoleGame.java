package com.jitterted.ebp.blackjack.adapter.in.console;

import com.jitterted.ebp.blackjack.domain.Game;

// beginning of our Adapter for the Console
public class ConsoleGame {

  private final Game game;

  public ConsoleGame(Game game) {
    this.game = game;
  }

  public static void main(String[] args) {
    Game game = new Game();
    ConsoleGame consoleGame = new ConsoleGame(game);
    consoleGame.start();
  }

  public void start() {
    Game.displayWelcomeScreen();

    game.initialDeal();

    playerPlays();

    game.dealerTurn();

    game.displayFinalGameState();

    game.determineOutcome();

    Game.resetScreen();
  }

  public void playerPlays() {
    while (!game.isPlayerDone()) {
      game.displayGameState();
      String command = game.inputFromPlayer();
      handle(command);
    }
  }

  // dispatch to COMMAND on the DOMAIN
  public void handle(String command) {
    if (command.toLowerCase().startsWith("h")) {
      game.playerHits();
    } else if (command.toLowerCase().startsWith("s")) {
      game.playerStands();
    }
  }

}

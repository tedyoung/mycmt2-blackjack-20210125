package com.jitterted.ebp.blackjack.adapter.in.console;

import com.jitterted.ebp.blackjack.domain.GameOutcome;

public class ConsoleOutcome {

  public static String of(GameOutcome gameOutcome) {
    switch (gameOutcome) {
      case PLAYER_WINS:
        return "You beat the Dealer! 💵";
      case PLAYER_BUSTS:
        return "You Busted, so you lose.  💸";
      case PLAYER_WIN_BLACKJACK:
        return "You win with Blackjack!";
      case DEALER_BUSTS:
        return "Dealer went BUST, Player wins! Yay for you!! 💵";
      case PLAYER_PUSHES:
        return "Push: The house wins, you Lose. 💸";
      case PLAYER_LOSES:
        return "You lost to the Dealer. 💸";
      default:
        throw new IllegalArgumentException();
    }
  }

}

package com.jitterted.ebp.blackjack.domain;

public enum GameOutcome {
  PLAYER_WINS("You beat the Dealer! 💵"),
  PLAYER_BUSTS("You Busted, so you lose.  💸"),
  PLAYER_WIN_BLACKJACK("You win with Blackjack!"),
  DEALER_BUSTS("Dealer went BUST, Player wins! Yay for you!! 💵"),
  PLAYER_PUSHES("Push: The house wins, you Lose. 💸"),
  PLAYER_LOSES("You lost to the Dealer. 💸");

  private final String display;

  GameOutcome(String display) {
    this.display = display;
  }

  public String display() {
    return display;
  }
}

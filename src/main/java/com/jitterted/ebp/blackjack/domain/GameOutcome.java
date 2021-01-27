package com.jitterted.ebp.blackjack.domain;

public enum GameOutcome {
  PLAYER_WINS("You beat the Dealer! 💵"),
  PLAYER_BUSTS("You Busted, so you lose.  💸"),
  PLAYER_WIN_BLACKJACK("You win with Blackjack!");

  private final String display;

  GameOutcome(String display) {
    this.display = display;
  }

  public String display() {
    return display;
  }
}

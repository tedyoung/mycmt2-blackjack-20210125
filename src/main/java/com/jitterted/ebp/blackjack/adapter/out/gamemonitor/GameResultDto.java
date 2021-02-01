package com.jitterted.ebp.blackjack.adapter.out.gamemonitor;

import com.jitterted.ebp.blackjack.domain.Game;

public class GameResultDto {
  private String playerName = "Ted";
  private String outcome;
  private String playerHandValue;
  private String dealerHandValue;

  public static GameResultDto of(Game game) {
    GameResultDto dto = new GameResultDto();
    dto.setDealerHandValue(game.dealerHand().displayValue());
    dto.setPlayerHandValue(game.playerHand().displayValue());
    dto.setOutcome(game.determineOutcome().toString().toLowerCase());
    return dto;
  }


  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public String getOutcome() {
    return outcome;
  }

  public void setOutcome(String outcome) {
    this.outcome = outcome;
  }

  public String getPlayerHandValue() {
    return playerHandValue;
  }

  public void setPlayerHandValue(String playerHandValue) {
    this.playerHandValue = playerHandValue;
  }

  public String getDealerHandValue() {
    return dealerHandValue;
  }

  public void setDealerHandValue(String dealerHandValue) {
    this.dealerHandValue = dealerHandValue;
  }
}

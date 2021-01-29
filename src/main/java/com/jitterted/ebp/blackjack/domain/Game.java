package com.jitterted.ebp.blackjack.domain;

import com.jitterted.ebp.blackjack.domain.port.GameMonitor;

public class Game {

  private final Deck deck;

  private final GameMonitor gameMonitor;

  private final Hand dealerHand = new Hand();
  private final Hand playerHand = new Hand();
  private boolean playerDone;

  public Game() {
    this(new Deck());
  }

  public Game(Deck deck) {
    this(deck, game -> {});
  }

  public Game(Deck deck, GameMonitor gameMonitor) {
    this.deck = deck;
    this.gameMonitor = gameMonitor;
  }

  public void initialDeal() {
    dealRoundOfCards();
    dealRoundOfCards();
    playerDone = playerHand.isBlackjack();
  }

  private void dealRoundOfCards() {
    // why: players first because this is the rule
    playerHand.drawFrom(deck);
    dealerHand.drawFrom(deck);
  }

  public GameOutcome determineOutcome() {
    if (playerHand.isBusted()) {
      return GameOutcome.PLAYER_BUSTS;
    } else if (playerHand.isBlackjack()) {
      return GameOutcome.PLAYER_WIN_BLACKJACK;
    } else if (dealerHand.isBusted()) {
      return GameOutcome.DEALER_BUSTS;
    } else if (playerHand.beats(dealerHand)) {
      return GameOutcome.PLAYER_WINS;
    } else if (playerHand.pushes(dealerHand)) {
      return GameOutcome.PLAYER_PUSHES;
    } else {
      return GameOutcome.PLAYER_LOSES;
    }
  }

  private void dealerTurn() {
    // Dealer makes its choice automatically based on a simple heuristic (<=16, hit, 17>stand)
    if (!playerHand.isBusted()) {
      while (dealerHand.dealerMustDrawCard()) {
        dealerHand.drawFrom(deck);
      }
    }
  }

  public Hand playerHand() {
    return playerHand;
  }

  public Hand dealerHand() {
    return dealerHand;
  }

  // These are basically externally available triggers (COMMANDS) for behavior

  public void playerHits() {
    playerHand.drawFrom(deck);
    playerDone = playerHand.isBusted();
    if (playerDone) {
      gameMonitor.roundCompleted(this);
    }
  }

  public void playerStands() {
    playerDone = true;
    dealerTurn();
    gameMonitor.roundCompleted(this);
  }

  // Query for current state of Game

  public boolean isPlayerDone() {
    return playerDone;
  }
}

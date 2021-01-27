package com.jitterted.ebp.blackjack.domain;

public class Game {

  private final Deck deck;

  private final Hand dealerHand = new Hand();
  private final Hand playerHand = new Hand();
  private boolean playerDone;

  public Game() {
    deck = new Deck();
  }

  public Game(Deck deck) {
    this.deck = deck;
  }

  public void initialDeal() {
    dealRoundOfCards();
    dealRoundOfCards();
  }

  private void dealRoundOfCards() {
    // why: players first because this is the rule
    playerHand.drawFrom(deck);
    dealerHand.drawFrom(deck);
  }

  public String determineOutcome() {
    if (playerHand.isBusted()) {
      return "You Busted, so you lose.  💸";
    } else if (dealerHand.isBusted()) {
      return "Dealer went BUST, Player wins! Yay for you!! 💵";
    } else if (playerHand.beats(dealerHand)) {
      return "You beat the Dealer! 💵";
    } else if (playerHand.pushes(dealerHand)) {
      return "Push: The house wins, you Lose. 💸";
    } else {
      return "You lost to the Dealer. 💸";
    }
  }

  public void dealerTurn() {
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
  }

  public void playerStands() {
    playerDone = true;
  }

  // Query for current state of Game

  public boolean isPlayerDone() {
    return playerDone;
  }
}

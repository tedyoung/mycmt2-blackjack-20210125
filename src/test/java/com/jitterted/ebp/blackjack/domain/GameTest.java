package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameTest {

  @Test
  public void whenPlayerStandsThenDealerTakesTheirTurn() throws Exception {
    Deck stubDeck = new StubDeck(Rank.TEN,   Rank.FOUR,
                                 Rank.SEVEN, Rank.FIVE,
                                 Rank.JACK);
    Game game = new Game(stubDeck);
    game.initialDeal();

    game.playerStands();

    assertThat(game.dealerHand().valueEquals(4 + 5 + 10))
        .describedAs(game.dealerHand().displayValue())
        .isTrue();
  }

}
package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

  @Test
  public void playerBeatsDealer() throws Exception {
    Deck playerBeatsDealerDeck = new StubDeck(Rank.TEN, Rank.EIGHT, Rank.NINE, Rank.QUEEN);
    Game game = new Game(playerBeatsDealerDeck);

    game.initialDeal();
    game.playerStands();
    game.dealerTurn();

    assertThat(game.isPlayerDone())
        .isTrue();
    assertThat(game.determineOutcome())
        .isEqualTo(GameOutcome.PLAYER_WINS.display());
  }
  
  @Test
  public void playerHitsGoesBustThenLoses() throws Exception {
    Deck playerGoesBustUponHitDeck = new StubDeck(Rank.TEN, Rank.EIGHT, Rank.NINE, Rank.QUEEN, Rank.FOUR);
    Game game = new Game(playerGoesBustUponHitDeck);

    game.initialDeal();
    game.playerHits();
    game.dealerTurn();

    assertThat(game.isPlayerDone())
        .isTrue();
    assertThat(game.determineOutcome())
        .isEqualTo(GameOutcome.PLAYER_BUSTS.display());
  }

  @Test
  public void playerInitiallyDealtBlackjackThenGameIsDoneAndWins() throws Exception {
    Deck playerDealtBlackjack = new StubDeck(Rank.ACE, Rank.FOUR, Rank.JACK, Rank.FIVE);
    Game game = new Game(playerDealtBlackjack);

    game.initialDeal();

    assertThat(game.determineOutcome())
        .isEqualTo(GameOutcome.PLAYER_WIN_BLACKJACK.display());
    assertThat(game.isPlayerDone())
        .isTrue();
  }

  @Test
  public void playerHandSameValueAsDealerThenGameIsPush() throws Exception {
    Deck playerHandValueSameAsDealer = new StubDeck(Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING);
    Game game = new Game(playerHandValueSameAsDealer);

    game.initialDeal();
    game.playerStands();
    game.dealerTurn();

    assertThat(game.isPlayerDone())
        .isTrue();
    assertThat(game.determineOutcome())
        .isEqualTo(GameOutcome.PLAYER_PUSHES.display());
  }

  @Test
  public void playerHandValueLessThanDealerThenPlayerLoses() throws Exception {
    Deck playerHandValueSameAsDealer = new StubDeck(Rank.TEN, Rank.JACK, Rank.SIX, Rank.EIGHT);
    Game game = new Game(playerHandValueSameAsDealer);

    game.initialDeal();
    game.playerStands();
    game.dealerTurn();

    assertThat(game.isPlayerDone())
        .isTrue();
    assertThat(game.determineOutcome())
        .isEqualTo(GameOutcome.PLAYER_LOSES.display());
  }

}
package com.jitterted.ebp.blackjack.domain;

import com.jitterted.ebp.blackjack.domain.port.GameMonitor;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class GameMonitorTest {

  @Test
  public void playerStandsCompletesGameSendsToMonitor() throws Exception {
    // creates the spy based on the interface
    GameMonitor gameMonitorSpy = spy(GameMonitor.class);
    Game game = new Game(new Deck(), gameMonitorSpy);

    game.initialDeal();
    game.playerStands();

    // verify that the roundCompleted method was called with any instance of a Game class
    verify(gameMonitorSpy).roundCompleted(any(Game.class));
  }

  @Test
  public void playerHitsAndGoesBustThenGameSendsResultToMonitor() throws Exception {
    GameMonitor gameMonitorSpy = spy(GameMonitor.class);
    Deck playerBustsDeck = new StubDeck(Rank.TEN, Rank.EIGHT, Rank.QUEEN, Rank.KING, Rank.FIVE);
    Game game = new Game(playerBustsDeck, gameMonitorSpy);

    game.initialDeal();
    game.playerHits();

    verify(gameMonitorSpy).roundCompleted(any(Game.class));
  }

  @Test
  public void playerHitsDoesNotGoBustThenNoResultsAreSentToMonitor() throws Exception {
    GameMonitor gameMonitorSpy = spy(GameMonitor.class);
    Deck playerDoesNotBustDeck = new StubDeck(Rank.TEN, Rank.EIGHT, Rank.FOUR, Rank.KING, Rank.FIVE);
    Game game = new Game(playerDoesNotBustDeck, gameMonitorSpy);

    game.initialDeal();
    game.playerHits();

    verify(gameMonitorSpy, never()).roundCompleted(any(Game.class));
  }

}
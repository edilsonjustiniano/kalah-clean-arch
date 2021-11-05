package kalah.clean.arch.domain.entity;

import kalah.clean.arch.domain.base.GameTestBuilder;
import org.junit.jupiter.api.Test;

import static kalah.clean.arch.domain.entity.Board.PLAYER_ONE_KALAH_INDEX;
import static kalah.clean.arch.domain.entity.Board.PLAYER_TWO_KALAH_INDEX;
import static kalah.clean.arch.domain.entity.Game.GameStatus.FINISHED;
import static kalah.clean.arch.domain.entity.Game.Player.PLAYER_ONE;
import static kalah.clean.arch.domain.entity.Game.Player.PLAYER_TWO;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Game} class.
 */
class GameTest {

    @Test
    void generateGameIdIsSuccessfullyGenerated() {
        // when
        var actual = Game.generateGameId();

        // then
        assertNotNull(actual);
    }

    @Test
    void isGameFinishedForAProperlyFinishedGame() {
        // given
        var game = GameTestBuilder.aFinishedPlayerOneWinnerGame().build();

        // when
        var actual = game.isGameFinished();

        // then
        assertTrue(actual);
    }

    @Test
    void isGameRunningForARunningGame() {
        // given
        var game = GameTestBuilder.aStartedGame().build();

        // when
        var actual = game.isGameRunning();

        // then
        assertTrue(actual);
    }

    @Test
    void isPlayerOneTurnForPlayerOneTurnGame() {
        // given
        var game = GameTestBuilder.aStartedGame().build();

        // when
        var actual = game.isPlayerOneTurn();

        // then
        assertTrue(actual);
    }

    @Test
    void isPlayerTwoTurnForPlayerTwoTurnGame() {
        // given
        var game = GameTestBuilder.aPlayerTwoGameTurn().build();

        // when
        var actual = game.isPlayerTwoTurn();

        // then
        assertTrue(actual);
    }

    @Test
    void isGameOverIfPlayerOneWins() {
        // given
        var game = GameTestBuilder.aFinishedPlayerOneWinnerGame().build();

        // when
        var actual = game.isGameOver();

        // then
        assertTrue(actual);
    }

    @Test
    void isGameOverIfPlayerTwoWins() {
        // given
        var game = GameTestBuilder.aFinishedPlayerTwoWinnerGame().build();

        // when
        var actual = game.isGameOver();

        // then
        assertTrue(actual);
    }

    @Test
    void isGameNotOverIfNonPlayerWins() {
        // given
        var game = GameTestBuilder.aStartedGame().build();

        // when
        var actual = game.isGameOver();

        // then
        assertFalse(actual);
    }

    @Test
    void finishSetItsStatus() {
        // given
        var game = GameTestBuilder.aStartedGame().build();

        // when
        var actual = game.finish();

        // then
        assertEquals(actual.getGameStatus(), FINISHED);
    }

    @Test
    void updateTurnToPlayerTwoIfCurrentTurnIsPlayerOne() {
        // given
        var game = GameTestBuilder.aStartedGame().build();

        // when
        var actual = game.updateTurn(PLAYER_TWO_KALAH_INDEX);

        // then
        assertEquals(actual.getNextPlayer(), PLAYER_TWO);
    }

    @Test
    void updateTurnToPlayerOneIfCurrentTurnIsPlayerTwo() {
        // given
        var game = GameTestBuilder.aStartedGame().nextPlayer(PLAYER_TWO).build();

        // when
        var actual = game.updateTurn(PLAYER_ONE_KALAH_INDEX);

        // then
        assertEquals(actual.getNextPlayer(), PLAYER_ONE);
    }

    @Test
    void canPlayerOneTakeOpponentStonesIfProvidedPitIsNotKalah() {
        // given
        var game = GameTestBuilder.aStartedGame().build();

        // when
        int nextPit = PLAYER_TWO_KALAH_INDEX - 1;
        var actual = game.canTakeOpponentStones(nextPit);

        // then
        assertTrue(actual);
    }

    @Test
    void canPlayerTwoTakeOpponentStonesIfProvidedPitIsNotKalah() {
        // given
        var game = GameTestBuilder.aStartedGame().nextPlayer(PLAYER_TWO).build();

        // when
        int nextPit = PLAYER_ONE_KALAH_INDEX - 1;
        var actual = game.canTakeOpponentStones(nextPit);

        // then
        assertTrue(actual);
    }

    @Test
    void canPlayerOneTakeOpponentStonesIfProvidedPitIsKalah() {
        // given
        var game = GameTestBuilder.aStartedGame().build();

        // when
        var actual = game.canTakeOpponentStones(PLAYER_TWO_KALAH_INDEX);

        // then
        assertFalse(actual);
    }

    @Test
    void canPlayerTwoTakeOpponentStonesIfProvidedPitIsKalah() {
        // given
        var game = GameTestBuilder.aStartedGame().nextPlayer(PLAYER_TWO).build();

        // when
        var actual = game.canTakeOpponentStones(PLAYER_ONE_KALAH_INDEX);

        // then
        assertFalse(actual);
    }

    @Test
    void durationIsSuccessfullyCalculatedForRunningGame() {
        // given
        var game = GameTestBuilder.aStartedGame().build();

        // when
        var actual = game.duration();

        // then
        assertTrue(() -> actual > 0);
    }

    @Test
    void durationIsSuccessfullyCalculatedForFinishedGame() {
        // given
        var game = GameTestBuilder.aFinishedPlayerOneWinnerGame().build();

        // when
        var actual = game.duration();

        // then
        assertTrue(() -> actual > 0);
    }
}

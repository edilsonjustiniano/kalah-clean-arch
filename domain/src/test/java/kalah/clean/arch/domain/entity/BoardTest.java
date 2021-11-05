package kalah.clean.arch.domain.entity;


import kalah.clean.arch.domain.base.BoardTestBuilder;
import org.junit.jupiter.api.Test;

import static kalah.clean.arch.domain.entity.Board.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Board} class.
 */
class BoardTest {

    @Test
    void isPlayerOnePitIfProvidedPitBelongsToPlayerOne() {
        // given
        Board board = BoardTestBuilder.aBoard().build();

        // when
        boolean actual = board.isPlayerOnePit(1);

        // then
        assertTrue(actual);
    }

    @Test
    void isPlayerOnePitIfProvidedPitDoesNotBelongToPlayerOne() {
        // given
        Board board = BoardTestBuilder.aBoard().build();

        // when
        boolean actual = board.isPlayerOnePit(7);

        // then
        assertFalse(actual);
    }

    @Test
    void isPlayerTwoPitIfProvidedPitBelongsToPlayerTwo() {
        // given
        Board board = BoardTestBuilder.aBoard().build();

        // when
        boolean actual = board.isPlayerTwoPit(12);

        // then
        assertTrue(actual);
    }

    @Test
    void isPlayerTwoPitIfProvidedPitDoesNotBelongToPlayerTwo() {
        // given
        Board board = BoardTestBuilder.aBoard().build();

        // when
        boolean actual = board.isPlayerTwoPit(5);

        // then
        assertFalse(actual);
    }

    @Test
    void isNotPlayerOneKalahIfProvidedPitDoesNotBelongToPlayerOne() {
        // given
        Board board = BoardTestBuilder.aBoard().build();

        // when
        boolean actual = board.isNotPlayerOneKalah(13);

        // then
        assertTrue(actual);
    }

    @Test
    void isNotPlayerOneKalahIfProvidedPitBelongsToPlayerOne() {
        // given
        Board board = BoardTestBuilder.aBoard().build();

        // when
        boolean actual = board.isNotPlayerOneKalah(6);

        // then
        assertFalse(actual);
    }

    @Test
    void isPlayerTwoKalahIfProvidedPitIsPlayerTwoKalah() {
        // given
        Board board = BoardTestBuilder.aBoard().build();

        // when
        boolean actual = board.isPlayerTwoKalah(PLAYER_TWO_KALAH_INDEX);

        // then
        assertTrue(actual);
    }

    @Test
    void isPlayerTwoKalahIfProvidedPitIsNotPlayerTwoKalah() {
        // given
        Board board = BoardTestBuilder.aBoard().build();

        // when
        boolean actual = board.isPlayerTwoKalah(PLAYER_ONE_KALAH_INDEX);

        // then
        assertFalse(actual);
    }

    @Test
    void isNotPlayerTwoKalahIfProvidedPitBelongsToPlayerOne() {
        // given
        Board board = BoardTestBuilder.aBoard().build();

        // when
        boolean actual = board.isNotPlayerTwoKalah(PLAYER_ONE_KALAH_INDEX);

        // then
        assertTrue(actual);
    }

    @Test
    void arePlayerOnePitsEmptyIfPlayerTwoWonTheGame() {
        // given
        Board board = BoardTestBuilder.aPlayerTwoWinnerPits().build();

        // when
        boolean actual = board.arePlayerOnePitsEmpty();

        // then
        assertTrue(actual);
    }

    @Test
    void arePlayerTwoPitsEmptyIfPlayerOneWonTheGame() {
        // given
        Board board = BoardTestBuilder.aPlayerOneWinnerPits().build();

        // when
        boolean actual = board.arePlayerTwoPitsEmpty();

        // then
        assertTrue(actual);
    }

    @Test
    void haveNoStonesOnPitIfProvidedPitIsEmpty() {
        // given
        Board board = BoardTestBuilder.aPlayerOneWinnerPits().build();

        // when
        boolean actual = board.haveNoStonesOnPit(PLAYER_TWO_KALAH_INDEX);

        // then
        assertTrue(actual);
    }

    @Test
    void getNextPitIndexIfCurrentPitIsPlayerTwoKalah() {
        // given
        Board board = BoardTestBuilder.aBoard().build();

        // when
        int actualPitIndex = board.getNextPitIndex(PLAYER_TWO_KALAH_INDEX);

        // then
        assertEquals(0, actualPitIndex);
    }

    @Test
    void getNextPitIndexIfCurrentPitIsPlayerOneKalah() {
        // given
        int expectedPitIndex = PLAYER_ONE_KALAH_INDEX + 1;
        Board board = BoardTestBuilder.aBoard().build();

        // when
        int actualPitIndex = board.getNextPitIndex(PLAYER_ONE_KALAH_INDEX);

        // then
        assertEquals(expectedPitIndex, actualPitIndex);
    }

    @Test
    void cleanPitResetsTheProvidedPit() {
        // given
        Board board = BoardTestBuilder.aBoard().build();

        // when
        board = board.cleanPit(PLAYER_TWO_LAST_PIT_INDEX);

        // then
        assertEquals(0, board.getPits()[PLAYER_TWO_LAST_PIT_INDEX]);
    }

    @Test
    void collectOppositeStonesForPlayerOneKalah() {
        // given
        var nextPitIndex = 0;
        Board board = BoardTestBuilder.aBoard().build();

        // when
        board = board.collectOppositeStones(nextPitIndex, PLAYER_ONE_KALAH_INDEX);

        // then
        assertEquals(2, board.getPits()[PLAYER_ONE_KALAH_INDEX]);
    }

    @Test
    void collectOppositeStonesForPlayerTwoKalah() {
        // given
        var nextPitIndex = PLAYER_TWO_LAST_PIT_INDEX;
        Board board = BoardTestBuilder.aBoard().build();

        // when
        board = board.collectOppositeStones(nextPitIndex, PLAYER_TWO_KALAH_INDEX);

        // then
        assertEquals(2, board.getPits()[PLAYER_TWO_KALAH_INDEX]);
    }
}
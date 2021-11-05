package kalah.clean.arch.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import static java.time.Duration.between;
import static java.time.Instant.now;
import static kalah.clean.arch.domain.entity.Game.GameStatus.FINISHED;
import static kalah.clean.arch.domain.entity.Game.Player.PLAYER_ONE;
import static kalah.clean.arch.domain.entity.Game.Player.PLAYER_TWO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    // I decided to use a Hash (UUID) as Primary Key (Id) because we faced a lot of issues during the data migration
    // on our micro-services. So I use the same idea we are using right now
    private String gameId;
    private long startedTime;
    private Long duration;

    private GameStatus gameStatus;
    private Board board;

    private Player nextPlayer;

    //Using tell don't ask design pattern to encapsulate some business logic and avoid split them around the code
    public static String generateGameId() {
        return UUID.randomUUID().toString();
    }

    //Using tell don't ask design pattern to encapsulate some business logic and avoid split them around the code
    // These transient annotated methods will not be stored on our db
    public boolean isGameFinished() {
        return FINISHED.equals(getGameStatus());
    }

    //Using tell don't ask design pattern to encapsulate some business logic and avoid split them around the code
    public boolean isGameRunning() {
        return GameStatus.RUNNING.equals(getGameStatus());
    }

    public boolean isPlayerOneTurn() {
        return PLAYER_ONE.equals(getNextPlayer());
    }

    public boolean isPlayerTwoTurn() {
        return PLAYER_TWO.equals(getNextPlayer());
    }

    public boolean isGameOver() {
        boolean isGameOver = false;
        if (getBoard().arePlayerOnePitsEmpty()) {
            isGameOver = true;
        } else if (getBoard().arePlayerTwoPitsEmpty()) {
            isGameOver = true;
        }

        return isGameOver;
    }

    public Game finish() {
        setGameStatus(FINISHED);

        // I added by myself at the model entity the field duration... It could be used in the future to check how long
        // the game is taken. I though it could be helpful to have it
        Instant startedTime = Instant.ofEpochMilli(getStartedTime());
        Instant currentTime = now();
        long duration = between(startedTime, currentTime).getSeconds();
        setDuration(duration);

        return this;
    }

    public Game updateTurn(int nextPitIndex) {
        Board board = getBoard();
        if (isPlayerOneTurn() && board.isNotPlayerOneKalah(nextPitIndex)) {
            setNextPlayer(PLAYER_TWO);
        } else if (isPlayerTwoTurn() && board.isNotPlayerTwoKalah(nextPitIndex)) {
            setNextPlayer(PLAYER_ONE);
        }

        return this;
    }

    public boolean canTakeOpponentStones(int pitIndex) {
        return (isPlayerOneTurn() && getBoard().isNotPlayerTwoKalah(pitIndex)) ||
                (isPlayerTwoTurn() && getBoard().isNotPlayerOneKalah(pitIndex));
    }

    public long duration() {
        if (isGameFinished()) {
            return duration;
        } else {
            var currentTime = Instant.now();
            var startedTime = Instant.ofEpochMilli(getStartedTime());
            return Duration.between(startedTime, currentTime).getSeconds();
        }
    }


    public enum GameStatus {
        RUNNING,
        FINISHED
    }

    public enum Player {
        PLAYER_ONE,
        PLAYER_TWO
    }
}

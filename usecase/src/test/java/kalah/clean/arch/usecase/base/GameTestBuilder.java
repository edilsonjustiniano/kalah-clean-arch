package kalah.clean.arch.usecase.base;

import kalah.clean.arch.domain.entity.Board;
import kalah.clean.arch.domain.entity.Game;

import static kalah.clean.arch.domain.entity.Game.GameStatus.FINISHED;
import static kalah.clean.arch.domain.entity.Game.Player.PLAYER_TWO;

public final class GameTestBuilder {

    private static final String DEFAULT_GAME_ID = "gameId";
    private static final long DEFAULT_STARTED_TIME = 1636122208L;

    private String gameId = DEFAULT_GAME_ID;
    private long startedTime = DEFAULT_STARTED_TIME;
    private Long duration = 30_000L;

    private Game.GameStatus gameStatus = Game.GameStatus.RUNNING;
    private Board board;

    private Game.Player nextPlayer = Game.Player.PLAYER_ONE;

    private GameTestBuilder() {
    }

    public static GameTestBuilder aGame() {
        return new GameTestBuilder();
    }

    public GameTestBuilder gameId(String gameId) {
        this.gameId = gameId;
        return this;
    }

    public GameTestBuilder startedTime(long startedTime) {
        this.startedTime = startedTime;
        return this;
    }

    public GameTestBuilder duration(Long duration) {
        this.duration = duration;
        return this;
    }

    public GameTestBuilder gameStatus(Game.GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        return this;
    }

    public GameTestBuilder board(Board board) {
        this.board = board;
        return this;
    }

    public GameTestBuilder nextPlayer(Game.Player nextPlayer) {
        this.nextPlayer = nextPlayer;
        return this;
    }

    public static GameTestBuilder aPlayerTwoGameTurn() {
        return aGame()
                .board(BoardTestBuilder.aBoard().build())
                .nextPlayer(PLAYER_TWO);
    }

    public static GameTestBuilder aStartedGame() {
        return aGame().board(BoardTestBuilder.aBoard().build());
    }

    public static GameTestBuilder aFinishedPlayerOneWinnerGame() {
        return aGame()
                .board(BoardTestBuilder.aPlayerOneWinnerPits().build())
                .gameStatus(FINISHED);
    }

    public static GameTestBuilder aFinishedPlayerTwoWinnerGame() {
        return aGame()
                .board(BoardTestBuilder.aPlayerTwoWinnerPits().build())
                .gameStatus(FINISHED);
    }

    public Game build() {
        return Game.builder()
                .gameId(gameId)
                .startedTime(startedTime)
                .duration(duration)
                .gameStatus(gameStatus)
                .board(board)
                .nextPlayer(nextPlayer)
                .build();
    }
}

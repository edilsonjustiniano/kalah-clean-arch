package kalah.clean.arch.usecase.base;

import kalah.clean.arch.usecase.gateway.GameDto;

public final class GameDtoTestBuilder {

    private static final String DEFAULT_GAME_ID = "gameId";
    private static final long DEFAULT_STARTED_TIME = 1636122208L;
    private static final String DEFAULT_RUNNING_GAME_STATUS = "RUNNING";
    private static final int[] DEFAULT_PITS = {1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1};
    public static final String DEFAULT_PLAYER_ONE = "PLAYER_ONE";

    private String gameId = DEFAULT_GAME_ID;
    private long startedTime = DEFAULT_STARTED_TIME;
    private Long duration = 30_000L;

    private String status = DEFAULT_RUNNING_GAME_STATUS;
    private int[] pits = DEFAULT_PITS;

    private String nextPlayer = DEFAULT_PLAYER_ONE;

    private GameDtoTestBuilder() {
    }

    public static GameDtoTestBuilder aDto() {
        return new GameDtoTestBuilder();
    }

    public GameDtoTestBuilder gameId(String gameId) {
        this.gameId = gameId;
        return this;
    }

    public GameDtoTestBuilder startedTime(long startedTime) {
        this.startedTime = startedTime;
        return this;
    }

    public GameDtoTestBuilder duration(Long duration) {
        this.duration = duration;
        return this;
    }

    public GameDtoTestBuilder gameStatus(String status) {
        this.status = status;
        return this;
    }

    public GameDtoTestBuilder pits(int[] pits) {
        this.pits = pits;
        return this;
    }

    public GameDtoTestBuilder nextPlayer(String nextPlayer) {
        this.nextPlayer = nextPlayer;
        return this;
    }

    public GameDto build() {
        return GameDto.builder()
                .gameId(gameId)
                .startedTime(startedTime)
                .duration(duration)
                .status(status)
                .pits(pits)
                .nextPlayer(nextPlayer)
                .build();
    }
}

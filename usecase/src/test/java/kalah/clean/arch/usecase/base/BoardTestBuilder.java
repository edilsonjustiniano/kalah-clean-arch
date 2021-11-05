package kalah.clean.arch.usecase.base;

import kalah.clean.arch.domain.entity.Board;

public final class BoardTestBuilder {

    private static final int[] DEFAULT_PITS = {1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1};

    private static final int[] DEFAULT_PLAYER_ONE_EMPTY_PITS = {0, 0, 0, 0, 0, 0, 0,
            2, 2, 2, 2, 2, 2, 2};

    private static final int[] DEFAULT_PLAYER_TWO_EMPTY_PITS = {2, 2, 2, 2, 2, 2, 2,
            0, 0, 0, 0, 0, 0, 0};

    private BoardTestBuilder() {
    }

    private int[] pits = DEFAULT_PITS;

    public static BoardTestBuilder aBoard() {
        return new BoardTestBuilder();
    }

    public BoardTestBuilder pits(int[] pits) {
        this.pits = pits;
        return this;
    }

    public static BoardTestBuilder aPlayerTwoWinnerPits() {
        return new BoardTestBuilder().pits(DEFAULT_PLAYER_ONE_EMPTY_PITS);
    }

    public static BoardTestBuilder aPlayerOneWinnerPits() {
        return new BoardTestBuilder().pits(DEFAULT_PLAYER_TWO_EMPTY_PITS);
    }

    public Board build() {
        return Board.builder()
                .pits(pits)
                .build();
    }
}

package kalah.clean.arch.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.stream.IntStream;

@Getter
@Builder
@AllArgsConstructor
public class Board {

    public static final int PLAYER_ONE_KALAH_INDEX = 6;
    public static final int PLAYER_TWO_KALAH_INDEX = 13;
    public static final int PLAYER_TWO_LAST_PIT_INDEX = 12;

    private static final int PLAYER_ONE_KALAH = 7;
    private static final int PLAYER_TWO_KALAH = 14;

    @Builder.Default
    private int[] pits = {1, 1, 1, 1, 1, 1, 0,
            1, 1, 1, 1, 1, 1, 0};

    //Using tell don't ask design pattern to encapsulate some business logic and avoid split them around the code
    // These transient annotated methods will not be stored on our db
    public boolean isPlayerOnePit(int selectedPit) {
        return selectedPit >= 0 && selectedPit < (PLAYER_ONE_KALAH - 1);
    }

    public boolean isPlayerTwoPit(int selectedPit) {
        return selectedPit >= PLAYER_ONE_KALAH && selectedPit < (PLAYER_TWO_KALAH - 1);
    }

    public boolean isNotPlayerOneKalah(int pitIndex) {
        return PLAYER_ONE_KALAH != (pitIndex + 1);
    }

    public boolean isPlayerTwoKalah(int pitIndex) {
        return PLAYER_TWO_KALAH == (pitIndex + 1);
    }

    public boolean isNotPlayerTwoKalah(int pitIndex) {
        return !isPlayerTwoKalah(pitIndex);
    }

    public boolean arePlayerOnePitsEmpty() {
        return IntStream.range(0, (PLAYER_ONE_KALAH - 1))
                .noneMatch(i -> pits[i] > 0);
    }

    public boolean arePlayerTwoPitsEmpty() {
        return IntStream.range(PLAYER_ONE_KALAH, (PLAYER_TWO_KALAH - 1))
                .noneMatch(i -> pits[i] > 0);
    }

    public boolean haveNoStonesOnPit(int pitIndex) {
        return pits[pitIndex] == 0;
    }

    public int getNextPitIndex(int nextPitIndex) {
        // We need to reset the next Pit index if it is the Player two's kalah
        if (isPlayerTwoKalah(nextPitIndex)) {
            nextPitIndex = 0;
        } else {
            nextPitIndex++;
        }

        return nextPitIndex;
    }

    public Board cleanPit(int pitIndex) {
        pits[pitIndex] = 0;
        return this;
    }

    public Board collectOppositeStones(int nextPitIndex, int kalahPalyerIndex) {
        // In order to find the opposite pit index I did the following calc (assuming the following pit positions):
        //   12 11 10  9  8  7
        // 13                  6
        //    0  1  2  3  4  5
        // 12 = Last Player two pit
        // So to get the opposite pit is just subtract the last player two pit index to the nextPitId.
        // For example: 12 - 3 (nextPitId) = 9. So, the opposite pit of 3 is the pit of index 9
        // The same logic but in inverse way
        getPits()[nextPitIndex] = 0;
        getPits()[PLAYER_TWO_LAST_PIT_INDEX - nextPitIndex] = 0;
        int oppositeStones = getPits()[PLAYER_TWO_LAST_PIT_INDEX - nextPitIndex];
        getPits()[kalahPalyerIndex] += (oppositeStones + 1);

        return this;
    }

}

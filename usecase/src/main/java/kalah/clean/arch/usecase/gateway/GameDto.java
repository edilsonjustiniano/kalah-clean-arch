package kalah.clean.arch.usecase.gateway;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GameDto {

    private String gameId;
    private long startedTime;
    private Long duration;

    private String status;
    private int[] pits;

    private String nextPlayer;
}

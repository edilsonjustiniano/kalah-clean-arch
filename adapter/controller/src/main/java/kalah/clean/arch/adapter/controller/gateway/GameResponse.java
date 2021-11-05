package kalah.clean.arch.adapter.controller.gateway;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class GameResponse {

    private String gameId;
    private long startedTime;
    private Long duration;

    private String status;
    private int[] pits;

    private String nextPlayer;
}

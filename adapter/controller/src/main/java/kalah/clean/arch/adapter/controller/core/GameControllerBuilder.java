package kalah.clean.arch.adapter.controller.core;

import kalah.clean.arch.adapter.controller.gateway.GameResponse;
import kalah.clean.arch.usecase.gateway.GameDto;

public class GameControllerBuilder {

    public GameResponse buildGameResponse(GameDto dto) {
        return GameResponse.builder()
                .gameId(dto.getGameId())
                .startedTime(dto.getStartedTime())
                .duration(dto.getDuration())
                .nextPlayer(dto.getNextPlayer())
                .pits(dto.getPits())
                .status(dto.getStatus())
                .build();
    }
}

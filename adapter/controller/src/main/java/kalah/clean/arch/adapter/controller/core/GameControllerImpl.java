package kalah.clean.arch.adapter.controller.core;

import kalah.clean.arch.adapter.controller.gateway.GameController;
import kalah.clean.arch.adapter.controller.gateway.GameResponse;
import kalah.clean.arch.usecase.exception.GameException;
import kalah.clean.arch.usecase.gateway.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GameControllerImpl implements GameController {

    private final CreateGame createGame;
    private final SearchGame searchGame;
    private final DeleteGame deleteGame;
    private final MovePit movePit;
    private final GameControllerBuilder builder;

    @Override
    public GameResponse createGame() {
        log.info("Creating a new game");
        return builder.buildGameResponse(createGame.start());
    }

    @Override
    public GameResponse searchGame(String gameId) {
        log.info("Searching for a game. GameId: {}.", gameId);
        var dto = searchGame.search(gameId);
        return builder.buildGameResponse(dto);
    }

    @Override
    public void removeGame(String gameId) {
        log.info("Removing a game. GameId: {}.", gameId);
        deleteGame.delete(gameId);
    }

    @Override
    public GameResponse makeMovement(String gameId, int pitId) throws GameException {
        log.info("Making a movement. GameId: {} and Pit: {}.", gameId, pitId);
        var dto = movePit.play(gameId, pitId);
        return builder.buildGameResponse(dto);
    }
}

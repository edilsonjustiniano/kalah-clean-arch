package kalah.clean.arch.adapter.controller.gateway;

import kalah.clean.arch.usecase.exception.GameException;

public interface GameController {

    GameResponse createGame();

    GameResponse searchGame(String gameId);

    void removeGame(String gameId);

    GameResponse makeMovement(String gameId, int pitId) throws GameException;
}

package kalah.clean.arch.usecase.gateway;

import kalah.clean.arch.usecase.exception.GameException;

public interface MovePit {

    GameDto play(String gameId, int pitId) throws GameException;
}

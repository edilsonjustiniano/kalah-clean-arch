package kalah.clean.arch.repository.gateway;


import kalah.clean.arch.domain.entity.Game;

import java.util.Optional;

public interface GameRepository {

    Optional<Game> findById(String gameId);

    Game save(Game game);

    void delete(Game game);
}

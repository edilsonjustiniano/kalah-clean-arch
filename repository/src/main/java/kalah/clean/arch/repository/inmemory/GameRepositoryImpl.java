package kalah.clean.arch.repository.inmemory;

import kalah.clean.arch.domain.entity.Game;
import kalah.clean.arch.repository.gateway.GameRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameRepositoryImpl implements GameRepository {

    private static final Map<String, Game> DB = new HashMap<>();

    @Override
    public Optional<Game> findById(String gameId) {
        return Optional.ofNullable(DB.get(gameId));
    }

    @Override
    public Game save(Game game) {
        DB.putIfAbsent(game.getGameId(), game);
        return game;
    }

    @Override
    public void delete(Game game) {
        DB.remove(game.getGameId(), game);
    }
}

package kalah.clean.arch.usecase;

import kalah.clean.arch.domain.entity.Game;
import kalah.clean.arch.repository.gateway.GameRepository;
import kalah.clean.arch.usecase.gateway.CreateGame;
import kalah.clean.arch.usecase.gateway.GameDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CreateGameImpl implements CreateGame {

    private final GameRepository repository;
    private final GameBuilder builder;

    @Override
    public GameDto start() {
        log.info("Creating a new game.");

        Game game = builder.buildGame();

        log.debug("Saving the new game. GameId: {}.", game.getGameId());
        repository.save(game);

        log.debug("Game successfully created. GameId: {}.", game.getGameId());
        return builder.buildDto(game);
    }
}

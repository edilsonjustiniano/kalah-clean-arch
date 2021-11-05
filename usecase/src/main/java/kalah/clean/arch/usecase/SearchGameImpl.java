package kalah.clean.arch.usecase;

import kalah.clean.arch.repository.gateway.GameRepository;
import kalah.clean.arch.usecase.gateway.GameDto;
import kalah.clean.arch.usecase.gateway.SearchGame;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SearchGameImpl implements SearchGame {

    private final GameRepository repository;
    private final GameBuilder builder;

    @Override
    public GameDto search(String gameId) {
        return retrieveGame(gameId);
    }

    private GameDto retrieveGame(String gameId) {
        return repository.findById(gameId)
                .map(builder::buildDto)
                .orElseThrow(() -> {
                    log.info("The game was not found. GameId: {}.", gameId);
                    return new RuntimeException("Game not found!");
                });
    }
}

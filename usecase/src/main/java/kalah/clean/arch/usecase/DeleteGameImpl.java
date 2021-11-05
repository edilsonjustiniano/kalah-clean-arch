package kalah.clean.arch.usecase;

import kalah.clean.arch.domain.entity.Game;
import kalah.clean.arch.repository.gateway.GameRepository;
import kalah.clean.arch.usecase.gateway.DeleteGame;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DeleteGameImpl implements DeleteGame {

    private final GameRepository repository;

    @Override
    public void delete(String gameId) {
        log.info("Deleting a game. GameId: {}.", gameId);

        Game game = retrieveGame(gameId);

        if (game.isGameRunning()) {
            //Here I could implement a data validation and disallow such operation for game still pending
            log.info("The game is still running...");
        }

        log.debug("Deleting the game. GameId: {} and status: {}.", game.getGameId(), game.getGameStatus());
        repository.delete(game);

        log.debug("Game successfully deleted. GameId: {} and status: {}.", game.getGameId(), game.getGameStatus());
    }

    private Game retrieveGame(String gameId) {
        return repository.findById(gameId).orElseThrow(() -> new RuntimeException("The Game was not found."));
    }
}

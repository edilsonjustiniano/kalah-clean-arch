package kalah.clean.arch.application.terminal.config;

import kalah.clean.arch.adapter.controller.core.GameControllerBuilder;
import kalah.clean.arch.adapter.controller.core.GameControllerImpl;
import kalah.clean.arch.adapter.controller.gateway.GameController;
import kalah.clean.arch.repository.gateway.GameRepository;
import kalah.clean.arch.repository.inmemory.GameRepositoryImpl;
import kalah.clean.arch.usecase.*;
import kalah.clean.arch.usecase.gateway.CreateGame;
import kalah.clean.arch.usecase.gateway.DeleteGame;
import kalah.clean.arch.usecase.gateway.MovePit;
import kalah.clean.arch.usecase.gateway.SearchGame;

public class TerminalGameConfig {

    private GameBuilder gameBuilder = new GameBuilder();
    private GameRepository gameRepository = new GameRepositoryImpl();
    private CreateGame createGame = new CreateGameImpl(gameRepository, gameBuilder);
    private DeleteGame deleteGame = new DeleteGameImpl(gameRepository);
    private SearchGame searchGame = new SearchGameImpl(gameRepository, gameBuilder);
    private GameDataValidator gameDataValidator = new GameDataValidator();
    private MovePit movePit = new MovePitImpl(gameRepository, gameBuilder, gameDataValidator);
    private GameControllerBuilder gameControllerBuilder = new GameControllerBuilder();
    private GameController controller = new GameControllerImpl(createGame, searchGame, deleteGame, movePit, gameControllerBuilder);

    public GameBuilder getGameBuilder() {
        return gameBuilder;
    }

    public GameRepository getGameRepository() {
        return gameRepository;
    }

    public CreateGame getCreateGame() {
        return createGame;
    }

    public DeleteGame getDeleteGame() {
        return deleteGame;
    }

    public SearchGame getSearchGame() {
        return searchGame;
    }

    public GameDataValidator getGameDataValidator() {
        return gameDataValidator;
    }

    public MovePit getMovePit() {
        return movePit;
    }

    public GameControllerBuilder getGameControllerBuilder() {
        return gameControllerBuilder;
    }

    public GameController getController() {
        return controller;
    }
}

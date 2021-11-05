package kalah.clean.arch.application.spring.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringGameConfig {

    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return objectMapper;
    }

    @Bean
    public GameBuilder gameBuilder() {
        return new GameBuilder();
    }

    @Bean
    public GameRepository gameRepository() {
        return new GameRepositoryImpl();
    }

    @Bean
    public CreateGame createGame(GameRepository gameRepository, GameBuilder gameBuilder) {
        return new CreateGameImpl(gameRepository, gameBuilder);
    }

    @Bean
    public DeleteGame deleteGame(GameRepository gameRepository, GameBuilder gameBuilder) {
        return new DeleteGameImpl(gameRepository);
    }

    @Bean
    public SearchGame searchGame(GameRepository gameRepository, GameBuilder gameBuilder) {
        return new SearchGameImpl(gameRepository, gameBuilder);
    }

    @Bean
    public GameDataValidator gameDataValidator() {
        return new GameDataValidator();
    }

    @Bean
    public MovePit movePit(GameRepository gameRepository, GameBuilder gameBuilder, GameDataValidator gameDataValidator) {
        return new MovePitImpl(gameRepository, gameBuilder, gameDataValidator);
    }

    @Bean
    public GameControllerBuilder gameControllerBuilder() {
        return new GameControllerBuilder();
    }

    @Bean
    public GameController controller(CreateGame createGame, SearchGame searchGame, DeleteGame deleteGame, MovePit movePit, GameControllerBuilder gameControllerBuilder) {
        return new GameControllerImpl(createGame, searchGame, deleteGame, movePit, gameControllerBuilder);
    }
}

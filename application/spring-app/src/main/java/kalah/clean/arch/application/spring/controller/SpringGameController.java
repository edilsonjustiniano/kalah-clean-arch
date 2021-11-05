package kalah.clean.arch.application.spring.controller;

import kalah.clean.arch.adapter.controller.gateway.GameController;
import kalah.clean.arch.adapter.controller.gateway.GameResponse;
import kalah.clean.arch.usecase.exception.GameException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SpringGameController {

    private static final String GAME_URL = "/games";
    private static final String GAME_URL_WITH_GAME_ID = "/games/{gameId}";
    private static final String MOVEMENT_URL = "/games/{gameId}/pits/{pitId}";

    private static final String PATH_PARAM_GAME_ID = "gameId";
    private static final String PATH_PARAM_PIT_ID = "pitId";

    private final GameController controller;

    @PostMapping(value = GAME_URL, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<GameResponse> createGame() {
        log.info("Requesting a new game.");

        return ResponseEntity
                .status(CREATED)
                .body(controller.createGame());
    }

    @GetMapping(GAME_URL_WITH_GAME_ID)
    public ResponseEntity<GameResponse> getGame(@PathVariable(PATH_PARAM_GAME_ID) String gameId) {
        log.info("Getting the game. GameId: {}.", gameId);

        return ResponseEntity
                .ok(controller.searchGame(gameId));
    }

    @DeleteMapping(GAME_URL_WITH_GAME_ID)
    public ResponseEntity deleteGame(@PathVariable(PATH_PARAM_GAME_ID) String gameId) {
        log.info("Requesting the game deletion. GameId: {}.", gameId);

        controller.removeGame(gameId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping(value = MOVEMENT_URL, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<GameResponse> moveGame(@PathVariable(PATH_PARAM_GAME_ID) String gameId,
                                                 @PathVariable(PATH_PARAM_PIT_ID) int pitId) throws GameException {
        log.info("Making a movement in the game. GameId: {} and PitId: {}.", gameId, pitId);


        return ResponseEntity
                .ok(controller.makeMovement(gameId, pitId));
    }
}

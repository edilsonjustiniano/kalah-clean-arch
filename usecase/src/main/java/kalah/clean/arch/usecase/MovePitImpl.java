package kalah.clean.arch.usecase;

import kalah.clean.arch.domain.entity.Board;
import kalah.clean.arch.domain.entity.Game;
import kalah.clean.arch.repository.gateway.GameRepository;
import kalah.clean.arch.usecase.exception.GameException;
import kalah.clean.arch.usecase.gateway.GameDto;
import kalah.clean.arch.usecase.gateway.MovePit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static kalah.clean.arch.domain.entity.Board.PLAYER_ONE_KALAH_INDEX;
import static kalah.clean.arch.domain.entity.Board.PLAYER_TWO_KALAH_INDEX;

@Slf4j
@RequiredArgsConstructor
public class MovePitImpl implements MovePit {

    private final GameRepository repository;
    private final GameBuilder builder;
    private final GameDataValidator validator;

    @Override
    public GameDto play(String gameId, int pitId) throws GameException {
        log.info("Making a movement. GameId: {} and PitId: {}.", gameId, pitId);

        int normalizePitId = pitId - 1;
        Game game = retrieveGame(gameId);

        validator.validateMovement(game, normalizePitId);

        startMovement(game, normalizePitId);

        log.debug("The movement has been done successfully. GameId: {} and PitId: {}.", game.getGameId(), pitId);
        return builder.buildDto(game);
    }

    /*
     * Method that is responsible only to apply the movement according the selected pitId
     */
    private void startMovement(Game game, int pitId) {
        int nextPitIndex = moveStones(game, pitId);

        // Check the opposite pit is empty
        takeOppositeStones(game, nextPitIndex);

        // Check the end's game after all turns
        boolean isGameOver = game.isGameOver();

        if (isGameOver) {
            game.finish();
        } else {
            // Set the next player according the rules
            game.updateTurn(nextPitIndex);
        }

        log.debug("Saving the game after apply the movement. GameId: {}.", game.getGameId());
        repository.save(game);

    }

    private int moveStones(Game game, int pitId) {
        Board board = game.getBoard();
        int numberOfStones = board.getPits()[pitId];
        board.cleanPit(pitId);

        int nextPitIndex = pitId;

        do {
            nextPitIndex = board.getNextPitIndex(nextPitIndex);

            // The stones cannot be put in the opponent's kalah. So, skip it
            if (game.canTakeOpponentStones(nextPitIndex)) {
                board.getPits()[nextPitIndex] += 1;
                numberOfStones--;
            }
        } while (numberOfStones > 0);

        return nextPitIndex;
    }

    private void takeOppositeStones(Game game, int nextPitIndex) {
        Board board = game.getBoard();
        // If it is the player one turn and the last stone is put in his/her owns pit, get the opposite pits stone
        if (game.isPlayerOneTurn() && board.isPlayerOnePit(nextPitIndex)) {
            if (board.getPits()[nextPitIndex] == 1) {
                board.collectOppositeStones(nextPitIndex, PLAYER_ONE_KALAH_INDEX);
            }
        } else if (game.isPlayerTwoTurn() && board.isPlayerTwoPit(nextPitIndex)) {
            if (board.getPits()[nextPitIndex] == 1) {
                board.collectOppositeStones(nextPitIndex, PLAYER_TWO_KALAH_INDEX);
            }
        }
    }

    private Game retrieveGame(String gameId) {
        return repository.findById(gameId).orElseThrow(() -> new RuntimeException("The Game was not found."));
    }
}

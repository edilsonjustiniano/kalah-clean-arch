package kalah.clean.arch.usecase;

import kalah.clean.arch.domain.entity.Board;
import kalah.clean.arch.domain.entity.Game;
import kalah.clean.arch.usecase.exception.GameException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameDataValidator {

    public void validateMovement(Game game, int selectedPitId) throws GameException {
        Board board = game.getBoard();
        if (game.isGameFinished()) {
            log.error("The provided game is already finished. GameId: {}.", game.getGameId());
            throw new GameException("The game is already finished, please start a new game.");
        } else if (game.isPlayerOneTurn() && !board.isPlayerOnePit(selectedPitId)) {
            log.error("The selected Pit does not belong to the Player one. GameId: {} and PitId: {}.", game.getGameId(), selectedPitId);
            throw new GameException("The movement is not allowed, please select a different pit.");
        } else if (game.isPlayerTwoTurn() && !board.isPlayerTwoPit(selectedPitId)) {
            log.error("The selected Pit does not belong to the Player two. GameId: {} and PitId: {}.", game.getGameId(), selectedPitId);
            throw new GameException("The movement is not allowed, please select a different pit.");
        } else if (board.haveNoStonesOnPit(selectedPitId)) {
            log.error("There is no more stone in the selected pit. GameId: {} and PitId: {}.", game.getGameId(), selectedPitId);
            throw new GameException("There is no more stone in the selected pit, please select another pit.");
        }
    }
}

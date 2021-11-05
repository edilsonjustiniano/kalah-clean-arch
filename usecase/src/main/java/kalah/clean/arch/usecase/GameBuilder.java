package kalah.clean.arch.usecase;

import kalah.clean.arch.domain.entity.Board;
import kalah.clean.arch.domain.entity.Game;
import kalah.clean.arch.usecase.gateway.GameDto;

import static java.time.Instant.now;
import static kalah.clean.arch.domain.entity.Game.GameStatus.RUNNING;
import static kalah.clean.arch.domain.entity.Game.Player.PLAYER_ONE;

public class GameBuilder {

    public Game buildGame() {
        return Game.builder()
                .gameId(Game.generateGameId())
                .startedTime(now().toEpochMilli())
                .board(buildBoard())
                .nextPlayer(PLAYER_ONE)
                .gameStatus(RUNNING)
                .build();
    }

    public GameDto buildDto(Game game) {
        return GameDto.builder()
                .gameId(game.getGameId())
                .startedTime(game.getStartedTime())
                .duration(game.duration())
                .nextPlayer(game.getNextPlayer().name())
                .status(game.getGameStatus().name())
                .pits(game.getBoard().getPits())
                .build();
    }

    private Board buildBoard() {
        return Board.builder()
                .build();
    }


}

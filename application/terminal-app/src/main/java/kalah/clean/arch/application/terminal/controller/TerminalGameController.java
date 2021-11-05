package kalah.clean.arch.application.terminal.controller;

import kalah.clean.arch.adapter.controller.gateway.GameResponse;
import kalah.clean.arch.application.terminal.config.TerminalGameConfig;
import kalah.clean.arch.usecase.exception.GameException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TerminalGameController {

    private TerminalGameConfig config = new TerminalGameConfig();

    public GameResponse createGame() {
        return config.getController().createGame();
    }

    public GameResponse searchGame(String gameId) {
        return config.getController().searchGame(gameId);
    }

    public GameResponse makeMovement(String gameId, int pitNumber) throws GameException {
        return config.getController().makeMovement(gameId, pitNumber);
    }

    public void removeGame(String gameId) {
        config.getController().removeGame(gameId);
    }
}

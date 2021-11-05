package kalah.clean.arch.application.terminal.view;

import kalah.clean.arch.adapter.controller.gateway.GameResponse;
import kalah.clean.arch.application.terminal.controller.TerminalGameController;
import kalah.clean.arch.usecase.exception.GameException;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

@RequiredArgsConstructor
public class TerminalGameView {

    private static final int CREATE_GAME_OPTION = 1;
    private static final int SEARCH_GAME_OPTION = 2;
    private static final int MAKE_PIT_MOVE_OPTION = 3;
    private static final int DELETE_GAME_OPTION = 4;
    private static final int SHUTDOWN_OPTION = 5;

    private static final int PLAYER_ONE_KALAH_INDEX = 6;
    private static final int PLAYER_TWO_KALAH_INDEX = 13;

    private static final Scanner scanner = new Scanner(System.in);
    public static final int NUMBER_OF_PITS = 6;

    private final TerminalGameController controller = new TerminalGameController();

    public void menu() {
        System.out.println("Please, choose one of the options below:");
        System.out.println("1 - Creates a new game.");
        System.out.println("2 - Search for a game.");
        System.out.println("3 - Make a pit movement.");
        System.out.println("4 - Delete a game.");
        System.out.println("5 - Shutdown.");
        System.out.println("\nWaiting for your option...\n");

        var option = scanner.nextLine();

        try {
            var optionNumber = Integer.parseInt(option);
            switch (optionNumber) {
                case CREATE_GAME_OPTION:
                    create();
                case SEARCH_GAME_OPTION:
                    search();
                case MAKE_PIT_MOVE_OPTION:
                    movePit();
                case DELETE_GAME_OPTION:
                    delete();
                case SHUTDOWN_OPTION:
                    shutdown();
                default:
                    invalidOption();
            }
        } catch (NumberFormatException e) {
            System.out.println("Failure to convert the option number.");
        }
    }

    public void create() {
        System.out.println("A new game is being created...\n");
        var response = controller.createGame();
        printGame(response);

        menu();
    }

    public void search() {
        System.out.println("Please, provide the gameId:");
        String gameId = scanner.nextLine();

        var response = controller.searchGame(gameId);
        printGame(response);

        menu();
    }

    public void movePit() {
        System.out.println("Please, provide the gameId:");
        String gameId = scanner.nextLine();

        System.out.println("Please, provide the pit number:");
        String pit = scanner.nextLine();

        try {
            var pitNumber = Integer.parseInt(pit);
            var response = controller.makeMovement(gameId, pitNumber);
            printGame(response);

        } catch (NumberFormatException e) {
            System.out.println("Failure to convert the pit number.");
        } catch (GameException e) {
            System.out.println(e.getMessage());
        }

        menu();
    }

    public void delete() {
        System.out.println("Please, provide the gameId:");
        String gameId = scanner.nextLine();

        controller.removeGame(gameId);

        menu();
    }

    private void shutdown() {
        System.out.println("Game is over!");
        System.exit(0);
    }

    private void invalidOption() {
        System.out.println("Invalid option. Please, provide a valid one!");
        menu();
    }

    private void printGame(GameResponse response) {
        System.out.println("GameId: " + response.getGameId());
        System.out.println("Started time: " + response.getStartedTime());
        System.out.println("Duration in seconds: " + response.getDuration());
        System.out.println("Game status: " + response.getStatus());
        System.out.println("Board: ");
        printBoard(response);
        System.out.println("Next Player: " + response.getNextPlayer());
    }

    private void printBoard(GameResponse response) {

        for (int i = 0; i < PLAYER_ONE_KALAH_INDEX; i++) {
            System.out.print("\t" + response.getPits()[i]);
        }

        System.out.println("\n" + response.getPits()[PLAYER_ONE_KALAH_INDEX] + "\t\t\t\t\t\t\t " + response.getPits()[PLAYER_TWO_KALAH_INDEX]);

        for (int i = (PLAYER_ONE_KALAH_INDEX + 1); i < PLAYER_TWO_KALAH_INDEX; i++) {
            System.out.print("\t" + response.getPits()[i]);
        }

        System.out.println("\n");
        printBoardSubtitle();
    }

    private void printBoardSubtitle() {
        for (int i = 0; i < NUMBER_OF_PITS; i++) {
            System.out.print("\t *");
        }

        System.out.println("\n+ \t\t\t\t\t\t\t +");
        for (int i = 0; i < NUMBER_OF_PITS; i++) {
            System.out.print("\t #");
        }
        System.out.println("\nSubtitle:");
        System.out.println("* : Player One's pits");
        System.out.println("+ : Player's kalah");
        System.out.println("# : Player two's pits");
    }

}

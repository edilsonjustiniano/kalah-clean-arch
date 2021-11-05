package kalah.clean.arch.application.terminal;

import kalah.clean.arch.application.terminal.view.TerminalGameView;

public class Application {

    public static void main(String[] args) {
        var view = new TerminalGameView();
        view.menu();
    }
}

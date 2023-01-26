

public class GameLoop {

    private final Connect4Game game;
    private final GUI ourGUI;
    

    public GameLoop() {
        game = new Connect4Game("B", "P", 6, 7);
        ourGUI = new GUI(game.isIs1playing(), game, 6, 7);
        
    }

}
package com.chess_POO;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

import java.util.Random;

import com.chess_POO.board.Board;
import com.chess_POO.board.Move;
import com.chess_POO.board.MoveCreator;
import com.chess_POO.player.MoveBoard;
import com.chess_POO.player.ai.Minimax;
import com.chess_POO.player.ai.Strategy;

public class ChessGuest {

    static Strategy strategy = new Minimax(1);
    static MoveBoard move;
    static MoveBoard moveOpponent;
    static int startMove;
    static int endMove;
    static String startMoveOpponent;
    static String endMoveOpponent;
    static String moveReceive;

    public static void main(String[] args) throws Exception {
        // get the facade as a player
        PlayerFacade facade = Facade.getFacade();
        facade.waitReady();
        // set our palyer name
        final String playerName = "Joseph_Arezki" + new Random().nextInt();
        facade.createNewPlayer(playerName);
        System.out.println("I am: " + playerName);
        // wait until we are able to join a new game
        Game currentGame = facade.autoJoinGame("chess2");

        // get our mark
        GameCommand command = facade.receiveGameCommand(currentGame);
        if (!command.name().equals("youare")) {
            throw new RuntimeException();
        }

        Board board = Board.initialBoard();

        while (true) {
            // get a command from someone else
            GameCommand commandLoop = facade.receiveGameCommand(currentGame);

            switch (commandLoop.name()) {
                case "move":
                    handleNewBoard(facade, currentGame, commandLoop, board);
                    break;
                case "gameover":
                    // if the game is other, stop playing and show the results
                    handleGameOver(facade, currentGame, commandLoop, board);
                    System.exit(0);
            }

        }

    }

    /**
     * react uppon gameover command received
     *
     * @param facade          the player facade
     * @param currentGame     the game currently played
     * @param gameOverCommand
     */
    private static void handleGameOver(PlayerFacade facade, Game currentGame, GameCommand gameOverCommand,
            Board board) {

        // show a message depending on our victory or not
        switch (gameOverCommand.body()) {
            case "victory":
                System.out.println("I've won\n ");
                break;
            case "defeat":
                System.out.println("I've lost\n");
                break;
            case "tie":
                System.out.println("It's a tie\n");
        }

        // after the game over command, we should receive the final version of the board
        // and display it
        GameCommand command = facade.receiveGameCommand(currentGame);

        moveReceive = command.body();
        startMoveOpponent = moveReceive.substring(0, 2);
        endMoveOpponent = moveReceive.substring(3, 2);

        moveOpponent = board.currentPlayer()
                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition(startMoveOpponent),
                        Board.getCoordinateAtPosition(endMoveOpponent)));
        board = moveOpponent.getToBoard();

        System.out.println("final board: \n " + board);
    }

    private static void handleNewBoard(PlayerFacade facade, Game currentGame, GameCommand commandLoop, Board board) {
        // get the other player's move and retreive the board
        
        moveReceive = commandLoop.body();
        startMoveOpponent = moveReceive.substring(0, 2);
        endMoveOpponent = moveReceive.substring(3, 5);

        moveOpponent = board.currentPlayer()
                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition(startMoveOpponent),
                        Board.getCoordinateAtPosition(endMoveOpponent)));
        board = moveOpponent.getToBoard();

        // create your move
        Move calculatedMove = strategy.execute(board);
        startMove = calculatedMove.getCurrentCoordinate();
        endMove = calculatedMove.getPieceDestination();
        String moveSend = Board.getPositionAtCoordinate(startMove) + "-" + Board.getPositionAtCoordinate(endMove);

        move = board.currentPlayer().makeMove(MoveCreator.createMove(board, startMove, endMove));
        board = move.getToBoard();

        // send the move to the other player
        facade.sendGameCommandToAll(currentGame, new GameCommand("move", moveSend));
    }

}

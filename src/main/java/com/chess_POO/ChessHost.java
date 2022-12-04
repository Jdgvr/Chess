package com.chess_POO;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.HostFacade;
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

public class ChessHost {

    static Strategy strategy = new Minimax(5);
    static MoveBoard move;
    static MoveBoard moveOpponent;
    static int startMove;
    static int endMove;
    static String startMoveOpponent;
    static String endMoveOpponent;
    static String moveReceive;

    private ChessHost() {
    }

    public static void main(String[] args) throws Exception {
        // get the player facade, to interact with other player
        PlayerFacade playerFacade = (PlayerFacade) Facade.getFacade();
        // get the host facade, to manage the game
        HostFacade hostFacade = (HostFacade) Facade.getFacade();
        // wait until we are ready to use the host facade
        hostFacade.waitReady();
        // set our player name
        playerFacade.createNewPlayer("Joseph_Arezki" + new Random().nextInt());

        // play the game until the program quits
        while (true) {
            // creata a new game
            Game game = hostFacade.createNewGame("chess2");
            // wait for another player to join
            hostFacade.waitForExtraPlayerCount(2);
            // play the game using the player facade
            playTheGame(playerFacade, game);

        }
    }

    private static void playTheGame(PlayerFacade playerFacade, Game game) {
        // create a new board
        Board board = Board.initialBoard();
        System.out.println(board);
        // send its mark to the other player
        playerFacade.sendGameCommandToAll(game, new GameCommand("youare", "black"));

        // loop until the game is other
        while (true) {

            // check if the game is over
            if (handleGameOver(playerFacade, game, board))
                break;

            // create your move
            Move calculatedMove = strategy.execute(board);
            startMove = calculatedMove.getCurrentCoordinate();
            endMove = calculatedMove.getPieceDestination();
            String moveSend = Board.getPositionAtCoordinate(startMove) + "-" + Board.getPositionAtCoordinate(endMove);

            move = board.currentPlayer().makeMove(MoveCreator.createMove(board, startMove, endMove));
            board = move.getToBoard();
            System.out.println(board);

            // send the move to the other player
            playerFacade.sendGameCommandToAll(game, new GameCommand("move", moveSend));

            // get the other player's move and retreive the board
            GameCommand command = playerFacade.receiveGameCommand(game);

            moveReceive = command.body();
            startMoveOpponent = moveReceive.substring(0, 2);
            endMoveOpponent = moveReceive.substring(3, 5);

            moveOpponent = board.currentPlayer()
                    .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition(startMoveOpponent),
                            Board.getCoordinateAtPosition(endMoveOpponent)));
            board = moveOpponent.getToBoard();
            System.out.println(board);

        }
    }

    private static boolean handleGameOver(PlayerFacade playerFacade, Game game, Board board) {
        // check if the game is over
        if (board.blackPlayer().inCheckMate()) {
            whoWin(playerFacade, game, board);
        } else if (board.whitePlayer().inCheckMate()) {
            whoWin(playerFacade, game, board);
        } else if (board.currentPlayer().inStaleMate()) {
            whoWin(playerFacade, game, board);
        }
        // the game is not over
        return false;

    }

    private static boolean whoWin(final PlayerFacade playerFacade, final Game game, final Board board) {
        if (EvaluateWinner.winnerOfTheGame(board) == "White") {
            // we've won :-)
            playerFacade.sendGameCommandToAll(game, new GameCommand("gameover", "defeat"));
            playerFacade.sendGameCommandToAll(game, new GameCommand("board", board.toString()));
            System.out.println("victory!\n" + board);
            return true;
        } else if (EvaluateWinner.winnerOfTheGame(board) == "Black") {
            // we've lost :-(
            playerFacade.sendGameCommandToAll(game, new GameCommand("gameover", "victory"));
            playerFacade.sendGameCommandToAll(game, new GameCommand("board", board.toString()));
            System.out.println("defeat!\n" + board);
            return true;
        } else if (EvaluateWinner.winnerOfTheGame(board) == "Tie") {
            // it's a tie :-/
            playerFacade.sendGameCommandToAll(game, new GameCommand("gameover", "tie"));
            playerFacade.sendGameCommandToAll(game, new GameCommand("board", board.toString()));
            System.out.println("tie!\n" + board);
            return true;
        }
        return false;
    }
}

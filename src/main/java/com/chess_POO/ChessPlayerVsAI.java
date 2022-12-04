package com.chess_POO;

import com.chess_POO.board.Board;
import com.chess_POO.board.Move;
import com.chess_POO.board.MoveCreator;
import com.chess_POO.player.MoveBoard;
import com.chess_POO.player.MoveStatus;
import com.chess_POO.player.ai.Minimax;
import com.chess_POO.player.ai.Strategy;
import java.util.Scanner;

public class ChessPlayerVsAI {

    public static void main(String[] args) {
        Board board = Board.initialBoard();
        System.out.println(board);

        Scanner sc = new Scanner(System.in);

        int count = 0;
        Strategy strategy = new Minimax(1);
        MoveBoard move1;

        while (!board.currentPlayer().inCheckMate()) {
            count++;

            do {
                System.out.println("Choissisez la case de depart : ");
                String startMove = sc.nextLine();
                System.out.println("Choissisez la case d'arrivé' : ");
                String endMove = sc.nextLine();

                move1 = board.currentPlayer()
                        .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition(startMove),
                                Board.getCoordinateAtPosition(endMove)));
                board = move1.getToBoard();
            } while (move1.getMoveStatus() == MoveStatus.ILLEGAL_MOVE);

            Move calculatedMove = strategy.execute(board);

            MoveBoard move2 = board.currentPlayer().makeMove(calculatedMove);
            board = move2.getToBoard();

            System.out.println("Round : " + count + " -----------------");
            System.out.println(board);

        }

        if (EvaluateWinner.winnerOfTheGame(board) == "Black") {
            System.out.println("Black won !");
        } else if (EvaluateWinner.winnerOfTheGame(board) == "White") {
            System.out.println("White won");
        } else if (EvaluateWinner.winnerOfTheGame(board) == "Tie") {
            System.out.println("Tie");
        }
    }

}

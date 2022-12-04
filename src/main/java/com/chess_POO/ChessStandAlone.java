package com.chess_POO;

import com.chess_POO.board.Board;
import com.chess_POO.board.Move;
import com.chess_POO.player.MoveBoard;
import com.chess_POO.player.ai.Minimax;
import com.chess_POO.player.ai.Strategy;

public class ChessStandAlone {

    public static void main(String[] args) {
        Board board = Board.initialBoard();
        System.out.println(board);
        int count = 0;
        Strategy strategy = new Minimax(5);
        Strategy strategy2 = new Minimax(1);
        while (!board.currentPlayer().inCheckMate()) {
            count++;
            
            Move calculatedMove = strategy.execute(board);
            MoveBoard move = board.currentPlayer().makeMove(calculatedMove);
            board = move.getToBoard();

            System.out.println("Move : " + count +" -----------------");
            System.out.println(board);

            Move calculatedMove2 = strategy2.execute(board);
            MoveBoard move2 = board.currentPlayer().makeMove(calculatedMove2);
            board = move2.getToBoard();

            System.out.println("Move : " + count +" -----------------");
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

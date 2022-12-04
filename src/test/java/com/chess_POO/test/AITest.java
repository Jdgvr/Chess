package com.chess_POO.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.chess_POO.board.Board;
import com.chess_POO.board.Move;
import com.chess_POO.board.MoveCreator;
import com.chess_POO.player.MoveBoard;
import com.chess_POO.player.MoveStatus;
import com.chess_POO.player.ai.Minimax;
import com.chess_POO.player.ai.Strategy;

public class AITest {

    @Test
    public void foolMate() {
        Board board = Board.initialBoard();
        MoveBoard move1 = board.currentPlayer()
                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("f2"),
                        Board.getCoordinateAtPosition("f3")));
        assertTrue(move1.getMoveStatus() == MoveStatus.DONE);
        board = move1.getToBoard();

        MoveBoard move2 = board.currentPlayer()
                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e7"),
                        Board.getCoordinateAtPosition("e5")));
        assertTrue(move1.getMoveStatus() == MoveStatus.DONE);
        board = move2.getToBoard();

        MoveBoard move3 = board.currentPlayer()
                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("g2"),
                        Board.getCoordinateAtPosition("g4")));
        assertTrue(move1.getMoveStatus() == MoveStatus.DONE);
        board = move3.getToBoard();
        System.out.println(board);

        Strategy strategy = new Minimax(4);
        Move moveAI = strategy.execute(board);
        Move bestMove  = MoveCreator.createMove(board, Board.getCoordinateAtPosition("d8"),
        Board.getCoordinateAtPosition("h4"));
        assertEquals(bestMove, moveAI);
    
    
    }

}

package com.chess_POO.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.chess_POO.Color;
import com.chess_POO.board.Board;
import com.chess_POO.board.MoveCreator;
import com.chess_POO.board.Board.Builder;
import com.chess_POO.pieces.King;
import com.chess_POO.pieces.Pawn;
import com.chess_POO.pieces.Queen;
import com.chess_POO.pieces.Rook;
import com.chess_POO.player.MoveBoard;
import com.chess_POO.player.MoveStatus;

public class CheckTest {

    @Test
    public void testQueenRookMate() {

        Builder builder = new Builder();
        builder.setPiece(new King(5, Color.BLACK, false, false));
        builder.setPiece(new Rook(9, Color.WHITE, false));
        builder.setPiece(new Queen(16, Color.WHITE, false, false));
        builder.setPiece(new King(59, Color.WHITE, false, false));
        builder.setMoveSide(Color.WHITE);
        Board board = builder.build();

        MoveBoard move1 = board.currentPlayer()
                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a6"),
                        Board.getCoordinateAtPosition("a8")));
        assertTrue(move1.getMoveStatus() == MoveStatus.DONE);
        board = move1.getToBoard();
        assertTrue(board.currentPlayer().inCheckMate());

    }

    @Test
    public void testStaleMate() {

        Builder builder = new Builder();
        builder.setPiece(new Pawn(11, Color.BLACK, false));
        builder.setPiece(new King(3, Color.BLACK, false, false));
        builder.setPiece(new King(19, Color.WHITE, false, false));
        builder.setMoveSide(Color.WHITE);
        Board board = builder.build();
        assertFalse(board.currentPlayer().inStaleMate());
        assertTrue(board.currentPlayer().getOpponent().inStaleMate());
        assertFalse(board.currentPlayer().inCheck());
        assertFalse(board.currentPlayer().inCheckMate());
    }

}

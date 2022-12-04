package com.chess_POO.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.chess_POO.Color;
import com.chess_POO.board.Board;
import com.chess_POO.board.Move;
import com.chess_POO.board.MoveCreator;
import com.chess_POO.board.Board.Builder;
import com.chess_POO.pieces.Bishop;
import com.chess_POO.pieces.King;
import com.chess_POO.pieces.Knight;
import com.chess_POO.pieces.Pawn;
import com.chess_POO.pieces.Queen;
import com.chess_POO.pieces.Rook;
import com.chess_POO.player.MoveBoard;
import com.chess_POO.player.MoveStatus;

public class BoardTest {

    @Test
    public void initialBoard() {
        Board board = Board.initialBoard();
        assertEquals(20, board.currentPlayer().getLegalMoves().size());
        assertEquals(20, board.currentPlayer().getOpponent().getLegalMoves().size());
        assertFalse(board.currentPlayer().inCheck());
        assertFalse(board.currentPlayer().inCheckMate());
        assertFalse(board.currentPlayer().getKing().isCastled());
        assertEquals(board.whitePlayer(), board.currentPlayer());
        assertEquals(board.blackPlayer(), board.currentPlayer().getOpponent());
        assertFalse(board.currentPlayer().getOpponent().inCheck());
        assertFalse(board.currentPlayer().getOpponent().inCheckMate());
        assertFalse(board.currentPlayer().getOpponent().getKing().isCastled());
        assertTrue(board.whitePlayer().toString().equals("White"));
        assertTrue(board.blackPlayer().toString().equals("Black"));

        List<Move> allMovesTest = new ArrayList<>();
        allMovesTest.addAll(board.currentPlayer().getLegalMoves());
        for (final Move move : allMovesTest) {
            assertFalse(move.isAttack());
            assertFalse(move.isCastledMove());
        }
    }

    @Test
    public void testActivePieces() {
        Board board = Board.initialBoard();
        assertEquals(16, board.getBlackPieces().size());
        assertEquals(16, board.getWhitePieces().size());
    }

    @Test
    public void testCurrentPlayer() {
        Board board = Board.initialBoard();
        assertTrue(board.currentPlayer().toString().equals("White"));
        assertTrue(board.currentPlayer().getOpponent().toString().equals("Black"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBoard() {
        Builder builder = new Builder();
        builder.setPiece(new Rook(0, Color.BLACK, true));
        builder.setPiece(new Knight(1, Color.BLACK, true));
        builder.setPiece(new Bishop(2, Color.BLACK, true));
        builder.setPiece(new Queen(3, Color.BLACK, true, false));
        builder.setPiece(new King(4, Color.BLACK, true, false));
        builder.setPiece(new Bishop(5, Color.BLACK, true));
        builder.setPiece(new Knight(6, Color.BLACK, true));
        builder.setPiece(new Pawn(7, Color.BLACK, true));
        builder.setPiece(new Pawn(8, Color.BLACK, true));
        builder.setPiece(new Pawn(9, Color.BLACK, true));
        builder.setPiece(new Pawn(10, Color.BLACK, true));
        builder.setPiece(new Pawn(11, Color.BLACK, true));
        builder.setPiece(new Pawn(12, Color.BLACK, true));
        builder.setPiece(new Pawn(13, Color.BLACK, true));
        builder.setPiece(new Pawn(14, Color.BLACK, true));
        builder.setPiece(new Pawn(15, Color.BLACK, true));
        builder.setPiece(new Pawn(48, Color.WHITE, true));
        builder.setPiece(new Pawn(49, Color.WHITE, true));
        builder.setPiece(new Pawn(50, Color.WHITE, true));
        builder.setPiece(new Pawn(51, Color.WHITE, true));
        builder.setPiece(new Pawn(52, Color.WHITE, true));
        builder.setPiece(new Pawn(53, Color.WHITE, true));
        builder.setPiece(new Pawn(54, Color.WHITE, true));
        builder.setPiece(new Pawn(55, Color.WHITE, true));
        builder.setPiece(new Rook(56, Color.WHITE, true));
        builder.setPiece(new Knight(57, Color.WHITE, true));
        builder.setPiece(new Bishop(58, Color.WHITE, true));
        builder.setPiece(new Queen(59, Color.WHITE, true, false));
        builder.setPiece(new Bishop(61, Color.WHITE, true));
        builder.setPiece(new Knight(62, Color.WHITE, true));
        builder.setPiece(new Rook(63, Color.WHITE, true));
        builder.setMoveSide(Color.WHITE);
        builder.build();
    }

    @Test
    public void testAlgebreicNotation() {
        assertEquals("a8", Board.getPositionAtCoordinate(0));
        assertEquals("b8", Board.getPositionAtCoordinate(1));
        assertEquals("c8", Board.getPositionAtCoordinate(2));
        assertEquals("d8", Board.getPositionAtCoordinate(3));
        assertEquals("e8", Board.getPositionAtCoordinate(4));
        assertEquals("f8", Board.getPositionAtCoordinate(5));
        assertEquals("g8", Board.getPositionAtCoordinate(6));
        assertEquals("h8", Board.getPositionAtCoordinate(7));
        assertEquals("a7", Board.getPositionAtCoordinate(8));
        assertEquals("b7", Board.getPositionAtCoordinate(9));
        assertEquals("e6", Board.getPositionAtCoordinate(20));
        assertEquals("e2", Board.getPositionAtCoordinate(52));
    }

    @Test
    public void testIllegalMove() {
        Board board = Board.initialBoard();
        MoveBoard move1 = board.currentPlayer()
                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e2"),
                        Board.getCoordinateAtPosition("e3")));
        assertTrue(move1.getMoveStatus() == MoveStatus.DONE);
    }

}
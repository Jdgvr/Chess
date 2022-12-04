package com.chess_POO.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.chess_POO.Color;
import com.chess_POO.board.Board;
import com.chess_POO.board.Move;
import com.chess_POO.board.MoveCreator;
import com.chess_POO.pieces.Bishop;
import com.chess_POO.pieces.King;
import com.chess_POO.pieces.Knight;
import com.chess_POO.pieces.Pawn;
import com.chess_POO.pieces.Queen;
import com.chess_POO.pieces.Rook;
import com.chess_POO.player.MoveBoard;
import com.chess_POO.player.MoveStatus;

public class PieceTest {

        @Test
        public void testQueen() {
                Board.Builder builder = new Board.Builder();
                builder.setPiece(new King(4, Color.BLACK, false, false));
                builder.setPiece(new Queen(36, Color.WHITE, false, false));
                builder.setPiece(new King(60, Color.WHITE, false, false));
                builder.setMoveSide(Color.WHITE);
                Board board = builder.build();
                Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();
                assertEquals(31, whiteLegals.size());

                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e4"),
                                Board.getCoordinateAtPosition("e8"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e4"),
                                Board.getCoordinateAtPosition("e7"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e4"),
                                Board.getCoordinateAtPosition("e6"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e4"),
                                Board.getCoordinateAtPosition("e5"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e4"),
                                Board.getCoordinateAtPosition("e3"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e4"),
                                Board.getCoordinateAtPosition("e2"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e4"),
                                Board.getCoordinateAtPosition("a4"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e4"),
                                Board.getCoordinateAtPosition("b4"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e4"),
                                Board.getCoordinateAtPosition("c4"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e4"),
                                Board.getCoordinateAtPosition("d4"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e4"),
                                Board.getCoordinateAtPosition("f4"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e4"),
                                Board.getCoordinateAtPosition("g4"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e4"),
                                Board.getCoordinateAtPosition("h4"))));
        }

        @Test
        public void testKnight() {
                Board.Builder builder = new Board.Builder();
                builder.setPiece(new King(4, Color.BLACK, false, false));
                builder.setPiece(new Knight(0, Color.BLACK, false));
                builder.setPiece(new Knight(28, Color.WHITE, false));
                builder.setPiece(new Knight(56, Color.WHITE, false));
                builder.setPiece(new King(60, Color.WHITE, false, false));
                builder.setMoveSide(Color.WHITE);
                Board board = builder.build();
                Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();

                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e5"),
                                Board.getCoordinateAtPosition("d7"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e5"),
                                Board.getCoordinateAtPosition("f7"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e5"),
                                Board.getCoordinateAtPosition("g6"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e5"),
                                Board.getCoordinateAtPosition("g4"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e5"),
                                Board.getCoordinateAtPosition("f3"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e5"),
                                Board.getCoordinateAtPosition("d3"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e5"),
                                Board.getCoordinateAtPosition("c4"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e5"),
                                Board.getCoordinateAtPosition("c6"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a1"),
                                Board.getCoordinateAtPosition("b3"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a1"),
                                Board.getCoordinateAtPosition("c2"))));

                builder.setMoveSide(Color.BLACK);
                board = builder.build();
                Collection<Move> blackLegals = board.blackPlayer().getLegalMoves();
                assertTrue(blackLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a8"),
                                Board.getCoordinateAtPosition("c7"))));
                assertTrue(blackLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a8"),
                                Board.getCoordinateAtPosition("b6"))));

        }

        @Test
        public void testBishopMiddle() {
                final Board.Builder builder = new Board.Builder();
                builder.setPiece(new King(4, Color.BLACK, false, false));
                builder.setPiece(new Bishop(35, Color.WHITE, false));
                builder.setPiece(new King(60, Color.WHITE, false, false));
                builder.setMoveSide(Color.WHITE);
                final Board board = builder.build();
                Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("d4"),
                                Board.getCoordinateAtPosition("a7"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("d4"),
                                Board.getCoordinateAtPosition("b6"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("d4"),
                                Board.getCoordinateAtPosition("c5"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("d4"),
                                Board.getCoordinateAtPosition("e3"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("d4"),
                                Board.getCoordinateAtPosition("f2"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("d4"),
                                Board.getCoordinateAtPosition("g1"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("d4"),
                                Board.getCoordinateAtPosition("e5"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("d4"),
                                Board.getCoordinateAtPosition("c3"))));
        }

        @Test
        public void testBishopTopLeft() {
                final Board.Builder builder = new Board.Builder();
                builder.setPiece(new King(4, Color.BLACK, false, false));
                builder.setPiece(new Bishop(0, Color.WHITE, false));
                builder.setPiece(new King(60, Color.WHITE, false, false));
                builder.setMoveSide(Color.WHITE);
                final Board board = builder.build();
                Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a8"),
                                Board.getCoordinateAtPosition("b7"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a8"),
                                Board.getCoordinateAtPosition("c6"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a8"),
                                Board.getCoordinateAtPosition("d5"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a8"),
                                Board.getCoordinateAtPosition("e4"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a8"),
                                Board.getCoordinateAtPosition("f3"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a8"),
                                Board.getCoordinateAtPosition("g2"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a8"),
                                Board.getCoordinateAtPosition("h1"))));
        }

        @Test
        public void testBishopTopRight() {
                final Board.Builder builder = new Board.Builder();
                builder.setPiece(new King(4, Color.BLACK, false, false));
                builder.setPiece(new Bishop(7, Color.WHITE, false));
                builder.setPiece(new King(60, Color.WHITE, false, false));
                builder.setMoveSide(Color.WHITE);
                final Board board = builder.build();
                Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("h8"),
                                Board.getCoordinateAtPosition("g7"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("h8"),
                                Board.getCoordinateAtPosition("f6"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("h8"),
                                Board.getCoordinateAtPosition("e5"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("h8"),
                                Board.getCoordinateAtPosition("d4"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("h8"),
                                Board.getCoordinateAtPosition("c3"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("h8"),
                                Board.getCoordinateAtPosition("b2"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("h8"),
                                Board.getCoordinateAtPosition("a1"))));
        }

        @Test
        public void testBishopBottomRight() {
                final Board.Builder builder = new Board.Builder();
                builder.setPiece(new King(4, Color.BLACK, false, false));
                builder.setPiece(new Bishop(63, Color.WHITE, false));
                builder.setPiece(new King(60, Color.WHITE, false, false));
                builder.setMoveSide(Color.WHITE);
                final Board board = builder.build();
                Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("h1"),
                                Board.getCoordinateAtPosition("g2"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("h1"),
                                Board.getCoordinateAtPosition("f3"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("h1"),
                                Board.getCoordinateAtPosition("e4"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("h1"),
                                Board.getCoordinateAtPosition("d5"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("h1"),
                                Board.getCoordinateAtPosition("c6"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("h1"),
                                Board.getCoordinateAtPosition("b7"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("h1"),
                                Board.getCoordinateAtPosition("a8"))));
        }

        @Test
        public void testBishopBottomLeft() {
                final Board.Builder builder = new Board.Builder();
                builder.setPiece(new King(4, Color.BLACK, false, false));
                builder.setPiece(new Bishop(56, Color.WHITE, false));
                builder.setPiece(new King(60, Color.WHITE, false, false));
                builder.setMoveSide(Color.WHITE);
                final Board board = builder.build();
                Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a1"),
                                Board.getCoordinateAtPosition("b2"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a1"),
                                Board.getCoordinateAtPosition("c3"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a1"),
                                Board.getCoordinateAtPosition("d4"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a1"),
                                Board.getCoordinateAtPosition("e5"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a1"),
                                Board.getCoordinateAtPosition("f6"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a1"),
                                Board.getCoordinateAtPosition("g7"))));
                assertTrue(whiteLegals.contains(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a1"),
                                Board.getCoordinateAtPosition("h8"))));
        }

        @Test
        public void testPawnPromotion() {
                final Board.Builder builder = new Board.Builder();
                builder.setPiece(new Rook(3, Color.BLACK, false));
                builder.setPiece(new King(22, Color.BLACK, false, false));
                builder.setPiece(new Pawn(15, Color.WHITE, false));
                builder.setPiece(new King(52, Color.WHITE, false, false));
                builder.setMoveSide(Color.WHITE);
                Board board = builder.build();
                System.out.println(board);

                MoveBoard move1 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("h7"),
                                                Board.getCoordinateAtPosition("h8")));
                assertTrue(move1.getMoveStatus() == MoveStatus.DONE);
                board = move1.getToBoard();
                System.out.println(board);

                MoveBoard move2 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(move1.getToBoard(),
                                                Board.getCoordinateAtPosition("d8"),
                                                Board.getCoordinateAtPosition("e8")));
                assertTrue(move2.getMoveStatus() == MoveStatus.DONE);
                board = move2.getToBoard();
                System.out.println(board);
                MoveBoard move3 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(move2.getToBoard(),
                                                Board.getCoordinateAtPosition("h8"),
                                                Board.getCoordinateAtPosition("e8")));
                assertTrue(move3.getMoveStatus() == MoveStatus.DONE);
                System.out.println(board);
                assertTrue(board.currentPlayer().getQueen().isPromote());
        }

}

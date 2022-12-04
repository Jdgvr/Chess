package com.chess_POO.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.chess_POO.board.Board;
import com.chess_POO.board.MoveCreator;
import com.chess_POO.player.MoveBoard;
import com.chess_POO.player.MoveStatus;

public class CastleTest {

        @Test
        public void testWhiteKingSideCastle() {
                Board board = Board.initialBoard();
                MoveBoard move1 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("g2"),
                                                Board.getCoordinateAtPosition("g4")));
                assertTrue(move1.getMoveStatus() == MoveStatus.DONE);
                board = move1.getToBoard();

                MoveBoard move2 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e7"),
                                                Board.getCoordinateAtPosition("e6")));
                assertTrue(move2.getMoveStatus() == MoveStatus.DONE);
                board = move2.getToBoard();

                MoveBoard move3 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("f1"),
                                                Board.getCoordinateAtPosition("g2")));
                assertTrue(move3.getMoveStatus() == MoveStatus.DONE);
                board = move3.getToBoard();

                MoveBoard move4 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("b7"),
                                                Board.getCoordinateAtPosition("b6")));
                assertTrue(move4.getMoveStatus() == MoveStatus.DONE);
                board = move4.getToBoard();

                MoveBoard move5 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("g1"),
                                                Board.getCoordinateAtPosition("f3")));
                assertTrue(move5.getMoveStatus() == MoveStatus.DONE);
                board = move5.getToBoard();

                MoveBoard move6 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("c7"),
                                                Board.getCoordinateAtPosition("c6")));
                assertTrue(move6.getMoveStatus() == MoveStatus.DONE);
                board = move6.getToBoard();
                System.out.println(board);

                MoveBoard kingCastledMove = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e1"),
                                                Board.getCoordinateAtPosition("g1")));
                assertTrue(kingCastledMove.getMoveStatus() == MoveStatus.DONE);
                board = kingCastledMove.getToBoard();
                System.out.println(board);
        }

        @Test
        public void testWhiteQueenSideCastle() {
                Board board = Board.initialBoard();
                MoveBoard move1 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("d2"),
                                                Board.getCoordinateAtPosition("d4")));
                assertTrue(move1.getMoveStatus() == MoveStatus.DONE);
                board = move1.getToBoard();

                MoveBoard move2 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e7"),
                                                Board.getCoordinateAtPosition("e6")));
                assertTrue(move2.getMoveStatus() == MoveStatus.DONE);
                board = move2.getToBoard();

                MoveBoard move3 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("d1"),
                                                Board.getCoordinateAtPosition("d3")));
                assertTrue(move3.getMoveStatus() == MoveStatus.DONE);
                board = move3.getToBoard();

                MoveBoard move4 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("b7"),
                                                Board.getCoordinateAtPosition("b6")));
                assertTrue(move4.getMoveStatus() == MoveStatus.DONE);
                board = move4.getToBoard();

                MoveBoard move5 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("c1"),
                                                Board.getCoordinateAtPosition("d2")));
                assertTrue(move5.getMoveStatus() == MoveStatus.DONE);
                board = move5.getToBoard();

                MoveBoard move6 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("c7"),
                                                Board.getCoordinateAtPosition("c6")));
                assertTrue(move6.getMoveStatus() == MoveStatus.DONE);
                board = move6.getToBoard();

                MoveBoard move7 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("b1"),
                                                Board.getCoordinateAtPosition("a3")));
                assertTrue(move7.getMoveStatus() == MoveStatus.DONE);
                board = move7.getToBoard();

                MoveBoard move8 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("d7"),
                                                Board.getCoordinateAtPosition("d6")));
                assertTrue(move8.getMoveStatus() == MoveStatus.DONE);
                board = move8.getToBoard();

                MoveBoard queenCastledMove = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e1"),
                                                Board.getCoordinateAtPosition("c1")));
                assertTrue(queenCastledMove.getMoveStatus() == MoveStatus.DONE);
                board = queenCastledMove.getToBoard();
                System.out.println(board);


        }

        @Test
        public void testBlackKingSideCastle() {
                Board board = Board.initialBoard();
                MoveBoard move1 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("g8"),
                                                Board.getCoordinateAtPosition("h6")));
                assertTrue(move1.getMoveStatus() == MoveStatus.ILLEGAL_MOVE);

                MoveBoard move2 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a2"),
                                                Board.getCoordinateAtPosition("a3")));
                assertTrue(move2.getMoveStatus() == MoveStatus.DONE);
                board = move2.getToBoard();

                MoveBoard move3 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("g7"),
                                                Board.getCoordinateAtPosition("g6")));
                assertTrue(move3.getMoveStatus() == MoveStatus.DONE);
                board = move3.getToBoard();

                MoveBoard move4 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("b2"),
                                                Board.getCoordinateAtPosition("b3")));
                assertTrue(move4.getMoveStatus() == MoveStatus.DONE);
                board = move4.getToBoard();

                MoveBoard move5 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("g8"),
                                                Board.getCoordinateAtPosition("h6")));
                assertTrue(move5.getMoveStatus() == MoveStatus.DONE);
                board = move5.getToBoard();

                MoveBoard move6 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("c2"),
                                                Board.getCoordinateAtPosition("c3")));
                assertTrue(move6.getMoveStatus() == MoveStatus.DONE);
                board = move6.getToBoard();

                MoveBoard move7 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("f8"),
                                                Board.getCoordinateAtPosition("g7")));
                assertTrue(move7.getMoveStatus() == MoveStatus.DONE);
                board = move7.getToBoard();

                MoveBoard move8 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("d2"),
                                                Board.getCoordinateAtPosition("d3")));
                assertTrue(move8.getMoveStatus() == MoveStatus.DONE);
                board = move8.getToBoard();

                MoveBoard kingCastledMove = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e8"),
                                                Board.getCoordinateAtPosition("g8")));
                assertTrue(kingCastledMove.getMoveStatus() == MoveStatus.DONE);
                board = kingCastledMove.getToBoard();
                System.out.println(board);

        }

        @Test
        public void testBlackQueenSideCastle() {
                Board board = Board.initialBoard();
                MoveBoard move1 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("a2"),
                                                Board.getCoordinateAtPosition("a3")));
                assertTrue(move1.getMoveStatus() == MoveStatus.DONE);
                board = move1.getToBoard();

                MoveBoard move2 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("d7"),
                                                Board.getCoordinateAtPosition("d5")));
                assertTrue(move2.getMoveStatus() == MoveStatus.DONE);
                board = move2.getToBoard();

                MoveBoard move3 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("b2"),
                                                Board.getCoordinateAtPosition("b3")));
                assertTrue(move3.getMoveStatus() == MoveStatus.DONE);
                board = move3.getToBoard();

                MoveBoard move4 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("d8"),
                                                Board.getCoordinateAtPosition("d6")));
                assertTrue(move4.getMoveStatus() == MoveStatus.DONE);
                board = move4.getToBoard();

                MoveBoard move5 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("c2"),
                                                Board.getCoordinateAtPosition("c3")));
                assertTrue(move5.getMoveStatus() == MoveStatus.DONE);
                board = move5.getToBoard();

                MoveBoard move6 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("c8"),
                                                Board.getCoordinateAtPosition("d7")));
                assertTrue(move6.getMoveStatus() == MoveStatus.DONE);
                board = move6.getToBoard();

                MoveBoard move7 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("d2"),
                                                Board.getCoordinateAtPosition("d3")));
                assertTrue(move7.getMoveStatus() == MoveStatus.DONE);
                board = move7.getToBoard();

                MoveBoard move8 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("b8"),
                                                Board.getCoordinateAtPosition("a6")));
                assertTrue(move8.getMoveStatus() == MoveStatus.DONE);
                board = move8.getToBoard();

                MoveBoard move9 = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e2"),
                                                Board.getCoordinateAtPosition("e3")));
                assertTrue(move9.getMoveStatus() == MoveStatus.DONE);
                board = move9.getToBoard();

                MoveBoard queenCastledMove = board.currentPlayer()
                                .makeMove(MoveCreator.createMove(board, Board.getCoordinateAtPosition("e8"),
                                                Board.getCoordinateAtPosition("c8")));
                assertTrue(queenCastledMove.getMoveStatus() == MoveStatus.DONE);
                board = queenCastledMove.getToBoard();
                System.out.println(board);


        }

}

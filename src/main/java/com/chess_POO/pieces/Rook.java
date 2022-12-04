package com.chess_POO.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess_POO.Color;
import com.chess_POO.board.Board;
import com.chess_POO.board.Move;

public class Rook extends Piece {

    public Rook(int piecePosition, Color pieceColor, boolean firstMove) {
        super(PieceName.ROOK, piecePosition, pieceColor, firstMove);
    }

    private static final int TOP = -8;
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private static final int BOTTOM = 8;

    private final int[] movePossible = { TOP, LEFT, RIGHT, BOTTOM };
    private int moveDestination;

    @Override
    public final List<Move> legalMoves(final Board board) {
        List<Move> legalMoves = new ArrayList<>();

        for (int moveCurrent : movePossible) {
            moveDestination = this.piecePosition;

            while (Board.isValidCoordinate(moveDestination)) {

                if (firstColumnException(moveDestination, moveCurrent)
                        || eightColumnException(moveDestination, moveCurrent)) {
                    break;
                }

                moveDestination += moveCurrent;

                Move legalMove = tryCreateMove(board, moveDestination);
                if (legalMove != null) {
                    legalMoves.add(legalMove);
                    if (legalMove.isAttack()) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        return legalMoves;
    }

    @Override
    public final String toString() {
        return PieceName.ROOK.toString();
    }

    private final boolean firstColumnException(final int moveCurrent, final int moveOutOfBounds) {
        return Board.FIRST_COLUMN[moveCurrent] && (moveOutOfBounds == LEFT);
    }

    private final boolean eightColumnException(final int moveCurrent, final int moveOutOfBounds) {
        return Board.EIGHT_COLUMN[moveCurrent] && (moveOutOfBounds == RIGHT);
    }

    @Override
    public final Piece getMovedPiece(final int position, final Color color) {
        return new Rook(position, color, false);
    }

}

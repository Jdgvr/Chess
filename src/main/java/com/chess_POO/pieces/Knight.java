package com.chess_POO.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess_POO.Color;
import com.chess_POO.board.Board;
import com.chess_POO.board.Move;

public class Knight extends Piece {

    public Knight(int piecePosition, Color pieceColor, boolean firstMove) {
        super(PieceName.KNIGHT, piecePosition, pieceColor, firstMove);
    }

    private static final int TOP_TOP_LEFT = -17;
    private static final int TOP_TOP_RIGHT = -15;
    private static final int TOP_LEFT = -10;
    private static final int TOP_RIGHT = -6;
    private static final int BOTTOM_LEFT = 6;
    private static final int BOTTOM_RIGHT = 10;
    private static final int BOTTOM_BOTTOM_LEFT = 15;
    private static final int BOTTOM_BOTTOM_RIGHT = 17;

    private final int[] movePossible = { TOP_TOP_LEFT, TOP_TOP_RIGHT, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT,
            BOTTOM_BOTTOM_LEFT, BOTTOM_BOTTOM_RIGHT };
    private int moveDestination;

    @Override
    public final List<Move> legalMoves(final Board board) {
        List<Move> legalMoves = new ArrayList<>();

        for (int moveCurrent : movePossible) {
            moveDestination = this.piecePosition + moveCurrent;

            if (Board.isValidCoordinate(moveDestination)) {

                if (firstColumnException(this.piecePosition, moveCurrent)
                        || secondColumnException(this.piecePosition, moveCurrent) ||
                        sevenColumnException(this.piecePosition, moveCurrent)
                        || eightColumnException(this.piecePosition, moveCurrent)) {
                    continue;
                }

                Move legalMove = tryCreateMove(board, moveDestination);
                if (legalMove != null) {
                    legalMoves.add(legalMove);
                }

            }

        }

        return legalMoves;
    }

    @Override
    public final String toString() {
        return PieceName.KNIGHT.toString();
    }

    private final boolean firstColumnException(final int moveCurrent, final int moveOutOfBounds) {
        return Board.FIRST_COLUMN[moveCurrent]
                && (moveOutOfBounds == TOP_TOP_LEFT || moveOutOfBounds == TOP_LEFT || moveOutOfBounds == BOTTOM_LEFT
                        || moveOutOfBounds == BOTTOM_BOTTOM_LEFT);
    }

    private final boolean secondColumnException(final int moveCurrent, final int moveOutOfBounds) {
        return Board.SECOND_COLUMN[moveCurrent] && (moveOutOfBounds == TOP_LEFT || moveOutOfBounds == BOTTOM_LEFT);
    }

    private final boolean sevenColumnException(final int moveCurrent, final int moveOutOfBounds) {
        return Board.SEVEN_COLUMN[moveCurrent] && (moveOutOfBounds == BOTTOM_RIGHT || moveOutOfBounds == TOP_RIGHT);
    }

    private final boolean eightColumnException(final int moveCurrent, final int moveOutOfBounds) {
        return Board.EIGHT_COLUMN[moveCurrent]
                && (moveOutOfBounds == BOTTOM_BOTTOM_RIGHT || moveOutOfBounds == BOTTOM_RIGHT
                        || moveOutOfBounds == TOP_RIGHT || moveOutOfBounds == TOP_TOP_RIGHT);
    }

    @Override
    public final Piece getMovedPiece(final int position, final Color color) {
        return new Knight(position, color, false);
    }
}

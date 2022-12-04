package com.chess_POO.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess_POO.Color;
import com.chess_POO.board.Board;
import com.chess_POO.board.Move;

public class King extends Piece {

    private final boolean isCastled;

    public King(int piecePosition, Color pieceColor, boolean firstMove, boolean isCastled) {
        super(PieceName.KING, piecePosition, pieceColor, firstMove);
        this.isCastled = isCastled;
    }

    public final boolean isCastled() {
        return this.isCastled;
    }

    private static final int RIGHT = 1;
    private static final int BOTTOM_LEFT = 7;
    private static final int BOTTOM = 8;
    private static final int BOTTOM_RIGHT = 9;
    private static final int TOP_LEFT = -9;
    private static final int TOP = -8;
    private static final int TOP_RIGHT = -7;
    private static final int LEFT = -1;

    private final int[] movePossible = { LEFT, TOP_RIGHT, TOP, TOP_LEFT, BOTTOM_RIGHT, BOTTOM, BOTTOM_LEFT, RIGHT };
    private int moveDestination;

    @Override
    public final List<Move> legalMoves(final Board board) {
        List<Move> legalMoves = new ArrayList<>();

        for (int moveCurrent : movePossible) {
            moveDestination = this.piecePosition + moveCurrent;

            if (firstColumnException(this.piecePosition, moveCurrent)
                    || eightColumnException(this.piecePosition, moveCurrent)) {
                continue;
            }
            Move legalMove = tryCreateMove(board, moveDestination);
            if (legalMove != null) {
                legalMoves.add(legalMove);

            }
        }
        return legalMoves;
    }

    @Override
    public final String toString() {
        return PieceName.KING.toString();
    }

    private final boolean firstColumnException(final int moveCurrent, final int moveOutOfBounds) {
        return Board.FIRST_COLUMN[moveCurrent]
                && (moveOutOfBounds == TOP_LEFT || moveOutOfBounds == LEFT || moveOutOfBounds == BOTTOM_LEFT);
    }

    private final boolean eightColumnException(final int moveCurrent, final int moveOutOfBounds) {
        return Board.EIGHT_COLUMN[moveCurrent]
                && (moveOutOfBounds == BOTTOM_RIGHT || moveOutOfBounds == RIGHT || moveOutOfBounds == TOP_RIGHT);
    }

    @Override
    public final Piece getMovedPiece(final int position, final Color color) {
        return new King(position, color, false, false);
    }

}

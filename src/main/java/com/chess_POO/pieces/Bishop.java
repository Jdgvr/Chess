package com.chess_POO.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess_POO.Color;
import com.chess_POO.board.Board;
import com.chess_POO.board.Move;

public class Bishop extends Piece {

    public Bishop(int piecePosition, Color pieceColor, boolean firstMove) {
        super(PieceName.BISHOP,piecePosition, pieceColor, firstMove);
    }

    private static final int TOP_LEFT = -9;
    private static final int TOP_RIGHT = -7;
    private static final int BOTTOM_LEFT = 7;
    private static final int BOTTOM_RIGHT = 9;

    private final int[] movePossible = { TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT };
    private int moveDestination;

    @Override
    public final List<Move> legalMoves(final Board board) {
        List<Move> legalMoves = new ArrayList<>();

        for (int moveCurrent : movePossible) {
            moveDestination = this.piecePosition;

            while (Board.isValidCoordinate(moveDestination)) {
                
                if (firstColumnException(moveDestination, moveCurrent) || eightColumnException(moveDestination, moveCurrent)) {
                    break;
                }

                moveDestination += moveCurrent;

                Move legalMove = tryCreateMove(board, moveDestination);
                if(legalMove != null){
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
        return PieceName.BISHOP.toString();
    }

    private final boolean firstColumnException(final int moveCurrent, final int moveOutOfBounds) {
        return Board.FIRST_COLUMN[moveCurrent] && (moveOutOfBounds == TOP_LEFT || moveOutOfBounds == BOTTOM_LEFT);
    }

    private final boolean eightColumnException(final int moveCurrent, final int moveOutOfBounds) {
        return Board.EIGHT_COLUMN[moveCurrent] && (moveOutOfBounds == BOTTOM_RIGHT || moveOutOfBounds == TOP_RIGHT);
    }

    @Override
    public final Piece getMovedPiece(final int position, final Color color) {
        return new Bishop(position, color, false);
    }

}

package com.chess_POO.board;

public class MoveCreator {

    private MoveCreator() {}

    public static final Move createMove(final Board board, final int currentCoordinate, final int moveDestination) {
        for (Move move : board.getCurrentAllLegalMoves()) {
            if(move.getCurrentCoordinate() == currentCoordinate && move.getPieceDestination() == moveDestination) {
                return move;
            }
        }
        return null;
    }
}

package com.chess_POO.player.ai;

import com.chess_POO.Color;
import com.chess_POO.board.Board;
import com.chess_POO.board.Move;
import com.chess_POO.player.MoveBoard;
import com.chess_POO.player.MoveStatus;

public class Minimax implements Strategy {

    private final BoardEvaluator boardEvaluator;
    private final int depth;

    public Minimax(int depth) {
        this.boardEvaluator = new StandardBoardEvaluation();
        this.depth = depth;
    }

    @Override
    public Move execute(final Board board) {

        Move bestMove = null;

        int highestScoreValue = Integer.MIN_VALUE;
        int lowestScoreValue = Integer.MAX_VALUE;
        int currValue;

        for (Move move : board.currentPlayer().getLegalMoves()) {
            MoveBoard moveBoard = board.currentPlayer().makeMove(move);

            if (moveBoard.getMoveStatus() == MoveStatus.DONE) {

                currValue = board.currentPlayer().getColor() == Color.WHITE ? min(moveBoard.getToBoard(), depth - 1)
                        : max(moveBoard.getToBoard(), depth - 1);

                if (board.currentPlayer().getColor() == Color.WHITE && currValue >= highestScoreValue) {
                    highestScoreValue = currValue;
                    bestMove = move;
                } else if (board.currentPlayer().getColor() == Color.BLACK && currValue <= lowestScoreValue) {
                    lowestScoreValue = currValue;
                    bestMove = move;
                }
            }
        }
        return bestMove;
    }

    private final int min(final Board board, final int depth) {
        if (depth == 0) {
            return this.boardEvaluator.evaluate(board, depth);
        }
        int lowestScoreValue = Integer.MAX_VALUE;
        for (Move move : board.currentPlayer().getLegalMoves()) {
            MoveBoard moveBoard = board.currentPlayer().makeMove(move);

            if (moveBoard.getMoveStatus() == MoveStatus.DONE) {
                int currValue = max(moveBoard.getToBoard(), depth - 1);

                if (currValue <= lowestScoreValue) {
                    lowestScoreValue = currValue;
                }
            }
        }

        return lowestScoreValue;
    }

    private final int max(final Board board, final int depth) {
        if (depth == 0 || isEndGame(board)) {
            return this.boardEvaluator.evaluate(board, depth);
        }
        int highestScoreValue = Integer.MIN_VALUE;
        for (Move move : board.currentPlayer().getLegalMoves()) {
            MoveBoard moveBoard = board.currentPlayer().makeMove(move);

            if (moveBoard.getMoveStatus() == MoveStatus.DONE) {
                int currValue = min(moveBoard.getToBoard(), depth - 1);

                if (currValue >= highestScoreValue) {
                    highestScoreValue = currValue;
                }
            }
        }

        return highestScoreValue;
    }

    private final boolean isEndGame(final Board board) {
        return board.currentPlayer().inCheckMate() || board.currentPlayer().inStaleMate();
    }

    @Override
    public final String toString() {
        return "Minimax";
    }
}

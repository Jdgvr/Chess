package com.chess_POO.player.ai;

import com.chess_POO.board.Board;
import com.chess_POO.pieces.Piece;
import com.chess_POO.player.Player;

public class StandardBoardEvaluation implements BoardEvaluator {

    private static final int CHECK_BONUS = 60;
    private static final int CHECK_MATE_BONUS = 100000;
    private static final int DEPTH_BONUS = 150;
    private static final int CASTLE_BONUS = 100;
    private static int evalCount = 0;

    public static StandardBoardEvaluation get() {
        return new StandardBoardEvaluation();
    }

    @Override
    public final int evaluate(Board board, int depth) {
        ++evalCount;
        //if ((evalCount % 100) == 0) {
        //    System.out.println("[ Move evaluation numero ] [" + evalCount + "]");
        //}

        return scorePlayer(board, board.whitePlayer(), depth) - scorePlayer(board, board.blackPlayer(), depth);
    }

    private final int scorePlayer(final Board board, final Player player, final int depth) {
        return pieceValue(player) + mobility(player) + check(player) + checkmate(player, depth) + castled(player);
    }

    private final int castled(final Player player) {
        return player.getKing().isCastled() ? CASTLE_BONUS : 0;
    }

    private final int checkmate(final Player player, final int depth) {
        return player.getOpponent().inCheckMate() ? CHECK_MATE_BONUS * depthBonus(depth) : 0;
    }

    private final int depthBonus(final int depth) {
        return depth == 0 ? 1 : DEPTH_BONUS * depth;
    }

    private final int check(final Player player) {
        return player.getOpponent().inCheck() ? CHECK_BONUS : 0;
    }

    private final int mobility(final Player player) {
        return player.getLegalMoves().size();
    }

    private final int pieceValue(final Player player) {
        int pieceValueScore = 0;
        for (Piece piece : player.getPieceOnBoard()) {
            pieceValueScore += piece.getPieceValue();
        }
        return pieceValueScore;
    }

}
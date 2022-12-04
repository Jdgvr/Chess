package com.chess_POO;

import com.chess_POO.board.Board;
import com.chess_POO.pieces.Piece;
import com.chess_POO.pieces.Queen;
import com.chess_POO.pieces.Piece.PieceName;

public class EvaluateWinner {

    public static String winner;

    private EvaluateWinner() {
    }

    public static final String winnerOfTheGame(final Board board) {
        int pointsWhite = checkMatePoints(board)[0] +
                queenWasMoving(board)[0] +
                knightNotEat(board)[0] +
                eatingOpponentQueen(board)[0] +
                kingNotMoving(board)[0] +
                eatingOpponentPawn(board)[0] +
                notLooseRooks(board)[0] +
                leastPiece(board)[0] +
                noRock(board)[0] +
                promotionPawn(board)[0] +
                twoCheck(board)[0];

        int pointsBlack = checkMatePoints(board)[1] +
                queenWasMoving(board)[1] +
                knightNotEat(board)[1] +
                eatingOpponentQueen(board)[1] +
                kingNotMoving(board)[1] +
                eatingOpponentPawn(board)[1] +
                notLooseRooks(board)[1] +
                leastPiece(board)[1] +
                noRock(board)[1] +
                promotionPawn(board)[1] +
                twoCheck(board)[1];

        if (pointsWhite > pointsBlack) {
            winner = "White";
        } else if (pointsBlack > pointsWhite) {
            winner = "Black";
        } else if (pointsWhite == pointsBlack) {
            winner = "Tie";
        }
        return winner;
    }

    // CheckMate
    private static final int[] checkMatePoints(final Board board) {
        int[] points = new int[3];
        if (board.whitePlayer().inCheckMate()) {
            points[1] = 100;
        } else if (board.blackPlayer().inCheckMate()) {
            points[0] = 100;
        }
        return points;
    }

    // Pas bouger la dame
    private static final int[] queenWasMoving(final Board board) {
        int[] points = new int[2];

        for(Piece piece : board.whitePlayer().getPieceOnBoard()) {
            if (piece.getPieceType() == PieceName.QUEEN) {
                Piece queenWhite = board.whitePlayer().getQueen();
                if (queenWhite.firstMove()) {
                    points[0] = 20;
                }
            }
        }
        
        for(Piece piece : board.blackPlayer().getPieceOnBoard()) {
            if (piece.getPieceType() == PieceName.QUEEN) {
                Piece queenBlack = board.whitePlayer().getQueen();
                if (queenBlack.firstMove()) {
                    points[1] = 20;
                }
            }
        }
        
        return points;
    }

    // Pas manger de cavalier
    private static final int[] knightNotEat(final Board board) {
        int[] points = new int[2];
        int knightCountWhite = 0;
        int knightCountBlack = 0;
        for (Piece piece : board.blackPlayer().getPieceOnBoard()) {
            if (piece.getPieceType() == PieceName.KNIGHT) {
                knightCountWhite += 1;
            }
        }
        if (knightCountWhite < 2) {
            points[0] = 20;
        }
        for (Piece piece : board.whitePlayer().getPieceOnBoard()) {
            if (piece.getPieceType() == PieceName.KNIGHT) {
                knightCountBlack += 1;
            }
        }
        if (knightCountBlack < 2) {
            points[1] = 20;
        }
        return points;
    }

    // Manger la reine ennemie
    private static final int[] eatingOpponentQueen(final Board board) {
        int[] points = new int[2];
        int queenCountWhite = 0;
        int queenCountBlack = 0;
        for (Piece piece : board.blackPlayer().getPieceOnBoard()) {
            if (piece.getPieceType() == PieceName.QUEEN) {
                queenCountWhite += 1;
            }
        }
        if (queenCountWhite == 0) {
            points[0] = 20;
        }
        for (Piece piece : board.whitePlayer().getPieceOnBoard()) {
            if (piece.getPieceType() == PieceName.QUEEN) {
                queenCountBlack += 1;
            }
        }
        if (queenCountBlack == 0) {
            points[1] = 20;
        }
        return points;
    }

    // Pas bouger le roi jusqu'à l'échec
    private static final int[] kingNotMoving(final Board board) {
        int[] points = new int[2];
        Piece kingWhite = board.whitePlayer().getKing();
        Piece kingBlack = board.blackPlayer().getKing();
        if (kingWhite.firstMove()) {
            points[0] = 20;
        } else if (kingBlack.firstMove()) {
            points[1] = 20;
        }
        return points;
    }

    // Manger tous les pions
    private static final int[] eatingOpponentPawn(final Board board) {
        int[] points = new int[2];
        int pawnCountWhite = 0;
        int pawnCountBlack = 0;
        for (Piece piece : board.blackPlayer().getPieceOnBoard()) {
            if (piece.getPieceType() == PieceName.PAWN) {
                pawnCountBlack += 1;
            }
        }
        if (pawnCountBlack == 0) {
            points[0] = 20;
        }
        for (Piece piece : board.whitePlayer().getPieceOnBoard()) {
            if (piece.getPieceType() == PieceName.PAWN) {
                pawnCountWhite += 1;
            }
        }
        if (pawnCountWhite == 0) {
            points[1] = 20;
        }
        return points;
    }

    // Ne pas perdre ses 2 tours
    private static final int[] notLooseRooks(final Board board) {
        int[] points = new int[2];
        int rookCountWhite = 0;
        int rookCountBlack = 0;
        for (Piece piece : board.blackPlayer().getPieceOnBoard()) {
            if (piece.getPieceType() == PieceName.ROOK) {
                rookCountBlack += 1;
            }
        }
        if (rookCountBlack == 0) {
            points[1] = 20;
        }
        for (Piece piece : board.whitePlayer().getPieceOnBoard()) {
            if (piece.getPieceType() == PieceName.ROOK) {
                rookCountWhite += 1;
            }
        }
        if (rookCountWhite == 0) {
            points[0] = 20;
        }
        return points;
    }

    // Gagner la partie avec moins de pièce
    private static final int[] leastPiece(final Board board) {
        int[] points = new int[2];
        if (board.whitePlayer().getPieceOnBoard().size() > board.blackPlayer().getPieceOnBoard().size()) {
            points[0] = 20;
        } else if (board.blackPlayer().getPieceOnBoard().size() > board.whitePlayer().getPieceOnBoard().size()) {
            points[1] = 20;
        }
        return points;
    }

    // Pas de rock
    private static final int[] noRock(final Board board) {
        int[] points = new int[2];
        if (board.whitePlayer().getKing().isCastled() == false) {
            points[0] = 20;
        }
        if (board.blackPlayer().getKing().isCastled() == false) {
            points[1] = 20;
        }
        return points;
    }

    // Promouvoir un pion
    private static final int[] promotionPawn(final Board board) {
        int[] points = new int[2];
        int queenPromoteCountBlack = 0;
        int queenPromoteCountWhite = 0;

        for (Piece piece : board.blackPlayer().getPieceOnBoard()) {
            if(piece.getPieceType() == PieceName.QUEEN) {
                Queen queen = (Queen) piece;
                if(queen.isPromote()) {
                    queenPromoteCountBlack += 1;
                }
            }
        }
        if(queenPromoteCountBlack > 0) {
            points[0] = 20;
        }

        for (Piece piece : board.whitePlayer().getPieceOnBoard()) {
            if(piece.getPieceType() == PieceName.QUEEN) {
                Queen queen = (Queen) piece;
                if(queen.isPromote()) {
                    queenPromoteCountWhite += 1;
                }
            }
        }
        if(queenPromoteCountWhite > 0) {
            points[1] = 20;
        }

        return points;
    }

    // Faire deux echecs
    private static final int[] twoCheck(final Board board) {
        int[] points = new int[2];
        if(board.whitePlayer().countCheck() >= 2) {
            points[1] = 20;
        }
        if(board.blackPlayer().countCheck() >= 2) {
            points[1] = 20;
        }
        return points;
    }

}
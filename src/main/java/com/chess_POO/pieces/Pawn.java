package com.chess_POO.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess_POO.Color;
import com.chess_POO.board.Board;
import com.chess_POO.board.Move;
import com.chess_POO.board.PawnPromotionMove;
import com.chess_POO.board.Tile;
import com.chess_POO.board.Move.MovePawn;
import com.chess_POO.board.Move.MovePawnJump;
import com.chess_POO.board.MoveAttack.MovePawnAttack;

public class Pawn extends Piece {

    public Pawn(int piecePosition, Color pieceColor, boolean firstMove) {
        super(PieceName.PAWN, piecePosition, pieceColor, firstMove);
    }

    private static final int FORWARD = 8;
    private static final int JUMP = 16;
    private static final int LEFT_ATTACK_W = 7;
    private static final int RIGHT_ATTACK_W = 9; 

    private final int[] movePossible = { FORWARD, JUMP, LEFT_ATTACK_W, RIGHT_ATTACK_W };
    private int moveDestination;

    @Override
    public final List<Move> legalMoves(final Board board) {
        List<Move> legalMoves = new ArrayList<>();

        for (int moveCurrent : movePossible) {
            moveDestination = this.piecePosition + (this.getPieceColor().getDirection() * moveCurrent);

            if (!Board.isValidCoordinate(moveDestination)) {
                continue;
            }

            Tile moveDestinationTile = board.getTile(moveDestination);
            if (moveCurrent == FORWARD && !moveDestinationTile.isTileOccupied()) {

                if (this.pieceColor.isTileForPawnPromotion(moveDestination)) {
                    legalMoves.add(new PawnPromotionMove(new MovePawn(board, this, moveDestination)));
                } else {
                    legalMoves.add(new MovePawn(board, this, moveDestination));
                }

            } else if (moveCurrent == JUMP && firstMove()
                    && ((Board.SECOND_ROW[this.piecePosition] && this.pieceColor == Color.BLACK) ||
                            (Board.SEVEN_ROW[this.piecePosition] && this.pieceColor == Color.WHITE))) {

                int middleMove = this.piecePosition + (this.getPieceColor().getDirection() * FORWARD);
                Tile middleMoveTile = board.getTile(middleMove);

                if (!middleMoveTile.isTileOccupied() && !moveDestinationTile.isTileOccupied()) {
                    legalMoves.add(new MovePawnJump(board, this, moveDestination));
                }

            } else if (moveCurrent == LEFT_ATTACK_W
                    && ((Board.EIGHT_COLUMN[this.piecePosition] && this.pieceColor == Color.WHITE) ||
                            (Board.FIRST_COLUMN[this.piecePosition] && this.pieceColor == Color.BLACK))) {
                if (moveDestinationTile.isTileOccupied()) {
                    if (this.pieceColor !=  pieceColor) {

                        if (this.pieceColor.isTileForPawnPromotion(moveDestination)) {
                            legalMoves.add(new PawnPromotionMove(
                                    new MovePawnAttack(board, this, moveDestination, moveDestinationTile.getPiece())));
                        } else {
                            legalMoves.add(
                                    new MovePawnAttack(board, this, moveDestination, moveDestinationTile.getPiece()));
                        }
                    }
                }

            } else if (moveCurrent == RIGHT_ATTACK_W && ((Board.EIGHT_COLUMN[this.piecePosition] && this.pieceColor == Color.BLACK))
                    ||
                    (Board.FIRST_COLUMN[this.piecePosition] && this.pieceColor == Color.WHITE)) {
                if (moveDestinationTile.isTileOccupied()) {
                    if (this.pieceColor !=  pieceColor) {
                        if (this.pieceColor.isTileForPawnPromotion(moveDestination)) {
                            legalMoves.add(new PawnPromotionMove(
                                    new MovePawnAttack(board, this, moveDestination, moveDestinationTile.getPiece())));
                        } else {
                            legalMoves.add(
                                    new MovePawnAttack(board, this, moveDestination, moveDestinationTile.getPiece()));
                        }
                    }
                }
            }
        }
        return legalMoves;
    }

    @Override
    public final String toString() {
        return PieceName.PAWN.toString();
    }

    public final Object getPromotedPiece(final int position, final Color color) {
        return new Queen(position, color, false, true);
    }

    @Override
    public final Piece getMovedPiece(final int position, final Color color) {
        return new Pawn(position, color, false);
    }
}

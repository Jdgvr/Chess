package com.chess_POO.board;

import com.chess_POO.board.Board.Builder;
import com.chess_POO.pieces.Pawn;
import com.chess_POO.pieces.Piece;
import com.chess_POO.pieces.Queen;

public class PawnPromotionMove extends Move {

    private final Move promotionMove;
    private final Pawn promotedPawn;
    Pawn promotedPiece;

    public PawnPromotionMove(Move promotionMove) {
        super(promotionMove.getBoard(), promotionMove.getPieceDestination(), promotionMove.getPieceMove());
        this.promotionMove = promotionMove;
        this.promotedPawn = (Pawn) getPieceMove();
    }

    @Override
    public final Board execute() {
        Builder builder = new Builder();
        for (Piece piece : this.board.currentPlayer().getPieceOnBoard()) {
            if (!this.promotedPawn.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        for (Piece piece : this.board.currentPlayer().getOpponent().getPieceOnBoard()) {
            builder.setPiece(piece);
        }        
        builder.setPiece((Queen) promotedPawn.getPromotedPiece(this.pieceDestination,this.board.currentPlayer().getColor()));
        builder.setMoveSide(this.board.currentPlayer().getOpponent().getColor());
        return builder.build();

    }

    @Override
    public final boolean isAttack() {
        return this.promotionMove.isAttack();
    }

    @Override
    public final Piece getPieceAttack() {
        return this.promotionMove.getPieceAttack();
    }

    @Override
    public final String toString() {
        return "";
    }

}

package com.chess_POO.board;

import com.chess_POO.pieces.Piece;

public class MoveAttack extends Move {

    Piece attackPiece;

    public MoveAttack(Board board,  Piece movePiece, int pieceDestination, Piece attackPiece) {
        super(board, pieceDestination, movePiece);
        this.attackPiece = attackPiece;
    }

    @Override
    public final boolean isAttack() {
        return true;
    }

    @Override
    public final Piece getPieceAttack() {
        return this.attackPiece;
    }


    public static class MovePawnAttack extends MoveAttack {

        public MovePawnAttack(Board board, Piece movePiece, int pieceDestination, Piece attackPiece) {
            super(board, movePiece, pieceDestination, attackPiece); 
        }
    }
}

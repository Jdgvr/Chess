package com.chess_POO.board;

import com.chess_POO.Color;
import com.chess_POO.pieces.Piece;

public class TileOccupied extends Tile {

    Piece pieceTile;

    public TileOccupied(int tileCoordinate, Piece piecePosition) {
        super(tileCoordinate);
        this.pieceTile = piecePosition;
    }

    @Override
    public final boolean isTileOccupied() {
            return true;

    }

    @Override
    public final Piece getPiece() {
        return this.pieceTile;
    }

    @Override
    public final String toString() {
        return getPiece().getPieceColor() == Color.BLACK ? getPiece().toString().toLowerCase() : getPiece().toString();
    }

}

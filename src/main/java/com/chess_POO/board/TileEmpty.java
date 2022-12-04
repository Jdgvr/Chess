package com.chess_POO.board;

import com.chess_POO.pieces.Piece;

public class TileEmpty extends Tile {

    public TileEmpty(int tileCoordinate) {
        super(tileCoordinate);
    }

    @Override
    public final boolean isTileOccupied() {
        return false;
    }

    @Override
    public final Piece getPiece() {
        return null;
    }

    @Override
    public final String toString(){
        return "-";
    }
    
}
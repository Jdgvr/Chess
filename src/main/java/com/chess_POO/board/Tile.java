package com.chess_POO.board;

import com.chess_POO.pieces.Piece;

public abstract class Tile {

    int tileCoordinate;

    protected Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    public static final Tile newTile(final int tileCoordinate, final Piece piece) {
        if (piece == null) {
            return new TileEmpty(tileCoordinate);
        } else {
            return new TileOccupied(tileCoordinate, piece);
        }
    }

    public final int getTileCoordinate() {
        return this.tileCoordinate;
    }
}

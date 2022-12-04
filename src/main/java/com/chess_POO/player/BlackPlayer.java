package com.chess_POO.player;

import java.util.Collection;

import com.chess_POO.Color;
import com.chess_POO.board.Board;
import com.chess_POO.board.Move;
import com.chess_POO.pieces.Piece;

public class BlackPlayer extends Player {

    public BlackPlayer(Board board, Collection<Move> whiteMoves, Collection<Move> blackMoves) {
        super(board, blackMoves, whiteMoves);
    }

    @Override
    public final Collection<Piece> getPieceOnBoard() {
        return this.board.getBlackPieces();
    }

    @Override
    public final String toString() {
        return "Black";
    }

    @Override
    public final Color getColor() {
        return Color.BLACK;
    }

    @Override
    public final Player getOpponent() {
        return this.board.whitePlayer();
    }

    private static final int ROOK_TILE_CASTLE = 5;
    private static final int KING_TILE_CASTLE = 6;
    private static final int ROOK_TILE = 7;
    private static final int ROOK_TILE_CASTLE_QUEEN = 3;
    private static final int KING_TILE_CASTLE_QUEEN = 2;
    private static final int VOID_TILE_CASTLE_QUEEN = 1;
    private static final int ROOK_TILE_QUEEN_SIDE = 0;

    @Override
    public final int rookTileCastle() {
        return ROOK_TILE_CASTLE;
    }

    @Override
    public final int kingTileCastle() {
        return KING_TILE_CASTLE;
    }

    @Override
    public final int rookTile() {
        return ROOK_TILE;
    }

    @Override
    public final int rookTileCastleQueen() {
        return ROOK_TILE_CASTLE_QUEEN;
    }

    @Override
    public final int kingTileCastleQueen() {
        return KING_TILE_CASTLE_QUEEN;
    }

    @Override
    public final int voidTileCastleQueen() {
        return VOID_TILE_CASTLE_QUEEN;
    }

    @Override
    public final int rookTileQueenSide() {
        return ROOK_TILE_QUEEN_SIDE;
    }
}
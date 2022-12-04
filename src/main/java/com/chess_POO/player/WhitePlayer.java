package com.chess_POO.player;

import java.util.Collection;

import com.chess_POO.Color;
import com.chess_POO.board.Board;
import com.chess_POO.board.Move;

import com.chess_POO.pieces.Piece;

public class WhitePlayer extends Player {

    public WhitePlayer(final Board board, final Collection<Move> whiteMoves, final Collection<Move> blackMoves) {
        super(board, whiteMoves, blackMoves);
    }

    @Override
    public String toString() {
        return "White";
    }

    @Override
    public final Collection<Piece> getPieceOnBoard() {
        return this.board.getWhitePieces();
    }

    @Override
    public final Color getColor() {
        return Color.WHITE;
    }

    @Override
    public final Player getOpponent() {
        return this.board.blackPlayer();
    }


    private static final int ROOK_TILE_CASTLE = 61;
    private static final int KING_TILE_CASTLE = 62;
    private static final int ROOK_TILE = 63;
    private static final int ROOK_TILE_CASTLE_QUEEN = 59;
    private static final int KING_TILE_CASTLE_QUEEN = 58;
    private static final int VOID_TILE_CASTLE_QUEEN = 57;
    private static final int ROOK_TILE_QUEEN_SIDE = 56;

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
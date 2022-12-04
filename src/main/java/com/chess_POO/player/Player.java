package com.chess_POO.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess_POO.Color;
import com.chess_POO.board.Board;
import com.chess_POO.board.Move;
import com.chess_POO.board.Tile;
import com.chess_POO.board.Move.KingSideCastle;
import com.chess_POO.board.Move.QueenSideCastle;
import com.chess_POO.pieces.King;
import com.chess_POO.pieces.Queen;
import com.chess_POO.pieces.Piece;
import com.chess_POO.pieces.Rook;
import com.chess_POO.pieces.Piece.PieceName;

public abstract class Player {
    protected final Board board;
    protected final King king;
    private final Collection<Move> legalMoves;
    private final boolean inCheck;
    public int countCheck = 0;

    protected Player(Board board, Collection<Move> legalMoves, Collection<Move> opponentMoves) {
        List<Move> allLegalMoves = new ArrayList<>();
        this.board = board;
        this.legalMoves = allLegalMoves;
        this.king = findKing();
        this.inCheck = !Player.possiblesAttacksMovesOnPosition(this.king.getPiecePosition(), opponentMoves).isEmpty();
        allLegalMoves.addAll(legalMoves);
        allLegalMoves.addAll(calculateKingCastle(legalMoves, opponentMoves));
        countCheck = this.inCheck() ? countCheck = +1 : countCheck;
    }

    protected static final Collection<Move> possiblesAttacksMovesOnPosition(final Integer piecePosition,
            final Collection<Move> opponentMoves) {
        List<Move> attackMoves = new ArrayList<>();

        for (Move move : opponentMoves) {
            if (piecePosition == move.getPieceDestination()) {
                attackMoves.add(move);
            }
        }
        return attackMoves;
    }

    private final King findKing() {
        for (Piece piece : getPieceOnBoard()) {
            if (piece.getPieceType() == PieceName.KING) {
                return (King) piece;
            }
        }
        throw new IllegalArgumentException("Board not valid !");
    }

    public King getKing() {
        return this.king;
    }

    public final Queen getQueen() {
        for (Piece piece : getPieceOnBoard()) {
            if (piece.getPieceType() == PieceName.QUEEN) {
                return (Queen) piece;
            }
        }
        return null;
    }

    public final Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }

    public abstract Collection<Piece> getPieceOnBoard();

    public abstract Color getColor();

    public abstract Player getOpponent();

    public abstract int rookTileCastle();

    public abstract int kingTileCastle();

    public abstract int rookTile();

    public abstract int rookTileCastleQueen();

    public abstract int kingTileCastleQueen();

    public abstract int voidTileCastleQueen();

    public abstract int rookTileQueenSide();

    protected final Collection<Move> calculateKingCastle(Collection<Move> playerLegalMoves,
            Collection<Move> opponentLegalMoves) {
        List<Move> kingCastle = new ArrayList<>();

        Tile rookTile = this.board.getTile(rookTile());
        if (this.king.firstMove() &&
                !this.inCheck() &&
                rookTile.isTileOccupied() &&
                !this.board.getTile(rookTileCastle()).isTileOccupied() &&
                !this.board.getTile(kingTileCastle()).isTileOccupied() &&
                Player.possiblesAttacksMovesOnPosition(rookTileCastle(), opponentLegalMoves).isEmpty()
                && Player.possiblesAttacksMovesOnPosition(kingTileCastle(), opponentLegalMoves).isEmpty() &&
                rookTile.getPiece().getPieceType() == PieceName.ROOK) {

            kingCastle.add(new KingSideCastle(this.board, this.king, kingTileCastle(), (Rook) rookTile.getPiece(),
                    rookTile.getTileCoordinate(), rookTileCastle()));
        }

        Tile rookTileQueen = this.board.getTile(rookTileQueenSide());
        if (!this.board.getTile(rookTileCastleQueen()).isTileOccupied() &&
                !this.board.getTile(kingTileCastleQueen()).isTileOccupied() &&
                !this.board.getTile(voidTileCastleQueen()).isTileOccupied() &&
                rookTileQueen.isTileOccupied() &&
                rookTileQueen.getPiece().firstMove()) {
            kingCastle.add(
                    new QueenSideCastle(this.board, this.king, kingTileCastleQueen(), (Rook) rookTileQueen.getPiece(),
                            rookTileQueen.getTileCoordinate(), rookTileCastleQueen()));
        }

        return kingCastle;
    }

    public final boolean isMoveLegal(Move move) {
        return this.legalMoves.contains(move);
    }

    public final boolean inCheck() {
        return this.inCheck;
    }

    public final int countCheck() {
        return this.countCheck();
    }

    public final boolean inCheckMate() {
        return this.inCheck && !escapeMoves();
    }

    public final boolean inStaleMate() {
        return !this.inCheck && !escapeMoves();
    }

    private final boolean escapeMoves() {
        for (Move move : this.legalMoves) {
            MoveBoard boardMove = makeMove(move);
            if (boardMove.getMoveStatus() == MoveStatus.DONE) {
                return true;
            }
        }
        return false;
    }

    public final MoveBoard makeMove(Move move) {
        if (move == null || !isMoveLegal(move)) {
            return new MoveBoard(this.board, move, MoveStatus.ILLEGAL_MOVE);
        }
        Board boardAfterMove = move.execute();

        Collection<Move> kingAttack = Player.possiblesAttacksMovesOnPosition(
                boardAfterMove.currentPlayer().getOpponent().getKing().getPiecePosition(),
                boardAfterMove.currentPlayer().getLegalMoves());

        if (!kingAttack.isEmpty()) {
            return new MoveBoard(boardAfterMove, move, MoveStatus.LEAVE_CHECK);
        }

        return new MoveBoard(boardAfterMove, move, MoveStatus.DONE);
    }

}

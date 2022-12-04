package com.chess_POO.board;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import com.chess_POO.Color;
import com.chess_POO.pieces.Bishop;
import com.chess_POO.pieces.King;
import com.chess_POO.pieces.Knight;
import com.chess_POO.pieces.Pawn;
import com.chess_POO.pieces.Piece;
import com.chess_POO.pieces.Queen;
import com.chess_POO.pieces.Rook;
import com.chess_POO.player.BlackPlayer;
import com.chess_POO.player.Player;
import com.chess_POO.player.WhitePlayer;

public class Board {

    // board

    public static final int NUMBER_TILES = 64;
    public static final int NUMBER_TILES_ROW = 8;

    private final List<Tile> boardTiles;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;

    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;

    private final Player curPlayer;

    private Board(Builder builder) {
        this.boardTiles = createBoard(builder);
        this.whitePieces = activePieces(this.boardTiles, Color.WHITE);
        this.blackPieces = activePieces(this.boardTiles, Color.BLACK);

        Collection<Move> whiteMoves = calculateLegalMoves(this.whitePieces);
        Collection<Move> blackMoves = calculateLegalMoves(this.blackPieces);

        this.whitePlayer = new WhitePlayer(this, whiteMoves, blackMoves);
        this.blackPlayer = new BlackPlayer(this, whiteMoves, blackMoves);

        this.curPlayer = builder.nextMoveSide.pickPlayer(this.whitePlayer, this.blackPlayer);

    }

    public final Collection<Piece> getBlackPieces() {
        return this.blackPieces;
    }

    public final Collection<Piece> getWhitePieces() {
        return this.whitePieces;
    }

    public final Collection<Piece> getAllPieces() {
        List<Piece> allPieces = new ArrayList<>();
        allPieces.add((Piece) this.getWhitePieces());
        allPieces.add((Piece) this.getBlackPieces());
        return allPieces;
    }

    public final Player blackPlayer() {
        return this.blackPlayer;
    }

    public final Player whitePlayer() {
        return this.whitePlayer;
    }

    public final Player currentPlayer() {
        return this.curPlayer;
    }

    public final Tile getTile(final int moveDestination) {
        return boardTiles.get(moveDestination);
    }

    public final Collection<Move> getCurrentAllLegalMoves() {
        List<Move> allLegalMoves = new ArrayList<>();
        if (this.currentPlayer().getColor() == Color.WHITE) {
            allLegalMoves.addAll(this.whitePlayer.getLegalMoves());
        } else {
            allLegalMoves.addAll(this.blackPlayer.getLegalMoves());
        }
        return allLegalMoves;
    }

    public final Collection<Piece> activePieces(final List<Tile> board, final Color color) {
        List<Piece> activePiece = new ArrayList<>();
        for (Tile tile : board) {
            if (tile.isTileOccupied()) {
                Piece piece = tile.getPiece();
                if (piece.getPieceColor() == color) {
                    activePiece.add(piece);
                }
            }
        }
        return activePiece;
    }

    @Override
    public final String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < NUMBER_TILES; i++) {
            String tileText = this.boardTiles.get(i).toString();
            builder.append(String.format("%3s", tileText));
            if ((i + 1) % NUMBER_TILES_ROW == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private final Collection<Move> calculateLegalMoves(final Collection<Piece> pieces) {
        List<Move> legalMoves = new ArrayList<>();
        for (Piece piece : pieces) {
            List<Move> pieceLegalMoves = piece.legalMoves(this);
            legalMoves.addAll(pieceLegalMoves);
        }
        return legalMoves;
    }

    private final List<Tile> createBoard(final Builder builder) {
        Tile[] boardTile = new Tile[NUMBER_TILES];
        for (int i = 0; i < NUMBER_TILES; i++) {
            boardTile[i] = Tile.newTile(i, builder.boardState.get(i));
        }
        return Arrays.asList(boardTile);
    }

    public static final Board initialBoard() {
        Builder builder = new Builder();
        builder.setPiece(new Rook(0, Color.BLACK, true));
        builder.setPiece(new Knight(1, Color.BLACK, true));
        builder.setPiece(new Bishop(2, Color.BLACK, true));
        builder.setPiece(new Queen(3, Color.BLACK, true, false));
        builder.setPiece(new King(4, Color.BLACK, true, false));
        builder.setPiece(new Bishop(5, Color.BLACK, true));
        builder.setPiece(new Knight(6, Color.BLACK, true));
        builder.setPiece(new Rook(7, Color.BLACK, true));
        builder.setPiece(new Pawn(8, Color.BLACK, true));
        builder.setPiece(new Pawn(9, Color.BLACK, true));
        builder.setPiece(new Pawn(10, Color.BLACK, true));
        builder.setPiece(new Pawn(11, Color.BLACK, true));
        builder.setPiece(new Pawn(12, Color.BLACK, true));
        builder.setPiece(new Pawn(13, Color.BLACK, true));
        builder.setPiece(new Pawn(14, Color.BLACK, true));
        builder.setPiece(new Pawn(15, Color.BLACK, true));
        builder.setPiece(new Pawn(48, Color.WHITE, true));
        builder.setPiece(new Pawn(49, Color.WHITE, true));
        builder.setPiece(new Pawn(50, Color.WHITE, true));
        builder.setPiece(new Pawn(51, Color.WHITE, true));
        builder.setPiece(new Pawn(52, Color.WHITE, true));
        builder.setPiece(new Pawn(53, Color.WHITE, true));
        builder.setPiece(new Pawn(54, Color.WHITE, true));
        builder.setPiece(new Pawn(55, Color.WHITE, true));
        builder.setPiece(new Rook(56, Color.WHITE, true));
        builder.setPiece(new Knight(57, Color.WHITE, true));
        builder.setPiece(new Bishop(58, Color.WHITE, true));
        builder.setPiece(new Queen(59, Color.WHITE, true, false));
        builder.setPiece(new King(60, Color.WHITE, true, false));
        builder.setPiece(new Bishop(61, Color.WHITE, true));
        builder.setPiece(new Knight(62, Color.WHITE, true));
        builder.setPiece(new Rook(63, Color.WHITE, true));

        builder.setMoveSide(Color.WHITE);

        return builder.build();
    }

    public static class Builder {

        Map<Integer, Piece> boardState;
        Color nextMoveSide;

        public Builder() {
            this.boardState = new HashMap<>();
        }

        public final Builder setPiece(final Piece piece) {
            this.boardState.put(piece.getPiecePosition(), piece);
            return this;
        }

        public final Builder setMoveSide(final Color nextMoveSide) {
            this.nextMoveSide = nextMoveSide;
            return this;
        }

        public final Board build() {
            return new Board(this);
        }
    }

    // tools

    public static final boolean[] FIRST_COLUMN = initializeColumn(0);
    public static final boolean[] SECOND_COLUMN = initializeColumn(1);
    public static final boolean[] SEVEN_COLUMN = initializeColumn(6);
    public static final boolean[] EIGHT_COLUMN = initializeColumn(7);
    public static final boolean[] SECOND_ROW = initializeRow(8, 15);
    public static final boolean[] SEVEN_ROW = initializeRow(48, 55);
    public static final boolean[] FIRST_ROW = initializeRow(0, 7);
    public static final boolean[] EIGHT_ROW = initializeRow(56, 63);
    public static final List<String> ALGEBRIC_NOTATION = initializeAlgebricBoard();
    public static final Map<String, Integer> POSITION_COORDINATE = initializePositionToCoordinateMap();

    private static final boolean[] initializeColumn(int nbColumn) {
        boolean[] columnState = new boolean[NUMBER_TILES];
        do {
            columnState[nbColumn] = true;
            nbColumn += NUMBER_TILES_ROW;
        } while (nbColumn < NUMBER_TILES);
        return columnState;
    }

    private static final boolean[] initializeRow(final int start, final int end) {
        boolean[] rowState = new boolean[NUMBER_TILES];
        for (int i = start; i <= end; i++) {
            rowState[i] = true;
        }
        return rowState;
    }

    public static final boolean isValidCoordinate(final int coordinate) {
        return coordinate >= 0 && coordinate < NUMBER_TILES;
    }

    private static final List<String> initializeAlgebricBoard() {
        return Arrays.asList(
                "a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
                "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
                "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
                "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
                "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
                "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
                "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
                "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1");
    }

    private static final Map<String, Integer> initializePositionToCoordinateMap() {
        final Map<String, Integer> positionToCoordinate = new HashMap<>();
        for (int i = 0; i < NUMBER_TILES; i++) {
            positionToCoordinate.put(ALGEBRIC_NOTATION.get(i), i);
        }
        return positionToCoordinate;
    }

    public static final int getCoordinateAtPosition(final String position) {
        return POSITION_COORDINATE.get(position);
    }

    public static String getPositionAtCoordinate(final int coordinate) {
        return ALGEBRIC_NOTATION.get(coordinate);
    }

}

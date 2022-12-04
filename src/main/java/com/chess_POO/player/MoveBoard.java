package com.chess_POO.player;

import com.chess_POO.board.Board;
import com.chess_POO.board.Move;

public class MoveBoard {

    private final Board newBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveBoard(Board newBoard, Move move, MoveStatus moveStatus) {
        this.newBoard = newBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public final MoveStatus getMoveStatus() {
        return this.moveStatus;
    }

    public final Board getToBoard() {
        return this.newBoard;
   }

}

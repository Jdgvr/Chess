package com.chess_POO.player.ai;

import com.chess_POO.board.Board;
import com.chess_POO.board.Move;

public interface Strategy {
 
    Move execute(Board board);

}

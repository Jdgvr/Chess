package com.chess_POO.player.ai;

import com.chess_POO.board.Board;

public interface BoardEvaluator {

    int evaluate(Board board, int depth);

}

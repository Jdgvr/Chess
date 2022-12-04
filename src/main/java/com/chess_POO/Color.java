package com.chess_POO;

import com.chess_POO.board.Board;
import com.chess_POO.player.BlackPlayer;
import com.chess_POO.player.Player;
import com.chess_POO.player.WhitePlayer;

public enum Color {
    WHITE {
        @Override
        public int getDirection() {
            // TODO Auto-generated method stub
            return -1;
        }

        @Override
        public Player pickPlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer) {
            // TODO Auto-generated method stub
            return whitePlayer;
        }

        @Override
        public boolean isTileForPawnPromotion(final int position) {
            // TODO Auto-generated method stub
            return Board.FIRST_ROW[position];
        }
    },
    BLACK {
        @Override
        public int getDirection() {
            // TODO Auto-generated method stub
            return 1;
        }

        @Override
        public Player pickPlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            // TODO Auto-generated method stub
            return blackPlayer;
        }

        @Override
        public boolean isTileForPawnPromotion(final int position) {
            // TODO Auto-generated method stub
            return Board.EIGHT_ROW[position];
        }
    };

    public abstract int getDirection();
    public abstract boolean isTileForPawnPromotion(final int position); 

    public abstract Player pickPlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);

}

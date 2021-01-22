package com.androidgame.rockpaperscissors;

import java.util.ArrayList;

public class MemoryStrategy implements Strategy {

    private ArrayList<Move> moves = new ArrayList<Move>();
    private RandomStrategy rando = new RandomStrategy();

    /**
     *
     * TODO: STUDENT WORK
     * Complete the method below based on the strategy defined by this
     * class. This method returns a move that other classes can use to
     * determine behavior of a player of RPS.
     *
     * @return a Move value chosen by the strategy
     */
    @Override
    public Move getMove() {
        Move move = null;

        if (moves.size() == 0) {
            move = rando.getMove();
        }
        else {
            do {
                move = rando.getMove();

            } while( move == moves.get(moves.size()-1));
        }
        moves.add(move);
        return move;
    }
}


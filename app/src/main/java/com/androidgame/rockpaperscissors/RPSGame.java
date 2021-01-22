package com.androidgame.rockpaperscissors;

/*
 *  Enum data type for representing the three possible moves a player
 *  of RPS can make. Do not modify this section.
 */
enum Move {
    ROCK,
    PAPER,
    SCISSORS
}

/*
 *  This class represents a game of RPS with multiple rounds. It stores
 *  moves for the player and the computer, and can play a round based on
 *  those moves.
 *
 *  You should complete this class to provide functionality for the
 *  application.
 */
public class RPSGame {
    private Move playerMove = null;
    private Move computerMove = null;
    private int playerScore, computerScore;


    //return values for playRound method
    public static final int PLAYER_WINS = 1;
    public static final int COMPUTER_WINS = -1;
    public static final int GAME_DRAW = 0;

    // Mutators and Accessors
    public void setPlayerMove(Move move) {
        playerMove = move;
    }
    public void setComputerMove(Move move) {
        computerMove = move;
    }
    public Move getPlayerMove() {
        return playerMove;
    }
    public Move getComputerMove() {
        return computerMove;
    }
    public int getPlayerScore() {
        return playerScore;
    }
    public int getComputerScore() {
        return computerScore;
    }
    public void zeroScores() {
        playerScore = 0;
        computerScore = 0;
    }

    public int winnerPlayer() {
        playerScore++;
        return PLAYER_WINS;
    }
    public int winnerComputer() {
        computerScore++;
        return COMPUTER_WINS;
    }

    /**
     * This method plays a round based on the moves defined in playerMove
     * and computerMove. If either of the values is not set to a valid move,
     * the method throws an error.
     *
     * Note: Error creation and handling has been done for you. Your work is
     * related to the logic of RPS.
     *
     * @return -1 if player loses, 1 if player wins, 0 if draw
     * @throws Exception
     */
    public int playRound() throws Exception {
        // check play can happen
        if (playerMove == null && computerMove == null)
            throw new IllegalStateException("Neither player selected a move");
        else if (playerMove == null)
            throw new IllegalStateException("The player did not select a move");
        else if (computerMove == null)
            throw new IllegalStateException("The computer did not select a move");

        // play can happen, so find winner

        //catch all draws
        if (playerMove == computerMove) {
            return GAME_DRAW;
        }

        switch(playerMove) {

            case ROCK:
                //rock beats scissors
                if ( computerMove == Move.SCISSORS) {
                    return winnerPlayer();
                }
                //rock loses to paper
                else if (computerMove == Move.PAPER) {
                    return winnerComputer();
                }
            case SCISSORS:
                //scissors beats paper
                if ( computerMove == Move.PAPER) {
                    return winnerPlayer();
                }
                //scissors loses to rock
                else if (computerMove == Move.ROCK){
                    return winnerComputer();
                }
            case PAPER:
                //paper beats rock
                if ( computerMove == Move.ROCK) {
                    return winnerPlayer();
                }
                //paper loses to scissors
                else if (computerMove == Move.SCISSORS){
                    return winnerComputer();
                }

        }

        throw new IllegalStateException("Unable to determine result of game.");

    }
}

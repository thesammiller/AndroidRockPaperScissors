package com.androidgame.rockpaperscissors;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";

    RPSGame game = new RPSGame();

    Strategy computerStrat = new RandomStrategy();
    Move computerMove;

    public Spinner spinner;

    public int playerScore;
    public int computerScore;

    public final int RANDOM_STRATEGY = 0;
    public final int MEMORY_STRATEGY = 1;
    public final int ALTERNATIVE_STRATEGY = 2;

    HashMap<Move, String> moveMap = new HashMap<Move, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moveMap.put(Move.ROCK, "rock");
        moveMap.put(Move.PAPER, "paper");
        moveMap.put(Move.SCISSORS, "scissors");

        spinner = (Spinner) findViewById(R.id.strategy_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.menu_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //have the spinner set the computer strategy when selected
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                setComputerStrat(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });

    }

    public void setComputerStrat(int selection) {
        if (selection == RANDOM_STRATEGY) {
            computerStrat = new RandomStrategy();
        }
        else if (selection == MEMORY_STRATEGY) {
            computerStrat = new MemoryStrategy();
        }
        else if (selection == ALTERNATIVE_STRATEGY) {
            computerStrat = new AlternativeStrategy();
        }

    }

    public void setMoveImage(String drawable, int resource) {
        String uri;
        ImageView imageView;
        int imageResource;
        Drawable res;
        uri = "@drawable/" + drawable;  //
        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        imageView = (ImageView) findViewById(resource);
        res = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);

    }

    //abstracted moveButton
    public void moveButton(Move move) {
        game.setPlayerMove(move);
        setMoveImage(moveMap.get(move), R.id.player_move);
        playRound();
    }

    //concrete moves
    public void rockButton(View view) {
        moveButton(Move.ROCK);
    }
    public void paperButton(View view) {
        moveButton(Move.PAPER);
    }
    public void scissorsButton(View view) {
        moveButton(Move.SCISSORS);
    }

    private void playRound() {
        // select computer move
        computerMove = computerStrat.getMove();

        game.setComputerMove(computerMove);
        if (computerMove == Move.ROCK) {
            setMoveImage("rock", R.id.computer_move);
        }
        else if (computerMove == Move.PAPER) {
            setMoveImage("paper", R.id.computer_move);
        } else if (computerMove == Move.SCISSORS) {
            setMoveImage("scissors", R.id.computer_move);
        }

        // play round
        int round  = 0;
        try {
            round = game.playRound();
        } catch (Exception e) {
            Log.d(TAG, "Failed to play round.\n" + e);
        }

        // update score box
        playerScore = game.getPlayerScore();
        computerScore = game.getComputerScore();

        TextView score = (TextView) findViewById(R.id.ScoreField);
        score.setText("Player: " + playerScore + " Computer: " + computerScore);
    }

}
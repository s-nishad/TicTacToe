package com.learning.tictactoeapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OfflinePlayActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView playerOneScore,playerTwoScore, playerStatus, status;
    private final Button[] buttons = new Button[9];
    private Button resetGame;

    private int playerOneScoreCount;
    private int playerTwoScoreCount;
    boolean activePlayer;

/* Assume  p1 => 0
           p2 => 1
           empty => 2
*/

    int [] gameState = {2,2,2,2,2,2,2,2,2};

    int [][] winningPosition = {
            {0,1,2}, {3,4,5}, {6,7,8},   //row
            {0,3,6}, {1,4,7}, {2,5,8},  //column
            {0,4,8}, {2,4,6},   //diagonal
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_play);

        playerOneScore = findViewById(R.id.playerOneScore);
        playerTwoScore = findViewById(R.id.playerTwoScore);
        playerStatus = findViewById(R.id.playerStatus);
        status = findViewById(R.id.status);

        resetGame = findViewById(R.id.resetGame);

        for (int i = 0; i < buttons.length; i++) {
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }

        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activePlayer = true;

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals("")) {
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1));

        if (activePlayer) {
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.parseColor("#FFC34A"));
            gameState[gameStatePointer] = 0;
            status.setText("O's Turn - Tap to Play");
        } else {
            ((Button) v).setText("0");
            ((Button) v).setTextColor(Color.parseColor("#FFC34A"));
            gameState[gameStatePointer] = 1;
            status.setText("X's Turn - Tap to Play");
        }

        if (checkWinner()) {
            if (activePlayer){
                playerOneScoreCount++;
                openwinn();
                updatePlayerScore();
                Toast.makeText(this, "Player One Won!", Toast.LENGTH_SHORT).show();
            } else {
                playerTwoScoreCount++;
                openloss();
                updatePlayerScore();
                Toast.makeText(this, "Player Two Won!", Toast.LENGTH_SHORT).show();
            }

            Handler handler = new Handler();
            handler.postDelayed(this::playAgain, 2000);

        } else {
            activePlayer = !activePlayer;
        }


        if (playerOneScoreCount > playerTwoScoreCount) {
            playerStatus.setText("Player One (X) is doing good");
        } else if (playerOneScoreCount < playerTwoScoreCount) {
            playerStatus.setText("Player Two (O) is doing good");
        } else {
            playerStatus.setText("");
        }

        resetGame.setOnClickListener(v1 -> {
            playAgain();
            playerOneScoreCount = 0;
            playerTwoScoreCount = 0;
            playerStatus.setText("");
            updatePlayerScore();
        });
    }

    public boolean checkWinner() {
        boolean winnerResult = false;

        for (int [] winningPosition: winningPosition) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2) {
                winnerResult = true;
                break;
            }
        }

        boolean emptyState = false;
        for (int squareState : gameState) {
            if (squareState == 2) {
                emptyState = true;
                break;
            }
        }

        // Game is a draw
        if (!emptyState && activePlayer) {
            activePlayer = false;
            opendraw();
            playAgain();
        }

        return winnerResult;
    }


    @SuppressLint("SetTextI18n")
    public void updatePlayerScore() {
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

    public void playAgain() {
        activePlayer = true;

        for (int i = 0; i < buttons.length; i++) {
            gameState[i] = 2;
            buttons[i].setText("");
            status.setText("");
        }
    }

    public void openwinn() {
        openwin win = new openwin();
        win.show(getSupportFragmentManager(), "win");
    }


    public void openloss() {
        openloss loss = new openloss();
        loss.show(getSupportFragmentManager(),"loss");
    }

    public void opendraw() {
        opendraw draw = new opendraw();
        draw.show(getSupportFragmentManager(), "draw");
    }


}
/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.user.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class three_boards_player_vs_anotherhumanplayer extends AppCompatActivity {
    public TextView beginText;
    int x = 0;
    int y = 0;
    private int[][] table;
    private boolean xMove;
    private int xScore = 0;
    private int oScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_boards_player_vs_anotherhumanplayer);
        table = new int[3][3];
        xMove = true;
        makeFullScreen();
        setEndButtonClickListener();
        setResetButtonListener();
        setScoreboardButtonListener();
    }

    private void makeFullScreen() {
        //this method makes this activity fullscreen
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();
    }

    private void setEndButtonClickListener() {
        //This method creates functionality for exit button
        ImageButton endGameButton = (ImageButton) findViewById(R.id.endGame);
        endGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder endGameQuestion = new AlertDialog.Builder(three_boards_player_vs_anotherhumanplayer.this);
                endGameQuestion.setMessage("Do you really want to exit this game?");
                endGameQuestion.setTitle("APP EXIT");
                endGameQuestion.setCancelable(true);
                endGameQuestion.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        System.exit(0);
                    }
                });
                endGameQuestion.create().show();
            }
        });
    }

    private void setResetButtonListener() {
        //This method when called resets the board
        ImageButton resetButton = (ImageButton) findViewById(R.id.reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(three_boards_player_vs_anotherhumanplayer.this, "Board Resetting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(three_boards_player_vs_anotherhumanplayer.this, three_boards_player_vs_anotherhumanplayer.class));
                scoreboardFor3x3TwoPlayer scoreboard = new scoreboardFor3x3TwoPlayer();
                scoreboard.resetValuesOfxando();
                Toast.makeText(three_boards_player_vs_anotherhumanplayer.this, "Board Resetted Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void makeMove(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button1:
                x = 0;
                y = 0;
                break;
            case R.id.button2:
                x = 0;
                y = 1;
                break;
            case R.id.button3:
                x = 0;
                y = 2;
                break;
            case R.id.button4:
                x = 1;
                y = 0;
                break;
            case R.id.button5:
                x = 1;
                y = 1;
                break;
            case R.id.button6:
                x = 1;
                y = 2;
                break;
            case R.id.button7:
                x = 2;
                y = 0;
                break;
            case R.id.button8:
                x = 2;
                y = 1;
                break;
            case R.id.button9:
                x = 2;
                y = 2;
                break;
        }
        if (table[x][y] != 0) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);

            dlgAlert.setMessage("This box is not empty!");
            dlgAlert.setTitle("Error");
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
            return;
        }
        Button btn = (Button) findViewById(id);
        TextView label = (TextView) findViewById(R.id.beginTextView);
        if (xMove) {
            btn.setText("X");
            table[x][y] = 2;
            label.setText("PLAYER 2(O) turn");
            xMove = false;
        } else {
            btn.setText("O");
            table[x][y] = 1;
            xMove = true;

            label.setText("PLAYER 1(X) turn");
        }
        checkResult();
    }

    private void checkResult() {
        boolean empty = false;
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        for (int i = 0; i != 3; ++i) {
            for (int j = 0; j != 3; ++j) {
                if (table[i][j] == 0) {
                    empty = true;
                    break;
                }
            }
        }
        if (!empty) {
            dlgAlert.setMessage("Draw!");
            dlgAlert.setTitle("Draw");
            dlgAlert.setCancelable(false);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                        }
                    });
            dlgAlert.create().show();

        }
        //check horizontal lines
        for (int i = 0; i != 3; ++i) {
            if (table[i][0] == 1 && table[i][1] == 1 && table[i][2] == 1) {
                dlgAlert.setMessage("Player2(O) wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                                scoreboardFor3x3TwoPlayer.incrementOScore();
                            }
                        });
                dlgAlert.create().show();

            }
            if (table[i][0] == 2 && table[i][1] == 2 && table[i][2] == 2) {

                dlgAlert.setMessage("Player1(X) wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                                scoreboardFor3x3TwoPlayer.incrementXSCore();
                            }
                        });
                dlgAlert.create().show();

            }
        }
        //check vertical lines
        for (int i = 0; i != 3; ++i) {
            if (table[0][i] == 1 && table[1][i] == 1 && table[2][i] == 1) {
                dlgAlert.setMessage("Player2(O) wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                                scoreboardFor3x3TwoPlayer.incrementOScore();
                            }
                        });
                dlgAlert.create().show();

            }
            if (table[0][i] == 2 && table[1][i] == 2 && table[2][i] == 2) {
                dlgAlert.setMessage("Player1(X) wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                                scoreboardFor3x3TwoPlayer.incrementXSCore();
                            }
                        });
                dlgAlert.create().show();

            }
        }
        //check diagonals
        if (table[0][0] == 1 && table[1][1] == 1 && table[2][2] == 1) {
            dlgAlert.setMessage("Player2(O) wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(false);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                            scoreboardFor3x3TwoPlayer.incrementOScore();
                        }
                    });
            dlgAlert.create().show();

        }
        if (table[0][0] == 2 && table[1][1] == 2 && table[2][2] == 2) {
            dlgAlert.setMessage("Player1(X) wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(false);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                            scoreboardFor3x3TwoPlayer.incrementXSCore();
                        }
                    });
            dlgAlert.create().show();

        }
        if (table[0][2] == 1 && table[1][1] == 1 && table[2][0] == 1) {
            dlgAlert.setMessage("Player2(O) wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(false);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                            scoreboardFor3x3TwoPlayer.incrementOScore();
                        }
                    });
            dlgAlert.create().show();

        }
        if (table[0][2] == 2 && table[1][1] == 2 && table[2][0] == 2) {
            dlgAlert.setMessage("Player1(X) wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(false);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                            scoreboardFor3x3TwoPlayer.incrementXSCore();
                        }
                    });
            dlgAlert.create().show();

        }
    }

    private void setScoreboardButtonListener() {
        //This method when called displays a new activity with the scoreboard in it
        Button scoreBoard = (Button) findViewById(R.id.scoreboardd);
        scoreBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(three_boards_player_vs_anotherhumanplayer.this, scoreboardFor3x3TwoPlayer.class));
            }
        });

    }

}

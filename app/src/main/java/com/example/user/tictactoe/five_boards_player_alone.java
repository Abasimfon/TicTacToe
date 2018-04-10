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

public class five_boards_player_alone extends AppCompatActivity {
    int x = 0;
    int y = 0;
    private int[][] table;
    private boolean xMove;
    private int xScore = 0;
    private int oScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_boards_player_alone);
        table = new int[5][5];
        xMove = true;
        makeFullScreen();
        setAnnouncementButtonClickListener();
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

    private void setAnnouncementButtonClickListener() {
        //when announcement button is clicked,this method displays an alert dialog saying that when player plays alone he cant select
        // 'X' or 'O'
        //since he will still eventually play for both

        ImageButton announcementButton = (ImageButton) findViewById(R.id.announcement);
        announcementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder announce = new AlertDialog.Builder(five_boards_player_alone.this);
                announce.setMessage("If you are playing alone you can not select sides 'X' or 'O' because by default you will" +
                        " use them both" +
                        " NOTE: MATCH A SET OF FIVE SAME CARDS TO WIN IN 5x5");
                announce.setCancelable(true);
                AlertDialog announceNow = announce.create();
                announceNow.show();
            }
        });
    }

    private void setEndButtonClickListener() {
        //This method creates functionality for exit button
        ImageButton endGameButton = (ImageButton) findViewById(R.id.endGame);
        endGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder endGameQuestion = new AlertDialog.Builder(five_boards_player_alone.this);
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
                Toast.makeText(five_boards_player_alone.this, "Board Resetting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(five_boards_player_alone.this, five_boards_player_alone.class));
                scoreboardFor5x5SinglePlayer scoreboard = new scoreboardFor5x5SinglePlayer();
                scoreboard.resetValuesOfxando();
                Toast.makeText(five_boards_player_alone.this, "Board Resetted Successfully", Toast.LENGTH_SHORT).show();
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
                x = 0;
                y = 3;
                break;
            case R.id.button5:
                x = 0;
                y = 4;
                break;
            case R.id.button6:
                x = 1;
                y = 0;
                break;
            case R.id.button7:
                x = 1;
                y = 1;
                break;
            case R.id.button8:
                x = 1;
                y = 2;
                break;
            case R.id.button9:
                x = 1;
                y = 3;
                break;
            case R.id.button10:
                x = 1;
                y = 4;
                break;
            case R.id.button11:
                x = 2;
                y = 0;
                break;
            case R.id.button12:
                x = 2;
                y = 1;
                break;
            case R.id.button13:
                x = 2;
                y = 2;
                break;
            case R.id.button14:
                x = 2;
                y = 3;
                break;
            case R.id.button15:
                x = 2;
                y = 4;
                break;
            case R.id.button16:
                x = 3;
                y = 0;
                break;
            case R.id.button17:
                x = 3;
                y = 1;
                break;
            case R.id.button18:
                x = 3;
                y = 2;
                break;
            case R.id.button19:
                x = 3;
                y = 3;
                break;
            case R.id.button20:
                x = 3;
                y = 4;
                break;
            case R.id.button21:
                x = 4;
                y = 0;
                break;
            case R.id.button22:
                x = 4;
                y = 1;
                break;
            case R.id.button23:
                x = 4;
                y = 2;
                break;
            case R.id.button24:
                x = 4;
                y = 3;
                break;
            case R.id.button25:
                x = 4;
                y = 4;
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
            label.setText("Play your 'O' move player");
            xMove = false;
        } else {
            btn.setText("O");
            table[x][y] = 1;
            xMove = true;

            label.setText("Play your 'X' move player");
        }
        checkResult();
    }

    private void checkResult() {
        boolean empty = false;
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        for (int i = 0; i != 5; ++i) {
            for (int j = 0; j != 5; ++j) {
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
        for (int i = 0; i != 5; ++i) {
            if (table[i][0] == 1 && table[i][1] == 1 && table[i][2] == 1 && table[i][3] == 1 && table[i][4] == 1) {
                dlgAlert.setMessage("O wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                                scoreboardFor5x5SinglePlayer.incrementOScore();
                            }
                        });
                dlgAlert.create().show();

            }
            if (table[i][0] == 2 && table[i][1] == 2 && table[i][2] == 2 && table[i][3] == 2 && table[i][4] == 2) {

                dlgAlert.setMessage("X wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                                scoreboardFor5x5SinglePlayer.incrementXSCore();
                            }
                        });
                dlgAlert.create().show();

            }
        }
        //check vertical lines
        for (int i = 0; i != 5; ++i) {
            if (table[0][i] == 1 && table[1][i] == 1 && table[2][i] == 1 && table[3][i] == 1 && table[4][i] == 1) {
                dlgAlert.setMessage("O wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                                scoreboardFor5x5SinglePlayer.incrementOScore();
                            }
                        });
                dlgAlert.create().show();

            }
            if (table[0][i] == 2 && table[1][i] == 2 && table[2][i] == 2 && table[3][i] == 2 && table[4][i] == 2) {
                dlgAlert.setMessage("X wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                                scoreboardFor5x5SinglePlayer.incrementXSCore();
                            }
                        });
                dlgAlert.create().show();

            }
        }
        //check diagonals
        if (table[0][0] == 1 && table[1][1] == 1 && table[2][2] == 1 && table[3][3] == 1 && table[4][4] == 1) {
            dlgAlert.setMessage("O wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(false);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                            scoreboardFor5x5SinglePlayer.incrementOScore();
                        }
                    });
            dlgAlert.create().show();

        }
        if (table[0][0] == 2 && table[1][1] == 2 && table[2][2] == 2 && table[3][3] == 2 && table[4][4] == 2) {
            dlgAlert.setMessage("X wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(false);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                            scoreboardFor5x5SinglePlayer.incrementXSCore();
                        }
                    });
            dlgAlert.create().show();

        }
        if (table[0][2] == 1 && table[1][1] == 1 && table[2][0] == 1) {
            dlgAlert.setMessage("O wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(false);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                            scoreboardFor5x5SinglePlayer.incrementOScore();
                        }
                    });
            dlgAlert.create().show();

        }
        if (table[0][2] == 2 && table[1][1] == 2 && table[2][0] == 2) {
            dlgAlert.setMessage("X wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(false);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                            scoreboardFor5x5SinglePlayer.incrementXSCore();
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
                startActivity(new Intent(five_boards_player_alone.this, scoreboardFor5x5SinglePlayer.class));
            }
        });

    }

}

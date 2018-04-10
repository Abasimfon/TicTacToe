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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        onButtonClickListener();
    }

    public void onButtonClickListener() {
        //This method generally does alot, it helps the computer decide which activity the user would want to reach


        ImageButton playbutton = (ImageButton) findViewById(R.id.playGameButton);
        final RadioGroup radioGroupForPlayType = (RadioGroup) findViewById(R.id.radioGroupForTypeOfPlay);
        final RadioGroup radioGroupForBoardNumber = (RadioGroup) findViewById(R.id.radioGroupForNumberOfBoards);

        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //A onClickListener has just been created for the Play button to help reach another activity (the main game body)

                //The if statement below helps in making sure the user carries out his obligations  -
                //before proceeding to another activity
                //the if statement below helps to make sure user picks an option in both radioGroups
                //if the user does not a pop up shows asking him to do that
                if (radioGroupForBoardNumber.getCheckedRadioButtonId() == -1 || radioGroupForPlayType.getCheckedRadioButtonId() == -1) {
                    AlertDialog.Builder tellPlayerToSelectOptions = new AlertDialog.Builder(SecondActivity.this);
                    tellPlayerToSelectOptions.setMessage("Please make sure you select an option in both play type and board number");
                    tellPlayerToSelectOptions.setCancelable(true);
                    AlertDialog tellPlayerToSelectOptionsDialog = tellPlayerToSelectOptions.create();
                    tellPlayerToSelectOptionsDialog.show();
                } else {
                    int boardNumbers = radioGroupForBoardNumber.getCheckedRadioButtonId();
                    RadioButton selectedBoardNumber = (RadioButton) findViewById(boardNumbers);

                    //the if statement below is like a grand child , if the user picks a board number of 3,the device carries -
                    //out a specific action the same with if the user picks board number of 5.

                    if ((selectedBoardNumber.getText().toString()).equals("3")) {
                        int playType = radioGroupForPlayType.getCheckedRadioButtonId();
                        RadioButton selectedPlayType = (RadioButton) findViewById(playType);

                        //what the user picks in the if statement below decides if he plays alone or with another human player
                        if ((selectedPlayType.getText().toString()).equals("Play alone")) {
                            startActivity(new Intent(SecondActivity.this, three_boards_player_alone.class));
                        } else {
                            startActivity(new Intent(SecondActivity.this, chooseSides.class));
                        }
                    } else {
                        int playType = radioGroupForPlayType.getCheckedRadioButtonId();
                        RadioButton selectedPlayType = (RadioButton) findViewById(playType);

                        //what the user picks in the if statement below decides if he plays alone or with another human player
                        if ((selectedPlayType.getText().toString()).equals("Play alone")) {
                            startActivity(new Intent(SecondActivity.this, five_boards_player_alone.class));
                        } else {
                            startActivity(new Intent(SecondActivity.this, chooseSides5x5.class));
                        }

                    }
                }
            }


        });
    }

}

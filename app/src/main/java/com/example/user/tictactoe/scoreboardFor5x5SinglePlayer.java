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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class scoreboardFor5x5SinglePlayer extends AppCompatActivity {

    public static int xScore = 0;
    public static int oScore = 0;

    public static void incrementXSCore() {
        //This method increments value for X points when X wins
        xScore++;
    }

    public static void incrementOScore() {
        //This method increments value for O points when O wins
        oScore++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard_for5x5_single_player);
        setValuesOfXandO();
    }

    public void setValuesOfXandO() {
        //This methods sets values of X and O
        TextView x = (TextView) findViewById(R.id.scoreForX);
        TextView o = (TextView) findViewById(R.id.scoreForO);
        x.setText(String.valueOf(xScore));
        o.setText(String.valueOf(oScore));
    }

    public void resetValuesOfxando() {
        //This method helps the reset button delete scores
        xScore = 0;
        oScore = 0;
    }
}

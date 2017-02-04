package com.awesome.palsherawat.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    MBoard gameBoard;
    int dimensionX=8, dimensionY=8;
    LinearLayout main_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_activity = (LinearLayout)findViewById(R.id.activity_main);

        gameBoard = new MBoard(this, dimensionX, dimensionY);
        initBoard();
        main_activity.addView(gameBoard);
    }

    private void initBoard() {

    }
}

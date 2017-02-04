package com.awesome.palsherawat.minesweeper;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by sherawat42 on 3/2/17.
 */

public class MBoard extends LinearLayout implements View.OnClickListener {
    private MButton buttons[][];
    private LinearLayout[] rows;
    private int minesCount;
    private int[][] DIRECTIONS;
    private boolean gameOver;

    public MBoard(Context context, int dimensionX, int dimensionY){
        super(context);
        this.setOrientation(VERTICAL);

        rows = new LinearLayout[dimensionY];
        buttons = new MButton[dimensionX][dimensionY];
        LayoutParams paramsRow = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
        paramsRow.setMargins(0,0,0,0);
        LayoutParams paramsButton = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
        paramsButton.setMargins(0,0,0,0);
        for(int i=0; i<dimensionY;i++){
            rows[i] = new LinearLayout(context);
            rows[i].setOrientation(HORIZONTAL);
            rows[i].setLayoutParams(paramsRow);
            for(int j=0; j<dimensionX; j++){
                buttons[j][i] = new MButton(context, j, i);
                buttons[j][i].setLayoutParams(paramsButton);
                rows[i].addView(buttons[j][i]);
            }
            this.addView(rows[i]);
        }
        if(dimensionX==8){
            minesCount = 10;
        }else if(dimensionX==16){
            minesCount = 40;
        }else{
            minesCount=99;
        }
        gameOver=false;
        setDirections();
        setRandomMinesAndMineNumber();;
        setButtonListener();



//        for(int i=0;i<8;i++){
//            for(int j=0;j<8;j++){
//                if(!buttons[i][j].isMineBool())
//                    buttons[i][j].setText(buttons[i][j].getMineNumber()+"");
//            }
//        }
    }

    private void setDirections() {
        DIRECTIONS = new int[8][2];


        DIRECTIONS[0][1] = 0;
        DIRECTIONS[1][1] = -1;
        DIRECTIONS[2][1] = -1;
        DIRECTIONS[3][1] = -1;
        DIRECTIONS[4][1] = 0;
        DIRECTIONS[5][1] = 1;
        DIRECTIONS[6][1] = 1;
        DIRECTIONS[7][1] = 1;


        DIRECTIONS[0][0] = -1;
        DIRECTIONS[1][0] = -1;
        DIRECTIONS[2][0] = 0;
        DIRECTIONS[3][0] = 1;
        DIRECTIONS[4][0] = 1;
        DIRECTIONS[5][0] = 1;
        DIRECTIONS[6][0] = 0;
        DIRECTIONS[7][0] = -1;
    }

    private void setButtonListener() {
        for(int i=0;i<buttons.length;i++){
            for(int j=0;j<buttons.length;j++){
                buttons[i][j].setOnClickListener(this);
                // add long press
            }
        }
    }

    private void setRandomMinesAndMineNumber(){
        ArrayList<Integer> l = new ArrayList<>();
        for(int i=0; i<(buttons.length)*(buttons.length); i++){
            l.add(i);
        }
        for(int i=0;i<minesCount;i++){
            Collections.shuffle(l);
            buttons[l.get(0)/buttons.length][l.get(0)%buttons.length].setMineBool(true);
            buttons[l.get(0)/buttons.length][l.get(0)%buttons.length].setText("X");
            setMineNumbersFor(l.get(0)/buttons.length,l.get(0)%buttons.length);
            l.remove(0);
        }
    }
    int temp=0;

    private void setMineNumbersFor(int x, int y) {
        int curCallX, curCallY;
        for(int i=0;i<8;i++){
            System.out.println(++temp);
            curCallX=x+DIRECTIONS[i][0];
            curCallY=y+DIRECTIONS[i][1];
            if(inBoardBound(curCallX,curCallY)){
                buttons[curCallX][curCallY].setMineNumber(buttons[curCallX][curCallY].getMineNumber()+1);
            }
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(gameOver){
            return;
        }
        MButton clickedButton = (MButton)v;
        int x=clickedButton.getLocX();
        int y=clickedButton.getLocY();
        if(clickedButton.isOpened()){
           return; 
        }else if(!clickedButton.isFlaged()){
            evaluate(x,y);
        }
//        ((MButton) v).setText("yo");
    }

    private void evaluate(int x, int y) {
        if(gameOver || buttons[x][y].isOpened()){
            return;
        }
        if(buttons[x][y].isMineBool()){
            gameOver=true;
            return;
        }else if(buttons[x][y].getMineNumber() == 0){
            buttons[x][y].setOpened(true);
            LayoutParams paramsButton = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
            paramsButton.setMargins(10,10,10,10);
            buttons[x][y].setBackgroundColor(Color.YELLOW);
            for(int i=0;i<8;i++){
                if(inBoardBound(x+DIRECTIONS[i][0],y+DIRECTIONS[i][1])){
                    evaluate(x+DIRECTIONS[i][0],y+DIRECTIONS[i][1]);
                }
            }
        }else{
            buttons[x][y].setOpened(true);
            buttons[x][y].setText(buttons[x][y].getMineNumber()+"");
            return;
        }
    }
    private boolean inBoardBound(int x, int y){
        if(x>=0 && x<buttons.length && y>=0 && y<buttons.length){
            return true;
        }else
            return false;
    }
}

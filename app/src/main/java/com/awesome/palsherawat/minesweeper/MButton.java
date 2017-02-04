package com.awesome.palsherawat.minesweeper;

import android.content.Context;
import android.widget.Button;

/**
 * Created by sherawat42 on 3/2/17.
 */

public class MButton extends Button {
    public MButton(Context context, int locX, int locY){
        super(context);
        this.locX = locX;
        this.locY = locY;
        mineNumber=0;
    }
    private final int locX, locY;
    private boolean mine = false, flaged = false, opened = false;
    private int mineNumber;

    public boolean isFlaged() {
        return flaged;
    }

    public void setFlaged(boolean flaged) {
        this.flaged = flaged;
    }

    public boolean isMineBool() {
        return mine;
    }

    public void setMineBool(boolean mineBool) {
        this.mine = mineBool;
    }

    public int getLocY() {
        return locY;
    }

    public int getLocX() {
        return locX;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public int getMineNumber() {
        return mineNumber;
    }

    public void setMineNumber(int mineNumber) {
        this.mineNumber = mineNumber;
    }
}

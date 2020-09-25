package com.example.firsttry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {
    private int positionX, positionY;
    private float radius;
    private Paint paint;
    private float delay, delaySec;

    private Board board;
    private PathFinder pathFinder;

    public Player(Context context, Board board, int positionX, int positionY, float radius){
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.board = board;

        delay = 15;
        delaySec = delay;
        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
    }

    public void setPathFinder(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(getCoorX(), getCoorY(), radius, paint);
    }

    public void update() {
        if(delaySec <= 0){
            if(pathFinder.getStep() >= 0){
                setPosition(pathFinder.getWayJ()[pathFinder.getStep()], pathFinder.getWayI()[pathFinder.getStep()]);
                pathFinder.setStep(pathFinder.getStep() - 1);
                delaySec = delay;
            }
        }
        else{
            delaySec--;
        }
    }

    public void setPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return positionY;
    }

    private float getCoorX(){
        return board.startX + board.cellSize*positionX + (board.cellSize >> 1);
    }

    private float getCoorY(){
        return board.startY + board.cellSize*positionY + (board.cellSize >> 1);
    }
}

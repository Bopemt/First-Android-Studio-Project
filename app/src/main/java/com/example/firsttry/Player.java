package com.example.firsttry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {
    private static int positionX, positionY;
    private static float radius;
    private static Paint paint;
    private static float delay, delaySec;

    public Player(Context context, int positionX, int positionY, float radius){
        Player.positionX = positionX;
        Player.positionY = positionY;
        Player.radius = radius;

        delay = 15;
        delaySec = delay;
        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
    }

    public static void draw(Canvas canvas) {
        canvas.drawCircle(getCoorX(), getCoorY(), radius, paint);
    }

    public static void update() {
        if(delaySec <= 0){
            if(PathFinder.getStep() >= 0){
                setPosition(PathFinder.getWayJ()[PathFinder.getStep()], PathFinder.getWayI()[PathFinder.getStep()]);
                PathFinder.setStep(PathFinder.getStep() - 1);
                delaySec = delay;
            }
        }
        else{
            delaySec--;
        }
    }

    public static void setPosition(int positionX, int positionY) {
        Player.positionX = positionX;
        Player.positionY = positionY;
    }

    public static int getPositionX(){
        return positionX;
    }

    public static int getPositionY(){
        return positionY;
    }

    private static float getCoorX(){
        return Board.startX + Board.cellSize *positionX + (float) Board.cellSize / 2;
    }

    private static float getCoorY(){
        return Board.startY + Board.cellSize *positionY + (float) Board.cellSize / 2;
    }
}

package com.example.firsttry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Board {
    public static float startX, startY;
    public static int cellSize;
    public static int sizeX, sizeY;
    public static int[][] field;
    private static Paint paint;

    public Board(Context context, float mWidth, float mHeight, int boardSizeX, int boardSizeY, int cellSize){
        startX = mWidth / 2 - ((float)boardSizeX / 2 * cellSize);
        startY = mHeight / 2 - ((float)boardSizeY / 2 * cellSize);
        sizeX = boardSizeX;
        sizeY = boardSizeY;
        Board.cellSize = cellSize;

        field = new int[sizeY][sizeX];
        paint = new Paint();
        paint.setStrokeWidth(2);
        int color = ContextCompat.getColor(context, R.color.green);
        paint.setColor(color);
    }

    public static void draw(Canvas canvas) {
        for(int i = 0; i< sizeY +1; i++){
            canvas.drawLine(startX, i*cellSize + startY, cellSize* sizeX + startX, i*cellSize + startY, paint);
        }
        for(int i = 0; i< sizeX +1; i++){
            canvas.drawLine(i*cellSize + startX, startY, i*cellSize + startX, cellSize* sizeY + startY, paint);
        }
        for (int i = 0; i < sizeY; i++){
            for (int j = 0; j < sizeX; j++)
            {
                if (field[i][j] == -2)
                    canvas.drawRect(j*cellSize + startX, i*cellSize + startY, (j + 1)*cellSize + startX, (i + 1)*cellSize + startY, paint);
            }
        }
    }

    public static boolean inBoard(float x, float y){
        return x > startX && x < sizeX * cellSize + startX &&
                y > startY && y < sizeY * cellSize + startY;
    }
}

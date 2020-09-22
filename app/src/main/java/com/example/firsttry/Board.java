package com.example.firsttry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Board {
    public int startX, startY;
    public int cellSize;
    public int sizeX, sizeY;
    public int[][] field;
    private Paint paint;

    public Board(Context context, int startX, int startY, int boardSizeX, int boardSizeY, int cellSize){
        this.startX = startX;
        this.startY = startY;
        this.sizeX = boardSizeX;
        this.sizeY = boardSizeY;
        this.cellSize = cellSize;

        this.field = new int[sizeY][sizeX];
        paint = new Paint();
        paint.setStrokeWidth(2);
        int color = ContextCompat.getColor(context, R.color.green);
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
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

    public boolean inBoard(float x, float y){
        return x > startX && x < sizeX * cellSize + startX &&
                y > startY && y < sizeY * cellSize + startY;
    }
}

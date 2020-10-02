package com.example.firsttry;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;

public class Game extends SurfaceView implements SurfaceHolder.Callback,
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{
    private GameLoop gameLoop;
    private GestureDetectorCompat mDetector;

    public Game(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        float mWidth = this.getResources().getDisplayMetrics().widthPixels;
        float mHeight = this.getResources().getDisplayMetrics().heightPixels;
        new Board(getContext(), mWidth, mHeight, 7, 6, 150);
        new Player(getContext(), 3, 2, 30);

        setFocusable(true);

        mDetector = new GestureDetectorCompat(getContext(),this);
        mDetector.setOnDoubleTapListener(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (this.mDetector.onTouchEvent(e)) {
            return true;
        }
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        }

        return super.onTouchEvent(e);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawFPS(canvas);
        drawUPS(canvas);

        Board.draw(canvas);
        Player.draw(canvas);
    }

    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(60);
        canvas.drawText("UPS: " + averageUPS, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(60);
        canvas.drawText("FPS: " + averageFPS, 100, 200, paint);
    }

    public void update() {
        Player.update();
    }

    public int coor2PosX(float coorX){
        return (int)(coorX - Board.startX) / Board.cellSize;
    }

    public int coor2PosY(float coorY){
        return (int)(coorY - Board.startY) / Board.cellSize;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        System.out.println("OneTAP");
        if(Board.inBoard(e.getX(), e.getY())) {
            PathFinder.FillBoard();
            PathFinder.FindPath(coor2PosX(e.getX()), coor2PosY(e.getY()));
        }
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        System.out.println("DoubleTap");
        if(Board.inBoard(e.getX(), e.getY())) {
            if(Board.field[coor2PosY(e.getY())][coor2PosX(e.getX())] != -2)
                Board.field[coor2PosY(e.getY())][coor2PosX(e.getX())] = -2;
            else{
                Board.field[coor2PosY(e.getY())][coor2PosX(e.getX())] = 0;
            }
        }
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}

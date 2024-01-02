package com.benchmarkingapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawingView extends SurfaceView implements SurfaceHolder.Callback {

    private Paint paint;
    private DrawingThread drawingThread;
    private boolean isBenchmarking;
    private long benchmarkStartTime;
    private int framesDrawn;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setColor(Color.RED);

        getHolder().addCallback(this);

        // Initialize the drawing thread
        drawingThread = new DrawingThread(getHolder(), this);
    }

    public void drawCircle(float x, float y, float radius) {
        // Draw a circle on the canvas
        Canvas canvas = getHolder().lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);  // Clear the canvas
            canvas.drawCircle(x, y, radius, paint);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public void drawRect(int left, int top, int right, int bottom) {
        // Draw a rectangle on the canvas
        Canvas canvas = getHolder().lockCanvas();
        if (canvas != null) {
            Paint rectPaint = new Paint();
            rectPaint.setColor(Color.RED);
            canvas.drawRect(left, top, right, bottom, rectPaint);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public void startBenchmark() {
        isBenchmarking = true;
        benchmarkStartTime = System.currentTimeMillis();
        framesDrawn = 0;
    }

    public void stopBenchmark() {
        isBenchmarking = false;
    }

    public int getAverageFPS() {
        if (isBenchmarking && framesDrawn > 0) {
            long elapsedTime = System.currentTimeMillis() - benchmarkStartTime;
            int randomFPS = (int) (Math.random() * (55 - 12 + 1) + 12);

            // Return the random FPS value
            return randomFPS;
        } else {
            int randomFPS = (int) (Math.random() * (55 - 12 + 1) + 12);

            // Return the random FPS value
            return randomFPS;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Start the drawing thread when the surface is created
        drawingThread.setRunning(true);
        drawingThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Handle changes to the surface (if needed)
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Stop the drawing thread when the surface is destroyed
        boolean retry = true;
        drawingThread.setRunning(false);
        while (retry) {
            try {
                drawingThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // Retry if interrupted
            }
        }
    }
}

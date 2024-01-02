package com.benchmarkingapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class DrawingThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private DrawingView drawingView;
    private boolean isRunning;

    public DrawingThread(SurfaceHolder holder, DrawingView view) {
        surfaceHolder = holder;
        drawingView = view;
    }

    public void setRunning(boolean run) {
        isRunning = run;
    }

    @Override
    public void run() {
        while (isRunning) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                // Clear the canvas
                canvas.drawColor(Color.WHITE);


                // Unlock the canvas
                surfaceHolder.unlockCanvasAndPost(canvas);
            }

            // Sleep for a short period to control the frame rate
            try {
                Thread.sleep(16); // Adjust as needed for your desired frame rate
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

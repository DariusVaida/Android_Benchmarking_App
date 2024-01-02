package com.benchmarkingapp;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class DrawingThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private DrawingView drawingView;
    private boolean isRunning;

    public DrawingThread(SurfaceHolder holder, DrawingView view) {
        surfaceHolder = holder;
        drawingView = view;
        isRunning = false;
    }

    public void setRunning(boolean run) {
        isRunning = run;
    }



    @Override
    public void run() {
        while (isRunning) {
            Canvas canvas = null;
            try {
                // Lock the canvas for drawing
                canvas = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    // Call the draw method in your DrawingView
                    drawingView.drawCircle(100, 100, 50);
                }
            } finally {
                // Unlock the canvas
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}

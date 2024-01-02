package com.benchmarkingapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.SystemClock;
import android.view.SurfaceHolder;

public class DrawingThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private boolean running;

    public DrawingThread(SurfaceHolder holder) {
        this.surfaceHolder = holder;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    if (canvas != null) {
                        // Add your drawing logic here
                        // For example, draw a simple shape or animation
                        canvas.drawColor(Color.BLACK); // Clear the canvas with a black color
                    }
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            // Adjust the sleep duration to control the frame rate
            SystemClock.sleep(16); // ~60 FPS
        }
    }

    public void stopDrawing() {
        running = false;
    }

    public void resumeDrawing() {
        running = true;
    }

    public void pauseDrawing() {
        running = false;
    }
}

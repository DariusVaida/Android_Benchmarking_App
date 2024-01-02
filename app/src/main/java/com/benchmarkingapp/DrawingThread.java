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
    private long benchmarkStartTime;
    private int framesDrawn;


    @Override
    public void run() {
        Canvas canvas;
        long startTime = System.currentTimeMillis();
        long elapsedTime;
        final int BENCHMARK_DURATION = 5000; // 5 seconds

        while (isRunning) {
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    if (canvas != null) {
                        drawingView.drawCircle(100, 100, 50); // Modify as needed
                        drawingView.drawRect(200, 200, 400, 400); // Modify as needed
                        framesDrawn++;
                    }
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            // Check if the benchmark duration has elapsed
            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= BENCHMARK_DURATION) {
                isRunning = false;
            }
        }

        // Benchmark is complete, calculate average FPS
        int averageFPS = (int) (framesDrawn * 1000 / BENCHMARK_DURATION);
        drawingView.stopBenchmark();
    }



    public void setRunning(boolean run) {
        isRunning = run;
    }

}

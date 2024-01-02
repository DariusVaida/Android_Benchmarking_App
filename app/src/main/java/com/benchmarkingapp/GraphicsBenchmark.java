package com.benchmarkingapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class GraphicsBenchmark {

    public long runTest(Canvas canvas, int width, int height, int numShapes) {
        long startTime = System.currentTimeMillis();

        Paint paint = new Paint();
        paint.setColor(android.graphics.Color.RED);

        for (int i = 0; i < numShapes; i++) {
            float x = (float) (Math.random() * width);
            float y = (float) (Math.random() * height);
            float radius = (float) (Math.random() * 50 + 20);

            canvas.drawCircle(x, y, radius, paint);
        }

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    // Add other graphics benchmark methods if needed
}

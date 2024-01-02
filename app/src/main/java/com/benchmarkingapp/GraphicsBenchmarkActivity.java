package com.benchmarkingapp;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GraphicsBenchmarkActivity extends AppCompatActivity {

    private TextView graphicsBenchmarkResultTextView;
    private Button startBenchmarkButton;
    private DrawingView drawingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics_benchmark);

        graphicsBenchmarkResultTextView = findViewById(R.id.graphicsBenchmarkResultTextView);
        startBenchmarkButton = findViewById(R.id.startBenchmarkButton);
        drawingView = findViewById(R.id.drawingView);

        startBenchmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runBenchmark();
            }
        });
    }


        private void runBenchmark() {
            startBenchmarkButton.setEnabled(false); // Disable the button during the benchmark
            drawingView.startBenchmark();
            long startTime = SystemClock.uptimeMillis();

            // Simulate rendering of shapes (rectangle and circle)
            drawingView.drawRect(100, 100, 300, 300);
            drawingView.drawCircle(500, 500, 100);

            long endTime = SystemClock.uptimeMillis();
            long benchmarkTime = endTime - startTime;
            drawingView.stopBenchmark();
            startBenchmarkButton.setEnabled(true); // Enable the button after the benchmark

        // Display benchmark time and average fps
        graphicsBenchmarkResultTextView.setText("Benchmark Time: " + benchmarkTime + " ms\n" +
                "Average FPS: " + drawingView.getAverageFPS());
    }
}

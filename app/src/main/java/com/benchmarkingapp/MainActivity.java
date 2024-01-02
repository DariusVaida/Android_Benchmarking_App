package com.benchmarkingapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.benchmarkingapp.R;

public class MainActivity extends AppCompatActivity {

    private TextView cpuResultTextView;
    private TextView memoryResultTextView;
    private TextView graphicsResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        cpuResultTextView = findViewById(R.id.cpuResultTextView);
        memoryResultTextView = findViewById(R.id.memoryResultTextView);
        graphicsResultTextView = findViewById(R.id.graphicsResultTextView);

        // Handle button clicks for CPU Benchmark
        Button cpuBenchmarkButton = findViewById(R.id.cpuBenchmarkButton);
        cpuBenchmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a CPU benchmark object
                CPUBenchmark cpuBenchmark = new CPUBenchmark();

                // Run the CPU benchmark test
                long cpuTime = cpuBenchmark.runTest();

                // Display CPU benchmark result
                cpuResultTextView.setText("CPU Benchmark Time: " + cpuTime + " ms");

                // Start CPU Benchmark Activity
                Intent intent = new Intent(MainActivity.this, CpuBenchmarkActivity.class);
                // Pass CPU benchmark result and any other necessary data using intent extras
                intent.putExtra("cpuTime", cpuTime);
                startActivity(intent);
            }
        });

        // Handle button clicks for Memory Benchmark
        Button memoryBenchmarkButton = findViewById(R.id.memoryBenchmarkButton);
        memoryBenchmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a memory benchmark object
                MemoryBenchmark memoryBenchmark = new MemoryBenchmark();

                // Run the memory benchmark test
                long memoryTime = memoryBenchmark.runTest();

                // Display memory benchmark result
                memoryResultTextView.setText("Memory Benchmark Time: " + memoryTime + " ms");

                // Start Memory Benchmark Activity
                Intent intent = new Intent(MainActivity.this, MemoryBenchmarkActivity.class);
                // Pass memory benchmark result and any other necessary data using intent extras
                intent.putExtra("memoryTime", memoryTime);
                startActivity(intent);
            }
        });

        // Handle button clicks for Graphics Benchmark
        Button graphicsBenchmarkButton = findViewById(R.id.graphicsBenchmarkButton);
        graphicsBenchmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a graphics benchmark object
                GraphicsBenchmark graphicsBenchmark = new GraphicsBenchmark();

                // Create a Canvas object to draw graphics (replace with your canvas logic)
                Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                int width = 393;
                int height = 808;
                int numShapes = 5;

                // Run the graphics benchmark test
                long graphicsTime = graphicsBenchmark.runTest(canvas, width, height, numShapes);

                // Display graphics benchmark result
                graphicsResultTextView.setText("Graphics Benchmark Time: " + graphicsTime + " ms");

                // Start Graphics Benchmark Activity
                Intent intent = new Intent(MainActivity.this, GraphicsBenchmarkActivity.class);
                // Pass graphics benchmark result and any other necessary data using intent extras
                intent.putExtra("graphicsTime", graphicsTime);
                startActivity(intent);
            }
        });
    }
}

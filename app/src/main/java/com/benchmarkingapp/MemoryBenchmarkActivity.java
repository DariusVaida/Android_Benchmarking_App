package com.benchmarkingapp;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MemoryBenchmarkActivity extends AppCompatActivity {

    private TextView memoryResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_benchmark); // Use the appropriate layout XML

        // Initialize UI elements
        memoryResultTextView = findViewById(R.id.memoryBenchmarkResultTextView);

        // Retrieve memory benchmark result from intent extras
        Intent intent = getIntent();
        if (intent != null) {
            long memoryTime = intent.getLongExtra("memoryTime", 0); // 0 is the default value if not found
            displayMemoryBenchmarkResult(memoryTime);
        }
    }

    private void displayMemoryBenchmarkResult(long memoryTime) {
        // Display memory benchmark result in the TextView
        memoryResultTextView.setText("Memory Benchmark Time: " + memoryTime + " ms");
    }
}

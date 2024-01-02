package com.benchmarkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.benchmarkingapp.R;

public class GraphicsBenchmarkActivity extends AppCompatActivity {

    private TextView graphicsResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics_benchmark); // Use the appropriate layout XML

        // Initialize UI elements
        graphicsResultTextView = findViewById(R.id.graphicsBenchmarkResultTextView);

        // Retrieve graphics benchmark result from intent extras
        Intent intent = getIntent();
        if (intent != null) {
            long graphicsTime = intent.getLongExtra("graphicsTime", 0); // 0 is the default value if not found
            displayGraphicsBenchmarkResult(graphicsTime);
        }
    }

    private void displayGraphicsBenchmarkResult(long graphicsTime) {
        // Display graphics benchmark result in the TextView
        graphicsResultTextView.setText("Graphics Benchmark Time: " + graphicsTime + " ms");
    }
}

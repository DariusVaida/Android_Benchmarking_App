package com.benchmarkingapp;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.benchmarkingapp.R;

import java.io.File;

public class CpuBenchmarkActivity extends AppCompatActivity {

    private TextView cpuResultTextView;
    private TextView deviceInfoTextView;
    private long cpuTime = 0; // Initialize cpuTime to 0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu_benchmark);

        // Initialize UI elements
        cpuResultTextView = findViewById(R.id.cpuBenchmarkResultTextView);
        deviceInfoTextView = findViewById(R.id.deviceInfoTextView);

        // Retrieve CPU benchmark result from intent extras
        Intent intent = getIntent();
        if (intent != null) {
            cpuTime = intent.getLongExtra("cpuTime", 0); // Assign the value to cpuTime
            displayCpuBenchmarkResult(cpuTime);
        }

        // Retrieve and display device information
        displayDeviceInfo();

        // Add a button or trigger to launch the BenchmarkDetailsActivity
        Button showDetailsButton = findViewById(R.id.showDetailsButton);
        showDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to launch the BenchmarkDetailsActivity
                Intent intent = new Intent(CpuBenchmarkActivity.this, BenchmarkDetailsActivity.class);

                // Pass any necessary data to the BenchmarkDetailsActivity
                // For example, you can pass the CPU benchmark result as an extra
                intent.putExtra("cpuTime", cpuTime);

                // Start the BenchmarkDetailsActivity
                startActivity(intent);
            }
        });
    }

    private void displayCpuBenchmarkResult(long cpuTime) {
        // Display CPU benchmark result in the TextView
        cpuResultTextView.setText("CPU Benchmark Time: " + cpuTime + " ms");
    }

    private void displayDeviceInfo() {
        // Retrieve device information
        String deviceModel = Build.PRODUCT;
        String manufacturer = Build.MANUFACTURER;
        String androidVersion = Build.VERSION.RELEASE;

        // Get total RAM in GB
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        double totalRAMInGB = memoryInfo.totalMem / (1024.0 * 1024.0 * 1024.0);

        // Get total storage in GB
        File storage = Environment.getDataDirectory();
        StatFs statFs = new StatFs(storage.getPath());
        double totalStorageInGB = (double) statFs.getTotalBytes() / (1024.0 * 1024.0 * 1024.0);

        // Create a text string with device information
        String deviceInfoText = "Model: " + deviceModel +
                "\nManufacturer: " + manufacturer +
                "\nAndroid Version: " + androidVersion +
                "\nTotal RAM: " + String.format("%.2f", totalRAMInGB) + " GB" +
                "\nTotal Storage: " + String.format("%.2f", totalStorageInGB) + " GB";

        // Set the text in the TextView to display device information
        deviceInfoTextView.setText(deviceInfoText);
    }
}

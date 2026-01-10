package com.example.gpayapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class QRScanActivity extends AppCompatActivity {

    private DecoratedBarcodeView barcodeView;
    private CaptureManager captureManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);

        barcodeView = findViewById(R.id.barcodeScanner);

        captureManager = new CaptureManager(this, barcodeView);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();

        barcodeView.decodeContinuous(result -> {
            String scannedData = result.getText();

            Toast.makeText(QRScanActivity.this,
                    "Scanned: " + scannedData,
                    Toast.LENGTH_LONG).show();

            // Stop scanning after first scan
            barcodeView.pause();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }
}

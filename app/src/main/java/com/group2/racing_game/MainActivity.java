package com.group2.racing_game;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final Handler handler = new Handler();
    private final Random random = new Random();
    Button btnLogout, btnStart, btnReset;
    SeekBar seekBar1, seekBar2, seekBar3, seekBar4, seekBar5;
    private boolean raceRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RefElement();

        // Handle logout button
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Handle Start button
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!raceRunning) {
                    startRace();
                }
            }
        });

        // Handle Reset button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetRace();
            }
        });
    }

    protected void RefElement() {
        btnLogout = findViewById(R.id.btnLogout);
        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);

        seekBar1 = findViewById(R.id.seekBar1);
        seekBar2 = findViewById(R.id.seekBar2);
        seekBar3 = findViewById(R.id.seekBar3);
        seekBar4 = findViewById(R.id.seekBar4);
        seekBar5 = findViewById(R.id.seekBar5);
    }

    // Start the race
    private void startRace() {
        if (!raceRunning) {
            raceRunning = true;
            updateSeekBarProgress();
        }
    }

    // Reset the race
    private void resetRace() {
        seekBar1.setProgress(0);
        seekBar2.setProgress(0);
        seekBar3.setProgress(0);
        seekBar4.setProgress(0);
        seekBar5.setProgress(0);

        raceRunning = false;
    }

    private void updateSeekBarProgress() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (seekBar1.getProgress() < seekBar1.getMax()) {
                    seekBar1.setProgress(seekBar1.getProgress() + random.nextInt(5) + 1);
                }
                if (seekBar2.getProgress() < seekBar2.getMax()) {
                    seekBar2.setProgress(seekBar2.getProgress() + random.nextInt(5) + 1);
                }
                if (seekBar3.getProgress() < seekBar3.getMax()) {
                    seekBar3.setProgress(seekBar3.getProgress() + random.nextInt(5) + 1);
                }
                if (seekBar4.getProgress() < seekBar4.getMax()) {
                    seekBar4.setProgress(seekBar4.getProgress() + random.nextInt(5) + 1);
                }
                if (seekBar5.getProgress() < seekBar5.getMax()) {
                    seekBar5.setProgress(seekBar5.getProgress() + random.nextInt(5) + 1);
                }

                // Continue updating until all seekbars are complete
                if (seekBar1.getProgress() < seekBar1.getMax() || seekBar2.getProgress() < seekBar2.getMax() || seekBar3.getProgress() < seekBar3.getMax() || seekBar4.getProgress() < seekBar4.getMax() || seekBar5.getProgress() < seekBar5.getMax()) {

                    // Update every ms
                    handler.postDelayed(this, 100);
                } else {
                    // Stop the race
                    raceRunning = false;
                }
            }
        }, 100);
    }
}
package com.group2.racing_game;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.group2.racing_game.DAO.UserDAO;
import com.group2.racing_game.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final Handler handler = new Handler();
    private final Random random = new Random();
    Button btnLogout, btnStart, btnReset;
    SeekBar seekBar1, seekBar2, seekBar3, seekBar4, seekBar5;
    TextView totalMoney;
    ImageView coinImg;
    private boolean raceRunning = false;
    private UserDAO userDAO = UserDAO.getInstance();
    private List<String> winnerOrder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        User currentUser = UserDAO.getCurrentUser();
        if (currentUser == null) {
            finish();
        }
        RefElement();
        totalMoney.setText(String.valueOf(currentUser.getTotalCash()));

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
        totalMoney = findViewById(R.id.totalMoney);
        coinImg = findViewById(R.id.coinImg);
        coinImg.setImageResource(R.drawable.coin_icon);

        seekBar1.setEnabled(false);
        seekBar2.setEnabled(false);
        seekBar3.setEnabled(false);
        seekBar4.setEnabled(false);
        seekBar5.setEnabled(false);
    }

    // Start the race
    private void startRace() {
        if (!raceRunning) {
            btnStart.setEnabled(false);
            btnReset.setEnabled(false);
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

        btnStart.setEnabled(true);
        btnReset.setEnabled(false);
        winnerOrder.clear();
        raceRunning = false;
    }

    private void updateSeekBarProgress() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (seekBar1.getProgress() < seekBar1.getMax()) {
                    seekBar1.setProgress(seekBar1.getProgress() + random.nextInt(5) + 1);
                } else if (!winnerOrder.contains("SeekBar 1")) {
                    winnerOrder.add("SeekBar 1");
                }

                if (seekBar2.getProgress() < seekBar2.getMax()) {
                    seekBar2.setProgress(seekBar2.getProgress() + random.nextInt(5) + 1);
                } else if (!winnerOrder.contains("SeekBar 2")) {
                    winnerOrder.add("SeekBar 2");
                }

                if (seekBar3.getProgress() < seekBar3.getMax()) {
                    seekBar3.setProgress(seekBar3.getProgress() + random.nextInt(5) + 1);
                } else if (!winnerOrder.contains("SeekBar 3")) {
                    winnerOrder.add("SeekBar 3");
                }

                if (seekBar4.getProgress() < seekBar4.getMax()) {
                    seekBar4.setProgress(seekBar4.getProgress() + random.nextInt(5) + 1);
                } else if (!winnerOrder.contains("SeekBar 4")) {
                    winnerOrder.add("SeekBar 4");
                }

                if (seekBar5.getProgress() < seekBar5.getMax()) {
                    seekBar5.setProgress(seekBar5.getProgress() + random.nextInt(5) + 1);
                } else if (!winnerOrder.contains("SeekBar 5")) {
                    winnerOrder.add("SeekBar 5");
                }

                // Continue updating until all seekbars are complete
                if (seekBar1.getProgress() < seekBar1.getMax() || seekBar2.getProgress() < seekBar2.getMax() || seekBar3.getProgress() < seekBar3.getMax() || seekBar4.getProgress() < seekBar4.getMax() || seekBar5.getProgress() < seekBar5.getMax()) {
                    // Update every ms
                    handler.postDelayed(this, 100);
                } else {
                    // Stop the race and display the winner order
                    raceRunning = false;
                    btnReset.setEnabled(true);
                    displayWinnerOrder();
                }
            }
        }, 100);
    }

    private void displayWinnerOrder() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < winnerOrder.size(); i++) {
            result.append(i + 1).append(" place: ").append(winnerOrder.get(i)).append("\n");
        }

        // Send the result to ResultPageActivity
        Intent intent = new Intent(MainActivity.this, ResultPageActivity.class);
        intent.putExtra("raceResults", result.toString());
        startActivity(intent);
    }
}
package com.group2.racing_game;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.group2.racing_game.DAO.CarDAO;
import com.group2.racing_game.entity.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final Handler handler = new Handler();
    private final Random random = new Random();
    private List<SeekBar> seekBars = new ArrayList<>();
    private LinearLayout seekbarContainer;
    Button btnLogout, btnStart, btnReset;
    private boolean raceRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        seekbarContainer = findViewById(R.id.seekbarContainer);
        btnLogout = findViewById(R.id.btnLogout);
        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);

        // Add SeekBars from CarDAO
        addSeekBars();

        // Initially disable the Reset button
        btnReset.setEnabled(false);

        btnLogout.setOnClickListener(view -> finish());
        btnStart.setOnClickListener(view -> {
            if (!raceRunning) {
                startRace();
            }
        });
        btnReset.setOnClickListener(view -> resetRace());
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void addSeekBars() {
        List<Car> cars = CarDAO.getInstance().getCarList();
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Car car : cars) {
            // Inflate the custom layout
            View seekbarItem = inflater.inflate(R.layout.item_seekbar, seekbarContainer, false);

            // Find the views inside the custom layout
            CheckBox checkBox = seekbarItem.findViewById(R.id.checkBox);
            SeekBar seekBar = seekbarItem.findViewById(R.id.seekBar);
            EditText betAmount = seekbarItem.findViewById(R.id.editTextNumber);

            // Set the thumb drawable for the SeekBar
            seekBar.setThumb(getResources().getDrawable(car.getIcon(), null));

            // Disable the SeekBar
            seekBar.setEnabled(false);

            // Add the inflated view to the container
            seekbarContainer.addView(seekbarItem);

            // Add seekbar to the list
            seekBars.add(seekBar);
        }
    }


    // Start the race
    private void startRace() {
        if (!raceRunning) {
            raceRunning = true;

            btnStart.setEnabled(false);
            btnReset.setEnabled(false);

            updateSeekBarProgress();
        }
    }

    // Reset the race
    private void resetRace() {
        for (SeekBar seekBar : seekBars) {
            seekBar.setProgress(0);
        }
        raceRunning = false;

        btnReset.setEnabled(false);
        btnStart.setEnabled(true);
    }

    private void updateSeekBarProgress() {
        List<Car> cars = CarDAO.getInstance().getCarList();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean raceFinished = true;

                for (int i = 0; i < seekBars.size(); i++) {
                    SeekBar seekBar = seekBars.get(i);
                    Car car = cars.get(i);

                    if (seekBar.getProgress() < seekBar.getMax()) {
                        // Calculate a random progress
                        double randomSpeed = car.getMinSpeed() + (random.nextDouble() * (car.getMaxSpeed() - car.getMinSpeed()));

                        // Update SeekBar progress
                        int newProgress = seekBar.getProgress() + (int) Math.round(randomSpeed);
                        seekBar.setProgress(newProgress);

                        // Keep the race going
                        if (seekBar.getProgress() < seekBar.getMax()) {
                            raceFinished = false;
                        }
                    }
                }

                // Continue updating if the race hasn't finished
                if (!raceFinished) {
                    handler.postDelayed(this, 100);
                } else {
                    raceRunning = false; // Race finished

                    btnReset.setEnabled(true);
                }
            }
        }, 100);
    }
}

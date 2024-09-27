package com.group2.racing_game;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.group2.racing_game.DAO.CarDAO;
import com.group2.racing_game.DAO.UserDAO;
import com.group2.racing_game.entity.Car;
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
    EditText editTextNumber1, editTextNumber2, editTextNumber3, editTextNumber4, editTextNumber5;
    private boolean raceRunning = false;
    private UserDAO userDAO = UserDAO.getInstance();
    private List<Car> winnerOrder = new ArrayList<>();
    private int totalBetAmount = 0;
    private int totalIncrease = 0;
    private int totalDecrease = 0;
    private List<Car> carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        carList = CarDAO.getInstance().getCarList(); // Retrieve the list of cars

        User currentUser = UserDAO.getCurrentUser();
        if (currentUser == null) {
            finish();
        }

        RefElement();
        totalMoney.setText(String.valueOf(currentUser.getTotalCash()));

        // Handle logout button
        btnLogout.setOnClickListener(view -> finish());

        // Handle Start button
        btnStart.setOnClickListener(view -> {
            if (!raceRunning) {
                if (validateBets()) {
                    startRace();
                }
            }
        });

        // Handle Reset button
        btnReset.setOnClickListener(view -> resetRace());
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
        btnReset.setEnabled(false);

        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        editTextNumber3 = findViewById(R.id.editTextNumber3);
        editTextNumber4 = findViewById(R.id.editTextNumber4);
        editTextNumber5 = findViewById(R.id.editTextNumber5);

        seekBar1.setEnabled(false);
        seekBar2.setEnabled(false);
        seekBar3.setEnabled(false);
        seekBar4.setEnabled(false);
        seekBar5.setEnabled(false);
    }

    private boolean validateBets() {
        User currentUser = UserDAO.getCurrentUser();
        if (currentUser == null) return false;

        totalBetAmount = 0;
        totalIncrease = 0;
        totalDecrease = 0;

        // Check if any bet is placed
        boolean isBetPlaced = false;

        if (!editTextNumber1.getText().toString().isEmpty()) {
            int bet1 = Integer.parseInt(editTextNumber1.getText().toString());
            if (bet1 > 0) {
                totalBetAmount += bet1;
                isBetPlaced = true;
            }
        }

        if (!editTextNumber2.getText().toString().isEmpty()) {
            int bet2 = Integer.parseInt(editTextNumber2.getText().toString());
            if (bet2 > 0) {
                totalBetAmount += bet2;
                isBetPlaced = true;
            }
        }

        if (!editTextNumber3.getText().toString().isEmpty()) {
            int bet3 = Integer.parseInt(editTextNumber3.getText().toString());
            if (bet3 > 0) {
                totalBetAmount += bet3;
                isBetPlaced = true;
            }
        }

        if (!editTextNumber4.getText().toString().isEmpty()) {
            int bet4 = Integer.parseInt(editTextNumber4.getText().toString());
            if (bet4 > 0) {
                totalBetAmount += bet4;
                isBetPlaced = true;
            }
        }

        if (!editTextNumber5.getText().toString().isEmpty()) {
            int bet5 = Integer.parseInt(editTextNumber5.getText().toString());
            if (bet5 > 0) {
                totalBetAmount += bet5;
                isBetPlaced = true;
            }
        }

        // Check if at least one bet is placed
        if (!isBetPlaced) {
            showError("Please place a bet on at least one car.");
            return false;
        }

        // Check if the user has enough money
        if (totalBetAmount > currentUser.getTotalCash()) {
            showError("You don't have enough money to place these bets.");
            return false;
        }

        return true;
    }

    private void startRace() {
        if (!raceRunning) {
            btnStart.setEnabled(false);
            btnReset.setEnabled(false);
            raceRunning = true;
            updateSeekBarProgress();
        }
    }

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
                } else if (!winnerOrder.contains(carList.get(0))) {
                    winnerOrder.add(carList.get(0));
                }

                if (seekBar2.getProgress() < seekBar2.getMax()) {
                    seekBar2.setProgress(seekBar2.getProgress() + random.nextInt(5) + 1);
                } else if (!winnerOrder.contains(carList.get(1))) {
                    winnerOrder.add(carList.get(1));
                }

                if (seekBar3.getProgress() < seekBar3.getMax()) {
                    seekBar3.setProgress(seekBar3.getProgress() + random.nextInt(5) + 1);
                } else if (!winnerOrder.contains(carList.get(2))) {
                    winnerOrder.add(carList.get(2));
                }

                if (seekBar4.getProgress() < seekBar4.getMax()) {
                    seekBar4.setProgress(seekBar4.getProgress() + random.nextInt(5) + 1);
                } else if (!winnerOrder.contains(carList.get(3))) {
                    winnerOrder.add(carList.get(3));
                }

                if (seekBar5.getProgress() < seekBar5.getMax()) {
                    seekBar5.setProgress(seekBar5.getProgress() + random.nextInt(5) + 1);
                } else if (!winnerOrder.contains(carList.get(4))) {
                    winnerOrder.add(carList.get(4));
                }

                if (seekBar1.getProgress() < seekBar1.getMax() || seekBar2.getProgress() < seekBar2.getMax() || seekBar3.getProgress() < seekBar3.getMax() || seekBar4.getProgress() < seekBar4.getMax() || seekBar5.getProgress() < seekBar5.getMax()) {
                    handler.postDelayed(this, 100);
                } else {
                    raceRunning = false;
                    btnReset.setEnabled(true);
                    calculateWinnings();
                }
            }
        }, 100);
    }

    private void calculateWinnings() {
        User currentUser = UserDAO.getCurrentUser();
        carList = CarDAO.getInstance().getCarList();
        if (currentUser == null) return;

        // Deduct the total bet amount from the user's total cash
        currentUser.setTotalCash(currentUser.getTotalCash() - totalBetAmount);

        // Calculate the winnings for each car based on the bet and the race result
        if (winnerOrder.size() > 0 && winnerOrder.get(0).getName().equals(carList.get(0).getName()) && !editTextNumber1.getText().toString().isEmpty()) {
            int bet1 = Integer.parseInt(editTextNumber1.getText().toString());
            totalIncrease += bet1 * carList.get(0).getRate();
        } else if (!editTextNumber1.getText().toString().isEmpty()) {
            int bet1 = Integer.parseInt(editTextNumber1.getText().toString());
            totalDecrease += bet1;
        }

        if (winnerOrder.size() > 0 && winnerOrder.get(0).getName().equals(carList.get(1).getName()) && !editTextNumber2.getText().toString().isEmpty()) {
            int bet2 = Integer.parseInt(editTextNumber2.getText().toString());
            totalIncrease += bet2 * carList.get(1).getRate();
        } else if (!editTextNumber2.getText().toString().isEmpty()) {
            int bet2 = Integer.parseInt(editTextNumber2.getText().toString());
            totalDecrease += bet2;
        }

        if (winnerOrder.size() > 0 && winnerOrder.get(0).getName().equals(carList.get(2).getName()) && !editTextNumber3.getText().toString().isEmpty()) {
            int bet3 = Integer.parseInt(editTextNumber3.getText().toString());
            totalIncrease += bet3 * carList.get(2).getRate();
        } else if (!editTextNumber3.getText().toString().isEmpty()) {
            int bet3 = Integer.parseInt(editTextNumber3.getText().toString());
            totalDecrease += bet3;
        }

        if (winnerOrder.size() > 0 && winnerOrder.get(0).getName().equals(carList.get(3).getName()) && !editTextNumber4.getText().toString().isEmpty()) {
            int bet4 = Integer.parseInt(editTextNumber4.getText().toString());
            totalIncrease += bet4 * carList.get(3).getRate();
        } else if (!editTextNumber4.getText().toString().isEmpty()) {
            int bet4 = Integer.parseInt(editTextNumber4.getText().toString());
            totalDecrease += bet4;
        }

        if (winnerOrder.size() > 0 && winnerOrder.get(0).getName().equals(carList.get(4).getName()) && !editTextNumber5.getText().toString().isEmpty()) {
            int bet5 = Integer.parseInt(editTextNumber5.getText().toString());
            totalIncrease += bet5 * carList.get(4).getRate();
        } else if (!editTextNumber5.getText().toString().isEmpty()) {
            int bet5 = Integer.parseInt(editTextNumber5.getText().toString());
            totalDecrease += bet5;
        }

        // Recalculate total money: increase the winnings and update the user's total cash
        double newTotalMoney = currentUser.getTotalCash() + totalIncrease;
        currentUser.setTotalCash(newTotalMoney);

        // Update the total money on screen
        totalMoney.setText(String.valueOf(currentUser.getTotalCash()));

        // Send the result to ResultPageActivity
        Intent intent = new Intent(MainActivity.this, ResultPageActivity.class);
        ArrayList<Car> raceResults = new ArrayList<>(winnerOrder);
        intent.putExtra("raceResults", raceResults);
        intent.putExtra("totalIncrease", totalIncrease);
        intent.putExtra("totalDecrease", totalDecrease);
        startActivity(intent);
    }


    private void showError(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}

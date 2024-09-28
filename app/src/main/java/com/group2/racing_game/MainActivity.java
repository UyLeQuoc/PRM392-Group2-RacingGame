package com.group2.racing_game;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.group2.racing_game.DAO.CarDAO;
import com.group2.racing_game.DAO.RoundDAO;
import com.group2.racing_game.DAO.UserDAO;
import com.group2.racing_game.DTO.BetDTO;
import com.group2.racing_game.entity.Car;
import com.group2.racing_game.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final Handler handler = new Handler();
    private final Random random = new Random();
    Button btnLogout, btnStart, btnReset, btnProfile;
    SeekBar seekBar1, seekBar2, seekBar3, seekBar4, seekBar5;
    TextView totalMoney;
    ImageView coinImg;
    EditText editTextNumber1, editTextNumber2, editTextNumber3, editTextNumber4, editTextNumber5;
    ImageButton btnDeposit;
    ImageButton btnShowRules;
    private List<SeekBar> seekBars = new ArrayList<>();
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
        btnLogout = findViewById(R.id.btnLogout);
        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);
        btnProfile = findViewById(R.id.btnProfile);
        btnShowRules = findViewById(R.id.btn_show_rules);
        btnDeposit = findViewById(R.id.btn_deposit);

        // Add SeekBars from CarDAO

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

        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DepositPageActivity.class);
                startActivity(intent);
                finish();
            }
        });
        // Set sự kiện click cho button
        btnShowRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang RulePageActivity
                Intent intent = new Intent(MainActivity.this, RulePageActivity.class);
                startActivity(intent);
            }
        });
        //Handle profile button
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfilePageActivity.class);
                startActivity(intent);
                finish();
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

        seekBar1.setProgressDrawable(null);
        seekBar2.setProgressDrawable(null);
        seekBar3.setProgressDrawable(null);
        seekBar4.setProgressDrawable(null);
        seekBar5.setProgressDrawable(null);

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
                updateSeekBarProgress(seekBar1, carList.get(0));
                updateSeekBarProgress(seekBar2, carList.get(1));
                updateSeekBarProgress(seekBar3, carList.get(2));
                updateSeekBarProgress(seekBar4, carList.get(3));
                updateSeekBarProgress(seekBar5, carList.get(4));

                // Check if any seek bar has not reached its max
                if (seekBar1.getProgress() < seekBar1.getMax() ||
                        seekBar2.getProgress() < seekBar2.getMax() ||
                        seekBar3.getProgress() < seekBar3.getMax() ||
                        seekBar4.getProgress() < seekBar4.getMax() ||
                        seekBar5.getProgress() < seekBar5.getMax()) {
                    handler.postDelayed(this, 100);
                } else {
                    raceRunning = false;
                    btnReset.setEnabled(true);
                    calculateWinnings();
                }
            }

            private void updateSeekBarProgress(SeekBar seekBar, Car car) {
                if (seekBar.getProgress() < seekBar.getMax()) {
                    seekBar.setProgress(seekBar.getProgress() + car.getRandomProgressIncrement());
                } else if (!winnerOrder.contains(car)) {
                    winnerOrder.add(car);
                }
            }
        }, 100);
    }

    private void calculateWinnings() {
        User currentUser = UserDAO.getCurrentUser();
        carList = CarDAO.getInstance().getCarList();
        if (currentUser == null) return;

        List<BetDTO> betList = new ArrayList<>();

        // Deduct the total bet amount from the user's total cash
        currentUser.setTotalCash(currentUser.getTotalCash() - totalBetAmount);

        // Calculate the winnings for each car based on the bet and the race result
        if (!editTextNumber1.getText().toString().isEmpty()) {
            int bet1 = Integer.parseInt(editTextNumber1.getText().toString());
            if (bet1 > 0) {
                BetDTO betDTO = new BetDTO(carList.get(0), bet1);
                betList.add(betDTO);  // Add to the bet list
                if (winnerOrder.size() > 0 && winnerOrder.get(0).getName().equals(carList.get(0).getName())) {
                    totalIncrease += bet1 * carList.get(0).getRate();
                } else {
                    totalDecrease += bet1;
                }
            }
        }

        if (!editTextNumber2.getText().toString().isEmpty()) {
            int bet2 = Integer.parseInt(editTextNumber2.getText().toString());
            if (bet2 > 0) {
                BetDTO betDTO = new BetDTO(carList.get(1), bet2);
                betList.add(betDTO);
                if (winnerOrder.size() > 0 && winnerOrder.get(0).getName().equals(carList.get(1).getName())) {
                    totalIncrease += bet2 * carList.get(1).getRate();
                } else {
                    totalDecrease += bet2;
                }
            }
        }

        if (!editTextNumber3.getText().toString().isEmpty()) {
            int bet3 = Integer.parseInt(editTextNumber3.getText().toString());
            if (bet3 > 0) {
                BetDTO betDTO = new BetDTO(carList.get(2), bet3);
                betList.add(betDTO);
                if (winnerOrder.size() > 0 && winnerOrder.get(0).getName().equals(carList.get(2).getName())) {
                    totalIncrease += bet3 * carList.get(2).getRate();
                } else {
                    totalDecrease += bet3;
                }
            }
        }

        if (!editTextNumber4.getText().toString().isEmpty()) {
            int bet4 = Integer.parseInt(editTextNumber4.getText().toString());
            if (bet4 > 0) {
                BetDTO betDTO = new BetDTO(carList.get(3), bet4);
                betList.add(betDTO);
                if (winnerOrder.size() > 0 && winnerOrder.get(0).getName().equals(carList.get(3).getName())) {
                    totalIncrease += bet4 * carList.get(3).getRate();
                } else {
                    totalDecrease += bet4;
                }
            }
        }

        if (!editTextNumber5.getText().toString().isEmpty()) {
            int bet5 = Integer.parseInt(editTextNumber5.getText().toString());
            if (bet5 > 0) {
                BetDTO betDTO = new BetDTO(carList.get(4), bet5);
                betList.add(betDTO);
                if (winnerOrder.size() > 0 && winnerOrder.get(0).getName().equals(carList.get(4).getName())) {
                    totalIncrease += bet5 * carList.get(4).getRate();
                } else {
                    totalDecrease += bet5;
                }
            }
        }


        // Recalculate total money: increase the winnings and update the user's total cash
        double newTotalMoney = currentUser.getTotalCash() + totalIncrease;
        currentUser.setTotalCash(newTotalMoney);

        // Update the total money on screen
        totalMoney.setText(String.valueOf(currentUser.getTotalCash()));

        Car winningCar = winnerOrder.size() > 0 ? winnerOrder.get(0) : null;
        if (winningCar != null) {
            RoundDAO.getInstance().DoneBetting(currentUser, betList, winningCar);
        }


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

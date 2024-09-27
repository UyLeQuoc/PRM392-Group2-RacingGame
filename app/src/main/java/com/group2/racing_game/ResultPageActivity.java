package com.group2.racing_game;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group2.racing_game.DAO.UserDAO;
import com.group2.racing_game.entity.Car;
import com.group2.racing_game.entity.User;

import java.util.ArrayList;

public class ResultPageActivity extends AppCompatActivity {

    TextView top1Txt, top2Txt, top3Txt, top4Txt, top5Txt;
    TextView totalIncreaseTxt, totalDecreaseTxt, moneyLeftTxt;
    Button btnBackToMain;
    UserDAO userDAO = UserDAO.getInstance();
    User user = userDAO.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        top1Txt = findViewById(R.id.top1Txt);
        top2Txt = findViewById(R.id.top2Txt);
        top3Txt = findViewById(R.id.top3Txt);
        top4Txt = findViewById(R.id.top4Txt);
        top5Txt = findViewById(R.id.top5Txt);

        totalIncreaseTxt = findViewById(R.id.totalIncreaseTxt);
        totalDecreaseTxt = findViewById(R.id.totalDecreaseTxt);
        moneyLeftTxt = findViewById(R.id.moneyLeftTxt);

        btnBackToMain = findViewById(R.id.btnBackToMain);

        ArrayList<Car> raceResults = (ArrayList<Car>) getIntent().getSerializableExtra("raceResults");
        int totalIncrease = getIntent().getIntExtra("totalIncrease", 0);
        int totalDecrease = getIntent().getIntExtra("totalDecrease", 0);
        double moneyLeft = user.getTotalCash();

        if (raceResults != null) {
            if (raceResults.size() > 0) top1Txt.setText(raceResults.get(0).getName());
            if (raceResults.size() > 1) top2Txt.setText(raceResults.get(1).getName());
            if (raceResults.size() > 2) top3Txt.setText(raceResults.get(2).getName());
            if (raceResults.size() > 3) top4Txt.setText(raceResults.get(3).getName());
            if (raceResults.size() > 4) top5Txt.setText(raceResults.get(4).getName());
        }

        totalIncreaseTxt.setText("Increase: " + totalIncrease);
        totalDecreaseTxt.setText("Decrease: " + totalDecrease);
        moneyLeftTxt.setText("Money Left: " + moneyLeft);
2
        btnBackToMain.setOnClickListener(v -> finish());
    }
}

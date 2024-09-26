package com.group2.racing_game;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultPageActivity extends AppCompatActivity {

    TextView top1Txt, top2Txt, top3Txt, top4Txt, top5Txt;
    Button btnBackToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        // Reference the TextViews
        top1Txt = findViewById(R.id.top1Txt);
        top2Txt = findViewById(R.id.top2Txt);
        top3Txt = findViewById(R.id.top3Txt);
        top4Txt = findViewById(R.id.top4Txt);
        top5Txt = findViewById(R.id.top5Txt);
        btnBackToMain = findViewById(R.id.btnBackToMain);

        // Get the race results passed from MainActivity
        String raceResults = getIntent().getStringExtra("raceResults");

        // Split the result string into an array to extract the top places
        if (raceResults != null) {
            String[] resultArray = raceResults.split("\n");

            // Assign values to TextViews for top 1-5 or handle fewer participants
            if (resultArray.length > 0) top1Txt.setText(resultArray[0].replace(" place:", ""));
            if (resultArray.length > 1) top2Txt.setText(resultArray[1].replace(" place:", ""));
            if (resultArray.length > 2) top3Txt.setText(resultArray[2].replace(" place:", ""));
            if (resultArray.length > 3) top4Txt.setText(resultArray[3].replace(" place:", ""));
            if (resultArray.length > 4) top5Txt.setText(resultArray[4].replace(" place:", ""));
        }

        // Handle the "Back to Main" button
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close this activity and go back to MainActivity
                finish();
            }
        });
    }
}



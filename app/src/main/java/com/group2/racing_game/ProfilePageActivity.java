package com.group2.racing_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group2.racing_game.DAO.BetHistoryDAO;
import com.group2.racing_game.DAO.UserDAO;
import com.group2.racing_game.DTO.BetHistoryAdapter;
import com.group2.racing_game.entity.BetHistory;
import com.group2.racing_game.entity.Car;
import com.group2.racing_game.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ProfilePageActivity extends AppCompatActivity {
    UserDAO userDAO = UserDAO.getInstance();
    List<BetHistory> itemList = new ArrayList<>();
    BetHistoryDAO historyDAO = BetHistoryDAO.getInstance();
    ListView historyListView;
    EditText txtUsername, txtPassword, txtTotalCash;
    Button btnUpdate, btnBack;
    BetHistoryAdapter betHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        // Tham chiếu đến các phần tử giao diện
        RefElement();

        // Đổ dữ liệu của currentUser vào EditText khi mở trang Profile
        User currentUser = userDAO.getCurrentUser();
        if (currentUser != null) {
            txtUsername.setText(currentUser.getUsername());
            txtPassword.setText(currentUser.getPassword());
            txtTotalCash.setText(String.valueOf(currentUser.getTotalCash()));
        }

        itemList = historyDAO.getBetHistoriesByUserId(currentUser.getId());
        if(!itemList.isEmpty()){
            betHistoryAdapter = new BetHistoryAdapter(this,R.layout.history_layout ,itemList);
            historyListView.setAdapter(betHistoryAdapter);
        }

        // Xử lý sự kiện khi nhấn nút "Update"
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });

        //Handle profile button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilePageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    protected void RefElement() {
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtTotalCash = findViewById(R.id.txtTotalCash);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnBack = findViewById(R.id.btnBack);
        historyListView = findViewById(R.id.historyListView);
    }

    // Phương thức để cập nhật thông tin người dùng
    protected void updateProfile() {
        String newUsername = txtUsername.getText().toString().trim();
        String newPassword = txtPassword.getText().toString().trim();
        String totalCashStr = txtTotalCash.getText().toString().trim();

        if (newUsername.isEmpty() || newPassword.isEmpty() || totalCashStr.isEmpty()) {
            Toast.makeText(ProfilePageActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double newTotalCash;
        try {
            newTotalCash = Double.parseDouble(totalCashStr);
        } catch (NumberFormatException e) {
            Toast.makeText(ProfilePageActivity.this, "Invalid total cash amount", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cập nhật thông tin user trong UserDAO
        User currentUser = userDAO.getCurrentUser();
        if (currentUser != null) {
            currentUser.setUsername(newUsername);
            currentUser.setPassword(newPassword);
            currentUser.setTotalCash(newTotalCash);

            Toast.makeText(ProfilePageActivity.this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ProfilePageActivity.this, "No user logged in!", Toast.LENGTH_SHORT).show();
        }
    }
}

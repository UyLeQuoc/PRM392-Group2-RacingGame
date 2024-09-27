package com.group2.racing_game;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class RulePageActivity extends AppCompatActivity {
    Button buttonBackToMain;
    TextView tvRules;
    TableLayout tblVehicleInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_page);  // Gắn layout cho activity
        buttonBackToMain = findViewById(R.id.button_back_to_main);
        buttonBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RulePageActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Kết thúc activity hiện tại nếu không muốn quay lại
            }
        });

        // Mảng chứa thông tin xe
        String[][] vehicleInfo = {
                {"Xe lăn", "1.37", "1.5", "4"},
                {"Xe đạp", "0.35", "1.75", "2.5"},
                {"Mô tô", "-0.15", "2.25", "2"},
                {"Lamborghini", "-0.05", "2.5", "1.5"},
                {"F1", "-0.35", "2.75", "1"}
        };

        // Gán TableLayout
        tblVehicleInfo = findViewById(R.id.tbl_vehicle_info);

        // Thêm tiêu đề cho bảng
        TableRow headerRow = new TableRow(this);
        String[] headers = {"Car", "MinSpeed", "MaxSpeed", "Rate"};
        for (String header : headers) {
            TextView tvHeader = new TextView(this);
            tvHeader.setText(header);
            tvHeader.setPadding(10, 10, 10, 10);
            headerRow.addView(tvHeader);
            tvHeader.setTextColor(Color.WHITE);
            tvHeader.setTextSize(18); // Thiết lập kích thước văn bản là 18sp

        }
        tblVehicleInfo.addView(headerRow);

        // Thêm các dòng dữ liệu vào bảng
        for (String[] vehicle : vehicleInfo) {
            TableRow row = new TableRow(this);
            for (String data : vehicle) {
                TextView tvData = new TextView(this);
                tvData.setText(data);
                tvData.setPadding(10, 10, 10, 10);
                row.addView(tvData);
                tvData.setTextColor(Color.WHITE);
                tvData.setTextSize(18);
            }
            tblVehicleInfo.addView(row);
        }

        // Mảng chứa các luật
        String[] rules = {
                "Trước khi cuộc đua bắt đầu, người chơi sẽ được lựa chọn giữa nhiều chiếc xe để đặt cược.",
                "Người chơi sẽ đặt cược một số tiền ảo vào chiếc xe mà họ cho rằng sẽ về đích đầu tiên.",
                "Người chơi sẽ thắng cược nếu chiếc xe mà họ chọn về đích đầu tiên.",
                "Cuộc đua sẽ diễn ra tự động, người chơi không có khả năng can thiệp vào kết quả cuộc đua sau khi đã đặt cược.",
                "Sau khi thời gian đặt cược kết thúc, không ai có thể thay đổi lựa chọn của mình và cuộc đua sẽ diễn ra.",
                "Cuộc đua kết thúc khi tất cả các xe đã về đích hoặc khi xe chiến thắng được xác định.",
                "Số tiền sẽ được cộng vào tài khoản của người chơi thắng cược, và những người thua sẽ mất số tiền đã cược.",

        };

        // Gán TextView
        tvRules = findViewById(R.id.tv_rules);

        // Tạo chuỗi định dạng
        StringBuilder formattedRules = new StringBuilder();
        for (int i = 0; i < rules.length; i++) {
            formattedRules.append(i + 1).append(". ").append(rules[i]).append("\n"); // Thêm số thứ tự và xuống dòng
        }

        // Gán chuỗi đã định dạng cho TextView
        tvRules.setText(formattedRules.toString());
    }
}

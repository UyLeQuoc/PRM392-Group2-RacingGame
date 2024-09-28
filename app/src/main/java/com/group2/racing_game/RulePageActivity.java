package com.group2.racing_game;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RulePageActivity extends AppCompatActivity {
    ImageButton buttonBackToMain;
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

        // Mảng chứa ID hình ảnh xe
        int[] vehicleImages = {
                R.drawable.xe_lan,
                R.drawable.xe_dap,
                R.drawable.mo_to,
                R.drawable.lamborghini,
                R.drawable.f1
        };

        // Mảng chứa thông tin xe
        String[][] vehicleInfo = {
                {"Xe lăn", "0", "14", "10"},
                {"Xe đạp", "5", "10", "2.5"},
                {"Mô tô", "7", "8", "2.5"},
                {"Lamborghini", "1", "15", "1.5"},
                {"F1", "5", "11", "1.5"}
        };

        // Gán TableLayout
        tblVehicleInfo = findViewById(R.id.tbl_vehicle_info);

        // Thêm tiêu đề cho bảng
        TableRow headerRow = new TableRow(this);
        String[] headers = {"Icon", "Car", "Min", "Max", "Rate"};
        for (String header : headers) {
            TextView tvHeader = new TextView(this);
            tvHeader.setText(header);
            tvHeader.setPadding(0, 10, 80, 10);
            tvHeader.setTextColor(Color.WHITE);
            tvHeader.setTextSize(18); // Thiết lập kích thước văn bản là 18sp
            headerRow.addView(tvHeader);
        }
        tblVehicleInfo.addView(headerRow);

        // Thêm các dòng dữ liệu vào bảng
        for (int i = 0; i < vehicleInfo.length; i++) {
            TableRow row = new TableRow(this);

            // Thêm hình ảnh vào hàng
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(vehicleImages[i]);
            imageView.setPadding(-40, 10, 10, 10);
            row.addView(imageView);

            // Thêm thông tin xe vào hàng
            for (String data : vehicleInfo[i]) {
                TextView tvData = new TextView(this);
                tvData.setText(data);
                tvData.setPadding(0, 52, 80, 10);
                tvData.setTextColor(Color.WHITE);
                tvData.setTextSize(18);
                row.addView(tvData);
            }
            tblVehicleInfo.addView(row);
        }

        // Mảng chứa các luật
        String[] rules = {
                "Trước khi cuộc đua bắt đầu, người chơi sẽ được lựa chọn giữa nhiều chiếc xe để đặt cược.\n",
                "Người chơi sẽ đặt cược một số tiền ảo vào chiếc xe mà họ cho rằng sẽ về đích đầu tiên.\n",
                "Người chơi sẽ thắng cược nếu chiếc xe mà họ chọn về đích đầu tiên.\n",
                "Cuộc đua sẽ diễn ra tự động, người chơi không có khả năng can thiệp vào kết quả cuộc đua sau khi đã đặt cược.\n",
                "Sau khi thời gian đặt cược kết thúc, không ai có thể thay đổi lựa chọn của mình và cuộc đua sẽ diễn ra.\n",
                "Cuộc đua kết thúc khi tất cả các xe đã về đích hoặc khi xe chiến thắng được xác định.\n",
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

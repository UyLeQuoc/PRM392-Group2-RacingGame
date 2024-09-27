package com.group2.racing_game;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class RulePageActivity extends AppCompatActivity {
    Button buttonBackToMain;
    TextView tvRules;
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
        // Mảng chứa các luật
        String[] rules = {
                "Trước khi cuộc đua bắt đầu, người chơi sẽ được lựa chọn giữa nhiều chiếc xe để đặt cược.",
                "Người chơi sẽ đặt cược một số tiền ảo vào chiếc xe mà họ cho rằng sẽ về đích đầu tiên.",
                "Người chơi sẽ thắng cược nếu chiếc xe mà họ chọn về đích đầu tiên.",
                "Cuộc đua sẽ diễn ra tự động, người chơi không có khả năng can thiệp vào kết quả cuộc đua sau khi đã đặt cược.",
                "Sau khi thời gian đặt cược kết thúc, không ai có thể thay đổi lựa chọn của mình và cuộc đua sẽ diễn ra.",
                "Cuộc đua kết thúc khi tất cả các xe đã về đích hoặc khi xe chiến thắng được xác định.",
                "Số tiền sẽ được cộng vào tài khoản của người chơi thắng cược, và những người thua sẽ mất số tiền đã cược.",
                "Thông số xe:\n" +
                        "Xe lăn: minSpeed: 3, maxSpeed: 9, Rate: 1.2\n" +
                        "Xe đạp: minSpeed: 1, maxSpeed: 5, Rate: 0.2\n" +
                        "Mô tô: minSpeed: 1, maxSpeed: 5, Rate: 0.2\n" +
                        "Lambor: minSpeed: 1, maxSpeed: 5, Rate: 0.2\n" +
                        "F1: minSpeed: 1, maxSpeed: 5, Rate: 0.2"
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
package com.group2.racing_game;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.group2.racing_game.DAO.DepositInforDAO;
import com.group2.racing_game.DAO.UserDAO;
import com.group2.racing_game.DTO.DepositAdapter;
import com.group2.racing_game.entity.DepositInfor;
import com.group2.racing_game.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DepositPageActivity extends AppCompatActivity {

    TextView tvAccount;
    Button btnCancel, btnSubmit;
    ListView listView;
    EditText etInput;
    ArrayList<DepositInfor> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deposit_page);

        tvAccount = (TextView) findViewById(R.id.tvUsername);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        listView = (ListView) findViewById(R.id.lvDepositHistory);
        etInput = (EditText) findViewById(R.id.editTextInput);
        list = DepositInforDAO.GetDeposits();
        tvAccount.setText("Account:"+ UserDAO.getCurrentUser().getUsername() + " - $ " + UserDAO.getCurrentUser().getTotalCash());
        DepositAdapter adapter = new DepositAdapter(this, R.layout.deposit_infor_layout,list);
        listView.setAdapter(adapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etInput.getText().toString().isEmpty() || Double.parseDouble(etInput.getText().toString())<10 || Double.parseDouble(etInput.getText().toString())>10000000)
                    Toast.makeText(DepositPageActivity.this, "Please input a valid value! From 10 to 10000000",Toast.LENGTH_SHORT).show();
                else{
                    User tmp = UserDAO.getCurrentUser();
                    if (tmp.getTotalCash()+Double.parseDouble(etInput.getText().toString())>=1000000000){
                        Toast.makeText(DepositPageActivity.this, "Balance exceed max amount! Abort Depositing!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    tmp.setTotalCash(tmp.getTotalCash()+Double.parseDouble(etInput.getText().toString()));
                    DepositInfor infor = new DepositInfor(UserDAO.getCurrentUser().getId(),Double.parseDouble(etInput.getText().toString()), LocalDateTime.now());
                    DepositInforDAO.Add(infor);
                    list.add(infor);
                    adapter.notifyDataSetChanged();
                    tvAccount.setText("Account:"+ UserDAO.getCurrentUser().getUsername() + " - $ " + UserDAO.getCurrentUser().getTotalCash());
                    etInput.setText("");
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DepositPageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
package com.group2.racing_game;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.group2.racing_game.DAO.UserDAO;
import com.group2.racing_game.entity.User;

import com.bumptech.glide.Glide;

public class LoginPageActivity extends AppCompatActivity {
    Button btnLogin;
    EditText txtUsername, txtPassword;
    UserDAO userDAO = UserDAO.getInstance();
    ImageView gifBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);

        RefElement();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.doodle);



        Glide.with(this)
                .asGif()
                .load(R.drawable.game_background)
                .into(gifBackground);

        mediaPlayer.start();

    }

    protected void RefElement(){
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        gifBackground = (ImageView)findViewById(R.id.gifBackground);
    }

    // Function to handle login
    protected void performLogin() {
        String username = txtUsername.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginPageActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = userDAO.Login(username, password);

        if (user != null) {
            // Credentials are correct, navigate to MainActivity
            Intent myIntent = new Intent(LoginPageActivity.this, MainActivity.class);
            startActivity(myIntent);
        } else {
            // Invalid credentials, show error message
            Toast.makeText(LoginPageActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}

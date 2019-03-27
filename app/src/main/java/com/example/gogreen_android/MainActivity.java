package com.example.gogreen_android;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import models.User;
import static com.example.gogreen_android.requests.UserRequests.*;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edtUsername;
    private EditText edtPassword;
    private TextView messageLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnLogin = (Button) findViewById(R.id.loginButton);
        edtUsername = (EditText) findViewById(R.id.usernameInput);
        edtPassword = (EditText) findViewById(R.id.passField);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                //call login method from loginController class
                try {
                    login(username, password);
                } catch (IOException e){
                    messageLogin.setText("IO Error occured");
                };
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void login(String username, String password) throws IOException {

        messageLogin = (TextView) findViewById(R.id.messageLogin);
        messageLogin.setText("");
//        String username = nameLogin.getText();
//        String password = pwLogin.getText();
//        StatisticsController.setUserName(username); //(not sure if needed?)
        if (password.equals("") || username.equals("")) {
            messageLogin.setVisibility(View.VISIBLE);
            messageLogin.setText("Fields cannot be empty");
            messageLogin.setTextColor(Color.RED);
            return;
        }
        //send authentication to server
        User user = loginPostRequest(username, password);
        //authenticating

        if (user.isSuccess()) {
            //creating new Parent to change scenes
            //Parent root = FXMLLoader.load(getClass().getResource("/Statistics.fxml"));
            //change scene
            //ClientMain.setScene(root);
            messageLogin.setVisibility(View.VISIBLE);
            messageLogin.setText("Login Success!");
            messageLogin.setTextColor(Color.GREEN);


        } else {
            messageLogin.setVisibility(View.VISIBLE);
            messageLogin.setText("Username or password invalid");
            messageLogin.setTextColor(Color.RED);
            return;
        }
    }
}

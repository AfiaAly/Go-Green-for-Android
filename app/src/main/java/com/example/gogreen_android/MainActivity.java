package com.example.gogreen_android;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gogreen_android.controllers.StatisticsController;
import com.example.gogreen_android.models.User;
import com.example.gogreen_android.requests.UserRequests;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private User user;
    private Button btnLogin;
    private EditText edtUsername;
    private EditText edtPassword;
    private TextView messageLogin;


    protected void onSuccess(){
        Intent intent = new Intent(this, MyScore.class);
        System.out.println("onSuccess() method reads username as:" + edtUsername.getText().toString());
        intent.putExtra("username", edtUsername.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Login to GoGreen");
        setSupportActionBar(toolbar);

        btnLogin = (Button) findViewById(R.id.loginButton);
        edtUsername = (EditText) findViewById(R.id.usernameInput);
        edtPassword = (EditText) findViewById(R.id.passField);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = edtUsername.getText().toString();
                System.out.println(username);
                String password = edtPassword.getText().toString();
                System.out.println(password);;

                //call login method
                try {
                    AsyncTaskConnection taskConnection = new AsyncTaskConnection();
                    ArrayList array = new ArrayList(2);
                    array.add(0, username);
                    array.add(1, password);
                    taskConnection.execute(array);
                } catch (Exception e) {
                    messageLogin = (TextView) findViewById(R.id.messageLogin);
                    messageLogin.setText("IO Error occured");
                }
                ;
            }
        });
    }
    class AsyncTaskConnection extends AsyncTask<ArrayList, Void, User> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            messageLogin = (TextView) findViewById(R.id.messageLogin);
            messageLogin.setText("");

            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            StatisticsController.setUserName(username);
            if (password.equals("") || username.equals("")) {
                messageLogin.setVisibility(View.VISIBLE);
                messageLogin.setText("Fields cannot be empty");
                messageLogin.setTextColor(Color.RED);
                return;
            }
        }

        @Override
        protected User doInBackground(ArrayList... arrayLists){
            String username = arrayLists[0].get(0).toString();
            String password = arrayLists[0].get(1).toString();
            user = new User(username, password, false);
            System.out.println(user + "at doInBackground() in MainActivity.java");
            try {
                user = login(username, password);
                return user;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e){
                e.printStackTrace();
                System.out.println("Null Pointer exception...");
            }
            return user;
        }


        protected void onProgressUpdate(String... text){
            messageLogin.setVisibility(View.VISIBLE);
            messageLogin.setText("Connecting...");
            messageLogin.setTextColor(Color.BLUE);
        }

        @Override
        protected void onPostExecute(User user){
            super.onPostExecute(user);
            if (user != null) {
                if (user.isSuccess()) {
                    messageLogin.setVisibility(View.VISIBLE);
                    messageLogin.setText("Login Success!");
                    messageLogin.setTextColor(Color.GREEN);
                    //                init(user.getUsername());
                    System.out.println("After login succes, user is:" + user.getUsername() + " & pass is:" + user.getPassword());
                    onSuccess();

                } else if (!user.isSuccess()) {
                    messageLogin.setVisibility(View.VISIBLE);
                    messageLogin.setText("Login Failed");
                    messageLogin.setTextColor(Color.RED);
                    return;

                } else {
                    messageLogin.setVisibility(View.VISIBLE);
                    messageLogin.setText("Something else went wrong");
                    messageLogin.setTextColor(Color.RED);
                }
            } else {
                System.out.println("User passed to onPostExecute is null");
            }
        }
    }

    public User login(String username, String password) throws IOException {

        //Calls Login method, which has actual connection code
        try {
            user = (User) UserRequests.loginRequestConnection(username, password); //...............................................
            System.out.println(user + "at login()");
            return user;
        } catch (IOException e) {
            System.out.println("IOException caught in login method");;
        }
        return user;
    }
}

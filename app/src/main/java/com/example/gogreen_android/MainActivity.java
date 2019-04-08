package com.example.gogreen_android;

import android.graphics.Color;
import android.os.AsyncTask;
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
import java.util.ArrayList;

import models.User;

import static com.example.gogreen_android.requests.UserRequests.loginPostRequest;

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
        toolbar.setTitle("Login to GoGreen");
        setSupportActionBar(toolbar);

        btnLogin = (Button) findViewById(R.id.loginButton);
        edtUsername = (EditText) findViewById(R.id.usernameInput);
        edtPassword = (EditText) findViewById(R.id.passField);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //call myScore activity. For testing.
//                Intent intent = new Intent(v.getContext(), MyScore.class);
//                startActivity(intent);
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                //call login method
                try {



                    AsyncTaskConnection taskConnection = new AsyncTaskConnection();
                    User user = new User(username, password, false);
                    ArrayList array = new ArrayList(1);
                    array.add(0, user);
                    taskConnection.execute(array);
                } catch (Exception e) {
                    messageLogin.setText("IO Error occured");
                }
                ;
            }
        });
    }
    class AsyncTaskConnection extends AsyncTask<ArrayList, Void, User> {

        User user;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            messageLogin = (TextView) findViewById(R.id.messageLogin);
            messageLogin.setText("");

            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            if (password.equals("") || username.equals("")) {
                messageLogin.setVisibility(View.VISIBLE);
                messageLogin.setText("Fields cannot be empty");
                messageLogin.setTextColor(Color.RED);
                return;
            }
        }

        @Override
        protected User doInBackground(ArrayList... arrayLists){
            user = (User) arrayLists[0].get(0);
            try {
                login(user.getUsername(), user.getPassword());
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
            System.out.println(user.toString());
            if (user.isSuccess()) {
                //creating new Parent to change scenes
                //Parent root = FXMLLoader.load(getClass().getResource("/Statistics.fxml"));
                //change scene
                //ClientMain.setScene(root);
                messageLogin.setVisibility(View.VISIBLE);
                messageLogin.setText("Login Success!");
                messageLogin.setTextColor(Color.GREEN);


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
        }
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

        //send authentication to server
        try {
            User user = loginPostRequest(username, password);
        } catch (IOException e) {
            System.out.println("IOException caught in login method");;
        }
    }
}

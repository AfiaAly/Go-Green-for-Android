//package com.example.gogreen_android;
//
//import android.widget.TextView;
//
//import java.io.IOException;
//
//import models.User;
//
//public class LoginControllerAndroid {
//
//    private TextView messageLogin;
//
//    public void login(String username, String password) throws IOException {
//        messageLogin.setText("");
////        String username = nameLogin.getText();
////        String password = pwLogin.getText();
//        StatisticsController.setUserName(username);
//        if (password.equals("") || username.equals("")) {
//            messageLogin.setText("Fields cannot be empty");
//            return;
//        }
//        //send authentication to server
//        User user = loginPostRequest(username, password);
//        //authenticating
//
//        if (user.isSuccess()) {
//            //creating new Parent to change scenes
//            Parent root = FXMLLoader.load(getClass().getResource("/Statistics.fxml"));
//            //change scene
//            ClientMain.setScene(root);
//
//        } else {
//            messageLogin.setText("Username or password invalid");
//            return;
//        }
//    }
//}

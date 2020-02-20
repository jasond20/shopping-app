package com.example.salesapplication.adminInterface.adminInterfaceButtons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesapplication.adminInterface.AdminLogin;
import com.example.salesapplication.customerInterface.CustomerLoginScreen;

public class AdminToLoginScreenButton implements View.OnClickListener {

    private Context appContext;

    public AdminToLoginScreenButton(Context context) {this.appContext = context;}

    public void onClick(View view) {
        Intent intent = new Intent(this.appContext, AdminLogin.class);
        ((AppCompatActivity)appContext).startActivity(intent);
    }

}

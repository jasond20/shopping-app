package com.example.salesapplication.customerInterface.customerButtons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesapplication.customerInterface.CustomerLoginScreen;

public class CustomerToLoginScreenButton implements View.OnClickListener {

    private Context appContext;

    public CustomerToLoginScreenButton(Context context) {this.appContext = context;}

    public void onClick(View view) {
        Intent intent = new Intent(this.appContext, CustomerLoginScreen.class);
        ((AppCompatActivity)appContext).startActivity(intent);
    }

}

package com.example.salesapplication.customerInterface.customerButtons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesapplication.customerInterface.CustomerChoosePreviousCartScreen;

public class CustomerRestorePreviousButton implements View.OnClickListener {

    private Context appContext;

    public CustomerRestorePreviousButton(Context context) {this.appContext = context;}

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this.appContext, CustomerChoosePreviousCartScreen.class);
        ((AppCompatActivity)appContext).startActivity(intent);
    }

}

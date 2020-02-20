package com.example.salesapplication.customerInterface.customerButtons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesapplication.customerInterface.CustomerInterfaceScreen;

public class CustomerBackToInterface implements View.OnClickListener {

    private Context appContext;

    public CustomerBackToInterface(Context context) {this.appContext = context;}

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this.appContext, CustomerInterfaceScreen.class);
        ((AppCompatActivity)appContext).startActivity(intent);

    }

}

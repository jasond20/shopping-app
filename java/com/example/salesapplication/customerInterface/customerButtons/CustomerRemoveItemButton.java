package com.example.salesapplication.customerInterface.customerButtons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesapplication.customerInterface.CustomerRemoveItemScreen;

public class CustomerRemoveItemButton implements View.OnClickListener {

    private Context appContext;

    public CustomerRemoveItemButton(Context context) {this.appContext = context;}

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this.appContext, CustomerRemoveItemScreen.class);

        ((AppCompatActivity)appContext).startActivity(intent);
    }

}

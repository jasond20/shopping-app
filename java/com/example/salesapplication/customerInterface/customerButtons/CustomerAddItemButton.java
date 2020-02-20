package com.example.salesapplication.customerInterface.customerButtons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesapplication.customerInterface.CustomerAddItemScreen;

public class CustomerAddItemButton implements View.OnClickListener{

    private Context appContext;

    public CustomerAddItemButton(Context context) {this.appContext = context;}

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this.appContext, CustomerAddItemScreen.class);
        ((AppCompatActivity)appContext).startActivity(intent);

    }

}

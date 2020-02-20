package com.example.salesapplication.customerInterface.customerButtons;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesapplication.R;

public class CustomerAddItemInsertButton implements View.OnClickListener {

    private Context appContext;

    public CustomerAddItemInsertButton(Context context) {this.appContext = context;}

    @Override
    public void onClick(View view) {

        TextView insertItemResult = ((AppCompatActivity)appContext).findViewById(R.id.customerAddItemResult);


    }

}

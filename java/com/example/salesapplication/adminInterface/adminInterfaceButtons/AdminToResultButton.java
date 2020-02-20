package com.example.salesapplication.adminInterface.adminInterfaceButtons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesapplication.adminInterface.AdminInterfaceResult;

public class AdminToResultButton implements View.OnClickListener{

    private Context appContext;

    public AdminToResultButton(Context context) {this.appContext = context;}

    @Override

    public void onClick(View view) {
        Intent intent = new Intent(this.appContext, AdminInterfaceResult.class);

        ((AppCompatActivity)appContext).startActivity(intent);

    }


}

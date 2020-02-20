package com.example.salesapplication.adminInterface.adminInterfaceButtons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesapplication.adminInterface.AdminInterfaceDeserialize;
import com.example.salesapplication.adminInterface.AdminInterfacePromoteEmployee;

public class AdminToPromoteEmployeeButton implements View.OnClickListener{

    private Context appContext;

    public AdminToPromoteEmployeeButton(Context context) {this.appContext = context;}

    @Override

    public void onClick(View view) {
        Intent intent = new Intent(this.appContext, AdminInterfacePromoteEmployee.class);

        ((AppCompatActivity)appContext).startActivity(intent);

    }


}

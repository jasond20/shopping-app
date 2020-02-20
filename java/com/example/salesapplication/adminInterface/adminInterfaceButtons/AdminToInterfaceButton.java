package com.example.salesapplication.adminInterface.adminInterfaceButtons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesapplication.adminInterface.AdminInterfaceMenu;
import com.example.salesapplication.employeeInterface.EmployeeInterfaceMenu;

public class AdminToInterfaceButton implements View.OnClickListener{

    private Context appContext;

    public AdminToInterfaceButton(Context context) {this.appContext = context;}

    @Override

    public void onClick(View view) {
        Intent intent = new Intent(this.appContext, AdminInterfaceMenu.class);

        ((AppCompatActivity)appContext).startActivity(intent);

    }


}

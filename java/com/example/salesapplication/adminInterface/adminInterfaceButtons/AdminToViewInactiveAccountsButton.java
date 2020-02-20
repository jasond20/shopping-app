package com.example.salesapplication.adminInterface.adminInterfaceButtons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesapplication.adminInterface.AdminInterfaceViewActiveAccounts;
import com.example.salesapplication.adminInterface.AdminInterfaceViewInactiveAccounts;

public class AdminToViewInactiveAccountsButton implements View.OnClickListener{

    private Context appContext;

    public AdminToViewInactiveAccountsButton(Context context) {this.appContext = context;}

    @Override

    public void onClick(View view) {
        Intent intent = new Intent(this.appContext, AdminInterfaceViewInactiveAccounts.class);

        ((AppCompatActivity)appContext).startActivity(intent);

    }


}

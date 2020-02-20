package com.example.salesapplication.adminInterface.adminInterfaceButtons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesapplication.adminInterface.AdminInterfaceViewActiveAccounts;
import com.example.salesapplication.adminInterface.AdminInterfaceViewHistoricSales;

public class AdminToViewHistoricSalesButton implements View.OnClickListener{

    private Context appContext;

    public AdminToViewHistoricSalesButton(Context context) {this.appContext = context;}

    @Override

    public void onClick(View view) {
        Intent intent = new Intent(this.appContext, AdminInterfaceViewHistoricSales.class);

        ((AppCompatActivity)appContext).startActivity(intent);

    }


}

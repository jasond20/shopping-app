package com.example.salesapplication.adminInterface;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.salesapplication.R;
import com.example.salesapplication.adminInterface.adminInterfaceButtons.AdminToDeserializeButton;
import com.example.salesapplication.adminInterface.adminInterfaceButtons.AdminToMainActivity;
import com.example.salesapplication.adminInterface.adminInterfaceButtons.AdminToPromoteEmployeeButton;
import com.example.salesapplication.adminInterface.adminInterfaceButtons.AdminToSerializeButton;
import com.example.salesapplication.adminInterface.adminInterfaceButtons.AdminToViewActiveAccountsButton;
import com.example.salesapplication.adminInterface.adminInterfaceButtons.AdminToViewHistoricSalesButton;
import com.example.salesapplication.adminInterface.adminInterfaceButtons.AdminToViewInactiveAccountsButton;

public class AdminInterfaceMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.MemeTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_interface);
        Button toPromoteEmployee = findViewById(R.id.buttonPromoteEmployeeOption);
        toPromoteEmployee.setOnClickListener(new AdminToPromoteEmployeeButton(this));
        Button toViewHistoricSales = findViewById(R.id.buttonViewHistoricSalesOption);
        toViewHistoricSales.setOnClickListener(new AdminToViewHistoricSalesButton(this));
        Button toActiveAccounts = findViewById(R.id.buttonViewCustomerActiveAccountsOption);
        toActiveAccounts.setOnClickListener(new AdminToViewActiveAccountsButton(this));
        Button toInactiveAccounts = findViewById(R.id.buttonViewCustomerInactiveAccountsOption);
        toInactiveAccounts.setOnClickListener(new AdminToViewInactiveAccountsButton(this));
        Button toViewInventory = findViewById(R.id.buttonViewCurrentInventoryOption);
        toViewInventory.setOnClickListener(new AdminToViewHistoricSalesButton(this));
        Button toSerialize = findViewById(R.id.buttonSerializeOption);
        toSerialize.setOnClickListener(new AdminToSerializeButton(this));
        Button toDeserialize = findViewById(R.id.buttonDeserializeOption);
        toDeserialize.setOnClickListener(new AdminToDeserializeButton(this));
        Button toMainActivity = findViewById(R.id.buttonExitAdminInterface);
        toMainActivity.setOnClickListener(new AdminToMainActivity(this));
    }

}

package com.example.salesapplication.customerInterface;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.salesapplication.R;
import com.example.salesapplication.customerInterface.customerButtons.CustomerBackToInterface;

public class CustomerAddItemScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.MemeTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_interface_add_item);

        Button addItemBackToMenu = findViewById(R.id.customerAddItemBackToMenu);
        addItemBackToMenu.setOnClickListener(new CustomerBackToInterface(this));

    }

}

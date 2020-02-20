package com.example.salesapplication.adminInterface;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.salesapplication.R;
import com.example.salesapplication.adminInterface.adminInterfaceButtons.AdminToResultButton;

public class AdminInterfaceDeserialize extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.MemeTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_interface_deserialize_database);
        Button toDeserializeResult = findViewById(R.id.buttonDeserializeDatabase);
        toDeserializeResult.setOnClickListener(new AdminToResultButton(this));

    }

}

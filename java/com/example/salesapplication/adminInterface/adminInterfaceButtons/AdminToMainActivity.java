package com.example.salesapplication.adminInterface.adminInterfaceButtons;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesapplication.MainActivity;
import com.example.salesapplication.adminInterface.AdminInterfaceDeserialize;

public class AdminToMainActivity implements View.OnClickListener{

    private Context appContext;

    public AdminToMainActivity(Context context) {this.appContext = context;}

    @Override

    public void onClick(View view) {
        Intent intent = new Intent(this.appContext, MainActivity.class);

        ((AppCompatActivity)appContext).startActivity(intent);

    }


}

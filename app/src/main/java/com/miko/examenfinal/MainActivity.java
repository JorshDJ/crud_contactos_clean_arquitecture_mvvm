package com.miko.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.miko.examenfinal.presentation.routes.AppNavigator;

import dagger.hilt.android.AndroidEntryPoint;

public class MainActivity extends AppCompatActivity {

    private AppNavigator appNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appNavigator = new AppNavigator(this);

        Button button = findViewById(R.id.my_button);
        button.setOnClickListener(v -> appNavigator.navigateToContactList());
    }
}


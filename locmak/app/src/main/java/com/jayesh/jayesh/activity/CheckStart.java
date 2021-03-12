package com.jayesh.jayesh.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.jayesh.jayesh.MainActivity;
import com.jayesh.jayesh.app.AppConfig;
import com.jayesh.jayesh.app.AppController;
import com.jayesh.jayesh.helper.SQLiteHandler;
import com.jayesh.jayesh.helper.SessionManager;


import com.jayesh.jayesh.R;

public class CheckStart extends AppCompatActivity implements View.OnClickListener {

    ImageButton one,two;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_start);

        one = (ImageButton) findViewById(R.id.log);
        two = (ImageButton) findViewById(R.id.sig);
        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn())
        {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(CheckStart.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        one.setOnClickListener(this);
        two.setOnClickListener(this);
    }

        @Override
        public void onClick (View v)
        {
            if (v == one)
            {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();

            }
            if (v == two) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                finish();
            }
        }
    }
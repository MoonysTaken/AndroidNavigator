package com.example.vladimir.navigator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView cultureTextView;
    TextView foodTextView;
    TextView entertainmentTextView;
    TextView aboutTextView;
    public static int buttonChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cultureTextView = findViewById(R.id.cultureTextView);
        foodTextView = findViewById(R.id.foodTextView);
        entertainmentTextView = findViewById(R.id.entertainmentTextView);
        aboutTextView = findViewById(R.id.aboutTextView);
        cultureTextView.setOnClickListener(this);
        foodTextView.setOnClickListener(this);
        entertainmentTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MapsActivity.class);

        switch (view.getId()) {
            case (R.id.cultureTextView):
                buttonChange = 1;
                startActivity(intent);
                break;
            case (R.id.foodTextView):
                buttonChange = 2;
                startActivity(intent);
                break;
            case (R.id.entertainmentTextView):
                buttonChange = 3;
                startActivity(intent);
                break;
            case (R.id.aboutTextView):
                Intent intent2 = new Intent(this, AboutCity.class);
                startActivity(intent2);
                break;
        }
    }
}

package com.example.kut003.a007app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button1, Perform action on click");
                Intent intent = new Intent(getApplication(), SubActivity1.class);
                startActivity(intent);
            }
        });

        final Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug","button2, Perform action on click");
                Intent intent = new Intent(getApplication(), SubActivity2.class);
                startActivity(intent);
            }
        });

    }
}
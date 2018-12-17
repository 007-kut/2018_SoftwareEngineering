package com.example.kut003.a007app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SavedArt extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.saved_art);

        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


package com.example.reiya.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = this.getIntent();

        int text = intent.getIntExtra("date", 0);

        TextView textView = findViewById(R.id.text_view);
        textView.setText(String.valueOf(text));

        intent.putExtra("maincount",6);
        setResult(MainActivity.RESULT_OK,intent);

        ImageButton ImageButton = findViewById(R.id.shop1);
        ImageButton ImageButton2 = findViewById(R.id.shop2);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SubActivity.class);

                startActivity(intent);

            }
        });
        ImageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SubActivity.class);

                startActivity(intent);

            }
        });


    }

}

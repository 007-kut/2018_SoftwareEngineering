//おえかきゲーム画面(図5)
package com.example.kut003.a007app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class ChooseArt extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_art);

        //機能設定画面に戻る
        final Button button0 = findViewById(R.id.button_back);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ChooseGame.class);
                startActivity(intent);
            }
        });
        //りんご
        final Button button1 = findViewById(R.id.button_apple);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MakeArt.class);
                startActivity(intent);
            }
        });
        //ばなな
        final Button button2 = findViewById(R.id.button_banana);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MakeArt.class);
                startActivity(intent);
            }
        });
        //おえかき
        final Button button3 = findViewById(R.id.button_drawing);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MakeArt.class);
                startActivity(intent);
            }
        });
    }
}

//機能設定画面(図2)
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

        //ゲーム
        final Button button_game = findViewById(R.id.button_game);
        button_game.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug","button_game, Perform action on click");
                Intent intent = new Intent(getApplication(), ChooseGame.class);
                startActivity(intent);
            }
        });

        //成長記録
        final Button button_grow = findViewById(R.id.button_grow);
        button_grow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug","button_grow, Perform action on click");
                Intent intent = new Intent(getApplication(), SubActivity2.class);
                startActivity(intent);
            }
        });

        //子育て窓口
        final Button button_wind = findViewById(R.id.button_wind);
        button_wind.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug","button_wind, Perform action on click");
                Intent intent = new Intent(getApplication(), SubActivity2.class);
                startActivity(intent);
            }
        });

        //設定
        final Button button_setting = findViewById(R.id.button_setting);
        button_setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug","button_wind, Perform action on click");
                Intent intent = new Intent(getApplication(), SubActivity2.class);
                startActivity(intent);
            }
        });
    }
}
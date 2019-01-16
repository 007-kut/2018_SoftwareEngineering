//作る食べ物を選ぶ画面(図10)
package com.example.kut003.a007app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class ChooseFood extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_food);
        final ShareQuestion sq = (ShareQuestion) this.getApplication();
        int pork = 0;
        int beef = 0;
        int onion = 0;
        int carrots = 0;
        int egg = 0;
        int milk = 0;
        sq.setCountPork(pork);
        sq.setCountBeef(beef);
        sq.setCountOnion(onion);
        sq.setCountCarrots(carrots);
        sq.setCountEgg(egg);
        sq.setCountMilk(milk);
        //機能設定画面に戻る
        final Button button0 = findViewById(R.id.button_back);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button0, Perform action on click");
                Intent intent = new Intent(getApplication(), ChooseGame.class);
                startActivity(intent);
            }
        });

        //クッキー
        final Button button1 = findViewById(R.id.button_cookie);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sq.setChooseFood("cookie");
                Intent intent = new Intent(getApplication(), MaterialFood.class);
                startActivity(intent);
            }
        });

        //ハンバーグ
        final Button button2 = findViewById(R.id.button_hamburg);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sq.setChooseFood("hamburg");
                Intent intent = new Intent(getApplication(), MaterialFood.class);
                startActivity(intent);
            }
        });
    }
}

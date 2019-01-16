//お店を選択する画面(図12)
package com.example.kut003.a007app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;

public class ChooseShop extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_shop);
        final ShareQuestion sq = (ShareQuestion) this.getApplication();

        //材料確認画面に戻る
        final Button button0 = findViewById(R.id.button_back);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button0, Perform action on click");
                Intent intent = new Intent(getApplication(), MaterialFood.class);
                startActivity(intent);
            }
        });

        //八百屋
        final ImageButton button1 = findViewById(R.id.button_greengrocer);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sq.setChooseShop("grocer");
                Intent intent = new Intent(getApplication(), ChooseMaterial.class);
                startActivity(intent);
            }
        });

        //市場
        final ImageButton button2 = findViewById(R.id.button_market);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sq.setChooseShop("market");
                Intent intent = new Intent(getApplication(), ChooseMaterial.class);
                startActivity(intent);
            }
        });

        //肉屋
        final ImageButton button3 = findViewById(R.id.button_butcher);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sq.setChooseShop("butcher");
                Intent intent = new Intent(getApplication(), ChooseMaterial.class);
                startActivity(intent);
            }
        });

        /*//かご
        final Button button4 = findViewById(R.id.button_basket);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button2, Perform action on click");
                Intent intent = new Intent(getApplication(), InBasket.class);
                startActivity(intent);
            }
        });*/

        //おうちにかえる
        final Button button5 = findViewById(R.id.button_home);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), BackHome.class);
                startActivity(intent);
            }
        });
    }
}

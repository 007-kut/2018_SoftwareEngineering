//できあがり画面(図17) imai
package com.example.kut003.a007app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;

public class CheckFood extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ShareQuestion sq = (ShareQuestion) this.getApplication();
        /*String chooseFood = sq.getChooseFood();*/
        setContentView(R.layout.check_food);
        //機能設定画面に戻る

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

        String chooseFood = sq.getChooseFood();
        if(chooseFood.equals("cookie")) {
           final ImageView imageView2 = findViewById(R.id.humburg);
           imageView2.setImageResource(R.drawable.cookie);
        }else{
            final ImageView imageView = findViewById(R.id.humburg);
            imageView.setImageResource(R.drawable.hamburg);
            /*画像を一度セットすると、2回目からエラーがでるよ（試してみて）
            これはセットを初期化してあげるとなおるっぽいね
            調べて試してみてください*/
        }

        sq.setChooseFood("");

        final Button button0 = findViewById(R.id.button_back);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ChooseFood.class);
                startActivity(intent);
            }
        });
    }
}
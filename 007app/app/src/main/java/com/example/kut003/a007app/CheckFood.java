//できあがり画面(図17)
package com.example.kut003.a007app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class CheckFood extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_food);
        //機能設定画面に戻る
        final ShareQuestion sq = (ShareQuestion) this.getApplication();
        int pork = 0;
        int beef = 0;
        int mince = 0;
        int onion = 0;
        int carrots = 0;
        int egg = 0;
        int milk = 0;
        sq.setCountPork(pork);
        sq.setCountBeef(beef);
        sq.setCountMince(mince);
        sq.setCountOnion(onion);
        sq.setCountCarrots(carrots);
        sq.setCountEgg(egg);
        sq.setCountMilk(milk);
        final Button button0 = findViewById(R.id.button_back);
        button0.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ChooseFood.class);
                startActivity(intent);
            }
        });
    }
}

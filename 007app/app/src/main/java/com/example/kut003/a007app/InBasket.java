//持っている材料を確認(図13) imai
package com.example.kut003.a007app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

public class InBasket extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.in_basket);
        final ShareQuestion sq = (ShareQuestion) this.getApplication();

        final TextView textView1 = findViewById(R.id.textView1);
        int countBeef = sq.getBeef();
        textView1.setText(String.valueOf(countBeef));

        final TextView textView2 = findViewById(R.id.textView2);
        int countPork = sq.getPork();
        textView2.setText(String.valueOf(countPork));

        final TextView textView3 = findViewById(R.id.textView3);
        int countCarrots = sq.getCarrots();
        textView3.setText(String.valueOf(countCarrots));

        final TextView textView4 = findViewById(R.id.textView4);
        int countOnion = sq.getOnion();
        textView4.setText(String.valueOf(countOnion));

        final TextView textView5 = findViewById(R.id.textView5);
        int countEgg = sq.getEgg();
        textView5.setText(String.valueOf(countEgg));

        final TextView textView6 = findViewById(R.id.textView6);
        int countMilk = sq.getMilk();
        textView6.setText(String.valueOf(countMilk));



        final Button button1 = findViewById(R.id.button_back);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button1, Perform action on click");
                Intent intent = new Intent(getApplication(), ChooseShop.class);
                startActivity(intent);
            }
        });
    }
}
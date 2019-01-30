//材料をお店で選択する画面(図14) imai
package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChooseMaterial extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_material);
        final ShareQuestion sq = (ShareQuestion) this.getApplication();

        String chooseShop = sq.getChooseShop();

        if (chooseShop.equals("butcher")) {
            //肉屋の処理
            setContentView(R.layout.butcher);
            final TextView textView1 = findViewById(R.id.textView1);
            final ImageButton button_pork = findViewById(R.id.button_pork);
            int countPork = sq.getPork();
            textView1.setText(String.valueOf(countPork));
            button_pork.setOnClickListener(new View.OnClickListener() {
                int countPork = sq.getPork();
                public void onClick(View view) {
                    countPork++;
                    textView1.setText(String.valueOf(countPork));
                    sq.setCountPork(countPork);
                }
            });
            final TextView textView2 = findViewById(R.id.textView2);
            final ImageButton button_beef = findViewById(R.id.button_beef);
            int countBeef = sq.getBeef();
            textView2.setText(String.valueOf(countBeef));
            button_beef.setOnClickListener(new View.OnClickListener() {
                int countBeef = sq.getBeef();
                public void onClick(View view) {
                    countBeef++;
                    textView2.setText(String.valueOf(countBeef));
                    sq.setCountBeef(countBeef);
                }
            });
        } else if (chooseShop.equals("market")) {
            setContentView(R.layout.market);
            final TextView textView1 = findViewById(R.id.textView1);
            final ImageButton button_egg = findViewById(R.id.button_egg);
            int countEgg = sq.getEgg();
            textView1.setText(String.valueOf(countEgg));
            button_egg.setOnClickListener(new View.OnClickListener() {
                int countEgg = sq.getEgg();
                public void onClick(View view) {
                    countEgg++;
                    textView1.setText(String.valueOf(countEgg));
                    sq.setCountEgg(countEgg);
                }
            });
            final TextView textView2 = findViewById(R.id.textView2);
            final ImageButton button_milk = findViewById(R.id.button_milk);
            int countMilk = sq.getMilk();
            textView2.setText(String.valueOf(countMilk));
            button_milk.setOnClickListener(new View.OnClickListener() {
                int countMilk = sq.getMilk();
                public void onClick(View view) {
                    countMilk++;
                    textView2.setText(String.valueOf(countMilk));
                    sq.setCountMilk(countMilk);
                }
            });
        } else {
            setContentView(R.layout.greengrocer);
            final TextView textView1 = findViewById(R.id.textView1);
            final ImageButton button_onion = findViewById(R.id.button_onion);
            int countOnion = sq.getOnion();
            textView1.setText(String.valueOf(countOnion));
            button_onion.setOnClickListener(new View.OnClickListener() {
                int countOnion = sq.getOnion();
                public void onClick(View view) {
                    countOnion++;
                    textView1.setText(String.valueOf(countOnion));
                    sq.setCountOnion(countOnion);
                }
                //八百屋の処理
            });
            final TextView textView2 = findViewById(R.id.textView2);
            final ImageButton button_carrots = findViewById(R.id.button_carrots);
            int countCarrots = sq.getCarrots();
            textView2.setText(String.valueOf(countCarrots));
            button_carrots.setOnClickListener(new View.OnClickListener() {
                int countCarrots = sq.getCarrots();
                public void onClick(View view) {
                    countCarrots++;
                    textView2.setText(String.valueOf(countCarrots));
                    sq.setCountCarrots(countCarrots);
                }
            });
        }

        //お店選択に戻る
        final Button button0 = findViewById(R.id.button_back);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button0, Perform action on click");
                Intent intent = new Intent(getApplication(), ChooseShop.class);
                startActivity(intent);
            }
        });

        //籠の中身を確認
        final Button button1 = findViewById(R.id.button_basket);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button1, Perform action on click");
                Intent intent = new Intent(getApplication(), InBasket.class);
                startActivity(intent);
            }
        });

        //材料を確認
        final Button button2 = findViewById(R.id.button_material);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                toast();
            }
        });
    }

    private Context context;
    public void toast() {
        final ShareQuestion sq = (ShareQuestion) this.getApplication();
        String chooseFood = sq.getChooseFood();

        context = getApplicationContext();
        Toast toast = new Toast(context);
        LayoutInflater inflater = getLayoutInflater();
        if(chooseFood.equals("cookie")) {
            ViewGroup viewGroup = findViewById(R.id.relative_layout);
            View view = inflater.inflate(R.layout.custom_toast, viewGroup);
            toast.setView(view);
            // 表示時間
            toast.setDuration(Toast.LENGTH_SHORT);
            // 位置調整
            toast.show();
        }else {
            ViewGroup viewGroup1 = findViewById(R.id.relative_layout);
            View view1 = inflater.inflate(R.layout.layout, viewGroup1);
            toast.setView(view1);
            // 表示時間
            toast.setDuration(Toast.LENGTH_SHORT);
            // 位置調整
            toast.show();
        }
    }
}

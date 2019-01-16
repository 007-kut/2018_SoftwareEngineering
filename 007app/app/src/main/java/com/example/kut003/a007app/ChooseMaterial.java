//材料をお店で選択する画面(図14)
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
import android.widget.Toast;
import android.widget.ImageButton;
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
            final ImageButton button_pork = findViewById(R.id.button_pork);
            button_pork.setOnClickListener(new View.OnClickListener() {
                int countPork = sq.getPork();
                public void onClick(View view) {
                    countPork++;
                    sq.setCountPork(countPork);
                    //Intent intent = new Intent(getApplication(), SubActivity2.class);
                    //startActivity(intent);
                }
            });
            final ImageButton button_beef = findViewById(R.id.button_beef);
            button_beef.setOnClickListener(new View.OnClickListener() {
                int countBeef = sq.getBeef();
                public void onClick(View view) {
                    countBeef++;
                    sq.setCountBeef(countBeef);
                    //Intent intent = new Intent(getApplication(), SubActivity2.class);
                    //startActivity(intent);
                }
            });
            final ImageButton button_mince = findViewById(R.id.button_mince);
            button_mince.setOnClickListener(new View.OnClickListener() {
                int countMince = sq.getMince();
                public void onClick(View view) {
                    countMince++;
                    sq.setCountMince(countMince);
                    //Intent intent = new Intent(getApplication(), SubActivity2.class);
                    //startActivity(intent);
                }
            });
        } else if (chooseShop.equals("market")) {
            //
            setContentView(R.layout.market);
            final ImageButton button_egg = findViewById(R.id.button_egg);
            button_egg.setOnClickListener(new View.OnClickListener() {
                int countEgg = sq.getEgg();
                public void onClick(View view) {
                    countEgg++;
                    sq.setCountEgg(countEgg);
                    //Intent intent = new Intent(getApplication(), SubActivity2.class);
                    //startActivity(intent);
                }
            });
            final ImageButton button_milk = findViewById(R.id.button_milk);
            button_milk.setOnClickListener(new View.OnClickListener() {
                int countMilk = sq.getMilk();
                public void onClick(View view) {
                    countMilk++;
                    sq.setCountMilk(countMilk);
                    //Intent intent = new Intent(getApplication(), SubActivity2.class);
                    //startActivity(intent);
                }
            });
        } else {
            setContentView(R.layout.greengrocer);
            final ImageButton button_onion = findViewById(R.id.button_onion);
            button_onion.setOnClickListener(new View.OnClickListener() {
                int countOnion = sq.getOnion();

                public void onClick(View view) {
                    countOnion++;
                    sq.setCountOnion(countOnion);
                }
                //八百屋の処理
            });
            final ImageButton button_carrots = findViewById(R.id.button_carrots);
            button_carrots.setOnClickListener(new View.OnClickListener() {
                int countCarrots = sq.getCarrots();

                public void onClick(View view) {
                    countCarrots++;
                    sq.setCountCarrots(countCarrots);
                }
                //八百屋の処理
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
        context = getApplicationContext();
        Toast toast = new Toast(context);
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup viewGroup = findViewById(R.id.relative_layout);
        View view = inflater.inflate(R.layout.custom_toast, viewGroup);
        toast.setView(view);
        // 表示時間
        toast.setDuration(Toast.LENGTH_SHORT);
        // 位置調整
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}

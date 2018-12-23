//材料をお店で選択する画面(図14)
package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

public class ChooseMaterial extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.choose_material);

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
        //toast.setGravity(Gravity.CENTER, 0, -100);
        toast.show();
    }
}

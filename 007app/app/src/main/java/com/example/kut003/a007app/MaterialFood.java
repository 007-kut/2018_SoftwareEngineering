//材料確認画面(図11) imai
package com.example.kut003.a007app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;

public class MaterialFood extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_food);

        final ShareQuestion sq = (ShareQuestion) this.getApplication();
        String chooseFood = sq.getChooseFood();

        final ImageView imageView = findViewById(R.id.food);
        if(chooseFood.equals("cookie")) {
            imageView.setImageResource(R.drawable.material2);
        }else{
            imageView.setImageResource(R.drawable.material);
        }

        //作る食べ物を選ぶ画面に戻る
        final Button button0 = findViewById(R.id.button_albam_back);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button0, Perform action on click");
                sq.setChooseFood("");
                Intent intent = new Intent(getApplication(), ChooseFood.class);
                startActivity(intent);
            }
        });

        //おつかいへ
        final Button button1 = findViewById(R.id.button_go);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button1, Perform action on click");
                Intent intent = new Intent(getApplication(), ChooseShop.class);
                startActivity(intent);
            }
        });
    }
}

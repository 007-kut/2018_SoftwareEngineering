//材料をお店で選択する画面(図15)
package com.example.kut003.a007app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import java.util.Random;
import android.widget.Toast;
import android.view.Gravity;

public class BackHome extends Activity {

    private String lackedMessage = "たりないよ";   //不足って意味
    private boolean completeCheck = false;
    Random rand = new Random();    //1つのボタンで複数の遷移先を実装するための一次的な措置
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back_home);
        final ShareQuestion sq = (ShareQuestion) this.getApplication();

        String chooseFood = sq.getChooseFood();
        int pork = sq.getPork();    //材料全部
        int beef = sq.getBeef();
        int mince = sq.getMince();
        int onion = sq.getOnion();
        int carrots = sq.getCarrots();
        int egg = sq.getEgg();
        int milk = sq.getMilk();
        if(chooseFood.equals("cookie")) {
            //クッキーに必要な材料
            if(egg >= 5 && milk >= 1) {
                completeCheck = true;
            }
        } else {
            //ハンバーグに必要な材料
            if(pork >= 3 && onion >= 5 && carrots >= 2) {
                completeCheck = true;
            }
        }

        //料理を作る
        final Button button1 = findViewById(R.id.button_make);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(completeCheck) {
                    Intent intent = new Intent(getApplication(), CheckFood.class);
                    startActivity(intent);
                } else {
                    toastMake(lackedMessage);
                }
            }
        });
        //おつかいに行く
        final Button button2 = findViewById(R.id.button_go);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ChooseShop.class);
                startActivity(intent);
            }
        });
    }

    private void toastMake(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        // 位置調整
        toast.setGravity(Gravity.CENTER, 0, -200);
        toast.show();
    }
}

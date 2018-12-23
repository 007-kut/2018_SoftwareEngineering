//イラスト画面(図6)
package com.example.kut003.a007app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;


public class MakeArt extends Activity {

    private String savedMessage;    //保存確認画面を表示するために使う

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.make_art);

        //ゲーム選択画面にもどる
        final Button button0 = findViewById(R.id.button_back);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button1, Perform action on click");
                Intent intent = new Intent(getApplication(), ChooseArt.class);
                startActivity(intent);
            }
        });

        savedMessage = "SAVED";

        //保存する
        final Button button1 = findViewById(R.id.button_save);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                toastMake(savedMessage);
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
//子育て窓口画面(図24)
package com.example.kut003.a007app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class Parenting extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.parenting);

        //機能選択画面に戻る
        final Button button0 = findViewById(R.id.button_back);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button0, Perform action on click");
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
        //投稿作成画面に遷移する
        final Button button1 = findViewById(R.id.button_post);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button1, Perform action on click");
                Intent intent = new Intent(getApplication(), MakePost.class);
                startActivity(intent);
            }
        });

        //検索結果画面に遷移する


        //選択した質問に遷移する

    }
}

//投稿作成画面(図25)
package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MakePost extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.make_post);

        //子育て窓口画面に戻る
        final Button button0 = findViewById(R.id.button_return);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button0, Perform action on click");
                Intent intent = new Intent(getApplication(), Parenting.class);
                startActivity(intent);
            }
        });

        //投稿ボタン
        final Button button1 = findViewById(R.id.button_make_post);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button1, Perform action on click");
                Intent intent = new Intent(getApplication(), SubActivity2.class);
                startActivity(intent);
            }
        });
    }
}

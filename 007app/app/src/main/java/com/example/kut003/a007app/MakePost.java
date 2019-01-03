//投稿作成画面(図25)
package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.CheckBox;

public class MakePost extends Activity {

    private EditText questionText;
    private TextView textView;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_post);

        checkBox = findViewById(R.id.check);
        checkBox.setChecked(false);
        checkBox.setText("匿名");
        // リスナーを登録
        checkBox.setOnClickListener(new View.OnClickListener() {
            // タップされると呼び出される
            @Override
            public void onClick(View v) {
                // チェックステータス取得
                boolean check = checkBox.isChecked();
                if(check){
                    checkBox.setText("匿名");
                }
                else{
                    checkBox.setText("ユーザ名");
                }
            }
        });

        questionText = findViewById(R.id.question_text);
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

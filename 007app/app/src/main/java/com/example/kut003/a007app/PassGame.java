//パスワード入力画面
package com.example.kut003.a007app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

<<<<<<< HEAD
=======
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;

>>>>>>> morimoto_server
public class PassGame extends Activity {

    public SharedPreferences dataPass;
    public EditText editText;
<<<<<<< HEAD
    private TextView textWrite, textView;
    public String pass = "1";
=======
>>>>>>> morimoto_server

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pass_game);

        //パスワード入力のためのインスタンスを生成
        dataPass = getSharedPreferences("data", MODE_PRIVATE);

        //入力できるようにする
        editText = findViewById(R.id.edit_text);

<<<<<<< HEAD

=======
>>>>>>> morimoto_server
        //exitを押せるようにする
        final Button button0 = findViewById(R.id.button_write);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
<<<<<<< HEAD
                final String text = editText.getText().toString();  //入力内容を格納
                if(pass.equals(text)){  //パスワードと入力を比較
                    Log.d("debug", "button1, Perform action on click");
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Log.d("debug", "button1, Perform action on click");
=======
                String pass = readFile();
                final String text = editText.getText().toString();  //入力内容を格納
                if(pass.equals(text)){  //パスワードと入力を比較
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                } else {
>>>>>>> morimoto_server
                    Intent intent = new Intent(getApplication(), SubActivity2.class);
                    startActivity(intent);
                }
            }
        });

        final Button button1 = findViewById(R.id.button_return);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
<<<<<<< HEAD
                Log.d("debug", "button1, Perform action on click");
=======
>>>>>>> morimoto_server
                Intent intent = new Intent(getApplication(), ChooseGame.class);
                startActivity(intent);
            }
        });
    }
<<<<<<< HEAD
=======

    //パスワードを読み込む
    public String readFile() {
        String text = null;
        String file = "gamePass.txt";
        // try-with-resources
        try (FileInputStream fileInputStream = openFileInput(file);
             BufferedReader reader= new BufferedReader(
                     new InputStreamReader(fileInputStream, "UTF-8"))) {
            String lineBuffer;
            while( (lineBuffer = reader.readLine()) != null ) {
                text = lineBuffer ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
>>>>>>> morimoto_server
}

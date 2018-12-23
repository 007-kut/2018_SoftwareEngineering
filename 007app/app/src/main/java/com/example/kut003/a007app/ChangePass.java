//パスワード入力画面
package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.io.IOException;


public class ChangePass extends Activity {

    public SharedPreferences dataPass;
    public EditText editText2;
    private String passText = null;
    private String message = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.change_pass);

        //パスワード入力のためのインスタンスを生成
        dataPass = getSharedPreferences("data", MODE_PRIVATE);

        //入力できるようにする
        editText2 = findViewById(R.id.edit_text);

        //メインへ（試し用）
        final Button button3 = findViewById(R.id.button_return);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

        
        final Button button1 = findViewById(R.id.button_change);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                passText = editText2.getText().toString();  //入力内容を格納
                if(passText.isEmpty()){
                    message = "not changed";
                } else {
                    writePass(passText);
                    message = "changed";
                }
                toastMake(message);
            }
        });
    }

    //パスワードの書き込み
    public void writePass(String password){
        String file = "gamePass.txt";
        try (FileOutputStream fileOutputstream = openFileOutput(file,
                Context.MODE_PRIVATE);) {
            fileOutputstream.write(password.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //トースト表示
    private void toastMake(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        // 位置調整
        toast.setGravity(Gravity.CENTER, 0, -200);
        toast.show();
    }
}
//初期設定画面(図1)

package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Gravity;

import java.io.FileOutputStream;
import java.io.IOException;


public class NewAccount extends Activity {
    SharedPreferences dataPass;
    public EditText textName;   //アカウント名
    public EditText textArea;   //地域
    public EditText textPass;   //パスワード
    private String accountName;
    private String accountArea;
    private String accountPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account);

        //ニックネーム入力のためのインスタンスを生成
        dataPass = getSharedPreferences("data", MODE_PRIVATE);

        //入力できるようにする
        textName = findViewById(R.id.edit_text);
        textArea = findViewById(R.id.edit_text2);
        textPass = findViewById(R.id.edit_text3);

        //パスワード変更表示
        final Button button_finish = findViewById(R.id.button_finish);
        button_finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                accountName = textName.getText().toString();    //名前を格納
                accountArea = textArea.getText().toString();    //地域を格納
                accountPass = textPass.getText().toString();    //パスワード格納

                //すべて入力されているか判定
                if (!(accountName.isEmpty()) && !(accountArea.isEmpty()) && !(accountPass.isEmpty())) {
                    writePass(accountPass); //パスワード保存
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                } else {
                    toastMake();
                }
            }
        });

    }

    //パスワードの書き込み
    public void writePass(String password) {
        String file = "gamePass.txt";
        try (FileOutputStream fileOutputstream = openFileOutput(file,
                Context.MODE_PRIVATE);) {
            fileOutputstream.write(password.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //トースト表示
    private void toastMake() {
        String message = "正しく入力してください";
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        // 位置調整
        toast.setGravity(Gravity.CENTER, 0, -200);
        toast.show();
    }
}


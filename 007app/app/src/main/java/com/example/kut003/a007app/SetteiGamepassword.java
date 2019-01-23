//ゲームパスワードのクラス

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


public class SetteiGamepassword extends Activity {

//    String toastMessage;

    SharedPreferences dataPass;
    //    SharedPreferences dataPass2;
    public EditText editText4;
    public EditText editText5;
    private String passText = null;
    private String passText2 = null;
    private String message = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settei_gamepassword);

        //ニックネーム入力のためのインスタンスを生成
        dataPass = getSharedPreferences("data", MODE_PRIVATE);
//        dataPass2 = getSharedPreferences("data", MODE_PRIVATE);

        //入力できるようにする
        editText4 = findViewById(R.id.edit_text);
        editText5 = findViewById(R.id.edit_text2);

//        Button button7 = findViewById(R.id.button_finish);

//        toastMessage = "アカウントの変更が完了しました";
//
//        button7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toastMake(toastMessage, 0, -200);
//            }
//        });

        //パスワード変更表示
        final Button button_finish = findViewById(R.id.button_password_finish);
        button_finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//        button_return.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
                passText = editText4.getText().toString();  //入力内容を格納
                passText2 = editText5.getText().toString();  //入力内容を格納
                if (passText.equals(passText2) && !(passText.isEmpty())) {
                    message = "変更されました";
                    writePass(passText);
                } else {
                    message = "正しく入力してください";
                }
                toastMake(message);
            }
        });

        //ホームへ
        final Button button_return = findViewById(R.id.button_password_home);
        button_return.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button6, Perform action on click");
                Intent intent = new Intent(getApplication(), SetteiMain.class);
                startActivity(intent);
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

    private void toastMake(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        // 位置調整
        toast.setGravity(Gravity.CENTER, 0, -200);
        toast.show();
    }
}


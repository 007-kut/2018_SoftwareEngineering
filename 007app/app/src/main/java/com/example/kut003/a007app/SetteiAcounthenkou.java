//アカウント変更のクラス

package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.Gravity;

import java.io.FileOutputStream;
import java.io.IOException;


public class SetteiAcounthenkou extends Activity {

    SharedPreferences dataPass;
    public EditText editText2;
    private String passText = null;
    private String message = null;
    private String accountArea;
    private String [] listArea = new String[47];

    public SetteiAcounthenkou(){
        ListArea la = new ListArea();
        System.arraycopy(la.listArea, 0, this.listArea, 0, 47);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settei_acounthenkou);
        //ニックネーム入力のためのインスタンスを生成
        dataPass = getSharedPreferences("data", MODE_PRIVATE);

        //都道府県リスト
        Spinner sp = findViewById(R.id.area);
        // ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listArea);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        //都道府県のリストを表示
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //選択された
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                accountArea = (String)spinner.getSelectedItem();
            }
            //選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });


        //ニックネーム入力のためのインスタンスを生成
        dataPass = getSharedPreferences("data", MODE_PRIVATE);

        //入力できるようにする
        editText2 = findViewById(R.id.edit_text);

        //ニックネーム変更表示
        final Button button_finish = findViewById(R.id.acount_finish);
        button_finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                passText = editText2.getText().toString();  //入力内容を格納
                if (passText.isEmpty()) {
                    message = "正しく入力してください";
                } else {
                    writePass(passText);
                    message = "変更されました";
                }
                toastMake(message);
            }
        });


        //ホームへ
        final Button button_return = findViewById(R.id.button_home);
        button_return.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button6, Perform action on click");
                Intent intent = new Intent(getApplication(), SetteiMain.class);
                startActivity(intent);
            }
        });
    }

    //ニックネームの書き込み
    public void writePass(String name) {
        String file = "namePass.txt";
        try (FileOutputStream fileOutputstream = openFileOutput(file,
                Context.MODE_PRIVATE);) {
            fileOutputstream.write(name.getBytes());
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
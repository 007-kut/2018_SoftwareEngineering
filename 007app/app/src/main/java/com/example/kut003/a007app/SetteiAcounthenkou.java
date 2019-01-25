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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class SetteiAcounthenkou extends Activity {

    SharedPreferences dataPass;
    public EditText editText;
    private String accountName = "";
    private String message = null;
    private String accountArea = "";
    private String [] listArea = new String[48];
    DatabaseContents dc = new DatabaseContents();

    public SetteiAcounthenkou(){
        ListArea la = new ListArea();
        System.arraycopy(la.listArea, 0, this.listArea, 0, 48);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settei_acounthenkou);

        //都道府県リスト
        Spinner sp = findViewById(R.id.area);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listArea);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //選択された
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                accountArea = (String)spinner.getSelectedItem();
                if(accountArea.equals("タップして選択")){
                    accountArea = "";   //地域を選択してないことになるよ
                }
            }
            //選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                accountArea = "aaaa";   //地域を選択してないことになるよ
            }
        });

        //ニックネーム入力のためのインスタンスを生成
        dataPass = getSharedPreferences("data", MODE_PRIVATE);

        //入力できるようにする
        editText = findViewById(R.id.edit_text);

        //ニックネーム変更表示
        final Button button_finish = findViewById(R.id.acount_finish);
        button_finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                accountName = editText.getText().toString();  //入力内容を格納
                if(accountName.isEmpty() && accountArea.isEmpty()){
                    String toast = "何も変更されません";
                    toastMake(toast);
                } else {
                    String userID = readUserId();
                    dc.setLister(createListener());
                    dc.getContentsById("2", "UserID=" + userID, "Name=" + accountName, "Area=" + accountArea);
                }
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

    private void toastMake(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        // 位置調整
        toast.setGravity(Gravity.CENTER, 0, -200);
        toast.show();
    }

    private UploadTask.Listener createListener () {
        return new UploadTask.Listener() {
            // 通信が成功した場合の処理(result : 返り値)
            @Override
            public void onSuccess(String result) {
                Intent intent = new Intent(getApplication(), SuccessChangeAccount.class);
                startActivity(intent);
            }
            //通信が失敗した場合の処理(result : 返り値)
            @Override
            public void onFailure(String result) {
                String miss ="変更が失敗しました";
                toastMake(miss);
            }
        };
    }

    //ユーザIDを読み込む
    public String readUserId() {
        String text = "";
        String file = "userId.txt";
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
}
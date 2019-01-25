//初期設定画面(図1)
package com.example.kut003.a007app;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;
import java.io.FileOutputStream;
import java.io.IOException;

public class NewAccount extends Activity {
    SharedPreferences dataPass;
    public EditText textName;   //アカウント名
    public EditText textPass;   //パスワード
    public TextView textView;
    private String accountName;
    private String accountPass;
    private String accountArea;
    private String [] listArea = new String[48];
    DatabaseContents dc = new DatabaseContents();   //書く場所おかしい気がする

    public NewAccount(){
        ListArea la = new ListArea();
        System.arraycopy(la.listArea, 0, this.listArea, 0, 48);
    }

    //もどるが押せなくなる
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction()==KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account);

        //ニックネーム入力のためのインスタンスを生成
        dataPass = getSharedPreferences("data", MODE_PRIVATE);

        textName = findViewById(R.id.edit_text);
        textPass = findViewById(R.id.edit_text3);

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
                //特に処理はなし？
            }
        });

        //パスワード変更表示
        final Button button_finish = findViewById(R.id.button_finish);
        button_finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                accountName = textName.getText().toString();    //名前を格納
                accountPass = textPass.getText().toString();    //パスワード格納
                if(!(accountName.isEmpty() || accountPass.isEmpty() || accountArea.isEmpty())) {
                    dc.setLister(createListener());
                    dc.getContentsById("1", "Name=" + accountName, "Area=" + accountArea);
                } else {
                    toastMake();
                }
            }
        });
    }

    private UploadTask.Listener createListener () {
        return new UploadTask.Listener() {
            // 通信が成功した場合の処理(result : 返り値)
            @Override
            public void onSuccess(String result) {
                String file = "gamePass.txt";
                writeFile(accountPass,file);
                file = "userId.txt";
                writeFile(result, file);
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
            //通信が失敗した場合の処理(result : 返り値)
            @Override
            public void onFailure(String result){
                toastMake();
            }
        };
    }

    //ファイルへの書き込み
    public void writeFile(String word, String file) {
        try (FileOutputStream fileOutputstream = openFileOutput(file, Context.MODE_PRIVATE);) {
            fileOutputstream.write(word.getBytes());
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


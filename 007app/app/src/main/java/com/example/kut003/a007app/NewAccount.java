//初期設定画面(図1)
package com.example.kut003.a007app;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    private String [] listArea = new String[47];

    public NewAccount(){
        ListArea la = new ListArea();
        System.arraycopy(la.listArea, 0, this.listArea, 0, 47);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account);

        //ニックネーム入力のためのインスタンスを生成
        dataPass = getSharedPreferences("data", MODE_PRIVATE);

        textName = findViewById(R.id.edit_text);
        textPass = findViewById(R.id.edit_text3);
        textView = findViewById(R.id.database);


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
                DatabaseContents dc = new DatabaseContents();   //書く場所おかしい気がする
                dc.setLister(createListener());
                dc.getContentsById(textView,"1", "Name=" + accountName, "Area=" + accountArea);
            }
        });
    }

    private UploadTask.Listener createListener () {
        return new UploadTask.Listener() {
            // 通信が成功した場合の処理(result : 返り値)
            @Override
            public void onSuccess(String result) {
                // 例: textView.setText(result);
            }
            //通信が失敗した場合の処理(result : 返り値)
            @Override
            public void onFailure(String result) {
                // 例: System.out.print("Don't mind.");
            }
        };
    }

    //パスワードの書き込み
    public void writeFile(String password, String file) {
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


package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MakeAnswer extends Activity {

    private TextView textView;
    private EditText answerText;
    private CheckBox checkBox;  //匿名のチェックボックス
    private String checkAnonimity = "1";
    public static final String EXTRAMESSAGE = "com.example.kut003.a007app.MESSAGE";
    DatabaseContents dc = new DatabaseContents();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_answer);

        Intent intent = getIntent();
        final String questionContents = intent.getStringExtra(AnswerList.sendQuestionContents);
        final String qId = intent.getStringExtra(AnswerList.sendQId);
        textView = findViewById(R.id.contents_text);
        textView.setText(questionContents);

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
                    checkAnonimity = "0";
                }
                else{
                    checkAnonimity = "1";
                }
            }
        });

        answerText = findViewById(R.id.answer_text);
        //子育て窓口画面に戻る
        final Button button0 = findViewById(R.id.button_return);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        //回答ボタン
        final Button button1 = findViewById(R.id.button_make_answer);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String answerContent = answerText.getText().toString();
                if(answerContent.isEmpty()) {
                    toastMake();
                } else {
                    String userId = readFile();    //パスワードの読み込み
                    dc.setLister(createListener());
                    dc.getContentsById("4", "QID=" + qId, "UserID=" + userId, "Acontents=" + answerContent, "Anonimity=" + checkAnonimity);
                    //dc.getContentsById("4", "QID=10", "UserID=5", "Acontents=BugCheck", "Anonimity=True");
                }
            }
        });
    }

    //データベースアクセス結果
    private UploadTask.Listener createListener () {
        return new UploadTask.Listener() {
            // 通信が成功した場合の処理(result : 返り値)
            @Override
            public void onSuccess(String result) {
                result = "投稿されました";
                Intent intent = new Intent(getApplication(), CompleteQuestion.class);
                intent.putExtra(EXTRAMESSAGE, result);
                startActivity(intent);
            }
            //通信が失敗した場合の処理(result : 返り値)
            @Override
            public void onFailure(String result) {
                result = "投稿エラー";
                Intent intent = new Intent(getApplication(), CompleteQuestion.class);
                intent.putExtra(EXTRAMESSAGE, result);
                toastMake();
            }
        };
    }
    //トースト表示
    private void toastMake() {
        String message = "正しく入力してください";
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        // 位置調整
        toast.setGravity(Gravity.CENTER, 0, -200);
        toast.show();
    }

    //UserIDを読み込む
    public String readFile() {
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

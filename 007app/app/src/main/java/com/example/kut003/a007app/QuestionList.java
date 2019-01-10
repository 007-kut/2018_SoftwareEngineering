//子育て窓口メイン画面(図24)
package com.example.kut003.a007app;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class QuestionList extends Activity {

    private EditText editText;
    public static final String questionContents = "com.example.kut003.a007app.1";
    public static final String qId = "com.example.kut003.a007app.2";
    DatabaseContents dc = new DatabaseContents();
    private static final String[] questionContentsList = {
            //DBの処理が非同期なので最初は空の状態
            "", "", "", "", "", "","", "", "", "", "", "", "", "", ""
    };
    private static final String[] qIdList = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "", "", "", "", ""
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_list);
        dc.setLister(createListener());
        dc.getContentsById("5");

       // リスト表示
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.parentinglist);
        ListView listView = findViewById(R.id.listview);
        for (int i = 0; i < questionContentsList.length; i++) {
            String str = questionContentsList[i];
            arrayAdapter.add(str);
        }
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String contents = questionContentsList[position];    //要素数が対応しているっぽいです
                String qid = qIdList[position];
                Intent intent = new Intent(getApplication(), AnswerList.class);
                intent.putExtra(questionContents, contents);
                intent.putExtra(qId, qid);
                startActivity(intent);
            }
        });

        //入力できるようにする
        editText = findViewById(R.id.edit_text);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // editTextのフォーカスが外れた場合
                if (!hasFocus) {
                    // ソフトキーボードを非表示にする
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        editText = findViewById(R.id.edit_text);
        final Button button0 = findViewById(R.id.button_return);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
        final Button button1 = findViewById(R.id.button_search);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                button1.setFocusable(true);
                button1.setFocusableInTouchMode(true);
                button1.requestFocus();
                String search = editText.getText().toString();  //入力内容を格納
                //Intent intent = new Intent(getApplication(), MainActivity.class);
                //startActivity(intent);
            }
        });
        final Button button2 = findViewById(R.id.button_make);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MakePost.class);
                startActivity(intent);
            }
        });

    }

    //データベースアクセス結果
    private UploadTask.Listener createListener () {
        return new UploadTask.Listener() {
            // 通信が成功した場合の処理(result : 返り値)
            @Override
            public void onSuccess(String result) {
                String[][] datas = DatabaseContents.splitQuestionData(result);
                for(int y = 0; y < datas.length; y++) {
                    qIdList[y] = datas[y][0];
                    questionContentsList[y] = datas[y][3];
                }
            }
            //通信が失敗した場合の処理(result : 返り値)
            @Override
            public void onFailure(String result) {
                finish();
            }
        };
    }
}
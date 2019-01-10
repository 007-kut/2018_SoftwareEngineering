package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class AnswerList extends Activity {

    private static final String[] answerContentsList = {
            "", "", "", "", "", "","", "", "", "", "", "", "", "", ""
    };
    private static final String[] aIdList = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "", "", "", "", ""

    };
    private TextView textView;
    public static final String sendQuestionContents = "com.example.kut003.a007app.3";
    public static final String sendQId = "com.example.kut003.a007app.4";
    DatabaseContents dc = new DatabaseContents();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_list);


        //リスト表示
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.parentinglist);
        ListView listView = findViewById(R.id.listview);
        for (String str : answerContentsList) {
            // ArrayAdapterにitemを追加する
            arrayAdapter.add(str);
        }
        listView.setAdapter(arrayAdapter);

        //質問内容とQIDを格納
        Intent intent = getIntent();
        final String questionContents = intent.getStringExtra(QuestionList.questionContents);
        final String qId = intent.getStringExtra(QuestionList.qId);
        textView = findViewById(R.id.question_text);
        textView.setText(questionContents);
        dc.setLister(createListener());
        dc.getContentsById("7", "QID=" + qId);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplication(), SubActivity2.class);
                startActivity(intent);
            }
        });
        final Button button1 = findViewById(R.id.button_return);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), QuestionList.class);
                startActivity(intent);
            }
        });
        final Button button2 = findViewById(R.id.button_kaitoiusakusei);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MakeAnswer.class);
                intent.putExtra(sendQuestionContents, questionContents);
                intent.putExtra(sendQId, qId);
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
                    //aIdList[y] = datas[y][0];
                    //answerContentsList[y] = datas[y][3];
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
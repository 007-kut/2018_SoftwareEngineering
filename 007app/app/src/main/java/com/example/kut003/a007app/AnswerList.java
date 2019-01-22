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
    private TextView textAnonimity;
    private ArrayAdapter<String> arrayAdapter;
    DatabaseContents dc = new DatabaseContents();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_list);
        final ShareQuestion sq = (ShareQuestion) this.getApplication();

        //リスト表示
        arrayAdapter = new ArrayAdapter<>(this, R.layout.parentinglist);

        //質問内容とQIDを格納
        final String questionContents = sq.getChooseContents();
        final String qId = sq.getChooseId();
        textView = findViewById(R.id.question_text);
        textView.setText(questionContents);
        dc.setLister(createListener());
        dc.getContentsById("7", "QID=" + qId);

        //質問者の情報
        textAnonimity = findViewById(R.id.userName);
        String check = sq.getChooseAnonimity();
        String name = sq.getChooseName();
        String area = sq.getChooseArea();
        if(check.equals("0")) {
            check = "匿名投稿";
        } else {
            check = name + "による投稿 (" + area  + ")";
        }
        textAnonimity.setText(check);

        /* 回答の詳細表示はいるのか？いらないのか？要検討ポイント
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplication(), SubActivity2.class);
                startActivity(intent);
            }
        });*/
        final Button button1 = findViewById(R.id.button_return);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
        final Button button2 = findViewById(R.id.button_kaitoiusakusei);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MakeAnswer.class);
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
                ListView listView = findViewById(R.id.listview);
                if(!(result == null)) {
                    String[][] datas = DatabaseContents.splitQuestionData(result);
                    int count = datas.length - 1;   //DBからの返り値が古い順で返ってくるから
                    for (int y = 0; y < datas.length; y++) {
                        aIdList[y] = datas[count][0];
                        answerContentsList[y] = datas[count][3];
                        arrayAdapter.add(answerContentsList[y]);
                        count--;
                    }
                    //回答が10個ないときの処理
                    if(datas.length < 10) {
                        for (int y = datas.length; y < 10; y++ ) {
                            aIdList[y] = "";
                            answerContentsList[y] = "";
                            arrayAdapter.add(answerContentsList[y]);
                        }
                    }
                } else {
                    // 「他の回答が反映されたままの状態」を改善するため""で上書き
                    for(int y = 0; y < 10; y++) {
                        answerContentsList[y] = "";
                        arrayAdapter.add(answerContentsList[y]);
                    }
                }
                listView.setAdapter(arrayAdapter);
            }
            //通信が失敗した場合の処理(result : 返り値)
            @Override
            public void onFailure(String result) {
                finish();
            }
        };
    }
}
//子育て窓口メイン画面(図24)
package com.example.kut003.a007app;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
=======
>>>>>>> imai
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
<<<<<<< HEAD
import android.widget.Toast;

import java.util.Locale;

public class QuestionList extends Activity {

    public EditText editText;

    private static final String[] texts = {
            // Globe Decade の楽曲リストより
            "Feel Like dance",
            "Joy to the love (globe)",
            "SWEET PAIN",
            "DEPARTURES (RADIO EDIT)",
            "FREEDOM (RADIO EDIT)",
            "Is this love",
            "Can't Stop Fallin' in Love",
            "FACE",
            "FACES PLACES",
            "Anytime smokin' cigarette",
            "Wanderin' Destiny",
            "Love again",
            "wanna Be A Dreammaker",
            "Sa Yo Na Ra",
            "sweet heart",
            "Perfume of love",
            "MISS YOUR BODY",
            "still growin' up",
            "biting her nails",
            "とにかく無性に…"
    };
=======

public class QuestionList extends Activity {

    private EditText editText;
    String[] questionContentsList = new String[10];
    String[] qIdList = new String[10];
    String[] questionName = new String[10];
    String[] questionArea = new String[10];
    String[] questionAnonimity =  new String[10];
    private ArrayAdapter<String> arrayAdapter;
    DatabaseContents dc = new DatabaseContents();
>>>>>>> imai

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_list);
<<<<<<< HEAD

        // リスト表示
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.parentinglist);
        ListView listView = findViewById(R.id.listview);
        for (String str: texts) {
            arrayAdapter.add(str);
        }
=======
        final ShareQuestion sq = (ShareQuestion) this.getApplication();

        //リスト表示
        arrayAdapter = new ArrayAdapter<>(this, R.layout.parentinglist);

        dc.setLister(createListener());
        dc.getContentsById("5");

        //リスト表示
        ListView listView = findViewById(R.id.listview);
        //各質問をタップしたときの動作
>>>>>>> imai
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
<<<<<<< HEAD
                Intent intent = new Intent(getApplication(), MakePost.class);
                startActivity(intent);
            }
        });

        //入力できるようにする
        editText = findViewById(R.id.edit_text);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // EditTextのフォーカスが外れた場合
=======
                String qid = qIdList[position];
                String name = questionName[position];
                String area = questionArea[position];
                String contents = questionContentsList[position];    //要素数が対応しているっぽいです
                String anonimity = questionAnonimity[position];
                sq.setChooseQuestion(qid, name, area, contents, anonimity);
                Intent intent = new Intent(getApplication(), AnswerList.class);
                startActivity(intent);    //QIDと内容を送って遷移
            }
        });

        //検索欄
        editText = findViewById(R.id.edit_text);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // editTextのフォーカスが外れた場合
>>>>>>> imai
                if (!hasFocus) {
                    // ソフトキーボードを非表示にする
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

<<<<<<< HEAD
        final Button button0 = findViewById(R.id.button_search);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                button0.setFocusable(true);
                button0.setFocusableInTouchMode(true);
                button0.requestFocus();
                String search = editText.getText().toString();  //入力内容を格納
                Intent intent = new Intent(getApplication(), MainActivity.class);
                //startActivity(intent);
            }
        });

        final Button button1 = findViewById(R.id.button_return);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

        final Button button2 = findViewById(R.id.button_make);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SubActivity2.class);
=======
        //もどる
        final Button button0 = findViewById(R.id.button_return);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

        //検索ボタン
        final Button button1 = findViewById(R.id.button_search);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DatabaseContents dcs = new DatabaseContents();
                arrayAdapter.clear();
                button1.setFocusable(true);
                button1.setFocusableInTouchMode(true);
                button1.requestFocus();
                String search = editText.getText().toString();  //入力内容を格納
                dcs.setLister(createListener());
                dcs.getContentsById("6", "Search=" + search);
                //arrayAdapter.notifyDataSetChanged();    //動作未確認
            }
        });

        //回答作成
        final Button button2 = findViewById(R.id.button_make);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MakePost.class);
>>>>>>> imai
                startActivity(intent);
            }
        });
    }
<<<<<<< HEAD
=======

    //データベースアクセス結果
    private UploadTask.Listener createListener () {
        return new UploadTask.Listener() {

            // 通信が成功した場合の処理(result : 返り値)
            @Override
            public void onSuccess(String result) {
                ListView listView = findViewById(R.id.listview);
                if(!(result == null)) {
                    String[][] datas = DatabaseContents.splitQuestionData(result);
                    for (int y = 0; y < datas.length; y++) {
                        qIdList[y] = datas[y][0];
                        questionName[y] = datas[y][1];
                        questionArea[y] = datas[y][2];
                        questionContentsList[y] = datas[y][3];
                        questionAnonimity[y] = datas[y][4];
                        String name;
                        if(questionAnonimity[y].equals("0")) {
                            name = "匿名投稿";
                        } else {
                            name = questionName[y] + "による投稿";
                        }
                        arrayAdapter.add(name + "\r\n" + questionContentsList[y]);
                    }
                    //10個ないときの処理
                    if(datas.length < 10) {
                        for (int y = datas.length; y < 10; y++ ) {
                            qIdList[y] = "";
                            questionName[y] = "";
                            questionArea[y] = "";
                            questionContentsList[y] = "";
                            questionAnonimity[y] = "";
                            arrayAdapter.add(questionContentsList[y]);
                        }
                    }
                } else {
                    // 質問が1個もない状況で利用(DBの質問を初期化したときとか)
                    for(int y = 0; y < 10; y++) {
                        qIdList[y] = "";
                        questionName[y] = "";
                        questionArea[y] = "";
                        questionContentsList[y] = "";
                        questionAnonimity[y] = "";
                        arrayAdapter.add(questionContentsList[y]);
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
>>>>>>> imai
}
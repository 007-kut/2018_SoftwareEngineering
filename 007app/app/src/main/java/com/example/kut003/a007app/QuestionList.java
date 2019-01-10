//子育て窓口メイン画面(図24)
package com.example.kut003.a007app;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Locale;

public class QuestionList extends Activity {

    private EditText editText;
    public static final String questionContents = "com.example.kut003.a007app.1";
    public static final String qId = "com.example.kut003.a007app.2";

    private static final String[] questionContentsList = {
            // QIDに対応する内容を格納するための配列
            "保育園の入園に必要な手続きは？",
            "横田の成績が芳しくない",
            "かごめ",
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
    };

    private static final String[] qIdList = {
            // QIDを格納するための配列
          "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
          "11", "12", "13", "14", "15"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_list);

        // リスト表示
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.parentinglist);
        ListView listView = findViewById(R.id.listview);
        for (String str: questionContentsList) {
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
}
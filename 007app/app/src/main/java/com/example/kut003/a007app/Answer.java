package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Locale;

public class Answer extends Activity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);

        // itemを表示するTextViewが設定されているparentinglist.xmlを指す
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.parentinglist);

        // activity_main.xmlのlistViewにListViewをセット
        ListView listView = findViewById(R.id.listview);


        for (String str : texts) {
            // ArrayAdapterにitemを追加する
            arrayAdapter.add(str);
        }

        // adapterをListViewにセット
        listView.setAdapter(arrayAdapter);

        // itemがクリックされた時のリスナー
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
//                Toast.makeText(Parenting2.this,
//                        String.format(Locale.US,"%sがtapされました",texts[position]),
//                        Toast.LENGTH_LONG).show();
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
                startActivity(intent);
            }
        });
    }
}
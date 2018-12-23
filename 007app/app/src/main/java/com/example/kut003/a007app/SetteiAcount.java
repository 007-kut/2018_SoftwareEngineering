//アカウントのクラス

package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SetteiAcount extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settei_acount);

        final Button button_henkou = findViewById(R.id.button_acount_henkou);
        button_henkou.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button21 Perform action on click");
                Intent intent = new Intent(getApplication(), SetteiAcounthenkou.class);
                startActivity(intent);
            }
        });

        final Button button_sakujo = findViewById(R.id.button_acount_sakujo);
        button_sakujo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button22, Perform action on click");
                Intent intent = new Intent(getApplication(), SetteiAcountsakujo.class);
                startActivity(intent);
            }
        });

        final Button button_return= findViewById(R.id.button_acount_home);
        button_return.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button23, Perform action on click");
                Intent intent = new Intent(getApplication(), SetteiMain.class);
                startActivity(intent);
            }
        });
    }
}
//設定のメイン画面のクラス

package com.example.kut003.a007app;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class SetteiMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settei_main);

        final Button button_acount = findViewById(R.id.button_settei_acount);
        button_acount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button1, Perform action on click");
                Intent intent = new Intent(getApplication(), SetteiAcount.class);
                startActivity(intent);
            }
        });

        final Button button_toiawase = findViewById(R.id.button_settei_toiawase);
        button_toiawase.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug","button2, Perform action on click");
                Intent intent = new Intent(getApplication(), SetteiToiawase.class);
                startActivity(intent);
            }
        });

        final Button button_password = findViewById(R.id.button_settei_game_password);
        button_password.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug","button3, Perform action on click");
                Intent intent = new Intent(getApplication(), SetteiGamepassword.class);
                startActivity(intent);
            }
        });

        final Button button_return = findViewById(R.id.button_home);
        button_return.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug","button3, Perform action on click");
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

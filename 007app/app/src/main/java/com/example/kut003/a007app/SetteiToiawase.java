//問い合わせのクラス

package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Path;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.View;

public class SetteiToiawase extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settei_toiawase);

        final Button button_return= findViewById(R.id.button_toiawase_home);
        button_return.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button11, Perform action on click");
                Intent intent = new Intent(getApplication(), SetteiMain.class);
                startActivity(intent);
            }
        });

    }
}

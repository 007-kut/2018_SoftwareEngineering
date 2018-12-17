//材料をお店で選択する画面(図15)
package com.example.kut003.a007app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import java.util.Random;
import android.widget.Toast;
import android.view.Gravity;

public class BackHome extends Activity {

    private String lackedMessage = "LACKING";
    private int completeCheck = 0;
    Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.back_home);

        //料理を作る
        final Button button1 = findViewById(R.id.button_make);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button1, Perform action on click");
                completeCheck = rand.nextInt(2);
                if(completeCheck == 0) {
                    Intent intent = new Intent(getApplication(), CheckFood.class);
                    startActivity(intent);
                } else {
                    toastMake(lackedMessage);
                }
            }
        });

        //おつかいに行く
        final Button button2 = findViewById(R.id.button_go);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Log.d("debug", "button2, Perform action on click");
                Intent intent = new Intent(getApplication(), ChooseShop.class);
                startActivity(intent);
            }
        });
    }

    private void toastMake(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        // 位置調整
        toast.setGravity(Gravity.CENTER, 0, -200);
        toast.show();
    }
}

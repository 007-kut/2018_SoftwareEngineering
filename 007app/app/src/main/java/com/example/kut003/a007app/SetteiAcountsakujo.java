//アカウント削除のクラス

package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.Gravity;


public class SetteiAcountsakujo extends Activity {

    String toastMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settei_acountsakujo);

        Button button_yes = findViewById(R.id.button_sakujo_yes);

        toastMessage = "アカウントを削除しました";

        button_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMake(toastMessage, 0, -200);
            }
        });




        final Button button_no = findViewById(R.id.button_sakujo_no);
        button_no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button42, Perform action on click");
                Intent intent = new Intent(getApplication(), SetteiAcount.class);
                startActivity(intent);
            }
        });

        final Button button_return= findViewById(R.id.button_sakujo_home);
        button_return.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button44, Perform action on click");
                Intent intent = new Intent(getApplication(), SetteiMain.class);
                startActivity(intent);
            }
        });
    }

    private void toastMake(String message, int x, int y){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        // 位置調整
        toast.setGravity(Gravity.CENTER, x, y);
        toast.show();
    }
}

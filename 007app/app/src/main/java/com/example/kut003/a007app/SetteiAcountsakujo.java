//アカウント削除のクラス

package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.Gravity;

import java.io.FileOutputStream;
import java.io.IOException;


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
                toastMake(toastMessage);
                String word = "";
                String file = "gamePass.txt";
                writeFile(word, file);
                file = "userId.txt";
                writeFile(word, file);
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
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
    }

    private void toastMake(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, -200);
        toast.show();
    }

    //ファイルへの書き込み
    public void writeFile(String word, String file) {
        try (FileOutputStream fileOutputstream = openFileOutput(file, Context.MODE_PRIVATE);) {
            fileOutputstream.write(word.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

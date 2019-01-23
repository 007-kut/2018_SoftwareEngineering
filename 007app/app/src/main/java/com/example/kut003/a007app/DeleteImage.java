package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DeleteImage extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            final ShareQuestion sq = (ShareQuestion) this.getApplication();
            /*String chooseFood = sq.getChooseFood();*/
            setContentView(R.layout.delete_image);
            //機能設定画面に戻る

            final Button button0 = findViewById(R.id.button_back);
            button0.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(getApplication(), GrowHome.class);
                    startActivity(intent);
                }
            });
        }
}

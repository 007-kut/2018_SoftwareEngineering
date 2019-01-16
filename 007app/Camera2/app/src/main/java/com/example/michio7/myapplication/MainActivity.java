package com.example.michio7.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //XMLのButtonを検索する
        Button button = (Button) findViewById(R.id.camera_button);

        //Buttonがクリックされた時のイベントリスナー
        button.setOnClickListener(new OnClickListener(){

            //コールバックメソッド
            public void onClick(View view){

                //インテントの生成
                Intent intent = new Intent();

                //インテントのアクションを指定する
                intent.setAction("android.media.action.IMAGE_CAPTURE");

                //標準搭載されているカメラアプリのアクティビティを起動する
                startActivity(intent);

                //アクティビティを閉じる
                finish();
            }
        });
    }
}


package com.example.kut003.a007app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;


public class GrowHome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grow_home);

        //カメラ機能
        //XMLのButtonを検索する
        final Button button_camera = (Button) findViewById(R.id.button_grow_camera);
        button_camera.setOnClickListener(new OnClickListener() {
            //コールバックメソッド
            public void onClick(View view) {
                //インテントの生成
                //Intent intent = new Intent();
                //インテントのアクションを指定する
                //intent.setAction("android.media.action.IMAGE_CAPTURE");
                //標準搭載されているカメラアプリのアクティビティを起動する
                //startActivity(intent);
                //アクティビティを閉じる
                //finish();
                Intent intent = new Intent(getApplication(), Camera.class);
                startActivity(intent);
            }
        });

        //もどるボタン
        final Button button_back = findViewById(R.id.button_grow_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button_grow_back Perform action on click");
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

        //ギャラリー画面
        final Button button_gallery = findViewById(R.id.button_grow_gallery);
        button_gallery.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Log.d("debug", "button_grow_garally Perform action on click");
                Intent intent = new Intent(getApplication(), CheckImage.class);
                startActivity(intent);
            }
        });
    }
}

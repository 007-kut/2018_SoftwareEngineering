package com.example.kut003.a007app;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.ImageView;

public class GrowHome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grow_home);
        File dir = new File(Environment.getExternalStorageDirectory().getPath()+"/Pictures/");
        if(dir.exists()){
            File file = new File(dir.getAbsolutePath()+"/turbans.jpg");
            if (file.exists()) {
                Bitmap bm = BitmapFactory.decodeFile(file.getPath());
                ((ImageView)findViewById(R.id.imageView)).setImageBitmap(bm);
            }else{
                ;
            }
        }

        //XMLのButtonを検索する
        final Button button_camera = (Button) findViewById(R.id.button_grow_camera);
        //Buttonがクリックされた時のイベントリスナー
        button_camera.setOnClickListener(new OnClickListener() {
            //コールバックメソッド
            public void onClick(View view) {
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
        //もどるボタン
        final Button button_back = findViewById(R.id.button_grow_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button_grow_back Perform action on click");
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
        //共有ボタン
        final Button button_share = findViewById(R.id.button_grow_share);
        button_share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug","button_grow_share, Perform action on click");
                Intent intent = new Intent(getApplication(), SubActivity2.class);
                startActivity(intent);
            }
        });
        //削除ボタン
        final Button button_delete = findViewById(R.id.button_grow_delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug","button_grow_delete, Perform action on click");
                Intent intent = new Intent(getApplication(), SubActivity2.class);
                startActivity(intent);
            }
        });

        //画像の出力

    }
}

package com.example.kut003.a007app;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.widget.Toast;

import java.io.IOException;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.util.Locale;

public class CheckImage  extends AppCompatActivity {
    private static final int READ_REQUEST_CODE = 42;
    Bitmap bmp;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_image);

        imageView = (ImageView) findViewById(R.id.imageView);

        //@Override
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        // Filter to show only images, using the image MIME data type.
        intent.setType("image/*");
        startActivityForResult(intent, READ_REQUEST_CODE);

        //画像の表示
        //画像URIの受け渡し
        //Intent intent = getIntent();
        //Uri uri = intent.getIntExtra(GrowHome.EXTRA_DATA);

        //もどるボタン
        final Button button_back = findViewById(R.id.button_viewer_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button_grow_back Perform action on click");
                Intent intent = new Intent(getApplication(), GrowHome.class);
                startActivity(intent);
            }
        });
        //共有ボタン
        final Button button_share = findViewById(R.id.button_grow_share);
        button_share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button_grow_share, Perform action on click");
                Intent intent = new Intent(getApplication(), SubActivity2.class);
                startActivity(intent);
            }
        });
        //削除ボタン
        final Button button_delete = findViewById(R.id.button_grow_delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button_grow_delete, Perform action on click");
                Intent intent = new Intent(getApplication(), SubActivity2.class);
                startActivity(intent);
            }
        });
        final Button button_save = findViewById(R.id.button_grow_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button_grow_save, Perform action on click");
                Intent intent = new Intent(getApplication(), SubActivity2.class);
                startActivity(intent);
            }
        });
        /*databaseをゴニョニョしたら画像と連携させて実装
        //保存ボタン
        final Button button1 = findViewById(R.id.button_grow_save);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String questionContent = questionText.getText().toString();
                if(questionContent.isEmpty()) {
                    toastMake();
                } else {
                    String userId = readFile();    //パスワードの読み込み
                    dc.setLister(createListener());
                    dc.getContentsById("3", "UserID=" + userId, "Qcontents=" + questionContent, "Anonimity=" + checkAnonimity);
                }
            }
        });*/
    }
    //トースト表示
    /*
    private void toastMake() {
        String message = "正しく入力してください";
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        // 位置調整
        toast.setGravity(Gravity.CENTER, 0, -200);
        toast.show();
    }*/
    Intent resultData;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData){

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData()
            Uri uri = null;
            if (resultData.getData() != null) {
                uri = resultData.getData();
                try {
                    // Uriを表示
                    // textView.setText(String.format(Locale.US, "Uri:　%s", uri.toString()));
                    Bitmap bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imageView.setImageBitmap(bmp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /*@Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                try {
                    InputStream is = getContentResolver().openInputStream(data.getData());
                    bmp = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (NullPointerException e) {

                }
            }
        }*/
    }
}

package com.example.kut003.a007app;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Camera extends Activity, DatabaseModel{

    private final static int RESULT_CAMERA = 1001;
    private final static int REQUEST_PERMISSION = 1002;

    NameBookDBHelper dbHelper = new NameBookDBHelper( context );

    private ImageView imageView;
    private Uri cameraUri;
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_taking_photo);

        imageView = findViewById(R.id.image_view);

        // Android 6, API 23以上でパーミッシンの確認
        /*Button cameraButton = findViewById(R.id.camera_button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Android 6, API 23以上でパーミッシンの確認
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermission();
                }
                else {
                    cameraIntent();
                }
            }
        });*/


        if (Build.VERSION.SDK_INT >= 23) {
            checkPermission();
        }
        else {
            cameraIntent();
        }
        // もどるボタン
        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), GrowHome.class);
                startActivity(intent);
            }
        });

        // 削除ボタン
        Button buttonDelete = findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SubActivity2.class);
                startActivity(intent);
            }
        });
    }

    private void cameraIntent(){
        // 保存先のフォルダーを作成するケース
//        File cameraFolder = new File(
//                Environment.getExternalStoragePublicDirectory(
//                        Environment.DIRECTORY_PICTURES),"IMG");
//        cameraFolder.mkdirs();

        // 保存先のフォルダーをカメラに指定した場合
        File cameraFolder = new File(
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM),"Camera");


        // 保存ファイル名
        String fileName = new SimpleDateFormat(
                "ddHHmmss", Locale.US).format(new Date());
        filePath = String.format("%s/%s.jpg", cameraFolder.getPath(),fileName);

        // capture画像のファイルパス
        File cameraFile = new File(filePath);
        cameraUri = FileProvider.getUriForFile(
                Camera.this,
                getApplicationContext().getPackageName() + ".fileprovider",
                cameraFile);

        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, RESULT_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent intent) {
        if (requestCode == RESULT_CAMERA) {

            if(cameraUri != null){
                imageView.setImageURI(cameraUri);

                registerDatabase(filePath);
            }
            else{
            }
        }
    }

    // アンドロイドのデータベースへ登録する
    private void registerDatabase(String file) {
        ContentValues contentValues = new ContentValues();
        ContentResolver contentResolver = Camera.this.getContentResolver();
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        contentValues.put("_data", file);
        contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
        Log.d("VAR_DUMP", "!! -- MyDebug : " + MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                + " and " + MediaStore.Images.Media.MIME_TYPE);
    }

    // Runtime Permission check
    private void checkPermission(){
        // 既に許可している
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED){
            cameraIntent();
        }
        // 拒否していた場合
        else{
            requestPermission();
        }
    }

    // 許可を求める
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(Camera.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION);

        } else {
            Toast toast = Toast.makeText(this,
                    "許可されないとアプリが実行できません",
                    Toast.LENGTH_SHORT);
            toast.show();

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                    REQUEST_PERMISSION);

        }
    }

    // 結果の受け取り
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            // 使用が許可された
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                cameraIntent();

            } else {
                // それでも拒否された時の対応
                Toast toast = Toast.makeText(this,
                        "これ以上なにもできません", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
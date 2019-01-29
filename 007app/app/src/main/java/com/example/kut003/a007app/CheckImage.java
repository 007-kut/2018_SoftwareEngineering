package com.example.kut003.a007app;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.net.Uri;
import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import android.database.Cursor;
import android.graphics.Matrix;
import android.graphics.Point;
import android.view.Display;
import android.view.View.OnClickListener;
import android.view.WindowManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class CheckImage  extends AppCompatActivity {
    private static final int READ_REQUEST_CODE = 42;

    private Bitmap bmp;
    private ImageView iv;
    private Canvas canvas;
    private Uri uri;
    private String path;
    private int viewWidth, viewHeight;
    private DatabaseOpenHelper helper;
    private SQLiteDatabase db;

    public EditText editComment;
    public TextView viewComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_image);

        //コメント表示欄の処理
        editComment = findViewById(R.id.comment_text);
        viewComment = findViewById(R.id.comment_display);

        //画像の表示
        // ウィンドウマネージャのインスタンス取得
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        // ディスプレイのインスタンスを生成
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        // 画像サイズの取得
        display.getSize(point);
        viewWidth = point.x;
        viewHeight = point.y;

        iv = (ImageView) findViewById(R.id.image_view);
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/jpeg");
        startActivityForResult(intent, READ_REQUEST_CODE);

        //もどるボタン
        final Button button_back = findViewById(R.id.button_viewer_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button_grow_back Perform action on click");
                Intent intent = new Intent(getApplication(), GrowHome.class);
                startActivity(intent);
            }
        });
        /*
        //共有ボタン
        final Button button_share = findViewById(R.id.button_grow_share);
        button_share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button_grow_share, Perform action on click");
                Intent intent = new Intent(getApplication(), SubActivity2.class);
                startActivity(intent);
            }
        });
*/
        //削除ボタン
        /*
        final Button button_delete = findViewById(R.id.button_grow_delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                delete(Uri uri);
                //コメントの削除
                Intent intent = new Intent(getApplication(), DeleteImage.class);
                startActivity(intent);
                Log.d("debug", "button_grow_delete, Perform action on click");
            }
        });*/

        //保存ボタン
        Button insertButton = findViewById(R.id.button_grow_save);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helper == null){
                    helper = new DatabaseOpenHelper(getApplicationContext());
                }
                if(db == null){
                    db = helper.getWritableDatabase();
                }
                String key = editComment.getText().toString();
                insertData(db, key, uri);
                Log.d("debug", "コメントは保存されました。");
            }
        });
    }

    //画像の読み込み
    Intent resultData;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData){
        super.onActivityResult(requestCode, resultCode, resultData);
        uri = null;
        if (resultData != null) {
            uri = resultData.getData();
            try {
                // Uriを表示
                bmp = loadImage(uri, viewWidth, viewHeight);
                canvas = new Canvas(bmp);
                iv.setImageBitmap(bmp);
                //コメント機能
                readData(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //画像の可視化
    private Bitmap loadImage(Uri uri, int viewWidth, int viewHeight) {
        // Uriから画像を読み込みBitmapを作成
        Bitmap originalBitmap = null;
        try {
            originalBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // MediaStoreから回転情報を取得
        final int orientation;
        Cursor cursor = MediaStore.Images.Media.query(getContentResolver(), uri, new String[]{
                MediaStore.Images.ImageColumns.ORIENTATION
        });
        if (cursor != null) {
            cursor.moveToFirst();
            orientation = cursor.getInt(0);
        } else {
            orientation = 0;
        }

        int originalWidth = originalBitmap.getWidth();
        int originalHeight = originalBitmap.getHeight();

        // 縮小割合を計算
        final float scale;
        if (orientation == 90 || orientation == 270) {
            // 縦向きの画像は半分のサイズに変更
            scale = Math.min(((float) viewWidth / originalHeight) / 2, ((float) viewHeight / originalWidth) / 2);
        } else {
            // 横向きの画像
            scale = Math.min((float) viewWidth / originalWidth, (float) viewHeight / originalHeight);
        }

        // 変換行列の作成
        final Matrix matrix = new Matrix();
        if (orientation != 0) {
            //画像を回転させる
            matrix.postRotate(orientation);
        }
        if (scale < 1.0f) {
            // Bitmapを拡大縮小する
            matrix.postScale(scale, scale);
        }

        // 行列によって変換されたBitmapを返す
        return Bitmap.createBitmap(originalBitmap, 0, 0, originalWidth, originalHeight, matrix,
                true);
    }

    private void readData(Uri uri){
        String uris = uri.toString();
        if(helper == null){
            helper = new DatabaseOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "images",
                 new String[] {"comment"},
                "Path = ?",
                 new String[]{""+ uris},
                null,
                null,
                null
        );

        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++) {
            sbuilder.append(cursor.getString(0));
            cursor.moveToNext();
        }

        // 忘れずに！
        cursor.close();
        Log.d("debug","**********"+sbuilder.toString());
        viewComment.setText(sbuilder.toString());
    }

    //insertData(DataBase, comment, image URI)
    //コメントの更新
    private void insertData(SQLiteDatabase db, String com, Uri uri){
        path = uri.toString();
        //try {
            //db = SQLiteDatabase.openDatabase("images", null);
        //}catch (FileNotFoundException e){
            //db = null;
        //}

        deleteComment(path);

        ContentValues cv = new ContentValues();
        cv.put("Path", path);
        cv.put("Comment", com);

        db.insert("images", null, cv);
        readData(uri);
    }

    //コメントの削除
    private void deleteComment(String path){
        db.delete("images", "Path=?", new String[]{path});
    }
/*
    //画像の削除
    private void delete(Uri uri) {
        String outputPath = uri;
        Context context = this;

        File file = new File(outputPath);
        if (file.exists()){
            file.delete();
            //コメント削除
            deleteComment(outputPath);
            context.getContentResolver().delete(
                    MediaStore.Files.getContentUri("external");
                    MediaStore.Files.FileColumns.DATA + "=?",
                    new String[]{outputPath}
            );
        }
    }
    */


}

//イラスト画面(図6)
package com.example.kut003.a007app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Locale;

public class MakeArt extends AppCompatActivity implements View.OnClickListener{

    private PaintView drawingView;
    private String savedMessage;    //保存確認画面を表示するために使う

    private static final int REQUEST_SAVE_IMAGE = 1002;
    private TextView textView;

    private MediaProjectionManager mpManager;
    private MediaProjection mProjection;
    private static final int REQUEST_MEDIA_PROJECTION = 1001;

    private int displayWidth, displayHeight;
    private ImageReader imageReader;
    private VirtualDisplay virtualDisplay;
    private int screenDensity;
    private ImageView imageView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.make_art);

        textView = findViewById(R.id.text_view);
        imageView = findViewById(R.id.image_view);

        // 画面の縦横サイズとdpを取得
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenDensity = displayMetrics.densityDpi;
        displayWidth = displayMetrics.widthPixels;
        displayHeight = displayMetrics.heightPixels;

        mpManager = (MediaProjectionManager)
                getSystemService(MEDIA_PROJECTION_SERVICE);

        // permissionを確認するintentを投げ、ユーザーの許可・不許可を受け取る
        if(mpManager != null){
            startActivityForResult(mpManager.createScreenCaptureIntent(),
                    REQUEST_MEDIA_PROJECTION);
        }

        //ゲーム選択画面にもどる
        final Button button0 = findViewById(R.id.button_albam_back);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button1, Perform action on click");
                Intent intent = new Intent(getApplication(), ChooseArt.class);
                startActivity(intent);
            }
        });

        savedMessage = "ほぞんしたよ";

        //保存する
        final Button button1 = findViewById(R.id.button_save);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getScreenshot();

                Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_TITLE, "img.png");

                startActivityForResult(intent, REQUEST_SAVE_IMAGE);
                toastMake(savedMessage);
            }
        });

        //赤ボタン
        red_button= findViewById(R.id.button_red);
        red_button.setOnClickListener(this);
        //黒ボタン
        black_button= findViewById(R.id.button_black);
        black_button.setOnClickListener(this);
        //白ボタン
        white_button= findViewById(R.id.button_white);
        white_button.setOnClickListener(this);
        //青ボタン
        blue_button= findViewById(R.id.button_blue);
        blue_button.setOnClickListener(this);
        //黄色ボタン
        yellow_button= findViewById(R.id.button_yellow);
        yellow_button.setOnClickListener(this);
        //緑ボタン
        green_button= findViewById(R.id.button_green);
        green_button.setOnClickListener(this);

        this.drawingView = findViewById(R.id.drawing_view);

        findViewById(R.id.delete_button).setOnClickListener(deleteDrawing);
    }

    View.OnClickListener deleteDrawing = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            drawingView.delete();
        }
    };

    Button red_button,black_button,white_button,blue_button,yellow_button,green_button;
    public void onClick(View view){
        switch(view.getId()){
            case R.id.button_red:
                drawingView.setPen(Color.RED);
                Toast.makeText(this,"あか",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_white:
                drawingView.setPen(Color.WHITE);
                Toast.makeText(this,"しろ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_black:
                drawingView.setPen(Color.BLACK);
                Toast.makeText(this,"くろ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_blue:
                drawingView.setPen(Color.BLUE);
                Toast.makeText(this,"あお",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_yellow:
                drawingView.setPen(Color.YELLOW);
                Toast.makeText(this,"きいろ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_green:
                drawingView.setPen(Color.GREEN);
                Toast.makeText(this,"みどり",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void toastMake(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        // 位置調整
        toast.setGravity(Gravity.CENTER, 0, -200);
        toast.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (REQUEST_MEDIA_PROJECTION == requestCode) {
            if (resultCode != RESULT_OK) {
                // 拒否された
                Toast.makeText(this,
                        "User cancelled", Toast.LENGTH_LONG).show();
                return;
            }
            // 許可された結果を受け取る
            setUpMediaProjection(resultCode, resultData);
        }
        if (requestCode == REQUEST_SAVE_IMAGE && resultCode == Activity.RESULT_OK) {
            if(resultData.getData() != null){

                Uri uri = resultData.getData();

                // Uriを表示
                textView.setText(
                        String.format(Locale.US, "Uri:　%s",uri.toString()));

                try(OutputStream outputStream =
                            getContentResolver().openOutputStream(uri);) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void setUpMediaProjection(int code, Intent intent) {
        mProjection = mpManager.getMediaProjection(code, intent);
        setUpVirtualDisplay();
    }

    private void setUpVirtualDisplay() {
        imageReader = ImageReader.newInstance(
                displayWidth, displayHeight, PixelFormat.RGBA_8888, 2);

        virtualDisplay = mProjection.createVirtualDisplay("ScreenCapture",
                displayWidth, displayHeight, screenDensity,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                imageReader.getSurface(), null, null);
    }

    private void getScreenshot() {
        // ImageReaderから画面を取り出す
        Log.d("debug", "getScreenshot");

        Image image = imageReader.acquireLatestImage();
        Image.Plane[] planes = image.getPlanes();
        ByteBuffer buffer = planes[0].getBuffer();

        int pixelStride = planes[0].getPixelStride();
        int rowStride = planes[0].getRowStride();
        int rowPadding = rowStride - pixelStride * displayWidth;

        // バッファからBitmapを生成
        bitmap = Bitmap.createBitmap(
                displayWidth + rowPadding / pixelStride, displayHeight,
                Bitmap.Config.ARGB_8888);
        bitmap.copyPixelsFromBuffer(buffer);
        image.close();

        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
        if (virtualDisplay != null) {
            Log.d("debug","release VirtualDisplay");
            virtualDisplay.release();
        }
        super.onDestroy();
    }
}
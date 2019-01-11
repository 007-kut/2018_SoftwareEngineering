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

public class PaintApple extends AppCompatActivity implements View.OnClickListener {

    private PaintView drawingView;
    private String savedMessage;    //保存確認画面を表示するために使う

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.paint_apple);

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
                toastMake(savedMessage);
            }
        });

        //赤ボタン
        red_button = findViewById(R.id.button_red);
        red_button.setOnClickListener(this);
        //黒ボタン
        black_button = findViewById(R.id.button_black);
        black_button.setOnClickListener(this);
        //白ボタン
        white_button = findViewById(R.id.button_white);
        white_button.setOnClickListener(this);
        //青ボタン
        blue_button = findViewById(R.id.button_blue);
        blue_button.setOnClickListener(this);
        //黄色ボタン
        yellow_button = findViewById(R.id.button_yellow);
        yellow_button.setOnClickListener(this);
        //緑ボタン
        green_button = findViewById(R.id.button_green);
        green_button.setOnClickListener(this);

        this.drawingView = findViewById(R.id.drawing_view);

        findViewById(R.id.delete_button).setOnClickListener(deleteDrawing);
    }

    View.OnClickListener deleteDrawing = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            drawingView.delete();
        }
    };

    Button red_button, black_button, white_button, blue_button, yellow_button, green_button;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_red:
                drawingView.setPen(Color.RED);
                Toast.makeText(this, "あか", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_white:
                drawingView.setPen(Color.WHITE);
                Toast.makeText(this, "しろ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_black:
                drawingView.setPen(Color.BLACK);
                Toast.makeText(this, "くろ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_blue:
                drawingView.setPen(Color.BLUE);
                Toast.makeText(this, "あお", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_yellow:
                drawingView.setPen(Color.YELLOW);
                Toast.makeText(this, "きいろ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_green:
                drawingView.setPen(Color.GREEN);
                Toast.makeText(this, "みどり", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void toastMake(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        // 位置調整
        toast.setGravity(Gravity.CENTER, 0, -200);
        toast.show();
    }
}
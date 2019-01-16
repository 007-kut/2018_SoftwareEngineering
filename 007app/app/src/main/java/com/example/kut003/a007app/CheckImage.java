package com.example.kut003.a007app;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
public class CheckImage  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {yyyyyy
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_image);
        //ImageView imageView2 = findViewById(R.id.image_view_2);
        //imageView2.setImageResource(R.drawable.img_2);

        //もどるボタン
        final Button button_back = findViewById(R.id.button_viewer_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("debug", "button_grow_back Perform action on click");
                Intent intent = new Intent(getApplication(), GrowHome.class);
                startActivity(intent);
            }
        });
    }
}

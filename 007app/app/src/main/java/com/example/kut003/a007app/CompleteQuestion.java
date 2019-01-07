
package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CompleteQuestion extends Activity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_question);
        
        Intent intent = getIntent();
        String answer = intent.getStringExtra(MakePost.EXTRAMESSAGE);
        textView = findViewById(R.id.text_view);
        textView.setText(answer);

        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Parenting.class);
                startActivity(intent);
            }
        });
    }
}

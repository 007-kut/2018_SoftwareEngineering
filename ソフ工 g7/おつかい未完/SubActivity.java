    package com.example.reiya.myapplication;


    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;
    import android.widget.ImageButton;
    import android.content.Intent;

                    public class SubActivity extends AppCompatActivity {

                        private int count = 0;
                        static final int m = 1001;

                        protected void onCreate(Bundle savedInstanceState) {
                            super.onCreate(savedInstanceState);
                            setContentView(R.layout.activity_sub);
                            final TextView textView = findViewById(R.id.textView);
                            ImageButton ImageButton = findViewById(R.id.meat);
                            ImageButton.setOnClickListener(new View.OnClickListener() {
                                int num = Integer.parseInt(textView.getText().toString());

                                @Override
                                public void onClick(View view) {
                                    num = num + 1;
                                    textView.setText(String.valueOf(num));
                                    count++;
                                }
                            });

                            final TextView textView1 = findViewById(R.id.textView1);
                            ImageButton ImageButton1 = findViewById(R.id.hikiniku);
                            ImageButton1.setOnClickListener(new View.OnClickListener() {
                                int num1 = Integer.parseInt(textView1.getText().toString());

                                @Override
                                public void onClick(View view) {
                                    num1 = num1 + 1;
                                    textView1.setText(String.valueOf(num1));

                                }


                            });


                            Button returnButton = findViewById(R.id.return_button);
                            returnButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                    Intent intent = new Intent(SubActivity.this, com.example.reiya.myapplication.MainActivity.class);
                    // 値を引き渡す (識別名, 値)の順番で指定します
                    intent.putExtra("date", count);
                    // Activity起動
                    startActivityForResult( intent, m );
                    startActivity(intent);
                    finish();
                }
            });

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
            super.onActivityResult(requestCode, resultCode, intent);
            if(resultCode == RESULT_OK && requestCode == 1001 &&
                    null != intent) {

                int text1 = intent.getIntExtra("maincount",0);
                TextView textView = findViewById(R.id.textView);
                textView.setText(text1);

            }
        }
    }
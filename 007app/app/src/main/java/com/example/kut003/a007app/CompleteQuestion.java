//投稿完了画面(図26と29)
//CompleteQuestionって名前やけど回答投稿でも使ってるわ(今井)
package com.example.kut003.a007app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CompleteQuestion extends Activity {

    private TextView textView;
    String[] questionContentsList = new String[10];
    String[] qIdList = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_question);
        final ShareQuestion sq = (ShareQuestion) this.getApplication();
        DatabaseContents dc = new DatabaseContents();
        dc.setLister(createListener());
        dc.getContentsById("5");

        //投稿が成功したか否か表示
        Intent intent = getIntent();
        String answer = intent.getStringExtra(MakePost.EXTRAMESSAGE);
        textView = findViewById(R.id.text_view);
        textView.setText(answer);

        //質問一覧を更新してから子育て窓口のメイン画面に戻る
        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), QuestionList.class);
                sq.setContents(questionContentsList);
                sq.setId(qIdList);
                startActivity(intent);
            }
        });
    }

    //データベースアクセス結果
    private UploadTask.Listener createListener () {
        return new UploadTask.Listener() {
            // 通信が成功した場合の処理(result : 返り値)
            @Override
            public void onSuccess(String result) {
                String[][] datas = DatabaseContents.splitQuestionData(result);
                for (int y = 0; y < datas.length; y++) {
                    qIdList[y] = datas[y][0];
                    questionContentsList[y] = datas[y][3];
                }
            }

            //通信が失敗した場合の処理(result : 返り値)
            @Override
            public void onFailure(String result) {
                finish();
            }
        };
    }
}

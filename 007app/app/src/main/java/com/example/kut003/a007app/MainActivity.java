//機能設定画面(図2)
package com.example.kut003.a007app;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

    //子育て窓口で使う配列
    String[] questionContentsList = new String[10];
    String[] qIdList = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初期設定すんのかいせんのかい
        String pass = readFile();
        if(pass.isEmpty()) {
            Intent intent = new Intent(getApplication(), NewAccount.class);
            startActivity(intent);
        }

        //子育て窓口で使う
        final ShareQuestion sq = (ShareQuestion) this.getApplication();
        DatabaseContents dc = new DatabaseContents();
        dc.setLister(createListener());
        dc.getContentsById("5");

        //ゲーム
        final Button button_game = findViewById(R.id.button_game);
        button_game.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ChooseGame.class);
                startActivity(intent);
            }
        });

        //成長記録
        final Button button_grow = findViewById(R.id.button_grow);
        button_grow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SubActivity2.class);
                startActivity(intent);
            }
        });

        //子育て窓口
        final Button button_wind = findViewById(R.id.button_wind);
        button_wind.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sq.setContents(questionContentsList);
                sq.setId(qIdList);
                Intent intent = new Intent(getApplication(), QuestionList.class);
                startActivity(intent);
            }
        });

        //設定
        final Button button_setting = findViewById(R.id.button_setting);
        button_setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SetteiMain.class);
                startActivity(intent);
            }
        });
    }

    //パスワードを読み込むメソッド
    //別のクラスで作成してコンストラクタで呼び出そうとしたけど上手くいきませんでした
    //読み込む度にコピペはムダやからどうにかしたい(して)
    public String readFile() {
        String text = "";
        String file = "gamePass.txt";
        // try-with-resources
        try (FileInputStream fileInputStream = openFileInput(file);
             BufferedReader reader= new BufferedReader(
                     new InputStreamReader(fileInputStream, "UTF-8"))) {
            String lineBuffer;
            while( (lineBuffer = reader.readLine()) != null ) {
                text = lineBuffer ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    //データベースアクセス結果
    private UploadTask.Listener createListener () {
        return new UploadTask.Listener() {
            // 通信が成功した場合の処理(result : 返り値)
            @Override
            public void onSuccess(String result) {
                String[][] datas = DatabaseContents.splitQuestionData(result);
                for(int y = 0; y < datas.length; y++) {
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
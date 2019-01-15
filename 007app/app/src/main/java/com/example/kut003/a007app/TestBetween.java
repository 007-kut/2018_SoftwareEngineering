package com.example.kut003.a007app;

import android.app.Activity;

public class TestBetween extends Activity {

    public static final String questionContents = "com.example.kut003.a007app.111test";
    public static final String qId = "com.example.kut003.a007app.222test";
    String[] questionContentsList = new String[10];
    String[] qIdList = new String[10];
    public TestBetween(){
        DatabaseContents dc = new DatabaseContents();
        dc.getContentsById("5");
    }

    public static void main(String[] args) {

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

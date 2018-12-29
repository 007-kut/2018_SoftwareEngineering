package com.example.kut003.a007app;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/*
   ↓ 使い方 ↓ (textViewは事前に定義すること)

       DatabaseContents dc = new DatabaseContents();
       dc.setLister(createListener());
       dc.getContentsById(textView, "0");
                                     ↑id

      【 下のcreateListenerという関数もファイル内で定義してください。 (コピペでOK)】

       private UploadTask.Listener createListener () {
        return new UploadTask.Listener() {
            // 通信が成功した場合の処理(result : 返り値)
            @Override
            public void onSuccess(String result) {
                // 例: textView.setText(result);
            }
            //通信が失敗した場合の処理(result : 返り値)
            @Override
            public void onFailure(String result) {
            }
       };

   ↑ 使い方 ↑
   ※ 引数のidは『Google Spreadsheets』& 『Constants』クラス(定数定義クラス)を見ること。
   ※ なんか動作がおかしいと思ったらソースコードと出力ログを本城に送ってください。
*/


public class DatabaseContents extends AppCompatActivity {
    private UploadTask task;
    protected TextView textView;

    DatabaseContents() {
        task = new UploadTask();
    }

    public void getContentsById(TextView textView, String... params) {
        if (getLister() == null) {
            throw new ExceptionInInitializerError("listenerがセットされていません。詳しくは本城まで聞いて。");
        }
        this.textView = textView;
        task.execute(params);
    }

    @Override
    protected void onDestroy() {
        task.setListener(null);
        super.onDestroy();
    }

    public UploadTask.Listener getLister() {
        return task.listener;
    }

    public void setLister (UploadTask.Listener listener) {
        task.setListener(listener);
    }
}


class UploadTask extends AsyncTask<String, Void, String> {

    protected Listener listener;

    @Override
    protected String doInBackground(String... params) {

        String urlSt = Constants.getServerAddress(params[0]);
        HttpURLConnection httpConn = null;
        String result = null;

        try {
            URL url = new URL(urlSt);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setInstanceFollowRedirects(false);
            httpConn.setDoOutput(true);
            httpConn.setReadTimeout(10000);
            httpConn.setConnectTimeout(20000);
            httpConn.connect();
            OutputStream outStream = null;
            try {
                outStream = httpConn.getOutputStream();
                for (Integer i = 1; i < params.length; i++) {
                    outStream.write(params[i].getBytes("UTF-8"));
                    outStream.write("&".getBytes("UTF-8"));
                }
                outStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
                result="ネットワークエラー:UT01";
            } finally {
                if (outStream != null) {
                    outStream.close();
                }
            }

            final int status = httpConn.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpConn.getInputStream();
                String encoding = httpConn.getContentEncoding();
                if(null == encoding){
                    encoding = "UTF-8";
                }
                InputStreamReader inReader = new InputStreamReader(inputStream, encoding);
                BufferedReader bufReader = new BufferedReader(inReader);
                String line;
                while((line = bufReader.readLine()) != null) {
                    result = line;
                }
                bufReader.close();
                inReader.close();
            }
            else{
                result="ネットワークエラー:UT02";
            }
        } catch (IOException e) {
            e.printStackTrace();
            result="ネットワークエラー:UT03";
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (listener != null) {
            listener.onSuccess(result);
        } else {
            listener.onFailure(result);
        }
    }

    void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onSuccess(String result);
        void onFailure(String result);
    }

}

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
// ↓ 使い方 ↓ (textViewは事前に定義すること)
       DatabaseContents dc = new DatabaseContents();
       dc.getContentsById(textView, "0");
                                     ↑id
// ↑ 使い方 ↑
// ※ 引数のidは『Google Spreadsheets』& 『Constants』クラス(定数定義クラス)を見ること。
*/
public class DatabaseContents extends AppCompatActivity {
    private UploadTask task;
    protected TextView textView;

    public void getContentsById(TextView textView, String... params) {
        this.textView = textView;
        task = new UploadTask();
        task.setListener(createListener());
        task.execute(params);
    }

    @Override
    protected void onDestroy() {
        task.setListener(null);
        super.onDestroy();
    }

    private UploadTask.Listener createListener() {
        return new UploadTask.Listener() {
            @Override
            public void onSuccess(String result) {
                textView.setText(result);
            }
        };
    }
}


class UploadTask extends AsyncTask<String, Void, String> {

    private Listener listener;

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
        }
    }

    void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onSuccess(String result);
    }
}

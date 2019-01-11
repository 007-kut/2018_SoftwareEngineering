//QIDと質問をクラス間で共有するためのファイル
package com.example.kut003.a007app;

import android.app.Application;

public class ShareQuestion extends Application {
    private String [] savedContents = new String [10];
    private String [] SavedId = new String [10];
    private String chooseContents;
    private String chooseId;

    public  String[] getContents () {
        return this.savedContents;
    }
    public String[] getId() {
        return this.SavedId;
    }
    public String getChooseContents () {
        return this.chooseContents;
    }
    public String getChooseId() {
        return this.chooseId;
    }
    public void setContents(String[] array) {
        this.savedContents = array;
    }
    public void setId(String[] array) {
        this.SavedId = array;
    }
    public void setChooseContents(String contents) {
        this.chooseContents = contents;
    }
    public void setChooseId (String id) {
        this.chooseId = id;
    }

}

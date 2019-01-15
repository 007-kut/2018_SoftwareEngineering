//QIDと質問をクラス間で共有するためのファイル
package com.example.kut003.a007app;

import android.app.Application;

public class ShareQuestion extends Application {
    private String [] savedContents = new String [10];
    private String [] SavedId = new String [10];
    private String chooseId;
    private String chooseName;
    private String chooseArea;
    private String chooseContents;
    private String chooseAnonimity;


    public  String[] getContents () {
        return this.savedContents;
    }
    public String[] getId() {
        return this.SavedId;
    }
    public String getChooseId() {
        return this.chooseId;
    }
    public String getChooseName() {
        return this.chooseName;
    }
    public String getChooseArea() {
        return this.chooseArea;
    }
    public String getChooseContents () {
        return this.chooseContents;
    }
    public String getChooseAnonimity() {
        return this.chooseAnonimity;
    }
    public void setContents(String[] array) {
        this.savedContents = array;
    }
    public void setId(String[] array) {
        this.SavedId = array;
    }
    public void setChooseQuestion(String id, String name, String area, String contents, String anonimity) {
        this.chooseId = id;
        this.chooseName = name;
        this.chooseArea = area;
        this.chooseContents = contents;
        this.chooseAnonimity = anonimity;
    }
}

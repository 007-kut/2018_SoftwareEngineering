//QIDと質問をクラス間で共有するためのファイル
package com.example.kut003.a007app;

import android.app.Application;

public class ShareQuestion extends Application {
    //子育て窓口で使う
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

    //おつかいで使う
    private String chooseFood;
    private String chooseShop;
    private int onion = 0;
    private int carrots;
    private int pork = 0;
    private int beef = 0;
    private int mince = 0;
    private int egg = 0;
    private int milk = 0;

    public void setChooseFood(String food) {
        this.chooseFood = food;
    }
    public void setChooseShop(String shop) {
        this.chooseShop = shop;
    }
    public void setCountOnion(int count) {
        this.onion = count;
    }
    public void setCountPork(int count) {
        this.pork = count;
    }
    public void setCountCarrots(int count) {
        this.carrots = count;
    }
    public void setCountBeef(int count) {
        this.beef = count;
    }
    public void setCountMince(int count) {
        this.mince = count;
    }
    public void setCountEgg(int count) {
        this.egg = count;
    }
    public void setCountMilk(int count) {
        this.milk = count;
    }
    public String getChooseFood() {
        return this.chooseFood;
    }
    public String getChooseShop() {
        return this.chooseShop;
    }
    public int getOnion() {
        return this.onion;
    }
    public int getPork() {
        return this.pork;
    }
    public int getCarrots() {
        return this.carrots;
    }
    public int getBeef() {
        return this.beef;
    }
    public int getMince() {
        return this.mince;
    }
    public int getEgg() {
        return this.egg;
    }
    public int getMilk() {
        return this.milk;
    }
}



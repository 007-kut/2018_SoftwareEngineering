package com.example.kut003.a007app;

public final class Constants {

    public static final String SERVER_ADDRESS = "http://52.197.93.178/";

    //Google Spreadsheets と同じ。上からid=0。
    public static final String[] PHP_FILE_NAMES = {
            "GetQuestionContents",
            "InsertNewUser",
            "UpdateUserData",
            "InsertQuestion",
            "InsertAnswer",
            "GetNewest10QuestionData",
            "GetSearchedQuestionData",
            "GetAnswerContents"

    };

    public static String getServerAddress (String idstr) {
        int id = Integer.parseInt(idstr);
        return (SERVER_ADDRESS + PHP_FILE_NAMES[id] + ".php");
    }
}

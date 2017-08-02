package com.lhyla.itquiz.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.lhyla.itquiz.useful_methods.UsefulMethods;

import java.util.ArrayList;
import java.util.List;


public class DBQuerries {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public DBQuerries(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void startDbWritable() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void startDbReadable() {
        database = dbHelper.getReadableDatabase();
    }

    public void closeDb() {
        database.close();
    }

    public void clearDB() {
        database.execSQL("DELETE FROM " + Contract.QuestionsTable.TABLE_NAME);
    }

    public void addToBase(String question, String answer_A, String answer_B,
                          String answer_C, String answer_D, String correctAnswer) {
        ContentValues values = new ContentValues();

        values.put(Contract.QuestionsTable.COLUMN_QUESTION, question);
        values.put(Contract.QuestionsTable.COLUMN_ANSWER_A, answer_A);
        values.put(Contract.QuestionsTable.COLUMN_ANSWER_B, answer_B);
        values.put(Contract.QuestionsTable.COLUMN_ANSWER_C, answer_C);
        values.put(Contract.QuestionsTable.COLUMN_ANSWER_D, answer_D);
        values.put(Contract.QuestionsTable.COLUMN_CORRECT_ANSWER, correctAnswer);

        long dbRowNumber = database.insert
                (Contract.QuestionsTable.TABLE_NAME, null, values);

        UsefulMethods.printLOG("DBQuerries addToBase() rowNum: " + dbRowNumber);
    }

    public List<Question> getQuestionList() {
        List<Question> questionList = new ArrayList<>();

        Cursor cursor = database.query(Contract.QuestionsTable.TABLE_NAME,
                null, null, null, null, null, null);

        Question question;

        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                question = new Question();
                question.setID(cursor.getInt(0));
                question.setQuestion(cursor.getString(1));
                question.setAnswerA(cursor.getString(2));
                question.setAnswerB(cursor.getString(3));
                question.setAnswerC(cursor.getString(4));
                question.setAnswerD(cursor.getString(5));
                question.setCorrectAnswer(cursor.getString(6));
                questionList.add(question);
            }
        }
        return questionList;
    }
}

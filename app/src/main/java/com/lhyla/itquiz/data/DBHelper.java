package com.lhyla.itquiz.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RENT on 2017-08-01.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "Questions.db";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " +
            Contract.QuestionsTable.TABLE_NAME +
            " (" + Contract.QuestionsTable._ID + " INTEGER PRIMARY KEY," +
            Contract.QuestionsTable.COLUMN_QUESTION + " TEXT," +
            Contract.QuestionsTable.COLUMN_ANSWER_A + " TEXT," +
            Contract.QuestionsTable.COLUMN_ANSWER_B + " TEXT," +
            Contract.QuestionsTable.COLUMN_ANSWER_C + " TEXT," +
            Contract.QuestionsTable.COLUMN_ANSWER_D + " TEXT," +
            Contract.QuestionsTable.COLUMN_CORRECT_ANSWER + " TEXT)";

    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS "
            + Contract.QuestionsTable.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE);
        onCreate(db);
    }
}

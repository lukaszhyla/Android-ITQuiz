package com.lhyla.itquiz.data;

import android.provider.BaseColumns;



public class Contract {
    private Contract() {
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_ANSWER_A = "answer_A";
        public static final String COLUMN_ANSWER_B = "answer_B";
        public static final String COLUMN_ANSWER_C = "answer_C";
        public static final String COLUMN_ANSWER_D = "answer_D";
        public static final String COLUMN_CORRECT_ANSWER = "correctAnswer";
    }

    public static class ScoreTable implements BaseColumns {
        public static final String TABLE_NAME = "scores";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_POINTS = "points";
        public static final String COLUMN_QUESTIONS = "questions";
    }
}

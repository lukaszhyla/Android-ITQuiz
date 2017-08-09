package com.lhyla.itquiz.data;

import android.provider.BaseColumns;


class Contract {
    private Contract() {
    }

    static class QuestionsTable implements BaseColumns {
        static final String TABLE_NAME = "questions";
        static final String COLUMN_QUESTION = "question";
        static final String COLUMN_ANSWER_A = "answer_A";
        static final String COLUMN_ANSWER_B = "answer_B";
        static final String COLUMN_ANSWER_C = "answer_C";
        static final String COLUMN_ANSWER_D = "answer_D";
        static final String COLUMN_CORRECT_ANSWER = "correctAnswer";
    }

    static class ScoreTable implements BaseColumns {
        static final String TABLE_NAME = "scores";
        static final String COLUMN_DATE = "date";
        static final String COLUMN_POINTS = "points";
        static final String COLUMN_QUESTIONS = "questions";
    }
}

package com.lhyla.itquiz.data;

import android.provider.BaseColumns;

/**
 * Created by RENT on 2017-08-01.
 */

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
}

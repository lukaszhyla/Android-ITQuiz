package com.lhyla.itquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.act_start_quest_TV)
    TextView questionTV;

    @BindView(R.id.act_start_answer_A_TV)
    TextView answerATV;

    @BindView(R.id.act_start_answer_B_TV)
    TextView getAnswerBTV;

    @BindView(R.id.act_start_answer_C_TV)
    TextView getAnswerCTV;

    @BindView(R.id.act_start_answer_D_TV)
    TextView getAnswerDTV;

    private List<Integer> drawnQuestion;
    private List<Question> questions;
    private Question actualQuestion;
    private static final String correctInfo = "Correct";
    private static final String incorrectInfo = "Incorrect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        drawnQuestion = new ArrayList<>();
        questions = new ArrayList<>();
        loadQuestion();
    }



    private void oneOfAnswersOnClick(String answer) {
        if (checkIsAnswerIsCorrect(actualQuestion, answer)) {
            makeInfoToast(correctInfo);
        } else {
            makeInfoToast(incorrectInfo);
        }
    }



    private boolean checkIsAnswerIsCorrect(Question question, String answer) {
        return answer.equals(question.getCorrectAnswer());
    }

    private void loadQuestion() {
        questions = createQuestion();
        actualQuestion = drawQuestionWithoutRepeating();
        questionTV.setText(actualQuestion.getQuestion());
    }

    public Question drawQuestionWithoutRepeating() {
        Random random = new Random();
        Integer numOfQuestion = random.nextInt(questions.size());

        while (drawnQuestion.contains(numOfQuestion)) {
            numOfQuestion = random.nextInt(questions.size());
        }
        drawnQuestion.add(numOfQuestion);

        UsefulMethods.printLOG("Drawn Questions " + drawnQuestion.toString());
        UsefulMethods.printLOG("Num of Question " + numOfQuestion);
        return questions.get(numOfQuestion);
    }

    private List<Question> createQuestion() {
        Question question1 =
                new Question("What kind of variable you cannot create in Java?",
                        "var", "int", "String", "char", "A", 0);

        Question question2 =
                new Question("Who is the founder of Amazon?", "Steve Jobs",
                        "Linus Torvalds", "Bill Gates", " Jeff Bezos", "D", 1);

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);

        return questions;
    }

    @OnClick(R.id.act_start_answer_A_TV)
    protected void answerAOnClick() {
        oneOfAnswersOnClick("A");
    }

    @OnClick(R.id.act_start_answer_B_TV)
    protected void answerBOnClick() {
        oneOfAnswersOnClick("B");
    }

    @OnClick(R.id.act_start_answer_C_TV)
    protected void answerCOnClick() {
        oneOfAnswersOnClick("C");
    }

    @OnClick(R.id.act_start_answer_D_TV)
    protected void answerDOnClick() {
        oneOfAnswersOnClick("D");
    }

    private void makeInfoToast(String info) {
        Toast toast = Toast.makeText(StartActivity.this, info, Toast.LENGTH_LONG);
        toast.show();
    }
}

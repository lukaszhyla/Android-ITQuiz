package com.lhyla.itquiz;

import android.content.Intent;
import android.support.annotation.Nullable;
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
    TextView answerBTV;

    @BindView(R.id.act_start_answer_C_TV)
    TextView answerCTV;

    @BindView(R.id.act_start_answer_D_TV)
    TextView answerDTV;

    private List<Integer> drawnQuestion;
    private List<Question> questions;

    private int questionsAsked;
    private int points;

    private Question currentQuestion;
    private Question lastQuestion;

    private static final String correctInfo = "Correct";
    private static final String incorrectInfo = "Incorrect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        drawnQuestion = new ArrayList<>();
        questions = createQuestion();

        updateCurrentQuestion();
        loadActualQuestion();
        loadActualAnswers();

    }


    private void oneOfAnswersOnClick(String answer) {
        UsefulMethods.printLOG("One of answers on Click");
        if (checkIsAnswerIsCorrect(answer)) {
            questionsAsked++;
            points++;
            makeInfoShortToast(correctInfo);
        } else {
            makeInfoShortToast(incorrectInfo);
            questionsAsked++;
        }
        updateCurrentQuestion();
        loadActualQuestion();
        loadActualAnswers();
    }

    private boolean checkIsAnswerIsCorrect(String answer) {
        return answer.equals(currentQuestion.getCorrectAnswer());
    }

    private void loadActualQuestion() {
        questionTV.setText(currentQuestion.getQuestion());
    }

    private void loadActualAnswers() {
        answerATV.setText(currentQuestion.getAnswerA());
        answerBTV.setText(currentQuestion.getAnswerB());
        answerCTV.setText(currentQuestion.getAnswerC());
        answerDTV.setText(currentQuestion.getAnswerD());
    }

    private void updateCurrentQuestion() {
        currentQuestion = drawQuestionWithoutRepeating();

        if (currentQuestion == null) {
            //When current question is null
            //last question is the current question to save the view.
            if (lastQuestion != null) {
                currentQuestion = lastQuestion;
                UsefulMethods.printLOG("End of questions");
                Intent intent = new Intent(this, EndGameActivity.class);
                intent.putExtra("AskedQuestions", questionsAsked);
                intent.putExtra("Points", points);
                startActivity(intent);
            } else {
                currentQuestion = new Question("Question", "A", "B", "C", "D", "A", -1);
                UsefulMethods.printLOG("Default question");
            }

        } else {
            //Last question is the currentQuestion when the current question is`t null.
            lastQuestion = currentQuestion;
        }
    }

    @Nullable
    private Question drawQuestionWithoutRepeating() {
        Random random = new Random();
        Integer numOfQuestion = random.nextInt(questions.size());

        while (drawnQuestion.contains(numOfQuestion)) {
            numOfQuestion = random.nextInt(questions.size());
            if (drawnQuestion.size() == questions.size()) {
                return null;
            }
        }
        drawnQuestion.add(numOfQuestion);

        UsefulMethods.printLOG("Drawn Questions " + drawnQuestion.toString());
        UsefulMethods.printLOG("Num of Question " + numOfQuestion);
        return questions.get(numOfQuestion);
    }

    private List<Question> createQuestion() {
        Question question1 =
                new Question("What kind of primitive variable type you cannot create in Java?",
                        "var", "short", "byte", "char", "A", 1);

        Question question2 =
                new Question("Who is the founder of Amazon?", "Steve Jobs",
                        "Linus Torvalds", "Bill Gates", " Jeff Bezos", "D", 2);

        Question question3 = new Question("Where Google has it`s main Headquarters",
                "New York", "Los Angel", "Mountain View", "Chicago", "C", 3);

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        return questions;
    }

    @OnClick(R.id.act_start_answer_A_TV)
    protected void answerAOnClick() {
        UsefulMethods.printLOG("Answer A On Click");
        oneOfAnswersOnClick("A");
    }

    @OnClick(R.id.act_start_answer_B_TV)
    protected void answerBOnClick() {
        UsefulMethods.printLOG("Answer B On Click");
        oneOfAnswersOnClick("B");
    }

    @OnClick(R.id.act_start_answer_C_TV)
    protected void answerCOnClick() {
        UsefulMethods.printLOG("Answer C On Click");
        oneOfAnswersOnClick("C");
    }

    @OnClick(R.id.act_start_answer_D_TV)
    protected void answerDOnClick() {
        UsefulMethods.printLOG("Answer D On Click");
        oneOfAnswersOnClick("D");
    }

    private void makeInfoShortToast(String info) {
        Toast toast = Toast.makeText(StartActivity.this, info, Toast.LENGTH_SHORT);
        toast.show();
    }


}

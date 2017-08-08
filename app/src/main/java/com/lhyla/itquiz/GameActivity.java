package com.lhyla.itquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.lhyla.itquiz.async_tasks.TimeCountAsyncTask;
import com.lhyla.itquiz.data.DBQuerries;
import com.lhyla.itquiz.data.Question;
import com.lhyla.itquiz.useful_methods.UsefulMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity {

    @BindView(R.id.act_game_quest_TV)
    TextView questionTV;

    @BindView(R.id.act_game_answer_A_TV)
    TextView answerATV;

    @BindView(R.id.act_game_answer_B_TV)
    TextView answerBTV;

    @BindView(R.id.act_game_answer_C_TV)
    TextView answerCTV;

    @BindView(R.id.act_game_points_to_gain_TV)
    TextView pointsToGainTV;

    @BindView(R.id.act_game_actual_sum_of_points_TV)
    TextView sumOfPointsTV;

    @BindView(R.id.act_game_answer_D_TV)
    TextView answerDTV;

    private static final String correctInfo = "Correct";
    private static final String incorrectInfo = "Incorrect";

    private List<Integer> drawnQuestion;
    private List<Question> questions;

    private int questionsAsked;
    private int sumOfPoints;
    private int pointsToGain;

    private Question currentQuestion;
    private Question lastQuestion;

    private DBQuerries dbQuerries;

    private TimeCountAsyncTask timeCountAsyncTask;

    private boolean noMoreQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        UsefulMethods.printLOG("GameActivity onCreate()");

        dbQuerries = new DBQuerries(GameActivity.this);
        dbQuerries.startDbWritable();
        ifEmptyCreateQuestionList();

        drawnQuestion = new ArrayList<>();
        questions = dbQuerries.getQuestionList();

        loadQuestionOrEndActivity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        UsefulMethods.printLOG("GameActivity onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        UsefulMethods.printLOG("GameActivity onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        UsefulMethods.printLOG("GameActivity onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        UsefulMethods.printLOG("GameActivity onPause()");
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        UsefulMethods.printLOG("GameActivity onStop()");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UsefulMethods.printLOG("GameActivity onDestroy()");
        timeCountAsyncTask.cancel(true);
    }

    @Override
    public void onBackPressed() {
        UsefulMethods.printLOG("GameActivity onBackPressed()");
    }

    @OnClick(R.id.act_game_answer_A_TV)
    protected void answerAOnClick() {
        UsefulMethods.printLOG("Answer A On Click");
        oneOfAnswersOnClick("A");
    }

    @OnClick(R.id.act_game_answer_B_TV)
    protected void answerBOnClick() {
        UsefulMethods.printLOG("Answer B On Click");
        oneOfAnswersOnClick("B");
    }

    @OnClick(R.id.act_game_answer_C_TV)
    protected void answerCOnClick() {
        UsefulMethods.printLOG("Answer C On Click");
        oneOfAnswersOnClick("C");
    }

    @OnClick(R.id.act_game_answer_D_TV)
    protected void answerDOnClick() {
        UsefulMethods.printLOG("Answer D On Click");
        oneOfAnswersOnClick("D");
    }

    private void oneOfAnswersOnClick(String answer) {
        UsefulMethods.printLOG("GameActivity oneOfAnswersOnClick()");
        timeCountAsyncTask.cancel(true);
        if (checkIsAnswerIsCorrect(answer)) {
            questionsAsked++;
            sumOfPoints += pointsToGain;
            UsefulMethods.printLOG("GameActivity oneOfAnswersOnClick()" +
                    "answerIsCorrect, sumOfPoints = " + sumOfPoints);
            updateSumOfPointsTV(sumOfPoints);
            makeInfoShortToast(correctInfo);
        } else {
            UsefulMethods.printLOG("GameActivity oneOfAnswersOnClick()" +
                    "answerIsWrong");
            makeInfoShortToast(incorrectInfo);
            questionsAsked++;
        }
        loadQuestionOrEndActivity();
    }

    private void loadQuestionOrEndActivity() {
        updateCurrentQuestion();
        UsefulMethods.printLOG("GameActivity oneOfAnswersOnClick()" +
                " " + "noMoreQuestions = " + noMoreQuestions);
        if (!noMoreQuestions) {
            loadActualQuestion();
            loadActualAnswers();
        } else {
            startEndGameActivity();
        }
    }

    private void updateSumOfPointsTV(Integer points) {
        UsefulMethods.printLOG("GameActivity updateSumOfPointsTV()" +
                " actualSum = " + points);

        String sumOfPointsString = "Sum of points: " + String.valueOf(points);
        sumOfPointsTV.setText(sumOfPointsString);
    }


    public void updatePointsToGain(Integer pointsToGain) {
        this.pointsToGain = pointsToGain;
        UsefulMethods.printLOG("GameActivity updatePointsToGain() " +
                "this.pointsToGain = " + this.pointsToGain);

        String pointsToGainString = "Points to gain: "
                + String.valueOf(pointsToGain);
        pointsToGainTV.setText(pointsToGainString);
    }

    private void loadActualQuestion() {
        UsefulMethods.printLOG("GameActivity loadActualQuestion()");
        questionTV.setText(currentQuestion.getQuestion());
        timeCountAsyncTask = new TimeCountAsyncTask(GameActivity.this);
        timeCountAsyncTask.execute();
    }

    private void loadActualAnswers() {
        answerATV.setText(currentQuestion.getAnswerA());
        answerBTV.setText(currentQuestion.getAnswerB());
        answerCTV.setText(currentQuestion.getAnswerC());
        answerDTV.setText(currentQuestion.getAnswerD());
    }

    private boolean checkIsAnswerIsCorrect(String answer) {
        return answer.equals(currentQuestion.getCorrectAnswer());
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

        UsefulMethods.printLOG("GameActivity drawQuestionWithoutRepeating() " +
                "Drawn Questions " + drawnQuestion.toString());
        UsefulMethods.printLOG("GameActivity drawQuestionWithoutRepeating() " +
                "Num of actual Question " + numOfQuestion);
        return questions.get(numOfQuestion);
    }

    private void updateCurrentQuestion() {
        currentQuestion = drawQuestionWithoutRepeating();

        if (currentQuestion == null) {
            //When current question is null
            //last question is the current question to save the view.
            if (lastQuestion != null) {
                currentQuestion = lastQuestion;
                UsefulMethods.printLOG("GameActivity updateCurrentQuestion() end of questions");
                noMoreQuestions = true;
            } else {
                currentQuestion = new Question("Question", "A", "B", "C", "D", "A", -1);
                UsefulMethods.printLOG("GameActivity updateCurrentQuestion() default question");
            }
        } else {
            //Last question is the currentQuestion when the current question is`t null.
            lastQuestion = currentQuestion;
        }
    }

    private void startEndGameActivity() {
        Intent intent = new Intent(this, EndGameActivity.class);
        intent.putExtra("AskedQuestions", questionsAsked);
        intent.putExtra("Points", sumOfPoints);
        startActivity(intent);
        this.finish();
    }

    private void ifEmptyCreateQuestionList() {
        if (dbQuerries.getQuestionList().isEmpty()) {
            UsefulMethods.printLOG("GameActivity ifEmptyCreateQuestionList()");
            createQuestion();
        }
    }

    private void createQuestion() {
        dbQuerries.addToBase("Where Google has it main Headquarters",
                "New York", "Los Angel", "Mountain View", "Chicago", "C");

        dbQuerries.addToBase("Who is the founder of Amazon?", "Steve Jobs",
                "Linus Torvalds", "Bill Gates", " Jeff Bezos", "D");

        dbQuerries.addToBase("What kind of primitive variable type you cannot create in Java?",
                "var", "short", "byte", "char", "A");
    }

    private void makeInfoShortToast(String info) {
        Toast toast = Toast.makeText(GameActivity.this, info, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void timeEnds() {
        oneOfAnswersOnClick("No Answer");
    }


}

package com.lhyla.itquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lhyla.itquiz.data.DBQueries;
import com.lhyla.itquiz.data.Score;
import com.lhyla.itquiz.useful_methods.UsefulMethods;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EndGameActivity extends AppCompatActivity {

    @BindView(R.id.act_end_game_asked_questions_TV)
    TextView askedQuestionsTV;

    @BindView(R.id.act_end_game_points_TV)
    TextView pointsTV;

    private DBQueries dbQueries;
    private List<Score> bestScoreList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        ButterKnife.bind(this);

        dbQueries = new DBQueries(EndGameActivity.this);
        dbQueries.startDbWritable();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Integer askedQuestions = getAskedQuestions(bundle);
        Integer points = getPoints(bundle);

        addToScoreList(String.valueOf(points), String.valueOf(askedQuestions));

        bestScoreList = getBestScoreSortedList();
        for (Score score : bestScoreList) {
            UsefulMethods.printLOG("EndGameActivity onCreate()" + score.toString());
        }

        pointsTV.setText(String.valueOf(points));
        askedQuestionsTV.setText(String.valueOf(askedQuestions));

        UsefulMethods.printLOG("EndGameActivity onCreate() askedQuestions =  " + askedQuestions);
        UsefulMethods.printLOG("EndGameActivity onCreate() points = " + points);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbQueries.closeDb();
    }

    @Override
    public void onBackPressed() {
    }

    @OnClick(R.id.act_eng_game_play_again_btn)
    public void playAgainBtnOnClick() {
        Intent intent = new Intent(EndGameActivity.this, StartActivity.class);
        startActivity(intent);
        UsefulMethods.printLOG("EndGameActivity playAgainBtnOnClick()");
        this.finish();
        UsefulMethods.printLOG("EndGameActivity playAgainBtnOnClick() finish()");
    }

    private Integer getPoints(Bundle bundle) {
        Integer points = (Integer) bundle.get("Points");
        if (points == null) {
            points = 0;
        }
        return points;
    }

    private Integer getAskedQuestions(Bundle bundle) {
        Integer askedQuestions = (Integer) bundle.get("AskedQuestions");
        if (askedQuestions == null) {
            askedQuestions = 0;
        }
        return askedQuestions;
    }

    private void addToScoreList(String points, String questions) {
        bestScoreList = getBestScoreSortedList();

        if (isListSizeMoreOrEqualsThanTen(bestScoreList)) {
            UsefulMethods.printLOG("EndGameActivity addToScoreList listIsMoreOrEqualsThanTen");
            Integer intPoints = Integer.parseInt(bestScoreList.get(bestScoreList.size() - 1).getPoints());
            if (Integer.parseInt(points) > intPoints) {
                UsefulMethods.printLOG(
                        "EndGameActivity addToScoreList listIsMoreOrEqualsThanTen" +
                                "user collect more points than his 10 record!");
                Integer idToRemove = bestScoreList.get(bestScoreList.size() - 1).getID();
                dbQueries.removeRecordById(idToRemove);
                dbQueries.addScoreToBase(new Date().toString(), points, questions);
            }
        } else {
            dbQueries.addScoreToBase(new Date().toString(), points, questions);
        }

        UsefulMethods.printLOG("EndGameActivity addToScoreList()");
    }

    private List<Score> getBestScoreSortedList() {
        UsefulMethods.printLOG("EndGameActivity getBestScoreSortedList()");
        return dbQueries.getScoreSordetList();
    }

    private boolean isListSizeMoreOrEqualsThanTen(List<Score> list) {
        return bestScoreList.size() >= 10;
    }
}

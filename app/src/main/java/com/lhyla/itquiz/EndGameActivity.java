package com.lhyla.itquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lhyla.itquiz.useful_methods.UsefulMethods;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EndGameActivity extends AppCompatActivity {

    @BindView(R.id.act_end_game_asked_questions_TV)
    TextView askedQuestionsTV;

    @BindView(R.id.act_end_game_points_TV)
    TextView pointsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Integer askedQuestions = getAskedQuestions(bundle);
        Integer points = getPoints(bundle);

        pointsTV.setText(String.valueOf(points));
        askedQuestionsTV.setText(String.valueOf(askedQuestions));

        UsefulMethods.printLOG("Asked Questions: " + askedQuestions);
        UsefulMethods.printLOG("Points: " + points);

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

    @Override
    public void onBackPressed() {
    }

    @OnClick(R.id.act_eng_game_play_again_btn)
    protected void playAginBtnOnClick() {
        finish();
    }
}

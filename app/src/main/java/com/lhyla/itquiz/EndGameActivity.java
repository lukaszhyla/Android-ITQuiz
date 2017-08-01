package com.lhyla.itquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EndGameActivity extends AppCompatActivity {

    @BindView(R.id.act_end_game_asked_questions_TV)
    TextView askedQuestionsTV;

    @BindView(R.id.act_end_game_points_TV)
    TextView poitsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        int askedQuestions = (Integer) bundle.get("AskedQuestions");
        int points = (Integer) bundle.get("Points");

        poitsTV.setText(String.valueOf(points));
        askedQuestionsTV.setText(String.valueOf(askedQuestions));

        UsefulMethods.printLOG("Asked Questions: " + askedQuestions);
        UsefulMethods.printLOG("Points: " + points);

    }

    @Override
    public void onBackPressed() {
    }
}

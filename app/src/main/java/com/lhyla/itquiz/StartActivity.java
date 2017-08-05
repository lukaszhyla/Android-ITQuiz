package com.lhyla.itquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.act_start_game_starts)
    protected void startGameOnClickTV() {
        Intent intent = new Intent(StartActivity.this, GameActivity.class);
        startActivity(intent);
    }
}

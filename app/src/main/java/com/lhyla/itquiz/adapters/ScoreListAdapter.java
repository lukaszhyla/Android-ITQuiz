package com.lhyla.itquiz.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lhyla.itquiz.R;
import com.lhyla.itquiz.data.Score;
import com.lhyla.itquiz.useful_methods.UsefulMethods;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ScoreListAdapter extends RecyclerView.Adapter<ScoreListAdapter.MyViewHolder> {
    private List<Score> bestListScore;
    Context context;

    public ScoreListAdapter(List<Score> bestListScore, Context context) {
        this.bestListScore = bestListScore;
        this.context = context;
    }

    @Override
    public ScoreListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_score, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ScoreListAdapter.MyViewHolder holder, int position) {
        final Score score = bestListScore.get(position);
        holder.pointsTV.setText(score.getPoints());
        holder.questionsTV.setText(score.getQuestions());
        holder.date.setText(formatDate(score.getDate()));
    }

    private String formatDate(String dateStringParam) {
        Date date = new Date(dateStringParam);
        String stringDate = DateFormat.getDateTimeInstance().format(date);
        UsefulMethods.printLOG("ScoreListAdapter formatDate() " + date.toString());
        return stringDate;
    }

    @Override
    public int getItemCount() {
        return bestListScore.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView pointsTV;
        private TextView questionsTV;
        private TextView date;

        MyViewHolder(View itemView) {
            super(itemView);
            pointsTV = (TextView) itemView.findViewById(R.id.row_score_points_TV);
            questionsTV = (TextView) itemView.findViewById(R.id.row_score_asked_questions_TV);
            date = (TextView) itemView.findViewById(R.id.row_score_date_TV);

        }

    }
}

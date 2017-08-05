package com.lhyla.itquiz.async_tasks;


import android.os.AsyncTask;
import android.widget.TextView;

import com.lhyla.itquiz.GameActivity;
import com.lhyla.itquiz.R;
import com.lhyla.itquiz.useful_methods.UsefulMethods;


public class TimeCountAsyncTask extends AsyncTask<Void, Integer, Void> {

    private TextView timeTextView;
    private GameActivity gameActivity;

    public TimeCountAsyncTask(GameActivity activity) {
        this.gameActivity = activity;
        timeTextView = (TextView) activity.findViewById(R.id.act_game_time_counter_TV);
        UsefulMethods.printLOG("TimeCountAsyncTask constructor");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        UsefulMethods.printLOG("TimeCountAsyncTask onPreExecute()");

    }

    @Override
    protected Void doInBackground(Void... params) {
        UsefulMethods.printLOG("TimeCountAsyncTask doInBackground");
        for (int i = 10; i >= 0; i--) {
            if (!isCancelled()) {
                publishProgress(i);
                threadSleep(1000);
            } else break;
        }
        UsefulMethods.printLOG("TimeCountAsyncTask doInBackground isCancelled() " + isCancelled());
        return null;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        UsefulMethods.printLOG("TimeCountAsyncTask onProgressUpdate() values[0] = " + values[0]);
        if (values[0] == 0) {
            gameActivity.timeEnds();
        }
        gameActivity.updatePointsToGain(values[0]);
        timeTextView.setText("Time left: " + values[0] + " sec.");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        UsefulMethods.printLOG("TimeCountAsyncTask onPostExecute()");
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        UsefulMethods.printLOG("TimeCountAsyncTask onCancelled()" + isCancelled());

    }

    private void threadSleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

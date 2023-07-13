package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private WeakReference<TextView> mTextView;
    private ProgressBar mProgressBar;

    SimpleAsyncTask(TextView tv, ProgressBar progressBar) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = progressBar;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);
        int s = n * 200;

        for (int i = 0; i <= 100; i++) {
            publishProgress(i);
            try {
                Thread.sleep(s / 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return "Enfin réveillé après avoir dormi pendant " + s + " millisecondes !";
    }

    protected void onProgressUpdate(Integer... progress) {
        mProgressBar.setProgress(progress[0]);
        mTextView.get().setText(mTextView.get().getContext().getString(R.string.sleeping_percentage, progress[0]));
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}

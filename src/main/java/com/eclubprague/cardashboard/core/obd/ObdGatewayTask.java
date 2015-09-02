package com.eclubprague.cardashboard.core.obd;

import android.app.Service;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

public class ObdGatewayTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    DummyGatewayService dummyGatewayService;

    public ObdGatewayTask(DummyGatewayService s) {
        dummyGatewayService = s;
    }

    @Override
    protected Result doInBackground(Params... params) {
        while (true) {
            try {
                if (dummyGatewayService.isQueueEmpty()) {
                    Thread.sleep(100);
                } else {
                    ObdCommandJob job = dummyGatewayService.dequeue();
                    Long randomId = Long.valueOf((long) (Math.random() * 1000));
                    Log.d("OGT", "random ID "+randomId);
                    job.setId(randomId);
                    dummyGatewayService.putResult(job.getCommand().getClass(), job);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

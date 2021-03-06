package com.eclubprague.cardashboard.core.application;

import android.app.Application;
import android.content.Intent;

import com.eclubprague.cardashboard.core.application.update.UpdateFrequency;
import com.eclubprague.cardashboard.core.application.update.UpdateRunnable;
import com.eclubprague.cardashboard.core.obd.OBDGatewayService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 11. 8. 2015.
 */
public class UpdateApplication extends Application {
    private List<HandlerTimer> timers = new ArrayList<>();

    private final long DELAY = 100;

    @Override
    public void onCreate() {
        super.onCreate();
        for (UpdateFrequency updateFrequency : UpdateFrequency.values()) {
            timers.add(new HandlerTimer(new UpdateRunnable(updateFrequency), updateFrequency.getInterval()));
        }
        for (HandlerTimer timer : timers) {
            timer.start(DELAY);
        }

        Intent t = new Intent(this, OBDGatewayService.class);
        startService(t);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (HandlerTimer timer : timers) {
            timer.stop();
        }
    }
}

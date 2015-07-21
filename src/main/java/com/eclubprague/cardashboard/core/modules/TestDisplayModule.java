package com.eclubprague.cardashboard.core.modules;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.eclubprague.cardashboard.core.modules.base.AbstractDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.ISubmenuModule;
import com.eclubprague.cardashboard.core.modules.base.models.ModuleUpdateEvent;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

import java.util.Random;

import de.greenrobot.event.EventBus;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Simple display module implementation for testing.
 */
public class TestDisplayModule extends AbstractDisplayModule {
    private UpdateTask task;


    public TestDisplayModule(IModuleContext moduleContext, ISubmenuModule parent, StringResource titleResource, IconResource iconResource, ColorResource bgColorResource, ColorResource fgColorResource, StringResource unitResource) {
        super(moduleContext, parent, titleResource, iconResource, bgColorResource, fgColorResource, unitResource);
    }

    @Override
    public void onClickEvent(Context context) {
        Toast.makeText(context, "short click", Toast.LENGTH_SHORT);
//        new AlertDialog.Builder(context).setMessage("SHORT CLICK").create().show();
        try {
            Integer i = Integer.parseInt(getValue());
            updateValue(Integer.toString(i + 100));
        } catch (NumberFormatException ex) {
            // no value, do nothing then
        }
        Log.d("TestDisplayModule", "clicked");
    }

    @Override
    public void onLongClickEvent(Context context) {
        Toast.makeText(context, "long click", Toast.LENGTH_SHORT);

        try {
            Integer i = Integer.parseInt(getValue());
            updateValue(Integer.toString(i + 1000));
        } catch (NumberFormatException ex) {
            // no value, do nothing then
        }
    }

    private class UpdateTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Random rnd = new Random();
            int time = 0;
            int speed = 50;
            while (time < 600) {
                time++;
                speed += rnd.nextInt(10) - 4;
                EventBus.getDefault().post(new ModuleUpdateEvent(Integer.toString(speed)));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            updateValue(Integer.toString(values[0]));
        }
    }
}
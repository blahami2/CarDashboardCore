package com.eclubprague.cardashboard.core.modules.custom;

import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.data.modules.ModuleEnum;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalMediumUpdateEvent;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalSlowUpdateEvent;
import com.eclubprague.cardashboard.core.model.resources.ColorResource;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractTimedUpdateDisplayModule;

import java.util.Random;

/**
 * Created by Michael on 20.10.2015.
 */
public class TemperatureModule extends AbstractTimedUpdateDisplayModule<GlobalSlowUpdateEvent> {
    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_sensor_temperature_title);
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_clock_black_24dp);
    public static final StringResource UNIT_RESOURCE = StringResource.fromResourceId(R.string.module_sensor_temperature_units);

    public TemperatureModule(@NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource) {
        super(ModuleEnum.TEMPERATURE, TITLE_RESOURCE , ICON_RESOURCE, bgColorResource, fgColorResource, UNIT_RESOURCE);
    }

    public TemperatureModule() {
        super(ModuleEnum.TEMPERATURE, TITLE_RESOURCE , ICON_RESOURCE, UNIT_RESOURCE);
    }

    @Override
    public String getUpdatedValue() {
        Random rand = new Random();
        if(rand.nextDouble() <= 0.8){
            return "22.2";
        }
        if(rand.nextDouble() <= 0.5){
            return "22.3";
        }
        return "22.4";
    }
}

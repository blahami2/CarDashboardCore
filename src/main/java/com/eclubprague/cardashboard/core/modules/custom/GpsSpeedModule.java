package com.eclubprague.cardashboard.core.modules.custom;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.eventbus.FastEventBus;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalMediumUpdateEvent;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalSlowUpdateEvent;
import com.eclubprague.cardashboard.core.model.eventbus.interfaces.MainThreadReceiver;
import com.eclubprague.cardashboard.core.modules.base.AbstractDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

public class GpsSpeedModule extends AbstractDisplayModule implements MainThreadReceiver<GlobalMediumUpdateEvent>, LocationListener {

    private static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_others_gpsspeed_title);
    private static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_map_black_24dp);
    private static final StringResource UNIT_RESOURCE = StringResource.fromResourceId(R.string.module_others_gpsspeed_units);

    public GpsSpeedModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE);
        init();
    }

    public GpsSpeedModule(IModuleContext moduleContext, IParentModule parent) {
        super(moduleContext, parent, TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE);
        init();

    }

    public GpsSpeedModule(IModuleContext moduleContext, IParentModule parent, ColorResource bgColorResource, ColorResource fgColorResource) {
        super(moduleContext, parent, TITLE_RESOURCE, ICON_RESOURCE, bgColorResource, fgColorResource, UNIT_RESOURCE);
        init();

    }

    LocationManager locationManager;

    private void init() {
        FastEventBus.getInstance().register(this, GlobalMediumUpdateEvent.class);


    }

    @Override
    public void onEventMainThread(GlobalMediumUpdateEvent event) {
        if (locationManager == null && getModuleContext() != null) {
            locationManager = (LocationManager) getModuleContext().getContext().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }



    }

    @Override
    public void onLocationChanged(Location location) {
        updateValue(""+location.getSpeed());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}


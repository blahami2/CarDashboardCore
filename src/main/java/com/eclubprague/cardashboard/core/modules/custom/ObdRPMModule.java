package com.eclubprague.cardashboard.core.modules.custom;

import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.data.modules.ModuleEnum;
import com.eclubprague.cardashboard.core.model.eventbus.events.GlobalExtraFastUpdateEvent;
import com.eclubprague.cardashboard.core.modules.base.AbstractTimedUpdateDisplayModule;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.obd.OBDGatewayService;
import com.eclubprague.cardashboard.core.obd.ObdCommandJob;
import com.github.pires.obd.commands.engine.EngineRPMObdCommand;

public class ObdRpmModule extends AbstractTimedUpdateDisplayModule<GlobalExtraFastUpdateEvent> {

    public static final String TAG = ObdRpmModule.class.getSimpleName();

    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId(R.string.module_obd_rpm_title);
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId(R.drawable.ic_directions_car_black_24dp);
    public static final StringResource UNIT_RESOURCE = StringResource.fromResourceId(R.string.module_obd_rpm_units);

    public ObdRpmModule() {
        super(ModuleEnum.OBD_RPM, TITLE_RESOURCE, ICON_RESOURCE, UNIT_RESOURCE);
    }

    public ObdRpmModule(@NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull StringResource unitResource) {
        super(ModuleEnum.OBD_RPM, titleResource, iconResource, unitResource);
    }


    @Override
    public void onPause(IModuleContext moduleContext) {
        super.onPause(moduleContext);

    }

    @Override
    public String getUpdatedValue() {
        OBDGatewayService gatewayService = (OBDGatewayService) OBDGatewayService.getInstance();
        if (gatewayService != null) {
            gatewayService.enqueue(new ObdCommandJob(new EngineRPMObdCommand()));
            ObdCommandJob results = gatewayService.getResult(EngineRPMObdCommand.class);
            if (results != null && results.getCommand().getResult() != null){
                setLastValue(results.getCommand().getCalculatedResult());
            }

        }
        return getLastValue();
    }
}
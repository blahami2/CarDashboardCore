package com.eclubprague.cardashboard.core.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;
import com.eclubprague.cardashboard.core.modules.base.IModuleListener;
import com.eclubprague.cardashboard.core.modules.base.IQuickMenuListener;
import com.eclubprague.cardashboard.core.modules.base.ModuleEvent;
import com.eclubprague.cardashboard.core.views.ModuleActiveView;
import com.eclubprague.cardashboard.core.views.ModuleView;
import com.eclubprague.cardashboard.core.views.QuickMenuView;

import java.util.List;

/**
 * Utility class containing tools for module views
 * <p>
 * Created by Michael on 14. 7. 2015.
 */
public class ModuleViewUtils {

    public static ModuleView preparePassive(ModuleView moduleView, IModuleListener listener, IModuleContext moduleContext, IconResource iconResource, StringResource titleResource) {
        TextView titleView = (TextView) moduleView.findViewById(R.id.module_title);
        titleResource.setInView(titleView);
        ImageView iconView = (ImageView) moduleView.findViewById(R.id.module_icon);
        iconResource.setInView(iconView);
        return setModuleListeners(moduleView, listener, moduleContext);
    }

    public static ModuleActiveView prepareActive(ModuleActiveView moduleView, IModuleListener listener, IModuleContext moduleContext, IconResource iconResource, StringResource titleResource, StringResource unitResource) {
        TextView titleView = (TextView) moduleView.findViewById(R.id.module_title);
        titleResource.setInView(titleView);
        ImageView iconView = (ImageView) moduleView.findViewById(R.id.module_icon);
        iconResource.setInView(iconView);
        TextView unitView = (TextView) moduleView.findViewById(R.id.module_unit);
        unitResource.setInView(unitView);
        return (ModuleActiveView) setModuleListeners(moduleView, listener, moduleContext);
    }

    private static ModuleView setModuleListeners(final ModuleView moduleView, final IModuleListener listener, final IModuleContext moduleContext) {
        final ClickHolder clickHolder = new ClickHolder();
        moduleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickHolder.longClicked) {
                    clickHolder.longClicked = false;
                } else {
                    listener.onClickEvent(moduleContext);
                }
            }
        });
        moduleView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClickEvent(moduleContext);
                clickHolder.longClicked = true;
                return false;
            }
        });
        return moduleView;
    }

    public static View prepareQuickMenu(QuickMenuView quickMenuView, final IQuickMenuListener listener, final IModuleContext moduleContext) {
        final List<ModuleEvent> moduleEvents = listener.getAvailableActions();
        if (moduleEvents.size() > 0) {
            quickMenuView.getButtonTopLeft().setEvent(listener, moduleContext, moduleEvents.get(0));
        }
        if (moduleEvents.size() > 1) {
            quickMenuView.getButtonTopRight().setEvent(listener, moduleContext, moduleEvents.get(1));
        }
        if (moduleEvents.size() > 2) {
            quickMenuView.getButtonBottomLeft().setEvent(listener, moduleContext, moduleEvents.get(2));
        }
        if (moduleEvents.size() == 4) {
            quickMenuView.getButtonBottomRight().setEvent(listener, moduleContext, moduleEvents.get(3));
        }
        if (moduleEvents.size() > 4) {
            quickMenuView.getButtonBottomRight().setEvent(listener, moduleContext, ModuleEvent.MORE);
        }

        return quickMenuView;
    }

    private static class ClickHolder {
        boolean longClicked = false;
    }
}

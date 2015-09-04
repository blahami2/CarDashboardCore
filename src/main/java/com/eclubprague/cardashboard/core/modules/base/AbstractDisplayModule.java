package com.eclubprague.cardashboard.core.modules.base;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.eclubprague.cardashboard.core.data.modules.ModuleEnum;
import com.eclubprague.cardashboard.core.modules.base.models.ViewWithHolder;
import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;
import com.eclubprague.cardashboard.core.utils.ModuleViewFactory;
import com.eclubprague.cardashboard.core.views.ModuleActiveView;
import com.eclubprague.cardashboard.core.views.ModuleView;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Base implementation of display module.
 * Displays information. ATM it displays only String up to 4 characters.
 * Should launch simple menu on click (TODO)
 */
abstract public class AbstractDisplayModule extends AbstractSimpleModule {
    private String value = null;
    private StringResource unitResource;


    public AbstractDisplayModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource, StringResource unitResource) {
        super(moduleEnum, titleResource, iconResource);
        this.unitResource = unitResource;
    }

    public AbstractDisplayModule(@NonNull ModuleEnum moduleEnum, @NonNull StringResource titleResource, @NonNull IconResource iconResource, @NonNull ColorResource bgColorResource, @NonNull ColorResource fgColorResource, StringResource unitResource) {
        super(moduleEnum, titleResource, iconResource, bgColorResource, fgColorResource);
        this.unitResource = unitResource;
    }

    public StringResource getUnit() {
        return unitResource;
    }

    public AbstractDisplayModule setUnitResource(StringResource unitResource) {
        this.unitResource = unitResource;
        return this;
    }

    public void updateValue(String value) {
//        Log.d("DisplayModule", "updating value for module: " + this);
//        Log.d("DisplayModule", "with view: " + getView());
        this.value = value;
//        for(ModuleView v : views){
//            v.setVa
//        }
        if (value == null || getView() == null) {
            return;
        } else {
//            List<ModuleView> moduleViewList = getViews(moduleContext);
//            for (ModuleView moduleView : moduleViewList) {
//                ModuleActiveView moduleActiveView = (ModuleActiveView) moduleView;
//                moduleActiveView.setValue(value);
//            }
            ModuleActiveView v = (ModuleActiveView) getView();
            v.setValue(value);
        }
    }

    @Override
    public ModuleView createNewView(IModuleContext moduleContext, ViewGroup parent) {
        ModuleActiveView view = ModuleViewFactory.createActive(moduleContext, parent, this, getIcon(), getTitle(), getUnit());
        view.setValue(getUpdatedValue());
        return view;
    }

    @Override
    public ViewWithHolder<ModuleView> createNewViewWithHolder(IModuleContext moduleContext, int holderResourceId, ViewGroup holderParent) {
        ViewWithHolder<ModuleActiveView> viewWithHolder = ModuleViewFactory.createActiveWithHolder(moduleContext, holderResourceId, holderParent, this, getIcon(), getTitle(), getUnit());
        ModuleActiveView view = viewWithHolder.view;
        view.setValue(getUpdatedValue());
        return new ViewWithHolder<ModuleView>(viewWithHolder.view, viewWithHolder.holder);
    }

    public String getValue() {
        return value;
    }

    abstract public String getUpdatedValue();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                super.toString() + ", " +
                "value='" + value + '\'' +
                ", unitResource=" + unitResource +
                '}';
    }
}

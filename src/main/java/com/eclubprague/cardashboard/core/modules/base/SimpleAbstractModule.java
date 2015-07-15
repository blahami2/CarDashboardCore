package com.eclubprague.cardashboard.core.modules.base;

import com.eclubprague.cardashboard.core.modules.base.models.resources.ColorResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

/**
 * Created by Michael on 9. 7. 2015.
 * <p/>
 * Simple implementation of IModule interface.
 */
abstract public class SimpleAbstractModule implements IModule {
    private final StringResource titleResource;
    private final IconResource iconResource;
    private final ColorResource bgColorResource;
    private final ColorResource fgColorResource;

    public SimpleAbstractModule(StringResource titleResource, IconResource iconResource, ColorResource bgColorResource, ColorResource fgColorResource) {
        this.titleResource = titleResource;
        this.iconResource = iconResource;
        this.bgColorResource = bgColorResource;
        this.fgColorResource = fgColorResource;
    }

    @Override
    public IconResource getIcon() {
        return iconResource;
    }

    @Override
    public StringResource getTitle() {
        return titleResource;
    }

    @Override
    public ColorResource getBackgroundColor() {
        return bgColorResource;
    }

    @Override
    public ColorResource getForegroundColor() {
        return fgColorResource;
    }

}
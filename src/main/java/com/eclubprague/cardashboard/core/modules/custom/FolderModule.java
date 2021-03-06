package com.eclubprague.cardashboard.core.modules.custom;

import android.support.annotation.NonNull;

import com.eclubprague.cardashboard.core.R;

import com.eclubprague.cardashboard.core.model.resources.ColorResource;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.modules.base.AbstractParentModule;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.IParentModule;

/**
 * Created by Michael on 20.08.2015.
 */
public class FolderModule extends AbstractParentModule {

    public static final StringResource TITLE_RESOURCE = StringResource.fromResourceId( R.string.module_others_folder_new );
    public static final IconResource ICON_RESOURCE = IconResource.fromResourceId( R.drawable.ic_folder_black_24dp );

    public FolderModule() {
        super(TITLE_RESOURCE, ICON_RESOURCE);
    }


    @Override
    public IParentModule copy() {
        FolderModule newParent = new FolderModule();
        newParent.setTitle( getTitle() );
        newParent.setIcon( getIcon() );
        if ( hasBackgroundColor() ) {
            newParent.setBackgroundColor( getBackgroundColor() );
        }
        if ( hasForegroundColor() ) {
            newParent.setForegroundColor( getForegroundColor() );
        }
        newParent.addSubmodules( getSubmodules() );
        return newParent;
    }

    @Override
    public IParentModule copyDeep() {
        FolderModule newParent = new FolderModule();
        newParent.setTitle( getTitle() );
        newParent.setIcon( getIcon() );
        if ( hasBackgroundColor() ) {
            newParent.setBackgroundColor( getBackgroundColor() );
        }
        if ( hasForegroundColor() ) {
            newParent.setForegroundColor( getForegroundColor() );
        }
        for ( IModule m : getSubmodules() ) {
            if ( m instanceof IParentModule ) {
                IParentModule parentModule = (IParentModule) m;
                newParent.addSubmodules( parentModule.copyDeep() );
            } else {
                newParent.addSubmodules( m );
            }
        }
        return newParent;
    }
}

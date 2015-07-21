package com.eclubprague.cardashboard.core.modules.base;

/**
 * Created by Michael on 15. 7. 2015.
 *
 * Module context required for calls from modules.
 */
public interface IModuleContext {
    void goToSubmenu(ISubmenuModule parentModule);

    void goBack(ISubmenuModule parentModule);

    void swapModules(IModule oldModule, IModule newModule, boolean animate);
}

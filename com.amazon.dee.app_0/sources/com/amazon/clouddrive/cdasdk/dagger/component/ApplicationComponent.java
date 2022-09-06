package com.amazon.clouddrive.cdasdk.dagger.component;

import com.amazon.clouddrive.cdasdk.cdp.CDPUtil;
import com.amazon.clouddrive.cdasdk.dagger.module.ApplicationModule;
import com.amazon.clouddrive.cdasdk.util.Logger;
import com.amazon.clouddrive.cdasdk.util.SystemUtil;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {ApplicationModule.class})
@Singleton
/* loaded from: classes11.dex */
public interface ApplicationComponent {
    CDPUtil getCDPUtil();

    Logger getLogger();

    SystemUtil getSystemUtil();
}

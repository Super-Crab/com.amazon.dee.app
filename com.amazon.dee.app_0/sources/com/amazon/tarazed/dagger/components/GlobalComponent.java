package com.amazon.tarazed.dagger.components;

import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.dagger.modules.GlobalModule;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.ui.tv.TVUIManager;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import dagger.Component;
import javax.inject.Singleton;
import kotlinx.coroutines.CoroutineScope;
@Component(modules = {GlobalModule.class})
@Singleton
/* loaded from: classes13.dex */
public interface GlobalComponent {
    BizMetricsHelper getBizMetricsHelper();

    CoroutineScope getCoroutineScope();

    DeviceInfoUtility getDeviceInfoUtility();

    DeviceInfoUtilityAndroid getDeviceInfoUtilityAndroid();

    DispatcherProvider getDispatcherProvider();

    TarazedLogger getLogger();

    TarazedMetricsHelper getMetricsHelper();

    SessionClientCache getSessionClientCache();

    TarazedSessionLogger getSessionLogger();

    TarazedSessionNotifier getSessionNotifier();

    TVUIManager getTVIndicatorManager();

    ViewGroupManager getViewGroupManager();
}

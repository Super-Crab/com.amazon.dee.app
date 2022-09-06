package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import com.amazon.dee.app.services.tcomm.AndroidTCommServiceManager;
import com.amazon.dee.app.services.tcomm.GatewayConnectivityFactory;
import com.amazon.dee.app.services.tcomm.PhoenixTCommServiceManager;
import com.amazon.dee.app.services.tcomm.TCommServiceManager;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class TCommServiceModule {
    @Provides
    @ApplicationScope
    public TCommServiceManager provideTCommServiceManager(Context context, IdentityService identityService, NetworkService networkService, EventBus eventBus, MainActivityLifecycleService mainActivityLifecycleService, Mobilytics mobilytics, DeviceInformation deviceInformation, Lazy<FeatureServiceV2> lazy) {
        if (deviceInformation.isFireOS()) {
            return new PhoenixTCommServiceManager(context, identityService, networkService, eventBus, mainActivityLifecycleService, mobilytics, new GatewayConnectivityFactory(), lazy);
        }
        return new AndroidTCommServiceManager(context, identityService, networkService, eventBus, mainActivityLifecycleService, mobilytics, lazy);
    }
}

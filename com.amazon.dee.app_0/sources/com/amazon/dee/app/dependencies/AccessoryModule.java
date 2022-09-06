package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.accessory.Accessories;
import com.amazon.alexa.accessory.avsclient.AlexaConnection;
import com.amazon.alexa.accessory.avsclient.AlexaServicesAlexaConnection;
import com.amazon.alexa.accessorykit.AccessoriesFactory;
import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
@Module
/* loaded from: classes12.dex */
public final class AccessoryModule {
    static final String ACCESSORIES_ALEXA_SERVICE_CONNECTION = "AccessoriesAlexaServiceConnection";
    private final Context context;

    public AccessoryModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    public Accessories provideAccessories(Context context, @Named("AccessoriesAlexaServiceConnection") AlexaServicesConnection alexaServicesConnection) {
        return AccessoriesFactory.createAccessories(context, alexaServicesConnection, "com.amazon.dee.app", "AlexaMobileAndroid_prod", false, MobilyticsModule.MOBILYTICS_DOMAIN);
    }

    @Provides
    @ApplicationScope
    public AlexaConnection provideAlexaConnection(@Named("AccessoriesAlexaServiceConnection") AlexaServicesConnection alexaServicesConnection) {
        return new AlexaServicesAlexaConnection(alexaServicesConnection);
    }

    @Provides
    public Context provideContext() {
        return this.context;
    }
}

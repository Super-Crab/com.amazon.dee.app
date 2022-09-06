package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
@Module
/* loaded from: classes12.dex */
public final class AccessoriesAlexaModule {
    @Provides
    @ApplicationScope
    @Named("AccessoriesAlexaServiceConnection")
    public AlexaServicesConnection provideAlexaServicesConnection(Context context) {
        return new AlexaServicesConnection(context);
    }
}

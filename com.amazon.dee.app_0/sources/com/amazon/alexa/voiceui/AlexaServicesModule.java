package com.amazon.alexa.voiceui;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
/* JADX INFO: Access modifiers changed from: package-private */
@Module
/* loaded from: classes11.dex */
public class AlexaServicesModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public AlexaServicesApis providesAlexaServicesApis(AlexaServicesConnection alexaServicesConnection) {
        return new AlexaServicesWrapper(alexaServicesConnection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public AlexaServicesConnection providesAlexaServicesConnection(Context context) {
        return new AlexaServicesConnection(context);
    }
}

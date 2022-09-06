package com.amazon.alexa.voice.sdk;

import android.content.Context;
import android.widget.Toast;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.voice.enablement.VoiceEnablement;
import com.amazon.alexa.voice.features.FeatureChecker;
import com.amazon.alexa.voice.sdk.DefaultAlexaConnectionManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class SdkModule {
    private SdkModule() {
    }

    @Provides
    @Singleton
    public static AlexaServicesConnection provideAlexaServicesConnection(Context context) {
        return new AlexaServicesConnection(context);
    }

    @Provides
    @Singleton
    public static AlexaStateTracker provideAlexaStateTracker() {
        return new AlexaStateTracker();
    }

    @Provides
    @Singleton
    public static AttentionSystemManager provideAttentionSystemManager(AlexaServicesConnection alexaServicesConnection, FeatureChecker featureChecker) {
        return new AttentionSystemManager(alexaServicesConnection, featureChecker);
    }

    @Provides
    @Singleton
    public static DefaultAlexaConnectionManager provideDefaultAlexaConnectionManager(final Context context, AlexaServicesConnection alexaServicesConnection, ApplicationLifecycleService applicationLifecycleService, VoiceEnablement voiceEnablement) {
        return new DefaultAlexaConnectionManager(new DefaultAlexaConnectionManager.InvalidSigningReporter() { // from class: com.amazon.alexa.voice.sdk.-$$Lambda$SdkModule$d5-SLnhMvYw1hKTWwpls3dd2Y28
            @Override // com.amazon.alexa.voice.sdk.DefaultAlexaConnectionManager.InvalidSigningReporter
            public final void showToast(String str) {
                Toast.makeText(context, str, 1).show();
            }
        }, alexaServicesConnection, applicationLifecycleService, voiceEnablement);
    }
}

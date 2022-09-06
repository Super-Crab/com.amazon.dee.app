package com.amazon.alexa.voice.tta.sdk;

import android.content.Context;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.voice.tta.Constants;
import com.amazon.alexa.voice.tta.typing.TtaMessageSanitizer;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public class SdkModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public AlexaClientSdkApis providesAlexaClientSdkApis(AlexaMobileFrameworkApis alexaMobileFrameworkApis, Gson gson) {
        return new AlexaClientSdkApis(alexaMobileFrameworkApis, gson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public AlexaMobileFrameworkApis providesAlexaMobileFrameworkApis(Context context) {
        return new AlexaMobileFrameworkApis(context, Constants.TWA);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public AlexaPlayerInfoCardTracker providesAlexaPlayerInfoCardTracker() {
        return new AlexaPlayerInfoCardTracker();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public AlexaStateTracker providesAlexaStateTracker() {
        return new AlexaStateTracker();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public AlexaTextResponseTracker providesAlexaTextResponseTracker(TtaMessageSanitizer ttaMessageSanitizer) {
        return new AlexaTextResponseTracker(ttaMessageSanitizer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public UiEventReporter providesUiEventReporter(Context context) {
        return new UiEventReporter(context);
    }
}

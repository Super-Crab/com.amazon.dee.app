package com.amazon.alexa.voice.tta.statemachine;

import android.content.Context;
import com.amazon.alexa.voice.tta.sdk.AlexaClientSdkApis;
import com.amazon.alexa.voice.tta.sdk.AlexaPlayerInfoCardTracker;
import com.amazon.alexa.voice.tta.sdk.AlexaRenderedCardReceiver;
import com.amazon.alexa.voice.tta.sdk.AlexaStateTracker;
import com.amazon.alexa.voice.tta.sdk.AlexaTextResponseTracker;
import com.amazon.alexa.voice.tta.search.FrictionHelper;
import com.amazon.alexa.voice.tta.typing.TtaMessageSanitizer;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: StateMachineModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J@\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J(\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007¨\u0006\u001d"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/StateMachineModule;", "", "()V", "providesAlexaMediator", "Lcom/amazon/alexa/voice/tta/statemachine/AlexaMediator;", "alexaClientSdkApis", "Lcom/amazon/alexa/voice/tta/sdk/AlexaClientSdkApis;", "alexaStateTracker", "Lcom/amazon/alexa/voice/tta/sdk/AlexaStateTracker;", "alexaTextResponseTracker", "Lcom/amazon/alexa/voice/tta/sdk/AlexaTextResponseTracker;", "alexaRenderedCardReceiver", "Lcom/amazon/alexa/voice/tta/sdk/AlexaRenderedCardReceiver;", "alexaPlayerInfoCardTracker", "Lcom/amazon/alexa/voice/tta/sdk/AlexaPlayerInfoCardTracker;", "messageSanitizer", "Lcom/amazon/alexa/voice/tta/typing/TtaMessageSanitizer;", "ttaEventSender", "Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEventSender;", "providesSearchWorkflow", "Lcom/amazon/alexa/voice/tta/statemachine/SearchWorkflow;", "alexaMediator", "simbaMediator", "Lcom/amazon/alexa/voice/tta/statemachine/SimbaMediator;", "providesSimbaMediator", "context", "Landroid/content/Context;", "frictionHelper", "Lcom/amazon/alexa/voice/tta/search/FrictionHelper;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes11.dex */
public final class StateMachineModule {
    @Provides
    @Singleton
    @NotNull
    public final AlexaMediator providesAlexaMediator(@NotNull AlexaClientSdkApis alexaClientSdkApis, @NotNull AlexaStateTracker alexaStateTracker, @NotNull AlexaTextResponseTracker alexaTextResponseTracker, @NotNull AlexaRenderedCardReceiver alexaRenderedCardReceiver, @NotNull AlexaPlayerInfoCardTracker alexaPlayerInfoCardTracker, @NotNull TtaMessageSanitizer messageSanitizer, @NotNull TtaEventSender ttaEventSender) {
        Intrinsics.checkParameterIsNotNull(alexaClientSdkApis, "alexaClientSdkApis");
        Intrinsics.checkParameterIsNotNull(alexaStateTracker, "alexaStateTracker");
        Intrinsics.checkParameterIsNotNull(alexaTextResponseTracker, "alexaTextResponseTracker");
        Intrinsics.checkParameterIsNotNull(alexaRenderedCardReceiver, "alexaRenderedCardReceiver");
        Intrinsics.checkParameterIsNotNull(alexaPlayerInfoCardTracker, "alexaPlayerInfoCardTracker");
        Intrinsics.checkParameterIsNotNull(messageSanitizer, "messageSanitizer");
        Intrinsics.checkParameterIsNotNull(ttaEventSender, "ttaEventSender");
        return new AlexaMediator(alexaClientSdkApis, alexaStateTracker, alexaTextResponseTracker, alexaRenderedCardReceiver, alexaPlayerInfoCardTracker, messageSanitizer, ttaEventSender);
    }

    @Provides
    @Singleton
    @NotNull
    public final SearchWorkflow providesSearchWorkflow(@NotNull AlexaMediator alexaMediator, @NotNull SimbaMediator simbaMediator) {
        Intrinsics.checkParameterIsNotNull(alexaMediator, "alexaMediator");
        Intrinsics.checkParameterIsNotNull(simbaMediator, "simbaMediator");
        return new SearchWorkflow(alexaMediator, simbaMediator);
    }

    @Provides
    @Singleton
    @NotNull
    public final SimbaMediator providesSimbaMediator(@NotNull Context context, @NotNull AlexaMediator alexaMediator, @NotNull FrictionHelper frictionHelper, @NotNull TtaEventSender ttaEventSender) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(alexaMediator, "alexaMediator");
        Intrinsics.checkParameterIsNotNull(frictionHelper, "frictionHelper");
        Intrinsics.checkParameterIsNotNull(ttaEventSender, "ttaEventSender");
        return new SimbaMediator(context, alexaMediator, frictionHelper, ttaEventSender);
    }
}

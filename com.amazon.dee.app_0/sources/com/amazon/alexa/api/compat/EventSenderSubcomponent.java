package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: EventSenderSubcomponent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\"\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/api/compat/EventSenderSubcomponent;", "Lcom/amazon/alexa/api/compat/Subcomponent;", "Lcom/amazon/alexa/api/compat/EventSenderApi;", "frameworkApis", "Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;", "(Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;)V", "send", "", "alexaEvent", "Lcom/amazon/alexa/api/AlexaEvent;", "eventRequiresContexts", "", "apiCallbacks", "Lcom/amazon/alexa/api/AlexaApiCallbacks;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class EventSenderSubcomponent extends Subcomponent implements EventSenderApi {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EventSenderSubcomponent(@NotNull com.amazon.alexa.api.AlexaMobileFrameworkApis frameworkApis) {
        super(frameworkApis);
        Intrinsics.checkParameterIsNotNull(frameworkApis, "frameworkApis");
    }

    @Override // com.amazon.alexa.api.compat.EventSenderApi
    public void send(@NotNull AlexaEvent alexaEvent) {
        Intrinsics.checkParameterIsNotNull(alexaEvent, "alexaEvent");
        getFrameworkApis().getCapabilities().sendEvent(alexaEvent);
    }

    @Override // com.amazon.alexa.api.compat.EventSenderApi
    public void send(@NotNull AlexaEvent alexaEvent, boolean z) {
        Intrinsics.checkParameterIsNotNull(alexaEvent, "alexaEvent");
        getFrameworkApis().getCapabilities().sendEvent(alexaEvent, z);
    }

    @Override // com.amazon.alexa.api.compat.EventSenderApi
    public void send(@NotNull AlexaEvent alexaEvent, boolean z, @Nullable AlexaApiCallbacks alexaApiCallbacks) {
        Intrinsics.checkParameterIsNotNull(alexaEvent, "alexaEvent");
        getFrameworkApis().getCapabilities().sendEvent(alexaEvent, z, alexaApiCallbacks);
    }
}

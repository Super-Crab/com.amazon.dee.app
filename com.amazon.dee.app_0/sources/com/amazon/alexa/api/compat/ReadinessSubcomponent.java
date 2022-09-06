package com.amazon.alexa.api.compat;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ReadinessSubcomponent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u000b2\u00020\u00012\u00020\u0002:\u0001\u000bB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/api/compat/ReadinessSubcomponent;", "Lcom/amazon/alexa/api/compat/Subcomponent;", "Lcom/amazon/alexa/api/compat/ReadinessApi;", "frameworkApis", "Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;", "(Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;)V", "deregisterReadinessListener", "", "alexaReadinessListener", "Lcom/amazon/alexa/api/compat/AlexaReadinessListener;", "registerReadinessListener", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ReadinessSubcomponent extends Subcomponent implements ReadinessApi {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Map<AlexaReadinessListener, AlexaReadinessListenerAdapter> listeners = new LinkedHashMap();

    /* compiled from: ReadinessSubcomponent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/api/compat/ReadinessSubcomponent$Companion;", "", "()V", "listeners", "", "Lcom/amazon/alexa/api/compat/AlexaReadinessListener;", "Lcom/amazon/alexa/api/compat/AlexaReadinessListenerAdapter;", "getListeners", "()Ljava/util/Map;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Map<AlexaReadinessListener, AlexaReadinessListenerAdapter> getListeners() {
            return ReadinessSubcomponent.listeners;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReadinessSubcomponent(@NotNull com.amazon.alexa.api.AlexaMobileFrameworkApis frameworkApis) {
        super(frameworkApis);
        Intrinsics.checkParameterIsNotNull(frameworkApis, "frameworkApis");
    }

    @Override // com.amazon.alexa.api.compat.ReadinessApi
    public void deregisterReadinessListener(@NotNull AlexaReadinessListener alexaReadinessListener) {
        Intrinsics.checkParameterIsNotNull(alexaReadinessListener, "alexaReadinessListener");
        AlexaReadinessListenerAdapter remove = listeners.remove(alexaReadinessListener);
        if (remove != null) {
            getFrameworkApis().getReadiness().deregisterListener(remove);
        }
    }

    @Override // com.amazon.alexa.api.compat.ReadinessApi
    public void registerReadinessListener(@NotNull AlexaReadinessListener alexaReadinessListener) {
        Intrinsics.checkParameterIsNotNull(alexaReadinessListener, "alexaReadinessListener");
        AlexaReadinessListenerAdapter alexaReadinessListenerAdapter = new AlexaReadinessListenerAdapter(alexaReadinessListener);
        listeners.put(alexaReadinessListener, alexaReadinessListenerAdapter);
        getFrameworkApis().getReadiness().registerListener(alexaReadinessListenerAdapter);
    }
}

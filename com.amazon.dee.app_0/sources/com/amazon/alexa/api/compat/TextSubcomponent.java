package com.amazon.alexa.api.compat;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TextSubcomponent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00122\u00020\u00012\u00020\u0002:\u0001\u0012B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/api/compat/TextSubcomponent;", "Lcom/amazon/alexa/api/compat/Subcomponent;", "Lcom/amazon/alexa/api/compat/TextApi;", "frameworkApis", "Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;", "(Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;)V", "deregisterExpectTextListener", "", "alexaExpectTextListener", "Lcom/amazon/alexa/api/compat/AlexaExpectTextListener;", "deregisterTextResponseListener", "alexaTextResponseListener", "Lcom/amazon/alexa/api/compat/AlexaTextResponseListener;", "registerExpectTextListener", "registerTextResponseListener", "sendMessage", "message", "", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class TextSubcomponent extends Subcomponent implements TextApi {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Map<AlexaTextResponseListener, AlexaTextResponseListenerAdapter> listeners = new LinkedHashMap();
    @NotNull
    private static final Map<AlexaExpectTextListener, AlexaExpectTextListenerAdapter> expectSpeechListeners = new LinkedHashMap();

    /* compiled from: TextSubcomponent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/api/compat/TextSubcomponent$Companion;", "", "()V", "expectSpeechListeners", "", "Lcom/amazon/alexa/api/compat/AlexaExpectTextListener;", "Lcom/amazon/alexa/api/compat/AlexaExpectTextListenerAdapter;", "getExpectSpeechListeners", "()Ljava/util/Map;", "listeners", "Lcom/amazon/alexa/api/compat/AlexaTextResponseListener;", "Lcom/amazon/alexa/api/compat/AlexaTextResponseListenerAdapter;", "getListeners", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Map<AlexaExpectTextListener, AlexaExpectTextListenerAdapter> getExpectSpeechListeners() {
            return TextSubcomponent.expectSpeechListeners;
        }

        @NotNull
        public final Map<AlexaTextResponseListener, AlexaTextResponseListenerAdapter> getListeners() {
            return TextSubcomponent.listeners;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextSubcomponent(@NotNull com.amazon.alexa.api.AlexaMobileFrameworkApis frameworkApis) {
        super(frameworkApis);
        Intrinsics.checkParameterIsNotNull(frameworkApis, "frameworkApis");
    }

    @Override // com.amazon.alexa.api.compat.TextApi
    public void deregisterExpectTextListener(@NotNull AlexaExpectTextListener alexaExpectTextListener) {
        Intrinsics.checkParameterIsNotNull(alexaExpectTextListener, "alexaExpectTextListener");
        AlexaExpectTextListenerAdapter remove = expectSpeechListeners.remove(alexaExpectTextListener);
        if (remove != null) {
            getFrameworkApis().getText().deregisterListener(remove);
        }
    }

    @Override // com.amazon.alexa.api.compat.TextApi
    public void deregisterTextResponseListener(@NotNull AlexaTextResponseListener alexaTextResponseListener) {
        Intrinsics.checkParameterIsNotNull(alexaTextResponseListener, "alexaTextResponseListener");
        AlexaTextResponseListenerAdapter remove = listeners.remove(alexaTextResponseListener);
        if (remove != null) {
            getFrameworkApis().getText().deregisterListener(remove);
        }
    }

    @Override // com.amazon.alexa.api.compat.TextApi
    public void registerExpectTextListener(@NotNull AlexaExpectTextListener alexaExpectTextListener) {
        Intrinsics.checkParameterIsNotNull(alexaExpectTextListener, "alexaExpectTextListener");
        AlexaExpectTextListenerAdapter alexaExpectTextListenerAdapter = new AlexaExpectTextListenerAdapter(alexaExpectTextListener);
        expectSpeechListeners.put(alexaExpectTextListener, alexaExpectTextListenerAdapter);
        getFrameworkApis().getText().registerListener(alexaExpectTextListenerAdapter);
    }

    @Override // com.amazon.alexa.api.compat.TextApi
    public void registerTextResponseListener(@NotNull AlexaTextResponseListener alexaTextResponseListener) {
        Intrinsics.checkParameterIsNotNull(alexaTextResponseListener, "alexaTextResponseListener");
        AlexaTextResponseListenerAdapter alexaTextResponseListenerAdapter = new AlexaTextResponseListenerAdapter(alexaTextResponseListener);
        listeners.put(alexaTextResponseListener, alexaTextResponseListenerAdapter);
        getFrameworkApis().getText().registerListener(alexaTextResponseListenerAdapter);
    }

    @Override // com.amazon.alexa.api.compat.TextApi
    public void sendMessage(@NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        getFrameworkApis().getText().sendMessage(message);
    }
}

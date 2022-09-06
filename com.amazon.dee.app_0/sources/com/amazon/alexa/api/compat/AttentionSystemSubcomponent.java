package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaAttentionSystemSettingsListener;
import com.amazon.alexa.api.AlexaUserSpeechListener;
import com.dee.app.metrics.MetricsConstants;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AttentionSystemSubcomponent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u0000 \u001a2\u00020\u00012\u00020\u0002:\u0001\u001aB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0016H\u0016J\b\u0010\u0019\u001a\u00020\u0007H\u0016¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/api/compat/AttentionSystemSubcomponent;", "Lcom/amazon/alexa/api/compat/Subcomponent;", "Lcom/amazon/alexa/api/compat/AttentionSystemApi;", "frameworkApis", "Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;", "(Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;)V", MetricsConstants.Method.CACHE_CLEAR, "", "deregisterAttentionSystemSettingsListener", "attentionSystemSettingsListener", "Lcom/amazon/alexa/api/AlexaAttentionSystemSettingsListener;", "deregisterStateListener", "alexaStateListener", "Lcom/amazon/alexa/api/compat/AlexaStateListener;", "deregisterUserSpeechListener", "alexaUserSpeechListener", "Lcom/amazon/alexa/api/AlexaUserSpeechListener;", "registerAttentionSystemSettingsListener", "registerStateListener", "registerUserSpeechListener", "setEndpointSoundEnabled", "isEndpointSoundEnabled", "", "setWakeSoundEnabled", "isWakeSoundEnabled", "stopForegroundAudioTask", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AttentionSystemSubcomponent extends Subcomponent implements AttentionSystemApi {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Map<AlexaStateListener, AlexaStateListenerAdapter> listeners = new LinkedHashMap();

    /* compiled from: AttentionSystemSubcomponent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/api/compat/AttentionSystemSubcomponent$Companion;", "", "()V", "listeners", "", "Lcom/amazon/alexa/api/compat/AlexaStateListener;", "Lcom/amazon/alexa/api/compat/AlexaStateListenerAdapter;", "getListeners", "()Ljava/util/Map;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Map<AlexaStateListener, AlexaStateListenerAdapter> getListeners() {
            return AttentionSystemSubcomponent.listeners;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AttentionSystemSubcomponent(@NotNull com.amazon.alexa.api.AlexaMobileFrameworkApis frameworkApis) {
        super(frameworkApis);
        Intrinsics.checkParameterIsNotNull(frameworkApis, "frameworkApis");
    }

    @Override // com.amazon.alexa.api.compat.AttentionSystemApi
    public void clear() {
        getFrameworkApis().getDialog().cancel();
    }

    @Override // com.amazon.alexa.api.compat.AttentionSystemApi
    public void deregisterAttentionSystemSettingsListener(@NotNull AlexaAttentionSystemSettingsListener attentionSystemSettingsListener) {
        Intrinsics.checkParameterIsNotNull(attentionSystemSettingsListener, "attentionSystemSettingsListener");
        getFrameworkApis().getAttentionSystem().deregisterListener(attentionSystemSettingsListener);
    }

    @Override // com.amazon.alexa.api.compat.AttentionSystemApi
    public void deregisterStateListener(@NotNull AlexaStateListener alexaStateListener) {
        Intrinsics.checkParameterIsNotNull(alexaStateListener, "alexaStateListener");
        AlexaStateListenerAdapter remove = listeners.remove(alexaStateListener);
        if (remove != null) {
            getFrameworkApis().getAttentionSystem().deregisterListener(remove);
        }
    }

    @Override // com.amazon.alexa.api.compat.AttentionSystemApi
    public void deregisterUserSpeechListener(@NotNull AlexaUserSpeechListener alexaUserSpeechListener) {
        Intrinsics.checkParameterIsNotNull(alexaUserSpeechListener, "alexaUserSpeechListener");
        getFrameworkApis().getAttentionSystem().deregisterListener(alexaUserSpeechListener);
    }

    @Override // com.amazon.alexa.api.compat.AttentionSystemApi
    public void registerAttentionSystemSettingsListener(@NotNull AlexaAttentionSystemSettingsListener attentionSystemSettingsListener) {
        Intrinsics.checkParameterIsNotNull(attentionSystemSettingsListener, "attentionSystemSettingsListener");
        getFrameworkApis().getAttentionSystem().registerListener(attentionSystemSettingsListener);
    }

    @Override // com.amazon.alexa.api.compat.AttentionSystemApi
    public void registerStateListener(@NotNull AlexaStateListener alexaStateListener) {
        Intrinsics.checkParameterIsNotNull(alexaStateListener, "alexaStateListener");
        AlexaStateListenerAdapter alexaStateListenerAdapter = new AlexaStateListenerAdapter(alexaStateListener);
        listeners.put(alexaStateListener, alexaStateListenerAdapter);
        getFrameworkApis().getAttentionSystem().registerListener(alexaStateListenerAdapter);
    }

    @Override // com.amazon.alexa.api.compat.AttentionSystemApi
    public void registerUserSpeechListener(@NotNull AlexaUserSpeechListener alexaUserSpeechListener) {
        Intrinsics.checkParameterIsNotNull(alexaUserSpeechListener, "alexaUserSpeechListener");
        getFrameworkApis().getAttentionSystem().registerListener(alexaUserSpeechListener);
    }

    @Override // com.amazon.alexa.api.compat.AttentionSystemApi
    public void setEndpointSoundEnabled(boolean z) {
        getFrameworkApis().getAttentionSystem().setEndpointSoundEnabled(z);
    }

    @Override // com.amazon.alexa.api.compat.AttentionSystemApi
    public void setWakeSoundEnabled(boolean z) {
        getFrameworkApis().getAttentionSystem().setWakeSoundEnabled(z);
    }

    @Override // com.amazon.alexa.api.compat.AttentionSystemApi
    public void stopForegroundAudioTask() {
        getFrameworkApis().getAttentionSystem().stopForegroundAudioTask();
    }
}

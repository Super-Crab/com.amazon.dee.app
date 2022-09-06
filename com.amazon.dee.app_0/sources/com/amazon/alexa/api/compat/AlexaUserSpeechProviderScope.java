package com.amazon.alexa.api.compat;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaUserSpeechProviderScope.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderScope;", "", "(Ljava/lang/String;I)V", "EXTERNAL_DEVICE", "SYSTEM", "APPLICATION", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum AlexaUserSpeechProviderScope {
    EXTERNAL_DEVICE,
    SYSTEM,
    APPLICATION;
    
    public static final Companion Companion = new Companion(null);

    /* compiled from: AlexaUserSpeechProviderScope.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0004¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderScope$Companion;", "", "()V", "convertFromCompat", "Lcom/amazon/alexa/api/AlexaUserSpeechProviderScope;", "compatScope", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderScope;", "convertToCompat", "noncompatScope", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final com.amazon.alexa.api.AlexaUserSpeechProviderScope convertFromCompat(@Nullable AlexaUserSpeechProviderScope alexaUserSpeechProviderScope) {
            if (alexaUserSpeechProviderScope != null) {
                return com.amazon.alexa.api.AlexaUserSpeechProviderScope.valueOf(alexaUserSpeechProviderScope.name());
            }
            return null;
        }

        @Nullable
        public final AlexaUserSpeechProviderScope convertToCompat(@Nullable com.amazon.alexa.api.AlexaUserSpeechProviderScope alexaUserSpeechProviderScope) {
            if (alexaUserSpeechProviderScope != null) {
                return AlexaUserSpeechProviderScope.valueOf(alexaUserSpeechProviderScope.name());
            }
            return null;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

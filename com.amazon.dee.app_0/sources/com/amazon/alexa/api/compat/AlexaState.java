package com.amazon.alexa.api.compat;

import com.amazon.devicesetup.common.v1.WifiConnectionState;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaState.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaState;", "", "(Ljava/lang/String;I)V", WifiConnectionState.IDLE, "PREPARING_TO_LISTEN", "LISTENING", "FINISHING_LISTENING", "THINKING", "SPEAKING", "ERROR", "UNKNOWN", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum AlexaState {
    IDLE,
    PREPARING_TO_LISTEN,
    LISTENING,
    FINISHING_LISTENING,
    THINKING,
    SPEAKING,
    ERROR,
    UNKNOWN;
    
    public static final Companion Companion = new Companion(null);

    /* compiled from: AlexaState.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaState$Companion;", "", "()V", "convertToCompat", "Lcom/amazon/alexa/api/compat/AlexaState;", "noncompatState", "Lcom/amazon/alexa/api/AlexaState;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final AlexaState convertToCompat(@Nullable com.amazon.alexa.api.AlexaState alexaState) {
            if (alexaState != null) {
                return AlexaState.valueOf(alexaState.name());
            }
            return null;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

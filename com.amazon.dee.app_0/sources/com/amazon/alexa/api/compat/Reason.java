package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.DialogRequestDeniedReason;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DialogRequestDeniedReason.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u001a\b\u0086\u0001\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001aB\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/api/compat/Reason;", "", "(Ljava/lang/String;I)V", "UNKNOWN", "INVALID_AUDIO_METADATA", "INVALID_WAKE_WORD", "SCREEN_LOCKED", "SOURCE_ARBITRATION", "SPEECH_PROVIDER_NOT_REGISTERED", "OUT_OF_TURN_CANNOT_REQUEST_DIALOG", "OUT_OF_TURN_CANNOT_REQUEST_FIRST_TURN", "OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_BARGE_IN", "OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_START_FIRST_TURN", "OUT_OF_TURN_DIALOG_NOT_STARTED", "OUT_OF_TURN_DIALOG_NOT_CURRENT", "OUT_OF_TURN_UNEXPECTED_TURN", "OUT_OF_TURN_UNEXPECTED_NEXT_TURN", "OUT_OF_TURN_START_DIALOG_NOT_ALLOWED", "OUT_OF_TURN_CONTINUE_INVALID_DIALOG", "WAKE_WORD_ENGINE_NOT_READY", "WAKE_WORD_AUDIO_INCOMPLETE", "BINDING_ERROR", "LOCAL_SERVICE_DISCONNECTED", "LEADER_SELECTION_ERROR", "LEADER_DISABLED_ERROR", "INTERNAL_CLIENT_ERROR_MESSAGING", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum Reason {
    UNKNOWN,
    INVALID_AUDIO_METADATA,
    INVALID_WAKE_WORD,
    SCREEN_LOCKED,
    SOURCE_ARBITRATION,
    SPEECH_PROVIDER_NOT_REGISTERED,
    OUT_OF_TURN_CANNOT_REQUEST_DIALOG,
    OUT_OF_TURN_CANNOT_REQUEST_FIRST_TURN,
    OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_BARGE_IN,
    OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_START_FIRST_TURN,
    OUT_OF_TURN_DIALOG_NOT_STARTED,
    OUT_OF_TURN_DIALOG_NOT_CURRENT,
    OUT_OF_TURN_UNEXPECTED_TURN,
    OUT_OF_TURN_UNEXPECTED_NEXT_TURN,
    OUT_OF_TURN_START_DIALOG_NOT_ALLOWED,
    OUT_OF_TURN_CONTINUE_INVALID_DIALOG,
    WAKE_WORD_ENGINE_NOT_READY,
    WAKE_WORD_AUDIO_INCOMPLETE,
    BINDING_ERROR,
    LOCAL_SERVICE_DISCONNECTED,
    LEADER_SELECTION_ERROR,
    LEADER_DISABLED_ERROR,
    INTERNAL_CLIENT_ERROR_MESSAGING;
    
    public static final Companion Companion = new Companion(null);

    /* compiled from: DialogRequestDeniedReason.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/api/compat/Reason$Companion;", "", "()V", "convertToCompat", "Lcom/amazon/alexa/api/compat/Reason;", "noncompatReason", "Lcom/amazon/alexa/api/DialogRequestDeniedReason$Reason;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Reason convertToCompat(@NotNull DialogRequestDeniedReason.Reason noncompatReason) {
            Intrinsics.checkParameterIsNotNull(noncompatReason, "noncompatReason");
            return Reason.valueOf(noncompatReason.name());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

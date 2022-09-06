package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.Objects;
/* loaded from: classes6.dex */
public class DialogRequestDeniedReason {
    private final Exception exception;
    private final String message;
    private final Reason reason;

    /* loaded from: classes6.dex */
    static class DialogRequestDeniedReasonAdapter implements u<DialogRequestDeniedReason> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes6.dex */
        public enum DialogRequestDeniedReasonAdapterKey implements Bundles.Key {
            REQUEST_DENIED_REASON,
            REQUEST_DENIED_MESSAGE,
            REQUEST_DENIED_EXCEPTION
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.u
        /* renamed from: createFromBundle */
        public DialogRequestDeniedReason mo844createFromBundle(Bundle bundle) {
            return new DialogRequestDeniedReason(Reason.valueOf(Bundles.getString(bundle, DialogRequestDeniedReasonAdapterKey.REQUEST_DENIED_REASON)), Bundles.getOptionalString(bundle, DialogRequestDeniedReasonAdapterKey.REQUEST_DENIED_MESSAGE), (Exception) Bundles.getOptionalSerializable(bundle, DialogRequestDeniedReasonAdapterKey.REQUEST_DENIED_EXCEPTION, Exception.class));
        }

        @Override // com.amazon.alexa.api.u
        public Bundle toBundle(DialogRequestDeniedReason dialogRequestDeniedReason) {
            Bundle bundle = new Bundle();
            bundle.putString(DialogRequestDeniedReasonAdapterKey.REQUEST_DENIED_REASON.name(), dialogRequestDeniedReason.getReason().name());
            bundle.putString(DialogRequestDeniedReasonAdapterKey.REQUEST_DENIED_MESSAGE.name(), dialogRequestDeniedReason.getMessage());
            if (dialogRequestDeniedReason.getException() != null) {
                bundle.putSerializable(DialogRequestDeniedReasonAdapterKey.REQUEST_DENIED_EXCEPTION.name(), dialogRequestDeniedReason.getException());
            }
            return bundle;
        }
    }

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
        INTERNAL_CLIENT_ERROR_MESSAGING
    }

    public DialogRequestDeniedReason(Reason reason, @Nullable String str) {
        this(reason, str, null);
    }

    public DialogRequestDeniedReason(Reason reason, @Nullable String str, @Nullable Exception exc) {
        Preconditions.notNull(reason, "Reason cannot be null");
        this.reason = reason;
        this.message = str;
        this.exception = exc;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DialogRequestDeniedReason)) {
            return false;
        }
        DialogRequestDeniedReason dialogRequestDeniedReason = (DialogRequestDeniedReason) obj;
        return getReason() == dialogRequestDeniedReason.getReason() && Objects.equals(getMessage(), dialogRequestDeniedReason.getMessage()) && Objects.equals(getException(), dialogRequestDeniedReason.getException());
    }

    @Nullable
    public Exception getException() {
        return this.exception;
    }

    @Nullable
    public String getMessage() {
        return this.message;
    }

    public Reason getReason() {
        return this.reason;
    }

    public int hashCode() {
        return Objects.hash(getReason(), getMessage(), getException());
    }
}

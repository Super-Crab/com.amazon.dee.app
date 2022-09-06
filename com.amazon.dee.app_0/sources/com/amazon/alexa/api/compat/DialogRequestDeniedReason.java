package com.amazon.alexa.api.compat;

import amazon.speech.simclient.settings.Settings;
import com.amazon.alexa.api.DialogRequestDeniedReason;
import com.amazon.alexa.api.compat.Reason;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DialogRequestDeniedReason.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u0012\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bHÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0019\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/api/compat/DialogRequestDeniedReason;", "", Settings.ListeningSettings.EXTRA_REASON, "Lcom/amazon/alexa/api/compat/Reason;", "message", "", ADMRegistrationConstants.CALL_EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Lcom/amazon/alexa/api/compat/Reason;Ljava/lang/String;Ljava/lang/Exception;)V", "getException", "()Ljava/lang/Exception;", "getMessage", "()Ljava/lang/String;", "getReason", "()Lcom/amazon/alexa/api/compat/Reason;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DialogRequestDeniedReason {
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final Exception exception;
    @Nullable
    private final String message;
    @NotNull
    private final Reason reason;

    /* compiled from: DialogRequestDeniedReason.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/api/compat/DialogRequestDeniedReason$Companion;", "", "()V", "convertToCompat", "Lcom/amazon/alexa/api/compat/DialogRequestDeniedReason;", "noncompatReason", "Lcom/amazon/alexa/api/DialogRequestDeniedReason;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final DialogRequestDeniedReason convertToCompat(@NotNull com.amazon.alexa.api.DialogRequestDeniedReason noncompatReason) {
            Intrinsics.checkParameterIsNotNull(noncompatReason, "noncompatReason");
            Reason.Companion companion = Reason.Companion;
            DialogRequestDeniedReason.Reason reason = noncompatReason.getReason();
            Intrinsics.checkExpressionValueIsNotNull(reason, "noncompatReason.reason");
            return new DialogRequestDeniedReason(companion.convertToCompat(reason), noncompatReason.getMessage(), noncompatReason.getException());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public DialogRequestDeniedReason(@NotNull Reason reason, @Nullable String str, @Nullable Exception exc) {
        Intrinsics.checkParameterIsNotNull(reason, "reason");
        this.reason = reason;
        this.message = str;
        this.exception = exc;
    }

    public static /* synthetic */ DialogRequestDeniedReason copy$default(DialogRequestDeniedReason dialogRequestDeniedReason, Reason reason, String str, Exception exc, int i, Object obj) {
        if ((i & 1) != 0) {
            reason = dialogRequestDeniedReason.reason;
        }
        if ((i & 2) != 0) {
            str = dialogRequestDeniedReason.message;
        }
        if ((i & 4) != 0) {
            exc = dialogRequestDeniedReason.exception;
        }
        return dialogRequestDeniedReason.copy(reason, str, exc);
    }

    @NotNull
    public final Reason component1() {
        return this.reason;
    }

    @Nullable
    public final String component2() {
        return this.message;
    }

    @Nullable
    public final Exception component3() {
        return this.exception;
    }

    @NotNull
    public final DialogRequestDeniedReason copy(@NotNull Reason reason, @Nullable String str, @Nullable Exception exc) {
        Intrinsics.checkParameterIsNotNull(reason, "reason");
        return new DialogRequestDeniedReason(reason, str, exc);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DialogRequestDeniedReason)) {
                return false;
            }
            DialogRequestDeniedReason dialogRequestDeniedReason = (DialogRequestDeniedReason) obj;
            return Intrinsics.areEqual(this.reason, dialogRequestDeniedReason.reason) && Intrinsics.areEqual(this.message, dialogRequestDeniedReason.message) && Intrinsics.areEqual(this.exception, dialogRequestDeniedReason.exception);
        }
        return true;
    }

    @Nullable
    public final Exception getException() {
        return this.exception;
    }

    @Nullable
    public final String getMessage() {
        return this.message;
    }

    @NotNull
    public final Reason getReason() {
        return this.reason;
    }

    public int hashCode() {
        Reason reason = this.reason;
        int i = 0;
        int hashCode = (reason != null ? reason.hashCode() : 0) * 31;
        String str = this.message;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        Exception exc = this.exception;
        if (exc != null) {
            i = exc.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DialogRequestDeniedReason(reason=");
        outline107.append(this.reason);
        outline107.append(", message=");
        outline107.append(this.message);
        outline107.append(", exception=");
        outline107.append(this.exception);
        outline107.append(")");
        return outline107.toString();
    }

    public /* synthetic */ DialogRequestDeniedReason(Reason reason, String str, Exception exc, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reason, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : exc);
    }
}

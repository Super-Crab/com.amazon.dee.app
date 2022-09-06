package com.amazon.alexa.api.compat.metrics;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UserPerceivedLatencyData.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000  2\u00020\u0001:\u0001 B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u00112\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\u00118G¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00118G¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000b¨\u0006!"}, d2 = {"Lcom/amazon/alexa/api/compat/metrics/UserPerceivedLatencyData;", "", "dialogRequestId", "", "estimatedUserPerceivedLatency", "", "endOfSpeechOffset", "userPerceivedLatency", "alexaSpeechStarted", "(Ljava/lang/String;JJJJ)V", "getAlexaSpeechStarted", "()J", "getDialogRequestId", "()Ljava/lang/String;", "getEndOfSpeechOffset", "getEstimatedUserPerceivedLatency", "hasEndOfSpeechOffset", "", "()Z", "hasUserPerceivedLatency", "getUserPerceivedLatency", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class UserPerceivedLatencyData {
    public static final Companion Companion = new Companion(null);
    private final long alexaSpeechStarted;
    @NotNull
    private final String dialogRequestId;
    private final long endOfSpeechOffset;
    private final long estimatedUserPerceivedLatency;
    private final long userPerceivedLatency;

    /* compiled from: UserPerceivedLatencyData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/api/compat/metrics/UserPerceivedLatencyData$Companion;", "", "()V", "convertFromCompat", "Lcom/amazon/alexa/api/UserPerceivedLatencyData;", "userPerceivedLatencyData", "Lcom/amazon/alexa/api/compat/metrics/UserPerceivedLatencyData;", "convertToCompat", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final com.amazon.alexa.api.UserPerceivedLatencyData convertFromCompat(@NotNull UserPerceivedLatencyData userPerceivedLatencyData) {
            Intrinsics.checkParameterIsNotNull(userPerceivedLatencyData, "userPerceivedLatencyData");
            return new com.amazon.alexa.api.UserPerceivedLatencyData(userPerceivedLatencyData.getDialogRequestId(), userPerceivedLatencyData.getEstimatedUserPerceivedLatency(), userPerceivedLatencyData.getEndOfSpeechOffset(), userPerceivedLatencyData.getUserPerceivedLatency(), userPerceivedLatencyData.getAlexaSpeechStarted());
        }

        @NotNull
        public final UserPerceivedLatencyData convertToCompat(@NotNull com.amazon.alexa.api.UserPerceivedLatencyData userPerceivedLatencyData) {
            Intrinsics.checkParameterIsNotNull(userPerceivedLatencyData, "userPerceivedLatencyData");
            String dialogRequestId = userPerceivedLatencyData.getDialogRequestId();
            Intrinsics.checkExpressionValueIsNotNull(dialogRequestId, "userPerceivedLatencyData.dialogRequestId");
            return new UserPerceivedLatencyData(dialogRequestId, userPerceivedLatencyData.getEstimatedUserPerceivedLatency(), userPerceivedLatencyData.getEndOfSpeechOffset(), userPerceivedLatencyData.getUserPerceivedLatency(), userPerceivedLatencyData.getAlexaSpeechStarted());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public UserPerceivedLatencyData(@NotNull String dialogRequestId, long j, long j2, long j3, long j4) {
        Intrinsics.checkParameterIsNotNull(dialogRequestId, "dialogRequestId");
        this.dialogRequestId = dialogRequestId;
        this.estimatedUserPerceivedLatency = j;
        this.endOfSpeechOffset = j2;
        this.userPerceivedLatency = j3;
        this.alexaSpeechStarted = j4;
    }

    @NotNull
    public final String component1() {
        return this.dialogRequestId;
    }

    public final long component2() {
        return this.estimatedUserPerceivedLatency;
    }

    public final long component3() {
        return this.endOfSpeechOffset;
    }

    public final long component4() {
        return this.userPerceivedLatency;
    }

    public final long component5() {
        return this.alexaSpeechStarted;
    }

    @NotNull
    public final UserPerceivedLatencyData copy(@NotNull String dialogRequestId, long j, long j2, long j3, long j4) {
        Intrinsics.checkParameterIsNotNull(dialogRequestId, "dialogRequestId");
        return new UserPerceivedLatencyData(dialogRequestId, j, j2, j3, j4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof UserPerceivedLatencyData)) {
                return false;
            }
            UserPerceivedLatencyData userPerceivedLatencyData = (UserPerceivedLatencyData) obj;
            return Intrinsics.areEqual(this.dialogRequestId, userPerceivedLatencyData.dialogRequestId) && this.estimatedUserPerceivedLatency == userPerceivedLatencyData.estimatedUserPerceivedLatency && this.endOfSpeechOffset == userPerceivedLatencyData.endOfSpeechOffset && this.userPerceivedLatency == userPerceivedLatencyData.userPerceivedLatency && this.alexaSpeechStarted == userPerceivedLatencyData.alexaSpeechStarted;
        }
        return true;
    }

    public final long getAlexaSpeechStarted() {
        return this.alexaSpeechStarted;
    }

    @NotNull
    public final String getDialogRequestId() {
        return this.dialogRequestId;
    }

    public final long getEndOfSpeechOffset() {
        return this.endOfSpeechOffset;
    }

    public final long getEstimatedUserPerceivedLatency() {
        return this.estimatedUserPerceivedLatency;
    }

    public final long getUserPerceivedLatency() {
        return this.userPerceivedLatency;
    }

    @JvmName(name = "hasEndOfSpeechOffset")
    public final boolean hasEndOfSpeechOffset() {
        return this.endOfSpeechOffset >= 0;
    }

    @JvmName(name = "hasUserPerceivedLatency")
    public final boolean hasUserPerceivedLatency() {
        return this.userPerceivedLatency >= 0;
    }

    public int hashCode() {
        String str = this.dialogRequestId;
        int hashCode = str != null ? str.hashCode() : 0;
        long j = this.estimatedUserPerceivedLatency;
        long j2 = this.endOfSpeechOffset;
        long j3 = this.userPerceivedLatency;
        long j4 = this.alexaSpeechStarted;
        return (((((((hashCode * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + ((int) (j4 ^ (j4 >>> 32)));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UserPerceivedLatencyData(dialogRequestId=");
        outline107.append(this.dialogRequestId);
        outline107.append(", estimatedUserPerceivedLatency=");
        outline107.append(this.estimatedUserPerceivedLatency);
        outline107.append(", endOfSpeechOffset=");
        outline107.append(this.endOfSpeechOffset);
        outline107.append(", userPerceivedLatency=");
        outline107.append(this.userPerceivedLatency);
        outline107.append(", alexaSpeechStarted=");
        return GeneratedOutlineSupport1.outline87(outline107, this.alexaSpeechStarted, ")");
    }
}

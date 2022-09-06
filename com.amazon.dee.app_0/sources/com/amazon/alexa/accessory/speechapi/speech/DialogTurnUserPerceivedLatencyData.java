package com.amazon.alexa.accessory.speechapi.speech;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DialogTurnUserPerceivedLatencyData.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001d"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnUserPerceivedLatencyData;", "", "dialogRequestId", "", "estimatedUpl", "", "endOfSpeechOffset", "actualUpl", "alexaSpeechStarted", "(Ljava/lang/String;JJJJ)V", "getActualUpl", "()J", "getAlexaSpeechStarted", "getDialogRequestId", "()Ljava/lang/String;", "getEndOfSpeechOffset", "getEstimatedUpl", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DialogTurnUserPerceivedLatencyData {
    private final long actualUpl;
    private final long alexaSpeechStarted;
    @NotNull
    private final String dialogRequestId;
    private final long endOfSpeechOffset;
    private final long estimatedUpl;

    public DialogTurnUserPerceivedLatencyData(@NotNull String dialogRequestId, long j, long j2, long j3, long j4) {
        Intrinsics.checkParameterIsNotNull(dialogRequestId, "dialogRequestId");
        this.dialogRequestId = dialogRequestId;
        this.estimatedUpl = j;
        this.endOfSpeechOffset = j2;
        this.actualUpl = j3;
        this.alexaSpeechStarted = j4;
    }

    @NotNull
    public final String component1() {
        return this.dialogRequestId;
    }

    public final long component2() {
        return this.estimatedUpl;
    }

    public final long component3() {
        return this.endOfSpeechOffset;
    }

    public final long component4() {
        return this.actualUpl;
    }

    public final long component5() {
        return this.alexaSpeechStarted;
    }

    @NotNull
    public final DialogTurnUserPerceivedLatencyData copy(@NotNull String dialogRequestId, long j, long j2, long j3, long j4) {
        Intrinsics.checkParameterIsNotNull(dialogRequestId, "dialogRequestId");
        return new DialogTurnUserPerceivedLatencyData(dialogRequestId, j, j2, j3, j4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DialogTurnUserPerceivedLatencyData)) {
                return false;
            }
            DialogTurnUserPerceivedLatencyData dialogTurnUserPerceivedLatencyData = (DialogTurnUserPerceivedLatencyData) obj;
            return Intrinsics.areEqual(this.dialogRequestId, dialogTurnUserPerceivedLatencyData.dialogRequestId) && this.estimatedUpl == dialogTurnUserPerceivedLatencyData.estimatedUpl && this.endOfSpeechOffset == dialogTurnUserPerceivedLatencyData.endOfSpeechOffset && this.actualUpl == dialogTurnUserPerceivedLatencyData.actualUpl && this.alexaSpeechStarted == dialogTurnUserPerceivedLatencyData.alexaSpeechStarted;
        }
        return true;
    }

    public final long getActualUpl() {
        return this.actualUpl;
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

    public final long getEstimatedUpl() {
        return this.estimatedUpl;
    }

    public int hashCode() {
        String str = this.dialogRequestId;
        int hashCode = str != null ? str.hashCode() : 0;
        long j = this.estimatedUpl;
        long j2 = this.endOfSpeechOffset;
        long j3 = this.actualUpl;
        long j4 = this.alexaSpeechStarted;
        return (((((((hashCode * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + ((int) (j4 ^ (j4 >>> 32)));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DialogTurnUserPerceivedLatencyData(dialogRequestId=");
        outline107.append(this.dialogRequestId);
        outline107.append(", estimatedUpl=");
        outline107.append(this.estimatedUpl);
        outline107.append(", endOfSpeechOffset=");
        outline107.append(this.endOfSpeechOffset);
        outline107.append(", actualUpl=");
        outline107.append(this.actualUpl);
        outline107.append(", alexaSpeechStarted=");
        return GeneratedOutlineSupport1.outline87(outline107, this.alexaSpeechStarted, ")");
    }
}

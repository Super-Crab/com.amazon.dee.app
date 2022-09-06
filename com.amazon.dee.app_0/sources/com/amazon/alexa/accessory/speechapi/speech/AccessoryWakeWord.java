package com.amazon.alexa.accessory.speechapi.speech;

import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AccessoryWakeWord.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryWakeWord;", "", AlexaMetricsConstants.EventConstants.WAKE_WORD, "", "startIndexInSamples", "", "endIndexInSamples", "(Ljava/lang/String;II)V", "getEndIndexInSamples", "()I", "getStartIndexInSamples", "getWakeWord", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AccessoryWakeWord {
    private final int endIndexInSamples;
    private final int startIndexInSamples;
    @NotNull
    private final String wakeWord;

    public AccessoryWakeWord(@NotNull String wakeWord, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(wakeWord, "wakeWord");
        this.wakeWord = wakeWord;
        this.startIndexInSamples = i;
        this.endIndexInSamples = i2;
    }

    public static /* synthetic */ AccessoryWakeWord copy$default(AccessoryWakeWord accessoryWakeWord, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = accessoryWakeWord.wakeWord;
        }
        if ((i3 & 2) != 0) {
            i = accessoryWakeWord.startIndexInSamples;
        }
        if ((i3 & 4) != 0) {
            i2 = accessoryWakeWord.endIndexInSamples;
        }
        return accessoryWakeWord.copy(str, i, i2);
    }

    @NotNull
    public final String component1() {
        return this.wakeWord;
    }

    public final int component2() {
        return this.startIndexInSamples;
    }

    public final int component3() {
        return this.endIndexInSamples;
    }

    @NotNull
    public final AccessoryWakeWord copy(@NotNull String wakeWord, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(wakeWord, "wakeWord");
        return new AccessoryWakeWord(wakeWord, i, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof AccessoryWakeWord)) {
                return false;
            }
            AccessoryWakeWord accessoryWakeWord = (AccessoryWakeWord) obj;
            return Intrinsics.areEqual(this.wakeWord, accessoryWakeWord.wakeWord) && this.startIndexInSamples == accessoryWakeWord.startIndexInSamples && this.endIndexInSamples == accessoryWakeWord.endIndexInSamples;
        }
        return true;
    }

    public final int getEndIndexInSamples() {
        return this.endIndexInSamples;
    }

    public final int getStartIndexInSamples() {
        return this.startIndexInSamples;
    }

    @NotNull
    public final String getWakeWord() {
        return this.wakeWord;
    }

    public int hashCode() {
        String str = this.wakeWord;
        return ((((str != null ? str.hashCode() : 0) * 31) + this.startIndexInSamples) * 31) + this.endIndexInSamples;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryWakeWord(wakeWord=");
        outline107.append(this.wakeWord);
        outline107.append(", startIndexInSamples=");
        outline107.append(this.startIndexInSamples);
        outline107.append(", endIndexInSamples=");
        return GeneratedOutlineSupport1.outline86(outline107, this.endIndexInSamples, ")");
    }
}

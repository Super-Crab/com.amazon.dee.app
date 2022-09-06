package com.amazon.alexa;

import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.eYr;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: WakeWord.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003HÂ\u0003J\u0013\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nHÖ\u0003J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/client/alexaservice/speaker/payload/WakeWord;", "Lcom/amazon/alexa/client/core/networking/adapters/StronglyTypedString;", AlexaMetricsConstants.EventConstants.WAKE_WORD, "", "(Ljava/lang/String;)V", "component1", "copy", "equals", "", "other", "", "getValue", "hashCode", "", "toString", "Companion", "AVSAndroidClient-alexaservice_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class eYr implements StronglyTypedString {
    public static final zZm zZm = new zZm(null);
    public final String BIo;

    /* compiled from: WakeWord.kt */
    /* loaded from: classes.dex */
    public static final class zZm {
        public /* synthetic */ zZm(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @NotNull
        public final StronglyTypedString.StronglyTypedStringAdapter<eYr> zZm() {
            return new StronglyTypedString.StronglyTypedStringAdapter<eYr>() { // from class: com.amazon.alexa.client.alexaservice.speaker.payload.WakeWord$Companion$getTypeAdapter$1
                @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
                @NotNull
                /* renamed from: instantiate  reason: collision with other method in class */
                public eYr mo1132instantiate(@NotNull String value) {
                    Intrinsics.checkParameterIsNotNull(value, "value");
                    return new eYr(value);
                }
            };
        }
    }

    public eYr(@NotNull String wakeWord) {
        Intrinsics.checkParameterIsNotNull(wakeWord, "wakeWord");
        this.BIo = wakeWord;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof eYr) && Intrinsics.areEqual(this.BIo, ((eYr) obj).BIo);
        }
        return true;
    }

    @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString
    @NotNull
    public String getValue() {
        return this.BIo;
    }

    public int hashCode() {
        String str = this.BIo;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("WakeWord(wakeWord="), this.BIo, ")");
    }
}

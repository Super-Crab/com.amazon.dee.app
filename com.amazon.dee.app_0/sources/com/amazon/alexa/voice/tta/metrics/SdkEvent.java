package com.amazon.alexa.voice.tta.metrics;

import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.tta.metrics.EventTime;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SimbaEventProcessor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/SdkEvent;", "", "name", "Lcom/amazon/alexa/api/UiEventName;", "source", "", "latency", "Lcom/amazon/alexa/voice/tta/metrics/EventTime$Interval;", "(Lcom/amazon/alexa/api/UiEventName;Ljava/lang/String;Lcom/amazon/alexa/voice/tta/metrics/EventTime$Interval;)V", "getLatency", "()Lcom/amazon/alexa/voice/tta/metrics/EventTime$Interval;", "getName", "()Lcom/amazon/alexa/api/UiEventName;", "getSource", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class SdkEvent {
    @Nullable
    private final EventTime.Interval latency;
    @NotNull
    private final UiEventName name;
    @Nullable
    private final String source;

    public SdkEvent(@NotNull UiEventName name, @Nullable String str, @Nullable EventTime.Interval interval) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
        this.source = str;
        this.latency = interval;
    }

    public static /* synthetic */ SdkEvent copy$default(SdkEvent sdkEvent, UiEventName uiEventName, String str, EventTime.Interval interval, int i, Object obj) {
        if ((i & 1) != 0) {
            uiEventName = sdkEvent.name;
        }
        if ((i & 2) != 0) {
            str = sdkEvent.source;
        }
        if ((i & 4) != 0) {
            interval = sdkEvent.latency;
        }
        return sdkEvent.copy(uiEventName, str, interval);
    }

    @NotNull
    public final UiEventName component1() {
        return this.name;
    }

    @Nullable
    public final String component2() {
        return this.source;
    }

    @Nullable
    public final EventTime.Interval component3() {
        return this.latency;
    }

    @NotNull
    public final SdkEvent copy(@NotNull UiEventName name, @Nullable String str, @Nullable EventTime.Interval interval) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new SdkEvent(name, str, interval);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SdkEvent)) {
                return false;
            }
            SdkEvent sdkEvent = (SdkEvent) obj;
            return Intrinsics.areEqual(this.name, sdkEvent.name) && Intrinsics.areEqual(this.source, sdkEvent.source) && Intrinsics.areEqual(this.latency, sdkEvent.latency);
        }
        return true;
    }

    @Nullable
    public final EventTime.Interval getLatency() {
        return this.latency;
    }

    @NotNull
    public final UiEventName getName() {
        return this.name;
    }

    @Nullable
    public final String getSource() {
        return this.source;
    }

    public int hashCode() {
        UiEventName uiEventName = this.name;
        int i = 0;
        int hashCode = (uiEventName != null ? uiEventName.hashCode() : 0) * 31;
        String str = this.source;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        EventTime.Interval interval = this.latency;
        if (interval != null) {
            i = interval.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SdkEvent(name=");
        outline107.append(this.name);
        outline107.append(", source=");
        outline107.append(this.source);
        outline107.append(", latency=");
        outline107.append(this.latency);
        outline107.append(")");
        return outline107.toString();
    }

    public /* synthetic */ SdkEvent(UiEventName uiEventName, String str, EventTime.Interval interval, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(uiEventName, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : interval);
    }
}

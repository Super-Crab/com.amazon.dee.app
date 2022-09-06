package com.amazon.wellness.io.format.abs;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaBiometricData.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/amazon/wellness/io/format/abs/AlexaBiometricData;", "", DefaultDeliveryClient.EVENTS_DIRECTORY, "", "Lcom/amazon/wellness/io/format/abs/Event;", "(Ljava/util/Set;)V", "getEvents", "()Ljava/util/Set;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaBiometricSampleEncoderAndroid_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AlexaBiometricData {
    @NotNull
    private final Set<Event> events;

    public AlexaBiometricData(@NotNull Set<Event> events) {
        Intrinsics.checkParameterIsNotNull(events, "events");
        this.events = events;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AlexaBiometricData copy$default(AlexaBiometricData alexaBiometricData, Set set, int i, Object obj) {
        if ((i & 1) != 0) {
            set = alexaBiometricData.events;
        }
        return alexaBiometricData.copy(set);
    }

    @NotNull
    public final Set<Event> component1() {
        return this.events;
    }

    @NotNull
    public final AlexaBiometricData copy(@NotNull Set<Event> events) {
        Intrinsics.checkParameterIsNotNull(events, "events");
        return new AlexaBiometricData(events);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof AlexaBiometricData) && Intrinsics.areEqual(this.events, ((AlexaBiometricData) obj).events);
        }
        return true;
    }

    @NotNull
    public final Set<Event> getEvents() {
        return this.events;
    }

    public int hashCode() {
        Set<Event> set = this.events;
        if (set != null) {
            return set.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlexaBiometricData(events=");
        outline107.append(this.events);
        outline107.append(")");
        return outline107.toString();
    }
}

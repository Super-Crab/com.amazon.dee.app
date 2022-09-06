package com.amazon.alexa.fitness.api.afx;

import com.amazon.alexa.fitness.api.LocationCoordinate;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessSessionOrchestrator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\t\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/UISessionSummary;", "", "coordinates", "", "Lcom/amazon/alexa/fitness/api/LocationCoordinate;", "(Ljava/util/List;)V", "getCoordinates", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class UISessionSummary {
    @Nullable
    private final List<LocationCoordinate> coordinates;

    public UISessionSummary(@Nullable List<LocationCoordinate> list) {
        this.coordinates = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ UISessionSummary copy$default(UISessionSummary uISessionSummary, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = uISessionSummary.coordinates;
        }
        return uISessionSummary.copy(list);
    }

    @Nullable
    public final List<LocationCoordinate> component1() {
        return this.coordinates;
    }

    @NotNull
    public final UISessionSummary copy(@Nullable List<LocationCoordinate> list) {
        return new UISessionSummary(list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof UISessionSummary) && Intrinsics.areEqual(this.coordinates, ((UISessionSummary) obj).coordinates);
        }
        return true;
    }

    @Nullable
    public final List<LocationCoordinate> getCoordinates() {
        return this.coordinates;
    }

    public int hashCode() {
        List<LocationCoordinate> list = this.coordinates;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline95(GeneratedOutlineSupport1.outline107("UISessionSummary(coordinates="), this.coordinates, ")");
    }
}

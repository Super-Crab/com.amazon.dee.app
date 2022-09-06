package com.amazon.alexa.fitness.repository;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.SerializationUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionSummaryCache.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006J\u0015\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u001f\u0010\n\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0004HÖ\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/fitness/repository/PendingSessionSummaryMap;", "Ljava/io/Serializable;", "sessionIdToSummaryMap", "", "", "Lcom/amazon/alexa/fitness/repository/PendingSessionSummary;", "(Ljava/util/Map;)V", "getSessionIdToSummaryMap", "()Ljava/util/Map;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "serialize", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class PendingSessionSummaryMap implements Serializable {
    @NotNull
    private final Map<String, PendingSessionSummary> sessionIdToSummaryMap;

    public PendingSessionSummaryMap() {
        this(null, 1, null);
    }

    public PendingSessionSummaryMap(@NotNull Map<String, PendingSessionSummary> sessionIdToSummaryMap) {
        Intrinsics.checkParameterIsNotNull(sessionIdToSummaryMap, "sessionIdToSummaryMap");
        this.sessionIdToSummaryMap = sessionIdToSummaryMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PendingSessionSummaryMap copy$default(PendingSessionSummaryMap pendingSessionSummaryMap, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            map = pendingSessionSummaryMap.sessionIdToSummaryMap;
        }
        return pendingSessionSummaryMap.copy(map);
    }

    @NotNull
    public final Map<String, PendingSessionSummary> component1() {
        return this.sessionIdToSummaryMap;
    }

    @NotNull
    public final PendingSessionSummaryMap copy(@NotNull Map<String, PendingSessionSummary> sessionIdToSummaryMap) {
        Intrinsics.checkParameterIsNotNull(sessionIdToSummaryMap, "sessionIdToSummaryMap");
        return new PendingSessionSummaryMap(sessionIdToSummaryMap);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof PendingSessionSummaryMap) && Intrinsics.areEqual(this.sessionIdToSummaryMap, ((PendingSessionSummaryMap) obj).sessionIdToSummaryMap);
        }
        return true;
    }

    @NotNull
    public final Map<String, PendingSessionSummary> getSessionIdToSummaryMap() {
        return this.sessionIdToSummaryMap;
    }

    public int hashCode() {
        Map<String, PendingSessionSummary> map = this.sessionIdToSummaryMap;
        if (map != null) {
            return map.hashCode();
        }
        return 0;
    }

    @NotNull
    public final byte[] serialize() {
        byte[] serialize = SerializationUtils.serialize(this);
        Intrinsics.checkExpressionValueIsNotNull(serialize, "SerializationUtils.serialize(this)");
        return serialize;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PendingSessionSummaryMap(sessionIdToSummaryMap=");
        outline107.append(this.sessionIdToSummaryMap);
        outline107.append(")");
        return outline107.toString();
    }

    public /* synthetic */ PendingSessionSummaryMap(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new LinkedHashMap() : map);
    }
}

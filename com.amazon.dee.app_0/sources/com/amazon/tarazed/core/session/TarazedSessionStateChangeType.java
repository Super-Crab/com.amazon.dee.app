package com.amazon.tarazed.core.session;

import com.amazon.tarazed.core.session.sessionEvents.SessionEvents;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedSessionStateChangeType.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0086\u0001\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0013"}, d2 = {"Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeType;", "", SessionEvents.STATE_CHANGE, "", "(Ljava/lang/String;ILjava/lang/String;)V", "getStateChange", "()Ljava/lang/String;", "CONNECT_IOT", "REQUEST_START_SESSION", "START_SESSION", "RESUME_SESSION", "REQUEST_RESUME_SESSION", "PAUSE_SESSION", "BEGIN_SUSPENDING_SESSION", "SUSPEND_SESSION", "CONFIRM_END_SESSION", "BEGIN_ENDING_SESSION", "END_SESSION", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public enum TarazedSessionStateChangeType {
    CONNECT_IOT("connectIot"),
    REQUEST_START_SESSION("requestStartSession"),
    START_SESSION("startSession"),
    RESUME_SESSION("resumeSession"),
    REQUEST_RESUME_SESSION("requestResumeSession"),
    PAUSE_SESSION("pauseSession"),
    BEGIN_SUSPENDING_SESSION("beginSuspendingSession"),
    SUSPEND_SESSION("suspendSession"),
    CONFIRM_END_SESSION("confirmEndSession"),
    BEGIN_ENDING_SESSION("beginEndingSession"),
    END_SESSION("endSession");
    
    public static final Companion Companion = new Companion(null);
    private static final Map<String, TarazedSessionStateChangeType> VALUE_MAP;
    @NotNull
    private final String stateChange;

    /* compiled from: TarazedSessionStateChangeType.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeType$Companion;", "", "()V", "VALUE_MAP", "", "", "Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeType;", "lookupStateChange", SessionEvents.STATE_CHANGE, "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final TarazedSessionStateChangeType lookupStateChange(@NotNull String stateChange) {
            Intrinsics.checkParameterIsNotNull(stateChange, "stateChange");
            return (TarazedSessionStateChangeType) TarazedSessionStateChangeType.VALUE_MAP.get(stateChange);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        int mapCapacity;
        int coerceAtLeast;
        TarazedSessionStateChangeType[] values = values();
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(values.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (TarazedSessionStateChangeType tarazedSessionStateChangeType : values) {
            linkedHashMap.put(tarazedSessionStateChangeType.stateChange, tarazedSessionStateChangeType);
        }
        VALUE_MAP = linkedHashMap;
    }

    TarazedSessionStateChangeType(String str) {
        this.stateChange = str;
    }

    @NotNull
    public final String getStateChange() {
        return this.stateChange;
    }
}

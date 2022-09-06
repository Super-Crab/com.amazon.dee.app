package com.amazon.tarazed.core.session;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedSessionStateChangeRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeRequest;", "", "type", "Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeType;", "source", "Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeSource;", "(Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeType;Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeSource;)V", "getSource", "()Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeSource;", "getType", "()Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeType;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedSessionStateChangeRequest {
    @NotNull
    private final TarazedSessionStateChangeSource source;
    @NotNull
    private final TarazedSessionStateChangeType type;

    public TarazedSessionStateChangeRequest(@NotNull TarazedSessionStateChangeType type, @NotNull TarazedSessionStateChangeSource source) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.type = type;
        this.source = source;
    }

    public static /* synthetic */ TarazedSessionStateChangeRequest copy$default(TarazedSessionStateChangeRequest tarazedSessionStateChangeRequest, TarazedSessionStateChangeType tarazedSessionStateChangeType, TarazedSessionStateChangeSource tarazedSessionStateChangeSource, int i, Object obj) {
        if ((i & 1) != 0) {
            tarazedSessionStateChangeType = tarazedSessionStateChangeRequest.type;
        }
        if ((i & 2) != 0) {
            tarazedSessionStateChangeSource = tarazedSessionStateChangeRequest.source;
        }
        return tarazedSessionStateChangeRequest.copy(tarazedSessionStateChangeType, tarazedSessionStateChangeSource);
    }

    @NotNull
    public final TarazedSessionStateChangeType component1() {
        return this.type;
    }

    @NotNull
    public final TarazedSessionStateChangeSource component2() {
        return this.source;
    }

    @NotNull
    public final TarazedSessionStateChangeRequest copy(@NotNull TarazedSessionStateChangeType type, @NotNull TarazedSessionStateChangeSource source) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(source, "source");
        return new TarazedSessionStateChangeRequest(type, source);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof TarazedSessionStateChangeRequest)) {
                return false;
            }
            TarazedSessionStateChangeRequest tarazedSessionStateChangeRequest = (TarazedSessionStateChangeRequest) obj;
            return Intrinsics.areEqual(this.type, tarazedSessionStateChangeRequest.type) && Intrinsics.areEqual(this.source, tarazedSessionStateChangeRequest.source);
        }
        return true;
    }

    @NotNull
    public final TarazedSessionStateChangeSource getSource() {
        return this.source;
    }

    @NotNull
    public final TarazedSessionStateChangeType getType() {
        return this.type;
    }

    public int hashCode() {
        TarazedSessionStateChangeType tarazedSessionStateChangeType = this.type;
        int i = 0;
        int hashCode = (tarazedSessionStateChangeType != null ? tarazedSessionStateChangeType.hashCode() : 0) * 31;
        TarazedSessionStateChangeSource tarazedSessionStateChangeSource = this.source;
        if (tarazedSessionStateChangeSource != null) {
            i = tarazedSessionStateChangeSource.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TarazedSessionStateChangeRequest(type=");
        outline107.append(this.type);
        outline107.append(", source=");
        outline107.append(this.source);
        outline107.append(")");
        return outline107.toString();
    }
}

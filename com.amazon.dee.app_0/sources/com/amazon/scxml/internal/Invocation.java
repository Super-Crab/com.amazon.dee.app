package com.amazon.scxml.internal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Invocation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0017"}, d2 = {"Lcom/amazon/scxml/internal/Invocation;", "Lcom/amazon/scxml/internal/Executable;", "type", "", "src", "(Ljava/lang/String;Ljava/lang/String;)V", "getSrc", "()Ljava/lang/String;", "getType", "component1", "component2", "copy", "equals", "", "other", "", "execute", "", "machine", "Lcom/amazon/scxml/internal/SCXMLStateMachineExecutor;", "hashCode", "", "toString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class Invocation implements Executable {
    @NotNull
    private final String src;
    @NotNull
    private final String type;

    public Invocation(@NotNull String type, @NotNull String src) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(src, "src");
        this.type = type;
        this.src = src;
    }

    public static /* synthetic */ Invocation copy$default(Invocation invocation, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = invocation.type;
        }
        if ((i & 2) != 0) {
            str2 = invocation.src;
        }
        return invocation.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.type;
    }

    @NotNull
    public final String component2() {
        return this.src;
    }

    @NotNull
    public final Invocation copy(@NotNull String type, @NotNull String src) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(src, "src");
        return new Invocation(type, src);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Invocation)) {
                return false;
            }
            Invocation invocation = (Invocation) obj;
            return Intrinsics.areEqual(this.type, invocation.type) && Intrinsics.areEqual(this.src, invocation.src);
        }
        return true;
    }

    @Override // com.amazon.scxml.internal.Executable
    public void execute(@NotNull SCXMLStateMachineExecutor machine) {
        Intrinsics.checkParameterIsNotNull(machine, "machine");
        machine.execute(this);
    }

    @NotNull
    public final String getSrc() {
        return this.src;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        String str = this.type;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.src;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invocation(type=");
        outline107.append(this.type);
        outline107.append(", src=");
        return GeneratedOutlineSupport1.outline91(outline107, this.src, ")");
    }
}

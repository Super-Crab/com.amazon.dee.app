package com.amazon.scxml.internal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Raise.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lcom/amazon/scxml/internal/Raise;", "Lcom/amazon/scxml/internal/Executable;", "event", "", "(Ljava/lang/String;)V", "getEvent", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "execute", "", "machine", "Lcom/amazon/scxml/internal/SCXMLStateMachineExecutor;", "hashCode", "", "toString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class Raise implements Executable {
    @NotNull
    private final String event;

    public Raise(@NotNull String event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.event = event;
    }

    public static /* synthetic */ Raise copy$default(Raise raise, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = raise.event;
        }
        return raise.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.event;
    }

    @NotNull
    public final Raise copy(@NotNull String event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        return new Raise(event);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof Raise) && Intrinsics.areEqual(this.event, ((Raise) obj).event);
        }
        return true;
    }

    @Override // com.amazon.scxml.internal.Executable
    public void execute(@NotNull SCXMLStateMachineExecutor machine) {
        Intrinsics.checkParameterIsNotNull(machine, "machine");
        machine.execute(this);
    }

    @NotNull
    public final String getEvent() {
        return this.event;
    }

    public int hashCode() {
        String str = this.event;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Raise(event="), this.event, ")");
    }
}

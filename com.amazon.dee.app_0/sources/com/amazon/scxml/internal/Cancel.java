package com.amazon.scxml.internal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Cancel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/amazon/scxml/internal/Cancel;", "Lcom/amazon/scxml/internal/Executable;", "sendId", "", "(Ljava/lang/String;)V", "getSendId", "()Ljava/lang/String;", "execute", "", "machine", "Lcom/amazon/scxml/internal/SCXMLStateMachineExecutor;", "toString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class Cancel implements Executable {
    @NotNull
    private final String sendId;

    public Cancel(@NotNull String sendId) {
        Intrinsics.checkParameterIsNotNull(sendId, "sendId");
        this.sendId = sendId;
    }

    @Override // com.amazon.scxml.internal.Executable
    public void execute(@NotNull SCXMLStateMachineExecutor machine) {
        Intrinsics.checkParameterIsNotNull(machine, "machine");
        machine.execute(this);
    }

    @NotNull
    public final String getSendId() {
        return this.sendId;
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline89(GeneratedOutlineSupport1.outline107("Cancel("), this.sendId, ')');
    }
}

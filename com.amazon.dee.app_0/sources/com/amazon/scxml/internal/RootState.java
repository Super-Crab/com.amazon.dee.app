package com.amazon.scxml.internal;

import com.amazon.deecomms.common.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: RootState.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/amazon/scxml/internal/RootState;", "Lcom/amazon/scxml/internal/State;", Constants.ACCESSORY_PRIVACY_INITIAL_STATUS, "", "substates", "", "(Ljava/lang/String;[Lcom/amazon/scxml/internal/State;)V", "getLeastCommonProperCompoundAncestor", "other", "isRoot", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class RootState extends State {
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public RootState(@org.jetbrains.annotations.NotNull java.lang.String r12, @org.jetbrains.annotations.NotNull com.amazon.scxml.internal.State[] r13) {
        /*
            r11 = this;
            java.lang.String r0 = "initial"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            java.lang.String r0 = "substates"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r13, r0)
            java.lang.String r3 = com.amazon.scxml.internal.RootStateKt.access$getUniqueId$p()
            java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()
            java.util.List r8 = kotlin.collections.CollectionsKt.emptyList()
            java.util.List r9 = kotlin.collections.CollectionsKt.emptyList()
            java.util.List r10 = kotlin.collections.ArraysKt.toList(r13)
            r2 = 0
            r4 = 0
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r11
            r7 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            int r12 = r13.length
            r0 = 0
        L29:
            if (r0 >= r12) goto L33
            r1 = r13[r0]
            r1.setParent(r11)
            int r0 = r0 + 1
            goto L29
        L33:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.scxml.internal.RootState.<init>(java.lang.String, com.amazon.scxml.internal.State[]):void");
    }

    @Override // com.amazon.scxml.internal.State
    @Nullable
    public State getLeastCommonProperCompoundAncestor(@NotNull State other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return null;
    }

    @Override // com.amazon.scxml.internal.State
    public boolean isRoot() {
        return true;
    }
}

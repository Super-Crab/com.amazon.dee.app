package com.amazon.photos.discovery.workinfo;

import androidx.work.Data;
import androidx.work.WorkInfo;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DiscoveryWorkInfo.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b&\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B3\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/amazon/photos/discovery/workinfo/DiscoveryWorkInfo;", "", "workInfo", "Landroidx/work/WorkInfo;", "(Landroidx/work/WorkInfo;)V", "outputData", "Landroidx/work/Data;", "id", "Ljava/util/UUID;", "state", "Landroidx/work/WorkInfo$State;", "tags", "", "", "runAttemptCount", "", "(Landroidx/work/Data;Ljava/util/UUID;Landroidx/work/WorkInfo$State;Ljava/util/Set;I)V", "getId", "()Ljava/util/UUID;", "getOutputData", "()Landroidx/work/Data;", "getRunAttemptCount", "()I", "getState", "()Landroidx/work/WorkInfo$State;", "getTags", "()Ljava/util/Set;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class DiscoveryWorkInfo {
    @NotNull
    private final UUID id;
    @NotNull
    private final Data outputData;
    private final int runAttemptCount;
    @NotNull
    private final WorkInfo.State state;
    @NotNull
    private final Set<String> tags;

    public DiscoveryWorkInfo(@NotNull Data outputData, @NotNull UUID id, @NotNull WorkInfo.State state, @NotNull Set<String> tags, int i) {
        Intrinsics.checkParameterIsNotNull(outputData, "outputData");
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(tags, "tags");
        this.outputData = outputData;
        this.id = id;
        this.state = state;
        this.tags = tags;
        this.runAttemptCount = i;
    }

    @NotNull
    public final UUID getId() {
        return this.id;
    }

    @NotNull
    public final Data getOutputData() {
        return this.outputData;
    }

    public final int getRunAttemptCount() {
        return this.runAttemptCount;
    }

    @NotNull
    public final WorkInfo.State getState() {
        return this.state;
    }

    @NotNull
    public final Set<String> getTags() {
        return this.tags;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DiscoveryWorkInfo(@org.jetbrains.annotations.NotNull androidx.work.WorkInfo r8) {
        /*
            r7 = this;
            java.lang.String r0 = "workInfo"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            androidx.work.Data r2 = r8.getOutputData()
            java.lang.String r0 = "workInfo.outputData"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r0)
            java.util.UUID r3 = r8.getId()
            java.lang.String r0 = "workInfo.id"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r0)
            androidx.work.WorkInfo$State r4 = r8.getState()
            java.lang.String r0 = "workInfo.state"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r0)
            java.util.Set r5 = r8.getTags()
            java.lang.String r0 = "workInfo.tags"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)
            int r6 = r8.getRunAttemptCount()
            r1 = r7
            r1.<init>(r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.discovery.workinfo.DiscoveryWorkInfo.<init>(androidx.work.WorkInfo):void");
    }
}

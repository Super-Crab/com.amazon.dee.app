package com.amazon.photos.discovery.dedupe;

import androidx.work.Constraints;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DedupeStage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J\t\u0010\u001c\u001a\u00020\u000bHÆ\u0003JE\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\u0005HÖ\u0001J\t\u0010\"\u001a\u00020\u000bHÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006#"}, d2 = {"Lcom/amazon/photos/discovery/dedupe/DedupeStage;", "", "deduplicator", "Lcom/amazon/photos/discovery/dedupe/Deduplicator;", "stageId", "", "operatorStageId", "batchSize", "constraints", "Landroidx/work/Constraints;", "name", "", "(Lcom/amazon/photos/discovery/dedupe/Deduplicator;IIILandroidx/work/Constraints;Ljava/lang/String;)V", "getBatchSize", "()I", "getConstraints", "()Landroidx/work/Constraints;", "getDeduplicator", "()Lcom/amazon/photos/discovery/dedupe/Deduplicator;", "getName", "()Ljava/lang/String;", "getOperatorStageId", "getStageId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DedupeStage {
    private final int batchSize;
    @NotNull
    private final Constraints constraints;
    @NotNull
    private final Deduplicator deduplicator;
    @NotNull
    private final String name;
    private final int operatorStageId;
    private final int stageId;

    public DedupeStage(@NotNull Deduplicator deduplicator, int i, int i2, int i3, @NotNull Constraints constraints, @NotNull String name) {
        Intrinsics.checkParameterIsNotNull(deduplicator, "deduplicator");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.deduplicator = deduplicator;
        this.stageId = i;
        this.operatorStageId = i2;
        this.batchSize = i3;
        this.constraints = constraints;
        this.name = name;
    }

    public static /* synthetic */ DedupeStage copy$default(DedupeStage dedupeStage, Deduplicator deduplicator, int i, int i2, int i3, Constraints constraints, String str, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            deduplicator = dedupeStage.deduplicator;
        }
        if ((i4 & 2) != 0) {
            i = dedupeStage.stageId;
        }
        int i5 = i;
        if ((i4 & 4) != 0) {
            i2 = dedupeStage.operatorStageId;
        }
        int i6 = i2;
        if ((i4 & 8) != 0) {
            i3 = dedupeStage.batchSize;
        }
        int i7 = i3;
        if ((i4 & 16) != 0) {
            constraints = dedupeStage.constraints;
        }
        Constraints constraints2 = constraints;
        if ((i4 & 32) != 0) {
            str = dedupeStage.name;
        }
        return dedupeStage.copy(deduplicator, i5, i6, i7, constraints2, str);
    }

    @NotNull
    public final Deduplicator component1() {
        return this.deduplicator;
    }

    public final int component2() {
        return this.stageId;
    }

    public final int component3() {
        return this.operatorStageId;
    }

    public final int component4() {
        return this.batchSize;
    }

    @NotNull
    public final Constraints component5() {
        return this.constraints;
    }

    @NotNull
    public final String component6() {
        return this.name;
    }

    @NotNull
    public final DedupeStage copy(@NotNull Deduplicator deduplicator, int i, int i2, int i3, @NotNull Constraints constraints, @NotNull String name) {
        Intrinsics.checkParameterIsNotNull(deduplicator, "deduplicator");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new DedupeStage(deduplicator, i, i2, i3, constraints, name);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DedupeStage)) {
                return false;
            }
            DedupeStage dedupeStage = (DedupeStage) obj;
            return Intrinsics.areEqual(this.deduplicator, dedupeStage.deduplicator) && this.stageId == dedupeStage.stageId && this.operatorStageId == dedupeStage.operatorStageId && this.batchSize == dedupeStage.batchSize && Intrinsics.areEqual(this.constraints, dedupeStage.constraints) && Intrinsics.areEqual(this.name, dedupeStage.name);
        }
        return true;
    }

    public final int getBatchSize() {
        return this.batchSize;
    }

    @NotNull
    public final Constraints getConstraints() {
        return this.constraints;
    }

    @NotNull
    public final Deduplicator getDeduplicator() {
        return this.deduplicator;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final int getOperatorStageId() {
        return this.operatorStageId;
    }

    public final int getStageId() {
        return this.stageId;
    }

    public int hashCode() {
        Deduplicator deduplicator = this.deduplicator;
        int i = 0;
        int hashCode = (((((((deduplicator != null ? deduplicator.hashCode() : 0) * 31) + this.stageId) * 31) + this.operatorStageId) * 31) + this.batchSize) * 31;
        Constraints constraints = this.constraints;
        int hashCode2 = (hashCode + (constraints != null ? constraints.hashCode() : 0)) * 31;
        String str = this.name;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DedupeStage(deduplicator=");
        outline107.append(this.deduplicator);
        outline107.append(", stageId=");
        outline107.append(this.stageId);
        outline107.append(", operatorStageId=");
        outline107.append(this.operatorStageId);
        outline107.append(", batchSize=");
        outline107.append(this.batchSize);
        outline107.append(", constraints=");
        outline107.append(this.constraints);
        outline107.append(", name=");
        return GeneratedOutlineSupport1.outline91(outline107, this.name, ")");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ DedupeStage(com.amazon.photos.discovery.dedupe.Deduplicator r10, int r11, int r12, int r13, androidx.work.Constraints r14, java.lang.String r15, int r16, kotlin.jvm.internal.DefaultConstructorMarker r17) {
        /*
            r9 = this;
            r0 = r16 & 2
            r1 = 0
            if (r0 == 0) goto L7
            r4 = r1
            goto L8
        L7:
            r4 = r11
        L8:
            r0 = r16 & 4
            if (r0 == 0) goto Le
            r5 = r1
            goto Lf
        Le:
            r5 = r12
        Lf:
            r0 = r16 & 8
            if (r0 == 0) goto L17
            r0 = 200(0xc8, float:2.8E-43)
            r6 = r0
            goto L18
        L17:
            r6 = r13
        L18:
            r0 = r16 & 16
            if (r0 == 0) goto L25
            androidx.work.Constraints r0 = androidx.work.Constraints.NONE
            java.lang.String r1 = "Constraints.NONE"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            r7 = r0
            goto L26
        L25:
            r7 = r14
        L26:
            r2 = r9
            r3 = r10
            r8 = r15
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.discovery.dedupe.DedupeStage.<init>(com.amazon.photos.discovery.dedupe.Deduplicator, int, int, int, androidx.work.Constraints, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}

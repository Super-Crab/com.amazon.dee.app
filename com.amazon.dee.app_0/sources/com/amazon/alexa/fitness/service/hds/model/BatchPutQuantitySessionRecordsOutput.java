package com.amazon.alexa.fitness.service.hds.model;

import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HdsDataModel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/BatchPutQuantitySessionRecordsOutput;", "", ContactsModuleConstants.OUTPUT, "", "Lcom/amazon/alexa/fitness/service/hds/model/ConcreteQuantitySessionRecord;", "(Ljava/util/List;)V", "getOutput", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class BatchPutQuantitySessionRecordsOutput {
    @NotNull
    private final List<ConcreteQuantitySessionRecord> output;

    public BatchPutQuantitySessionRecordsOutput(@NotNull List<ConcreteQuantitySessionRecord> output) {
        Intrinsics.checkParameterIsNotNull(output, "output");
        this.output = output;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BatchPutQuantitySessionRecordsOutput copy$default(BatchPutQuantitySessionRecordsOutput batchPutQuantitySessionRecordsOutput, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = batchPutQuantitySessionRecordsOutput.output;
        }
        return batchPutQuantitySessionRecordsOutput.copy(list);
    }

    @NotNull
    public final List<ConcreteQuantitySessionRecord> component1() {
        return this.output;
    }

    @NotNull
    public final BatchPutQuantitySessionRecordsOutput copy(@NotNull List<ConcreteQuantitySessionRecord> output) {
        Intrinsics.checkParameterIsNotNull(output, "output");
        return new BatchPutQuantitySessionRecordsOutput(output);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof BatchPutQuantitySessionRecordsOutput) && Intrinsics.areEqual(this.output, ((BatchPutQuantitySessionRecordsOutput) obj).output);
        }
        return true;
    }

    @NotNull
    public final List<ConcreteQuantitySessionRecord> getOutput() {
        return this.output;
    }

    public int hashCode() {
        List<ConcreteQuantitySessionRecord> list = this.output;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline95(GeneratedOutlineSupport1.outline107("BatchPutQuantitySessionRecordsOutput(output="), this.output, ")");
    }
}

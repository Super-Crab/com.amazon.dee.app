package com.amazon.alexa.fitness.service.hds.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HdsDataModel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010\f\u001a\u00020\rJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/BatchQuantitySessionRecords;", "", "dataSource", "Lcom/amazon/alexa/fitness/service/hds/model/DataSource;", "quantityRecords", "", "Lcom/amazon/alexa/fitness/service/hds/model/QuantitySessionRecord;", "(Lcom/amazon/alexa/fitness/service/hds/model/DataSource;Ljava/util/List;)V", "getDataSource", "()Lcom/amazon/alexa/fitness/service/hds/model/DataSource;", "getQuantityRecords", "()Ljava/util/List;", "buildBatchPutQuantitySessionRecordsInput", "", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class BatchQuantitySessionRecords {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final String outputFieldSelection = "{output {\nsessionId\nsessionVersion\n}\n}\n";
    @NotNull
    private final DataSource dataSource;
    @NotNull
    private final List<QuantitySessionRecord> quantityRecords;

    /* compiled from: HdsDataModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/BatchQuantitySessionRecords$Companion;", "", "()V", "outputFieldSelection", "", "getOutputFieldSelection", "()Ljava/lang/String;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final String getOutputFieldSelection() {
            return BatchQuantitySessionRecords.outputFieldSelection;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BatchQuantitySessionRecords(@NotNull DataSource dataSource, @NotNull List<QuantitySessionRecord> quantityRecords) {
        Intrinsics.checkParameterIsNotNull(dataSource, "dataSource");
        Intrinsics.checkParameterIsNotNull(quantityRecords, "quantityRecords");
        this.dataSource = dataSource;
        this.quantityRecords = quantityRecords;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BatchQuantitySessionRecords copy$default(BatchQuantitySessionRecords batchQuantitySessionRecords, DataSource dataSource, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            dataSource = batchQuantitySessionRecords.dataSource;
        }
        if ((i & 2) != 0) {
            list = batchQuantitySessionRecords.quantityRecords;
        }
        return batchQuantitySessionRecords.copy(dataSource, list);
    }

    @NotNull
    public final String buildBatchPutQuantitySessionRecordsInput() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("dataSource:");
        outline1072.append(this.dataSource.buildDataSourceInput());
        outline1072.append(JsonReaderKt.COMMA);
        outline107.append(outline1072.toString());
        outline107.append("quantityRecords:[");
        Iterator<QuantitySessionRecord> it2 = this.quantityRecords.iterator();
        while (it2.hasNext()) {
            outline107.append(it2.next().buildPutQuantitySessionRecordInput() + JsonReaderKt.COMMA);
        }
        outline107.append("]");
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        String sb = outline107.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "gqlBuilder.toString()");
        return sb;
    }

    @NotNull
    public final DataSource component1() {
        return this.dataSource;
    }

    @NotNull
    public final List<QuantitySessionRecord> component2() {
        return this.quantityRecords;
    }

    @NotNull
    public final BatchQuantitySessionRecords copy(@NotNull DataSource dataSource, @NotNull List<QuantitySessionRecord> quantityRecords) {
        Intrinsics.checkParameterIsNotNull(dataSource, "dataSource");
        Intrinsics.checkParameterIsNotNull(quantityRecords, "quantityRecords");
        return new BatchQuantitySessionRecords(dataSource, quantityRecords);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof BatchQuantitySessionRecords)) {
                return false;
            }
            BatchQuantitySessionRecords batchQuantitySessionRecords = (BatchQuantitySessionRecords) obj;
            return Intrinsics.areEqual(this.dataSource, batchQuantitySessionRecords.dataSource) && Intrinsics.areEqual(this.quantityRecords, batchQuantitySessionRecords.quantityRecords);
        }
        return true;
    }

    @NotNull
    public final DataSource getDataSource() {
        return this.dataSource;
    }

    @NotNull
    public final List<QuantitySessionRecord> getQuantityRecords() {
        return this.quantityRecords;
    }

    public int hashCode() {
        DataSource dataSource = this.dataSource;
        int i = 0;
        int hashCode = (dataSource != null ? dataSource.hashCode() : 0) * 31;
        List<QuantitySessionRecord> list = this.quantityRecords;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BatchQuantitySessionRecords(dataSource=");
        outline107.append(this.dataSource);
        outline107.append(", quantityRecords=");
        return GeneratedOutlineSupport1.outline95(outline107, this.quantityRecords, ")");
    }
}

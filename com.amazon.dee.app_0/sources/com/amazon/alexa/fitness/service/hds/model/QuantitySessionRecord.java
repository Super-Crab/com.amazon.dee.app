package com.amazon.alexa.fitness.service.hds.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HdsDataModel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0013\u001a\u00020\u0014J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006 "}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/QuantitySessionRecord;", "", "recordData", "Lcom/amazon/alexa/fitness/service/hds/model/Record;", "session", "Lcom/amazon/alexa/fitness/service/hds/model/Session;", "quantityRecordData", "Lcom/amazon/alexa/fitness/service/hds/model/QuantityRecordData;", "dataSource", "Lcom/amazon/alexa/fitness/service/hds/model/DataSource;", "(Lcom/amazon/alexa/fitness/service/hds/model/Record;Lcom/amazon/alexa/fitness/service/hds/model/Session;Lcom/amazon/alexa/fitness/service/hds/model/QuantityRecordData;Lcom/amazon/alexa/fitness/service/hds/model/DataSource;)V", "getDataSource", "()Lcom/amazon/alexa/fitness/service/hds/model/DataSource;", "getQuantityRecordData", "()Lcom/amazon/alexa/fitness/service/hds/model/QuantityRecordData;", "getRecordData", "()Lcom/amazon/alexa/fitness/service/hds/model/Record;", "getSession", "()Lcom/amazon/alexa/fitness/service/hds/model/Session;", "buildPutQuantitySessionRecordInput", "", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class QuantitySessionRecord {
    @NotNull
    private final DataSource dataSource;
    @NotNull
    private final QuantityRecordData quantityRecordData;
    @NotNull
    private final Record recordData;
    @NotNull
    private final Session session;

    public QuantitySessionRecord(@NotNull Record recordData, @NotNull Session session, @NotNull QuantityRecordData quantityRecordData, @NotNull DataSource dataSource) {
        Intrinsics.checkParameterIsNotNull(recordData, "recordData");
        Intrinsics.checkParameterIsNotNull(session, "session");
        Intrinsics.checkParameterIsNotNull(quantityRecordData, "quantityRecordData");
        Intrinsics.checkParameterIsNotNull(dataSource, "dataSource");
        this.recordData = recordData;
        this.session = session;
        this.quantityRecordData = quantityRecordData;
        this.dataSource = dataSource;
    }

    public static /* synthetic */ QuantitySessionRecord copy$default(QuantitySessionRecord quantitySessionRecord, Record record, Session session, QuantityRecordData quantityRecordData, DataSource dataSource, int i, Object obj) {
        if ((i & 1) != 0) {
            record = quantitySessionRecord.recordData;
        }
        if ((i & 2) != 0) {
            session = quantitySessionRecord.session;
        }
        if ((i & 4) != 0) {
            quantityRecordData = quantitySessionRecord.quantityRecordData;
        }
        if ((i & 8) != 0) {
            dataSource = quantitySessionRecord.dataSource;
        }
        return quantitySessionRecord.copy(record, session, quantityRecordData, dataSource);
    }

    @NotNull
    public final String buildPutQuantitySessionRecordInput() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("recordData:");
        outline1072.append(this.recordData.buildRecordInput());
        outline1072.append(JsonReaderKt.COMMA);
        outline107.append(outline1072.toString());
        outline107.append("session:" + this.session.buildSessionInput() + JsonReaderKt.COMMA);
        outline107.append("quantityRecordData:" + this.quantityRecordData.buildQuantityRecordDataInput() + JsonReaderKt.COMMA);
        outline107.append("dataSource:" + this.dataSource.buildDataSourceInput() + JsonReaderKt.COMMA);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        String sb = outline107.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "gqlBuilder.toString()");
        return sb;
    }

    @NotNull
    public final Record component1() {
        return this.recordData;
    }

    @NotNull
    public final Session component2() {
        return this.session;
    }

    @NotNull
    public final QuantityRecordData component3() {
        return this.quantityRecordData;
    }

    @NotNull
    public final DataSource component4() {
        return this.dataSource;
    }

    @NotNull
    public final QuantitySessionRecord copy(@NotNull Record recordData, @NotNull Session session, @NotNull QuantityRecordData quantityRecordData, @NotNull DataSource dataSource) {
        Intrinsics.checkParameterIsNotNull(recordData, "recordData");
        Intrinsics.checkParameterIsNotNull(session, "session");
        Intrinsics.checkParameterIsNotNull(quantityRecordData, "quantityRecordData");
        Intrinsics.checkParameterIsNotNull(dataSource, "dataSource");
        return new QuantitySessionRecord(recordData, session, quantityRecordData, dataSource);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof QuantitySessionRecord)) {
                return false;
            }
            QuantitySessionRecord quantitySessionRecord = (QuantitySessionRecord) obj;
            return Intrinsics.areEqual(this.recordData, quantitySessionRecord.recordData) && Intrinsics.areEqual(this.session, quantitySessionRecord.session) && Intrinsics.areEqual(this.quantityRecordData, quantitySessionRecord.quantityRecordData) && Intrinsics.areEqual(this.dataSource, quantitySessionRecord.dataSource);
        }
        return true;
    }

    @NotNull
    public final DataSource getDataSource() {
        return this.dataSource;
    }

    @NotNull
    public final QuantityRecordData getQuantityRecordData() {
        return this.quantityRecordData;
    }

    @NotNull
    public final Record getRecordData() {
        return this.recordData;
    }

    @NotNull
    public final Session getSession() {
        return this.session;
    }

    public int hashCode() {
        Record record = this.recordData;
        int i = 0;
        int hashCode = (record != null ? record.hashCode() : 0) * 31;
        Session session = this.session;
        int hashCode2 = (hashCode + (session != null ? session.hashCode() : 0)) * 31;
        QuantityRecordData quantityRecordData = this.quantityRecordData;
        int hashCode3 = (hashCode2 + (quantityRecordData != null ? quantityRecordData.hashCode() : 0)) * 31;
        DataSource dataSource = this.dataSource;
        if (dataSource != null) {
            i = dataSource.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("QuantitySessionRecord(recordData=");
        outline107.append(this.recordData);
        outline107.append(", session=");
        outline107.append(this.session);
        outline107.append(", quantityRecordData=");
        outline107.append(this.quantityRecordData);
        outline107.append(", dataSource=");
        outline107.append(this.dataSource);
        outline107.append(")");
        return outline107.toString();
    }
}

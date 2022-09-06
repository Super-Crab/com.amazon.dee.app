package com.amazon.alexa.fitness.service.hds.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HdsDataModel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/Record;", "", "type", "Lcom/amazon/alexa/fitness/service/hds/model/RecordType;", "(Lcom/amazon/alexa/fitness/service/hds/model/RecordType;)V", "getType", "()Lcom/amazon/alexa/fitness/service/hds/model/RecordType;", "buildRecordInput", "", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Record {
    @NotNull
    private final RecordType type;

    public Record(@NotNull RecordType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.type = type;
    }

    public static /* synthetic */ Record copy$default(Record record, RecordType recordType, int i, Object obj) {
        if ((i & 1) != 0) {
            recordType = record.type;
        }
        return record.copy(recordType);
    }

    @NotNull
    public final String buildRecordInput() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("type:");
        outline1072.append(this.type.getValue());
        outline1072.append(JsonReaderKt.COMMA);
        outline107.append(outline1072.toString());
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        String sb = outline107.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "gqlBuilder.toString()");
        return sb;
    }

    @NotNull
    public final RecordType component1() {
        return this.type;
    }

    @NotNull
    public final Record copy(@NotNull RecordType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        return new Record(type);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof Record) && Intrinsics.areEqual(this.type, ((Record) obj).type);
        }
        return true;
    }

    @NotNull
    public final RecordType getType() {
        return this.type;
    }

    public int hashCode() {
        RecordType recordType = this.type;
        if (recordType != null) {
            return recordType.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Record(type=");
        outline107.append(this.type);
        outline107.append(")");
        return outline107.toString();
    }
}

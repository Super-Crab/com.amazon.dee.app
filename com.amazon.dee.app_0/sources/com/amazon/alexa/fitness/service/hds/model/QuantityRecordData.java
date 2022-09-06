package com.amazon.alexa.fitness.service.hds.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HdsDataModel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/QuantityRecordData;", "", "amount", "", "unit", "Lcom/amazon/alexa/fitness/service/hds/model/Unit;", "(FLcom/amazon/alexa/fitness/service/hds/model/Unit;)V", "getAmount", "()F", "getUnit", "()Lcom/amazon/alexa/fitness/service/hds/model/Unit;", "buildQuantityRecordDataInput", "", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class QuantityRecordData {
    private final float amount;
    @NotNull
    private final Unit unit;

    public QuantityRecordData(float f, @NotNull Unit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        this.amount = f;
        this.unit = unit;
    }

    public static /* synthetic */ QuantityRecordData copy$default(QuantityRecordData quantityRecordData, float f, Unit unit, int i, Object obj) {
        if ((i & 1) != 0) {
            f = quantityRecordData.amount;
        }
        if ((i & 2) != 0) {
            unit = quantityRecordData.unit;
        }
        return quantityRecordData.copy(f, unit);
    }

    @NotNull
    public final String buildQuantityRecordDataInput() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("amount:");
        outline1072.append(this.amount);
        outline1072.append(JsonReaderKt.COMMA);
        outline107.append(outline1072.toString());
        outline107.append("unit:" + this.unit.getValue() + JsonReaderKt.COMMA);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        String sb = outline107.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "gqlBuilder.toString()");
        return sb;
    }

    public final float component1() {
        return this.amount;
    }

    @NotNull
    public final Unit component2() {
        return this.unit;
    }

    @NotNull
    public final QuantityRecordData copy(float f, @NotNull Unit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return new QuantityRecordData(f, unit);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof QuantityRecordData)) {
                return false;
            }
            QuantityRecordData quantityRecordData = (QuantityRecordData) obj;
            return Float.compare(this.amount, quantityRecordData.amount) == 0 && Intrinsics.areEqual(this.unit, quantityRecordData.unit);
        }
        return true;
    }

    public final float getAmount() {
        return this.amount;
    }

    @NotNull
    public final Unit getUnit() {
        return this.unit;
    }

    public int hashCode() {
        int floatToIntBits = Float.floatToIntBits(this.amount) * 31;
        Unit unit = this.unit;
        return floatToIntBits + (unit != null ? unit.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("QuantityRecordData(amount=");
        outline107.append(this.amount);
        outline107.append(", unit=");
        outline107.append(this.unit);
        outline107.append(")");
        return outline107.toString();
    }
}

package com.amazon.alexa.accessoryclient.common.api;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryDataBeaconRecord;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AccessoryDataBeaconResult.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/api/AccessoryDataBeaconResult;", "", ModelTransformer.KEY_ACCESSORY, "Lcom/amazon/alexa/accessory/Accessory;", "accessoryDataBeaconRecord", "Lcom/amazon/alexa/accessory/AccessoryDataBeaconRecord;", "rssi", "", "(Lcom/amazon/alexa/accessory/Accessory;Lcom/amazon/alexa/accessory/AccessoryDataBeaconRecord;I)V", "getAccessory", "()Lcom/amazon/alexa/accessory/Accessory;", "getAccessoryDataBeaconRecord", "()Lcom/amazon/alexa/accessory/AccessoryDataBeaconRecord;", "getRssi", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AccessoryDataBeaconResult {
    @NotNull
    private final Accessory accessory;
    @NotNull
    private final AccessoryDataBeaconRecord accessoryDataBeaconRecord;
    private final int rssi;

    public AccessoryDataBeaconResult(@NotNull Accessory accessory, @NotNull AccessoryDataBeaconRecord accessoryDataBeaconRecord, int i) {
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        Intrinsics.checkParameterIsNotNull(accessoryDataBeaconRecord, "accessoryDataBeaconRecord");
        this.accessory = accessory;
        this.accessoryDataBeaconRecord = accessoryDataBeaconRecord;
        this.rssi = i;
    }

    public static /* synthetic */ AccessoryDataBeaconResult copy$default(AccessoryDataBeaconResult accessoryDataBeaconResult, Accessory accessory, AccessoryDataBeaconRecord accessoryDataBeaconRecord, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            accessory = accessoryDataBeaconResult.accessory;
        }
        if ((i2 & 2) != 0) {
            accessoryDataBeaconRecord = accessoryDataBeaconResult.accessoryDataBeaconRecord;
        }
        if ((i2 & 4) != 0) {
            i = accessoryDataBeaconResult.rssi;
        }
        return accessoryDataBeaconResult.copy(accessory, accessoryDataBeaconRecord, i);
    }

    @NotNull
    public final Accessory component1() {
        return this.accessory;
    }

    @NotNull
    public final AccessoryDataBeaconRecord component2() {
        return this.accessoryDataBeaconRecord;
    }

    public final int component3() {
        return this.rssi;
    }

    @NotNull
    public final AccessoryDataBeaconResult copy(@NotNull Accessory accessory, @NotNull AccessoryDataBeaconRecord accessoryDataBeaconRecord, int i) {
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        Intrinsics.checkParameterIsNotNull(accessoryDataBeaconRecord, "accessoryDataBeaconRecord");
        return new AccessoryDataBeaconResult(accessory, accessoryDataBeaconRecord, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof AccessoryDataBeaconResult)) {
                return false;
            }
            AccessoryDataBeaconResult accessoryDataBeaconResult = (AccessoryDataBeaconResult) obj;
            return Intrinsics.areEqual(this.accessory, accessoryDataBeaconResult.accessory) && Intrinsics.areEqual(this.accessoryDataBeaconRecord, accessoryDataBeaconResult.accessoryDataBeaconRecord) && this.rssi == accessoryDataBeaconResult.rssi;
        }
        return true;
    }

    @NotNull
    public final Accessory getAccessory() {
        return this.accessory;
    }

    @NotNull
    public final AccessoryDataBeaconRecord getAccessoryDataBeaconRecord() {
        return this.accessoryDataBeaconRecord;
    }

    public final int getRssi() {
        return this.rssi;
    }

    public int hashCode() {
        Accessory accessory = this.accessory;
        int i = 0;
        int hashCode = (accessory != null ? accessory.hashCode() : 0) * 31;
        AccessoryDataBeaconRecord accessoryDataBeaconRecord = this.accessoryDataBeaconRecord;
        if (accessoryDataBeaconRecord != null) {
            i = accessoryDataBeaconRecord.hashCode();
        }
        return ((hashCode + i) * 31) + this.rssi;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryDataBeaconResult(accessory=");
        outline107.append(this.accessory);
        outline107.append(", accessoryDataBeaconRecord=");
        outline107.append(this.accessoryDataBeaconRecord);
        outline107.append(", rssi=");
        return GeneratedOutlineSupport1.outline86(outline107, this.rssi, ")");
    }
}

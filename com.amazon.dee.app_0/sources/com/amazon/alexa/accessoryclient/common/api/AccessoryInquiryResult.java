package com.amazon.alexa.accessoryclient.common.api;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryInquiryRecord;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AccessoryInquiryResult.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/api/AccessoryInquiryResult;", "", ModelTransformer.KEY_ACCESSORY, "Lcom/amazon/alexa/accessory/Accessory;", "accessoryInquiryRecord", "Lcom/amazon/alexa/accessory/AccessoryInquiryRecord;", "(Lcom/amazon/alexa/accessory/Accessory;Lcom/amazon/alexa/accessory/AccessoryInquiryRecord;)V", "getAccessory", "()Lcom/amazon/alexa/accessory/Accessory;", "getAccessoryInquiryRecord", "()Lcom/amazon/alexa/accessory/AccessoryInquiryRecord;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AccessoryInquiryResult {
    @NotNull
    private final Accessory accessory;
    @NotNull
    private final AccessoryInquiryRecord accessoryInquiryRecord;

    public AccessoryInquiryResult(@NotNull Accessory accessory, @NotNull AccessoryInquiryRecord accessoryInquiryRecord) {
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        Intrinsics.checkParameterIsNotNull(accessoryInquiryRecord, "accessoryInquiryRecord");
        this.accessory = accessory;
        this.accessoryInquiryRecord = accessoryInquiryRecord;
    }

    public static /* synthetic */ AccessoryInquiryResult copy$default(AccessoryInquiryResult accessoryInquiryResult, Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord, int i, Object obj) {
        if ((i & 1) != 0) {
            accessory = accessoryInquiryResult.accessory;
        }
        if ((i & 2) != 0) {
            accessoryInquiryRecord = accessoryInquiryResult.accessoryInquiryRecord;
        }
        return accessoryInquiryResult.copy(accessory, accessoryInquiryRecord);
    }

    @NotNull
    public final Accessory component1() {
        return this.accessory;
    }

    @NotNull
    public final AccessoryInquiryRecord component2() {
        return this.accessoryInquiryRecord;
    }

    @NotNull
    public final AccessoryInquiryResult copy(@NotNull Accessory accessory, @NotNull AccessoryInquiryRecord accessoryInquiryRecord) {
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        Intrinsics.checkParameterIsNotNull(accessoryInquiryRecord, "accessoryInquiryRecord");
        return new AccessoryInquiryResult(accessory, accessoryInquiryRecord);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof AccessoryInquiryResult)) {
                return false;
            }
            AccessoryInquiryResult accessoryInquiryResult = (AccessoryInquiryResult) obj;
            return Intrinsics.areEqual(this.accessory, accessoryInquiryResult.accessory) && Intrinsics.areEqual(this.accessoryInquiryRecord, accessoryInquiryResult.accessoryInquiryRecord);
        }
        return true;
    }

    @NotNull
    public final Accessory getAccessory() {
        return this.accessory;
    }

    @NotNull
    public final AccessoryInquiryRecord getAccessoryInquiryRecord() {
        return this.accessoryInquiryRecord;
    }

    public int hashCode() {
        Accessory accessory = this.accessory;
        int i = 0;
        int hashCode = (accessory != null ? accessory.hashCode() : 0) * 31;
        AccessoryInquiryRecord accessoryInquiryRecord = this.accessoryInquiryRecord;
        if (accessoryInquiryRecord != null) {
            i = accessoryInquiryRecord.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryInquiryResult(accessory=");
        outline107.append(this.accessory);
        outline107.append(", accessoryInquiryRecord=");
        outline107.append(this.accessoryInquiryRecord);
        outline107.append(")");
        return outline107.toString();
    }
}

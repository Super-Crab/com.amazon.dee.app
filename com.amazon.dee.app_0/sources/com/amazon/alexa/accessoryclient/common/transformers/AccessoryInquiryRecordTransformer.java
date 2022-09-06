package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import com.amazon.alexa.accessory.AccessoryInquiryRecord;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessoryInquiryRecordTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/transformers/AccessoryInquiryRecordTransformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessory/AccessoryInquiryRecord;", "()V", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "accessoryInquiryRecord", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AccessoryInquiryRecordTransformer implements BundleTransformer<AccessoryInquiryRecord> {
    public static final AccessoryInquiryRecordTransformer INSTANCE = new AccessoryInquiryRecordTransformer();

    private AccessoryInquiryRecordTransformer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    /* renamed from: fromBundle */
    public AccessoryInquiryRecord mo568fromBundle(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        return new AccessoryInquiryRecord(DeviceTransformer.INSTANCE.getListTransformer().mo568fromBundle(bundle));
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    public Bundle toBundle(@NotNull AccessoryInquiryRecord accessoryInquiryRecord) {
        List<Device> list;
        Intrinsics.checkParameterIsNotNull(accessoryInquiryRecord, "accessoryInquiryRecord");
        BundleTransformer<List<Device>> listTransformer = DeviceTransformer.INSTANCE.getListTransformer();
        Set<Device> devices = accessoryInquiryRecord.getDevices();
        Intrinsics.checkExpressionValueIsNotNull(devices, "accessoryInquiryRecord.devices");
        list = CollectionsKt___CollectionsKt.toList(devices);
        return listTransformer.toBundle(list);
    }
}

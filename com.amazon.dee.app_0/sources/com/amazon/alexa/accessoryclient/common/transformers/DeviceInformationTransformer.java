package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import com.amazon.alexa.accessory.protocol.Device;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DeviceInformationTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00070\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/transformers/DeviceInformationTransformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessory/protocol/Device$DeviceInformation;", "()V", "PROTO_BYTES_KEY", "", "listTransformer", "", "getListTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "deviceInformation", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DeviceInformationTransformer implements BundleTransformer<Device.DeviceInformation> {
    private static final String PROTO_BYTES_KEY = "proto";
    public static final DeviceInformationTransformer INSTANCE = new DeviceInformationTransformer();
    @NotNull
    private static final BundleTransformer<List<Device.DeviceInformation>> listTransformer = new ListTransformer(INSTANCE);

    private DeviceInformationTransformer() {
    }

    @NotNull
    public final BundleTransformer<List<Device.DeviceInformation>> getListTransformer() {
        return listTransformer;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    /* renamed from: fromBundle */
    public Device.DeviceInformation mo568fromBundle(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Device.DeviceInformation parseFrom = Device.DeviceInformation.parseFrom(bundle.getByteArray(PROTO_BYTES_KEY));
        Intrinsics.checkExpressionValueIsNotNull(parseFrom, "Device.DeviceInformation…teArray(PROTO_BYTES_KEY))");
        return parseFrom;
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    public Bundle toBundle(@NotNull Device.DeviceInformation deviceInformation) {
        Intrinsics.checkParameterIsNotNull(deviceInformation, "deviceInformation");
        Bundle bundle = new Bundle();
        bundle.putByteArray(PROTO_BYTES_KEY, deviceInformation.toByteArray());
        return bundle;
    }
}

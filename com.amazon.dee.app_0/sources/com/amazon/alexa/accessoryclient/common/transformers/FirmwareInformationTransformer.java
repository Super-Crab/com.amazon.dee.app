package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import com.amazon.alexa.accessory.protocol.Firmware;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FirmwareInformationTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00050\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/transformers/FirmwareInformationTransformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessory/protocol/Firmware$FirmwareInformation;", "()V", "ListTransformer", "", "getListTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "PROTO_BYTES_KEY", "", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "firmwareInformation", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class FirmwareInformationTransformer implements BundleTransformer<Firmware.FirmwareInformation> {
    public static final FirmwareInformationTransformer INSTANCE = new FirmwareInformationTransformer();
    @NotNull
    private static final BundleTransformer<List<Firmware.FirmwareInformation>> ListTransformer = new ListTransformer(INSTANCE);
    private static final String PROTO_BYTES_KEY = "proto";

    private FirmwareInformationTransformer() {
    }

    @NotNull
    public final BundleTransformer<List<Firmware.FirmwareInformation>> getListTransformer() {
        return ListTransformer;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    /* renamed from: fromBundle */
    public Firmware.FirmwareInformation mo568fromBundle(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Firmware.FirmwareInformation parseFrom = Firmware.FirmwareInformation.parseFrom(bundle.getByteArray(PROTO_BYTES_KEY));
        Intrinsics.checkExpressionValueIsNotNull(parseFrom, "Firmware.FirmwareInforma…teArray(PROTO_BYTES_KEY))");
        return parseFrom;
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    public Bundle toBundle(@NotNull Firmware.FirmwareInformation firmwareInformation) {
        Intrinsics.checkParameterIsNotNull(firmwareInformation, "firmwareInformation");
        Bundle bundle = new Bundle();
        bundle.putByteArray(PROTO_BYTES_KEY, firmwareInformation.toByteArray());
        return bundle;
    }
}

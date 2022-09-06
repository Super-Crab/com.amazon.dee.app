package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.DeviceInformationTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.FirmwareInformationTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UpdateRequestRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001aB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00000\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/UpdateRequestRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "deviceInformation", "Lcom/amazon/alexa/accessory/protocol/Device$DeviceInformation;", "firmwareInformation", "Lcom/amazon/alexa/accessory/protocol/Firmware$FirmwareInformation;", "(Lcom/amazon/alexa/accessory/protocol/Device$DeviceInformation;Lcom/amazon/alexa/accessory/protocol/Firmware$FirmwareInformation;)V", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getDeviceInformation", "()Lcom/amazon/alexa/accessory/protocol/Device$DeviceInformation;", "getFirmwareInformation", "()Lcom/amazon/alexa/accessory/protocol/Firmware$FirmwareInformation;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class UpdateRequestRequest implements Query.Request<UpdateRequestRequest> {
    @NotNull
    private final BundleTransformer<UpdateRequestRequest> bundleTransformer;
    @NotNull
    private final Device.DeviceInformation deviceInformation;
    @NotNull
    private final Firmware.FirmwareInformation firmwareInformation;

    /* compiled from: UpdateRequestRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/UpdateRequestRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/UpdateRequestRequest;", "()V", "DEVICE_INFORMATION_KEY", "", "FIRMWARE_INFORMATION_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "t", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<UpdateRequestRequest> {
        private static final String DEVICE_INFORMATION_KEY = "deviceInformation";
        private static final String FIRMWARE_INFORMATION_KEY = "firmwareInformation";
        public static final Transformer INSTANCE = new Transformer();

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public UpdateRequestRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            DeviceInformationTransformer deviceInformationTransformer = DeviceInformationTransformer.INSTANCE;
            Bundle bundle2 = bundle.getBundle(DEVICE_INFORMATION_KEY);
            if (bundle2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(DEVICE_INFORMATION_KEY)!!");
            Device.DeviceInformation mo568fromBundle = deviceInformationTransformer.mo568fromBundle(bundle2);
            FirmwareInformationTransformer firmwareInformationTransformer = FirmwareInformationTransformer.INSTANCE;
            Bundle bundle3 = bundle.getBundle(FIRMWARE_INFORMATION_KEY);
            if (bundle3 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(bundle3, "bundle.getBundle(FIRMWARE_INFORMATION_KEY)!!");
            return new UpdateRequestRequest(mo568fromBundle, firmwareInformationTransformer.mo568fromBundle(bundle3));
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull UpdateRequestRequest t) {
            Intrinsics.checkParameterIsNotNull(t, "t");
            Bundle bundle = new Bundle();
            bundle.putBundle(DEVICE_INFORMATION_KEY, DeviceInformationTransformer.INSTANCE.toBundle(t.getDeviceInformation()));
            bundle.putBundle(FIRMWARE_INFORMATION_KEY, FirmwareInformationTransformer.INSTANCE.toBundle(t.getFirmwareInformation()));
            return bundle;
        }
    }

    public UpdateRequestRequest(@NotNull Device.DeviceInformation deviceInformation, @NotNull Firmware.FirmwareInformation firmwareInformation) {
        Intrinsics.checkParameterIsNotNull(deviceInformation, "deviceInformation");
        Intrinsics.checkParameterIsNotNull(firmwareInformation, "firmwareInformation");
        this.deviceInformation = deviceInformation;
        this.firmwareInformation = firmwareInformation;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ UpdateRequestRequest copy$default(UpdateRequestRequest updateRequestRequest, Device.DeviceInformation deviceInformation, Firmware.FirmwareInformation firmwareInformation, int i, Object obj) {
        if ((i & 1) != 0) {
            deviceInformation = updateRequestRequest.deviceInformation;
        }
        if ((i & 2) != 0) {
            firmwareInformation = updateRequestRequest.firmwareInformation;
        }
        return updateRequestRequest.copy(deviceInformation, firmwareInformation);
    }

    @NotNull
    public final Device.DeviceInformation component1() {
        return this.deviceInformation;
    }

    @NotNull
    public final Firmware.FirmwareInformation component2() {
        return this.firmwareInformation;
    }

    @NotNull
    public final UpdateRequestRequest copy(@NotNull Device.DeviceInformation deviceInformation, @NotNull Firmware.FirmwareInformation firmwareInformation) {
        Intrinsics.checkParameterIsNotNull(deviceInformation, "deviceInformation");
        Intrinsics.checkParameterIsNotNull(firmwareInformation, "firmwareInformation");
        return new UpdateRequestRequest(deviceInformation, firmwareInformation);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof UpdateRequestRequest)) {
                return false;
            }
            UpdateRequestRequest updateRequestRequest = (UpdateRequestRequest) obj;
            return Intrinsics.areEqual(this.deviceInformation, updateRequestRequest.deviceInformation) && Intrinsics.areEqual(this.firmwareInformation, updateRequestRequest.firmwareInformation);
        }
        return true;
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public Bundle getBundle() {
        return Query.Request.DefaultImpls.getBundle(this);
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public BundleTransformer<UpdateRequestRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    @NotNull
    public final Device.DeviceInformation getDeviceInformation() {
        return this.deviceInformation;
    }

    @NotNull
    public final Firmware.FirmwareInformation getFirmwareInformation() {
        return this.firmwareInformation;
    }

    public int hashCode() {
        Device.DeviceInformation deviceInformation = this.deviceInformation;
        int i = 0;
        int hashCode = (deviceInformation != null ? deviceInformation.hashCode() : 0) * 31;
        Firmware.FirmwareInformation firmwareInformation = this.firmwareInformation;
        if (firmwareInformation != null) {
            i = firmwareInformation.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UpdateRequestRequest(deviceInformation=");
        outline107.append(this.deviceInformation);
        outline107.append(", firmwareInformation=");
        outline107.append(this.firmwareInformation);
        outline107.append(")");
        return outline107.toString();
    }
}

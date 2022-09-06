package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.DeviceGroupTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DeviceGroupRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/DeviceGroupRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "deviceGroup", "Lcom/amazon/alexa/accessory/repositories/device/v2/DeviceGroup;", "(Lcom/amazon/alexa/accessory/repositories/device/v2/DeviceGroup;)V", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getDeviceGroup", "()Lcom/amazon/alexa/accessory/repositories/device/v2/DeviceGroup;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DeviceGroupRequest implements Query.Request<DeviceGroupRequest> {
    @NotNull
    private final BundleTransformer<DeviceGroupRequest> bundleTransformer;
    @NotNull
    private final DeviceGroup deviceGroup;

    /* compiled from: DeviceGroupRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/DeviceGroupRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/DeviceGroupRequest;", "()V", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "deviceGroupRequest", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<DeviceGroupRequest> {
        public static final Transformer INSTANCE = new Transformer();

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public DeviceGroupRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            return new DeviceGroupRequest(DeviceGroupTransformer.INSTANCE.mo568fromBundle(bundle));
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull DeviceGroupRequest deviceGroupRequest) {
            Intrinsics.checkParameterIsNotNull(deviceGroupRequest, "deviceGroupRequest");
            return DeviceGroupTransformer.INSTANCE.toBundle(deviceGroupRequest.getDeviceGroup());
        }
    }

    public DeviceGroupRequest(@NotNull DeviceGroup deviceGroup) {
        Intrinsics.checkParameterIsNotNull(deviceGroup, "deviceGroup");
        this.deviceGroup = deviceGroup;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ DeviceGroupRequest copy$default(DeviceGroupRequest deviceGroupRequest, DeviceGroup deviceGroup, int i, Object obj) {
        if ((i & 1) != 0) {
            deviceGroup = deviceGroupRequest.deviceGroup;
        }
        return deviceGroupRequest.copy(deviceGroup);
    }

    @NotNull
    public final DeviceGroup component1() {
        return this.deviceGroup;
    }

    @NotNull
    public final DeviceGroupRequest copy(@NotNull DeviceGroup deviceGroup) {
        Intrinsics.checkParameterIsNotNull(deviceGroup, "deviceGroup");
        return new DeviceGroupRequest(deviceGroup);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof DeviceGroupRequest) && Intrinsics.areEqual(this.deviceGroup, ((DeviceGroupRequest) obj).deviceGroup);
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
    public BundleTransformer<DeviceGroupRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    @NotNull
    public final DeviceGroup getDeviceGroup() {
        return this.deviceGroup;
    }

    public int hashCode() {
        DeviceGroup deviceGroup = this.deviceGroup;
        if (deviceGroup != null) {
            return deviceGroup.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceGroupRequest(deviceGroup=");
        outline107.append(this.deviceGroup);
        outline107.append(")");
        return outline107.toString();
    }
}

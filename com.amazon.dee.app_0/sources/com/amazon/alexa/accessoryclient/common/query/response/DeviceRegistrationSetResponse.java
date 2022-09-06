package com.amazon.alexa.accessoryclient.common.query.response;

import android.os.Bundle;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.DeviceRegistrationTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DeviceRegistrationSetResponse.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0016B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00000\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/response/DeviceRegistrationSetResponse;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "deviceRegistrationSet", "", "Lcom/amazon/alexa/accessory/registration/DeviceRegistration;", "(Ljava/util/Set;)V", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getDeviceRegistrationSet", "()Ljava/util/Set;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DeviceRegistrationSetResponse implements Query.Response<DeviceRegistrationSetResponse> {
    @NotNull
    private final BundleTransformer<DeviceRegistrationSetResponse> bundleTransformer;
    @NotNull
    private final Set<DeviceRegistration> deviceRegistrationSet;

    /* compiled from: DeviceRegistrationSetResponse.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/response/DeviceRegistrationSetResponse$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/response/DeviceRegistrationSetResponse;", "()V", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "deviceRegistrationSetResponse", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<DeviceRegistrationSetResponse> {
        public static final Transformer INSTANCE = new Transformer();

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public DeviceRegistrationSetResponse mo568fromBundle(@NotNull Bundle bundle) {
            Set set;
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            set = CollectionsKt___CollectionsKt.toSet(DeviceRegistrationTransformer.INSTANCE.getListTransformer().mo568fromBundle(bundle));
            return new DeviceRegistrationSetResponse(set);
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull DeviceRegistrationSetResponse deviceRegistrationSetResponse) {
            List<DeviceRegistration> list;
            Intrinsics.checkParameterIsNotNull(deviceRegistrationSetResponse, "deviceRegistrationSetResponse");
            BundleTransformer<List<DeviceRegistration>> listTransformer = DeviceRegistrationTransformer.INSTANCE.getListTransformer();
            list = CollectionsKt___CollectionsKt.toList(deviceRegistrationSetResponse.getDeviceRegistrationSet());
            return listTransformer.toBundle(list);
        }
    }

    public DeviceRegistrationSetResponse(@NotNull Set<DeviceRegistration> deviceRegistrationSet) {
        Intrinsics.checkParameterIsNotNull(deviceRegistrationSet, "deviceRegistrationSet");
        this.deviceRegistrationSet = deviceRegistrationSet;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DeviceRegistrationSetResponse copy$default(DeviceRegistrationSetResponse deviceRegistrationSetResponse, Set set, int i, Object obj) {
        if ((i & 1) != 0) {
            set = deviceRegistrationSetResponse.deviceRegistrationSet;
        }
        return deviceRegistrationSetResponse.copy(set);
    }

    @NotNull
    public final Set<DeviceRegistration> component1() {
        return this.deviceRegistrationSet;
    }

    @NotNull
    public final DeviceRegistrationSetResponse copy(@NotNull Set<DeviceRegistration> deviceRegistrationSet) {
        Intrinsics.checkParameterIsNotNull(deviceRegistrationSet, "deviceRegistrationSet");
        return new DeviceRegistrationSetResponse(deviceRegistrationSet);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof DeviceRegistrationSetResponse) && Intrinsics.areEqual(this.deviceRegistrationSet, ((DeviceRegistrationSetResponse) obj).deviceRegistrationSet);
        }
        return true;
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Response
    @NotNull
    public Bundle getBundle() {
        return Query.Response.DefaultImpls.getBundle(this);
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Response
    @NotNull
    public BundleTransformer<DeviceRegistrationSetResponse> getBundleTransformer() {
        return this.bundleTransformer;
    }

    @NotNull
    public final Set<DeviceRegistration> getDeviceRegistrationSet() {
        return this.deviceRegistrationSet;
    }

    public int hashCode() {
        Set<DeviceRegistration> set = this.deviceRegistrationSet;
        if (set != null) {
            return set.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceRegistrationSetResponse(deviceRegistrationSet=");
        outline107.append(this.deviceRegistrationSet);
        outline107.append(")");
        return outline107.toString();
    }
}

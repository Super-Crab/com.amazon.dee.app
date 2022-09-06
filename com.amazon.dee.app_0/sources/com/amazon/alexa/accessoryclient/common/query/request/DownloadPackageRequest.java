package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessory.kota.InventoryUpdate;
import com.amazon.alexa.accessory.kota.KotaJobSchedulerService;
import com.amazon.alexa.accessory.kota.UpdateRequest;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.InventoryUpdateTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.UpdateRequestTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DownloadPackageRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001eB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00000\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/DownloadPackageRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", KotaJobSchedulerService.UPDATE_REQUEST_KEY, "Lcom/amazon/alexa/accessory/kota/UpdateRequest;", KotaJobSchedulerService.INVENTORY_UPDATE_KEY, "Lcom/amazon/alexa/accessory/kota/InventoryUpdate;", "hardUpdate", "", "(Lcom/amazon/alexa/accessory/kota/UpdateRequest;Lcom/amazon/alexa/accessory/kota/InventoryUpdate;Z)V", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getHardUpdate", "()Z", "getInventoryUpdate", "()Lcom/amazon/alexa/accessory/kota/InventoryUpdate;", "getUpdateRequest", "()Lcom/amazon/alexa/accessory/kota/UpdateRequest;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DownloadPackageRequest implements Query.Request<DownloadPackageRequest> {
    @NotNull
    private final BundleTransformer<DownloadPackageRequest> bundleTransformer;
    private final boolean hardUpdate;
    @NotNull
    private final InventoryUpdate inventoryUpdate;
    @NotNull
    private final UpdateRequest updateRequest;

    /* compiled from: DownloadPackageRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/DownloadPackageRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/DownloadPackageRequest;", "()V", "HARD_UPDATE_KEY", "", "INVENTORY_UPDATE_KEY", "UPDATE_REQUEST_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "t", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<DownloadPackageRequest> {
        private static final String HARD_UPDATE_KEY = "hardUpdate";
        public static final Transformer INSTANCE = new Transformer();
        private static final String INVENTORY_UPDATE_KEY = "inventoryUpdate";
        private static final String UPDATE_REQUEST_KEY = "updateRequest";

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public DownloadPackageRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            UpdateRequestTransformer updateRequestTransformer = UpdateRequestTransformer.INSTANCE;
            Bundle bundle2 = bundle.getBundle("updateRequest");
            if (bundle2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(UPDATE_REQUEST_KEY)!!");
            UpdateRequest mo568fromBundle = updateRequestTransformer.mo568fromBundle(bundle2);
            InventoryUpdateTransformer inventoryUpdateTransformer = InventoryUpdateTransformer.INSTANCE;
            Bundle bundle3 = bundle.getBundle("inventoryUpdate");
            if (bundle3 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(bundle3, "bundle.getBundle(INVENTORY_UPDATE_KEY)!!");
            return new DownloadPackageRequest(mo568fromBundle, inventoryUpdateTransformer.mo568fromBundle(bundle3), bundle.getBoolean(HARD_UPDATE_KEY));
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull DownloadPackageRequest t) {
            Intrinsics.checkParameterIsNotNull(t, "t");
            Bundle bundle = new Bundle();
            bundle.putBundle("updateRequest", UpdateRequestTransformer.INSTANCE.toBundle(t.getUpdateRequest()));
            bundle.putBundle("inventoryUpdate", InventoryUpdateTransformer.INSTANCE.toBundle(t.getInventoryUpdate()));
            bundle.putBoolean(HARD_UPDATE_KEY, t.getHardUpdate());
            return bundle;
        }
    }

    public DownloadPackageRequest(@NotNull UpdateRequest updateRequest, @NotNull InventoryUpdate inventoryUpdate, boolean z) {
        Intrinsics.checkParameterIsNotNull(updateRequest, "updateRequest");
        Intrinsics.checkParameterIsNotNull(inventoryUpdate, "inventoryUpdate");
        this.updateRequest = updateRequest;
        this.inventoryUpdate = inventoryUpdate;
        this.hardUpdate = z;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ DownloadPackageRequest copy$default(DownloadPackageRequest downloadPackageRequest, UpdateRequest updateRequest, InventoryUpdate inventoryUpdate, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            updateRequest = downloadPackageRequest.updateRequest;
        }
        if ((i & 2) != 0) {
            inventoryUpdate = downloadPackageRequest.inventoryUpdate;
        }
        if ((i & 4) != 0) {
            z = downloadPackageRequest.hardUpdate;
        }
        return downloadPackageRequest.copy(updateRequest, inventoryUpdate, z);
    }

    @NotNull
    public final UpdateRequest component1() {
        return this.updateRequest;
    }

    @NotNull
    public final InventoryUpdate component2() {
        return this.inventoryUpdate;
    }

    public final boolean component3() {
        return this.hardUpdate;
    }

    @NotNull
    public final DownloadPackageRequest copy(@NotNull UpdateRequest updateRequest, @NotNull InventoryUpdate inventoryUpdate, boolean z) {
        Intrinsics.checkParameterIsNotNull(updateRequest, "updateRequest");
        Intrinsics.checkParameterIsNotNull(inventoryUpdate, "inventoryUpdate");
        return new DownloadPackageRequest(updateRequest, inventoryUpdate, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DownloadPackageRequest)) {
                return false;
            }
            DownloadPackageRequest downloadPackageRequest = (DownloadPackageRequest) obj;
            return Intrinsics.areEqual(this.updateRequest, downloadPackageRequest.updateRequest) && Intrinsics.areEqual(this.inventoryUpdate, downloadPackageRequest.inventoryUpdate) && this.hardUpdate == downloadPackageRequest.hardUpdate;
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
    public BundleTransformer<DownloadPackageRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    public final boolean getHardUpdate() {
        return this.hardUpdate;
    }

    @NotNull
    public final InventoryUpdate getInventoryUpdate() {
        return this.inventoryUpdate;
    }

    @NotNull
    public final UpdateRequest getUpdateRequest() {
        return this.updateRequest;
    }

    public int hashCode() {
        UpdateRequest updateRequest = this.updateRequest;
        int i = 0;
        int hashCode = (updateRequest != null ? updateRequest.hashCode() : 0) * 31;
        InventoryUpdate inventoryUpdate = this.inventoryUpdate;
        if (inventoryUpdate != null) {
            i = inventoryUpdate.hashCode();
        }
        int i2 = (hashCode + i) * 31;
        boolean z = this.hardUpdate;
        if (z) {
            z = true;
        }
        int i3 = z ? 1 : 0;
        int i4 = z ? 1 : 0;
        return i2 + i3;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DownloadPackageRequest(updateRequest=");
        outline107.append(this.updateRequest);
        outline107.append(", inventoryUpdate=");
        outline107.append(this.inventoryUpdate);
        outline107.append(", hardUpdate=");
        return GeneratedOutlineSupport1.outline97(outline107, this.hardUpdate, ")");
    }
}

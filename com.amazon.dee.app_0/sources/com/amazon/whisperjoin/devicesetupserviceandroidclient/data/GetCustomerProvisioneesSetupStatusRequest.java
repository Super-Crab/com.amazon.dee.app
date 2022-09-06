package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes13.dex */
public class GetCustomerProvisioneesSetupStatusRequest {
    private final ImmutableList<AuthMaterialIdentifier> mAuthMaterialIdentifiers;
    private final ImmutableList<BarcodeIdentifier> mBarcodeIdentifiers;

    /* loaded from: classes13.dex */
    public static class Builder {
        private final Set<AuthMaterialIdentifier> mAuthMaterialIdentifiers = new LinkedHashSet();
        private final Set<BarcodeIdentifier> mBarcodeIdentifiers = new LinkedHashSet();

        /* JADX WARN: Multi-variable type inference failed */
        public Builder addAuthMaterialIdentifier(AuthMaterialIdentifier authMaterialIdentifier) {
            this.mAuthMaterialIdentifiers.add(Preconditions.checkNotNull(authMaterialIdentifier));
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder addBarcodeIdentifier(BarcodeIdentifier barcodeIdentifier) {
            this.mBarcodeIdentifiers.add(Preconditions.checkNotNull(barcodeIdentifier));
            return this;
        }

        public GetCustomerProvisioneesSetupStatusRequest createRequest() {
            return new GetCustomerProvisioneesSetupStatusRequest(this);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetCustomerProvisioneesSetupStatusRequest.class != obj.getClass()) {
            return false;
        }
        GetCustomerProvisioneesSetupStatusRequest getCustomerProvisioneesSetupStatusRequest = (GetCustomerProvisioneesSetupStatusRequest) obj;
        return Objects.equal(this.mAuthMaterialIdentifiers, getCustomerProvisioneesSetupStatusRequest.mAuthMaterialIdentifiers) && Objects.equal(this.mBarcodeIdentifiers, getCustomerProvisioneesSetupStatusRequest.mBarcodeIdentifiers);
    }

    public Iterable<AuthMaterialIdentifier> getAuthMaterialIdentifiers() {
        return this.mAuthMaterialIdentifiers;
    }

    public Iterable<BarcodeIdentifier> getBarcodeIdentifiers() {
        return this.mBarcodeIdentifiers;
    }

    public int hashCode() {
        return Objects.hashCode(this.mAuthMaterialIdentifiers, this.mBarcodeIdentifiers);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mAuthMaterialIdentifiers", this.mAuthMaterialIdentifiers).add("mBarcodeIdentifiers", this.mBarcodeIdentifiers).toString();
    }

    private GetCustomerProvisioneesSetupStatusRequest(Builder builder) {
        this.mAuthMaterialIdentifiers = ImmutableList.copyOf((Collection) builder.mAuthMaterialIdentifiers);
        this.mBarcodeIdentifiers = ImmutableList.copyOf((Collection) builder.mBarcodeIdentifiers);
    }
}

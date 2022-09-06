package com.amazon.alexa.marketplace.api;

import androidx.annotation.NonNull;
/* loaded from: classes9.dex */
public interface MarketplaceEndpoints {
    @NonNull
    default String getApiGatewayHost() {
        throw new UnsupportedOperationException("This operation is not implemented.");
    }

    String getAuthAssociationHandle();

    @NonNull
    String getAuthWebHost();

    @NonNull
    String getCoralHost();

    @NonNull
    default String getRetailHost() {
        throw new UnsupportedOperationException("This operation is not implemented.");
    }

    @NonNull
    default String getSkillsStoreEndpoint() {
        throw new UnsupportedOperationException("This operation is not implemented.");
    }

    @NonNull
    String getWebHost();
}

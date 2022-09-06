package com.amazon.alexa.voice.handsfree;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapperProvider;
import com.amazon.alexa.handsfree.vendor.bridge.dependencies.FalcoVendorBridgeComponent;
/* loaded from: classes11.dex */
public class PartnerIntentResolver {
    private final HandsFreePackageInfoResolver mHandsFreePackageInfoResolver;
    private final VendorAPIWrapperProvider mVendorAPIWrapperProvider;

    public PartnerIntentResolver(@NonNull Context context) {
        this(((FalcoVendorBridgeComponent) AhfComponentsProvider.getComponent(context, FalcoVendorBridgeComponent.class)).vendorAPIWrapperProvider(), new HandsFreePackageInfoResolver(context));
    }

    @Nullable
    public Intent getPartnerPermissionsIntent() {
        VendorAPIWrapper supportedAPIWrapper = this.mVendorAPIWrapperProvider.getSupportedAPIWrapper();
        if (supportedAPIWrapper != null) {
            return supportedAPIWrapper.getPermissionIntent();
        }
        return this.mHandsFreePackageInfoResolver.getPermissionsIntent();
    }

    @VisibleForTesting
    public PartnerIntentResolver(@NonNull VendorAPIWrapperProvider vendorAPIWrapperProvider, @NonNull HandsFreePackageInfoResolver handsFreePackageInfoResolver) {
        this.mVendorAPIWrapperProvider = vendorAPIWrapperProvider;
        this.mHandsFreePackageInfoResolver = handsFreePackageInfoResolver;
    }
}

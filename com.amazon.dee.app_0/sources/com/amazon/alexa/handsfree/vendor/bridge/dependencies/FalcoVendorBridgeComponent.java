package com.amazon.alexa.handsfree.vendor.bridge.dependencies;

import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentProtocol;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapperProvider;
import dagger.Subcomponent;
@Subcomponent
/* loaded from: classes8.dex */
public interface FalcoVendorBridgeComponent extends AhfComponentProtocol {
    VendorAPIWrapperProvider vendorAPIWrapperProvider();
}

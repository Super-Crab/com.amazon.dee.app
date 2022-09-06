package com.amazon.alexa.handsfree.vendor.bridge;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.vendor.bridge.dependencies.FalcoVendorBridgeComponent;
import com.amazon.alexa.utils.security.SignatureVerifier;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class VendorAPIWrapperProvider {
    private static VendorAPIWrapperProvider mVendorAPIWrapperProvider;
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final Context mContext;
    private final SignatureVerifier mSignatureVerifier;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public VendorAPIWrapperProvider(@NonNull Context context) {
        this(context, new SignatureVerifier(context), AMPDInformationProvider.getInstance(context));
    }

    @NonNull
    public static synchronized VendorAPIWrapperProvider getInstance(Context context) {
        VendorAPIWrapperProvider vendorAPIWrapperProvider;
        synchronized (VendorAPIWrapperProvider.class) {
            vendorAPIWrapperProvider = ((FalcoVendorBridgeComponent) AhfComponentsProvider.getComponent(context, FalcoVendorBridgeComponent.class)).vendorAPIWrapperProvider();
        }
        return vendorAPIWrapperProvider;
    }

    @Nullable
    public VendorAPIWrapper getSupportedAPIWrapper() {
        VoiceAppPackageInfo[] values;
        for (VoiceAppPackageInfo voiceAppPackageInfo : VoiceAppPackageInfo.values()) {
            if (voiceAppPackageInfo.isPackageOnDevice(this.mContext, this.mSignatureVerifier) && voiceAppPackageInfo.getPackageName().equals(this.mAMPDInformationProvider.getVoiceAppPackageName())) {
                return voiceAppPackageInfo.getVendorAPIWrapper();
            }
        }
        return null;
    }

    @VisibleForTesting
    VendorAPIWrapperProvider(@NonNull Context context, @NonNull SignatureVerifier signatureVerifier, @NonNull AMPDInformationProvider aMPDInformationProvider) {
        this.mContext = context;
        this.mSignatureVerifier = signatureVerifier;
        this.mAMPDInformationProvider = aMPDInformationProvider;
    }

    @Nullable
    public VendorAPIWrapper getSupportedAPIWrapper(@NonNull VoiceAppPackageInfo voiceAppPackageInfo) {
        return voiceAppPackageInfo.getVendorAPIWrapper();
    }
}

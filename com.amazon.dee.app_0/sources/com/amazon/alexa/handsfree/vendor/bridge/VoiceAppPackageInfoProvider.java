package com.amazon.alexa.handsfree.vendor.bridge;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.security.SignatureVerifier;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class VoiceAppPackageInfoProvider {
    private final Context mContext;
    private final SignatureVerifier mSignatureVerifier;

    @VisibleForTesting
    VoiceAppPackageInfoProvider(@NonNull Context context, @NonNull SignatureVerifier signatureVerifier) {
        this.mContext = context;
        this.mSignatureVerifier = signatureVerifier;
    }

    @Nullable
    public VoiceAppPackageInfo getSupportedVoiceApp() {
        VoiceAppPackageInfo[] values;
        for (VoiceAppPackageInfo voiceAppPackageInfo : VoiceAppPackageInfo.values()) {
            if (voiceAppPackageInfo.isPackageOnDevice(this.mContext, this.mSignatureVerifier)) {
                return voiceAppPackageInfo;
            }
        }
        return null;
    }

    @Inject
    public VoiceAppPackageInfoProvider(@NonNull Context context) {
        this(context, new SignatureVerifier(context));
    }
}

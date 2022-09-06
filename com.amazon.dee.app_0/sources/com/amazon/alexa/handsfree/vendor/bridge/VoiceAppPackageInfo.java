package com.amazon.alexa.handsfree.vendor.bridge;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.vendor.bridge.wrappers.MetroAPIWrapper;
import com.amazon.alexa.handsfree.vendor.bridge.wrappers.QuebecAPIWrapper;
import com.amazon.alexa.utils.security.SignatureVerifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.magiear.handsfree.util.Common;
/* loaded from: classes8.dex */
public enum VoiceAppPackageInfo {
    METRO_ASSISTANT(Common.HANDSFREE_ASSISTANT_PACKAGE_NAME, 1, 2100000000, new MetroAPIWrapper()),
    QUEBEC_ASSISTANT("com.quicinc.voice.activation", 301001, 2100000000, new QuebecAPIWrapper());
    
    private static final String TAG = VoiceAppPackageInfo.class.getSimpleName();
    private final int mMaxSupportedVersion;
    private final int mMinSupportedVersion;
    private final String mPackageName;
    private final VendorAPIWrapper mVendorAPIWrapper;

    VoiceAppPackageInfo(@NonNull String str, int i, int i2, @NonNull VendorAPIWrapper vendorAPIWrapper) {
        this.mPackageName = str;
        this.mMinSupportedVersion = i;
        this.mMaxSupportedVersion = i2;
        this.mVendorAPIWrapper = vendorAPIWrapper;
    }

    private boolean hasPermission(String str, String str2, Context context) {
        return context.getPackageManager().checkPermission(str, str2) == 0;
    }

    @VisibleForTesting
    int getMaxSupportedVersion() {
        return this.mMaxSupportedVersion;
    }

    @VisibleForTesting
    int getMinSupportedVersion() {
        return this.mMinSupportedVersion;
    }

    @NonNull
    public String getPackageName() {
        return this.mPackageName;
    }

    @NonNull
    public VendorAPIWrapper getVendorAPIWrapper() {
        return this.mVendorAPIWrapper;
    }

    public boolean hasRecordAudioPermissions(Context context) {
        return hasPermission("android.permission.RECORD_AUDIO", this.mPackageName, context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isPackageOnDevice(@NonNull Context context, @NonNull SignatureVerifier signatureVerifier) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(this.mPackageName, 0);
            if (!signatureVerifier.verify(this.mPackageName)) {
                Log.d(TAG, "unable to verify authenticity of voice APK");
                return false;
            } else if (packageInfo.versionCode < this.mMinSupportedVersion) {
                String str = TAG;
                Log.d(str, "version too old: " + this.mPackageName);
                return false;
            } else if (packageInfo.versionCode > this.mMaxSupportedVersion) {
                String str2 = TAG;
                Log.d(str2, "version too new: " + this.mPackageName);
                return false;
            } else if (!context.getPackageManager().getApplicationInfo(this.mPackageName, 0).enabled) {
                Log.d(TAG, "package is disabled");
                return false;
            } else {
                Log.d(TAG, "package found");
                return true;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            String str3 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("package not found: ");
            outline107.append(this.mPackageName);
            Log.d(str3, outline107.toString());
            return false;
        }
    }
}

package com.amazon.alexa.handsfree.metrics.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.utils.ApplicationInformationProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class AlexaApplicationInformationProvider implements ApplicationInformationProvider {
    private static final String TAG = "AlexaAppInfrmProv";
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final Context mContext;
    private Integer mDSPAppBuildCode;
    private Integer mFalcoVersionCode;
    private final PackageManager mPackageManager;
    private Integer mVoxAppBuildCode;

    public AlexaApplicationInformationProvider(@NonNull Context context) {
        this(context, AMPDInformationProvider.getInstance(context), context.getPackageManager());
    }

    @Nullable
    private Integer getVersionCodeForPackageName(@NonNull String str) {
        try {
            return Integer.valueOf(this.mPackageManager.getPackageInfo(str, 0).versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.i(TAG, "Could not get PackageInfo from given package name: " + str);
            return null;
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.utils.ApplicationInformationProvider
    @Nullable
    public Integer getDspAppBuildCode() {
        if (this.mDSPAppBuildCode == null) {
            String voiceAppPackageName = this.mAMPDInformationProvider.getVoiceAppPackageName();
            if (voiceAppPackageName == null) {
                return null;
            }
            this.mDSPAppBuildCode = getVersionCodeForPackageName(voiceAppPackageName);
        }
        return this.mDSPAppBuildCode;
    }

    @Override // com.amazon.alexa.handsfree.protocols.utils.ApplicationInformationProvider
    @Nullable
    public Integer getFalcoLibBuildCode() {
        return this.mFalcoVersionCode;
    }

    @Override // com.amazon.alexa.handsfree.protocols.utils.ApplicationInformationProvider
    @Nullable
    public Integer getVoxAppBuildCode() {
        if (this.mVoxAppBuildCode == null) {
            this.mVoxAppBuildCode = getVersionCodeForPackageName(this.mContext.getPackageName());
        }
        return this.mVoxAppBuildCode;
    }

    public void setFalcoBuildCode(@Nullable Integer num) {
        this.mFalcoVersionCode = num;
    }

    @VisibleForTesting
    AlexaApplicationInformationProvider(@NonNull Context context, @NonNull AMPDInformationProvider aMPDInformationProvider, @NonNull PackageManager packageManager) {
        this.mContext = context;
        this.mAMPDInformationProvider = aMPDInformationProvider;
        this.mPackageManager = packageManager;
    }
}

package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import org.apache.logging.log4j.util.ProcessIdUtil;
@ShowFirstParty
@KeepForSdk
/* loaded from: classes2.dex */
public class GoogleApiAvailabilityLight {
    @KeepForSdk
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @KeepForSdk
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    @KeepForSdk
    static final String TRACKING_SOURCE_DIALOG = "d";
    @KeepForSdk
    static final String TRACKING_SOURCE_NOTIFICATION = "n";
    @KeepForSdk
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final GoogleApiAvailabilityLight zzm = new GoogleApiAvailabilityLight();

    @KeepForSdk
    public static GoogleApiAvailabilityLight getInstance() {
        return zzm;
    }

    @VisibleForTesting
    private static String zza(@Nullable Context context, @Nullable String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("gcore_");
        outline107.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
        outline107.append(ProcessIdUtil.DEFAULT_PROCESSID);
        if (!TextUtils.isEmpty(str)) {
            outline107.append(str);
        }
        outline107.append(ProcessIdUtil.DEFAULT_PROCESSID);
        if (context != null) {
            outline107.append(context.getPackageName());
        }
        outline107.append(ProcessIdUtil.DEFAULT_PROCESSID);
        if (context != null) {
            try {
                outline107.append(Wrappers.packageManager(context).getPackageInfo(context.getPackageName(), 0).versionCode);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return outline107.toString();
    }

    @KeepForSdk
    public void cancelAvailabilityErrorNotifications(Context context) {
        GooglePlayServicesUtilLight.cancelAvailabilityErrorNotifications(context);
    }

    @ShowFirstParty
    @KeepForSdk
    public int getApkVersion(Context context) {
        return GooglePlayServicesUtilLight.getApkVersion(context);
    }

    @ShowFirstParty
    @KeepForSdk
    public int getClientVersion(Context context) {
        return GooglePlayServicesUtilLight.getClientVersion(context);
    }

    @Deprecated
    @Nullable
    @ShowFirstParty
    @KeepForSdk
    public Intent getErrorResolutionIntent(int i) {
        return getErrorResolutionIntent(null, i, null);
    }

    @Nullable
    @KeepForSdk
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2) {
        return getErrorResolutionPendingIntent(context, i, i2, null);
    }

    @KeepForSdk
    public String getErrorString(int i) {
        return GooglePlayServicesUtilLight.getErrorString(i);
    }

    @HideFirstParty
    @KeepForSdk
    public int isGooglePlayServicesAvailable(Context context) {
        return isGooglePlayServicesAvailable(context, GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPlayServicesPossiblyUpdating(Context context, int i) {
        return GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, i);
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPlayStorePossiblyUpdating(Context context, int i) {
        return GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(context, i);
    }

    @KeepForSdk
    public boolean isUninstalledAppPossiblyUpdating(Context context, String str) {
        return GooglePlayServicesUtilLight.isUninstalledAppPossiblyUpdating(context, str);
    }

    @KeepForSdk
    public boolean isUserResolvableError(int i) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(i);
    }

    @KeepForSdk
    public void verifyGooglePlayServicesIsAvailable(Context context, int i) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context, i);
    }

    @Nullable
    @ShowFirstParty
    @KeepForSdk
    public Intent getErrorResolutionIntent(Context context, int i, @Nullable String str) {
        if (i != 1 && i != 2) {
            if (i == 3) {
                return com.google.android.gms.common.internal.zzg.zzg("com.google.android.gms");
            }
            return null;
        } else if (context != null && DeviceProperties.isWearableWithoutPlayStore(context)) {
            return com.google.android.gms.common.internal.zzg.zzs();
        } else {
            return com.google.android.gms.common.internal.zzg.zza("com.google.android.gms", zza(context, str));
        }
    }

    @Nullable
    @ShowFirstParty
    @KeepForSdk
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2, @Nullable String str) {
        Intent errorResolutionIntent = getErrorResolutionIntent(context, i, str);
        if (errorResolutionIntent == null) {
            return null;
        }
        return PendingIntent.getActivity(context, i2, errorResolutionIntent, 134217728);
    }

    @KeepForSdk
    public int isGooglePlayServicesAvailable(Context context, int i) {
        int isGooglePlayServicesAvailable = GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context, i);
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, isGooglePlayServicesAvailable)) {
            return 18;
        }
        return isGooglePlayServicesAvailable;
    }
}

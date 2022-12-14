package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.R;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;
@KeepForSdk
@Deprecated
/* loaded from: classes2.dex */
public final class GoogleServices {
    private static final Object sLock = new Object();
    @GuardedBy("sLock")
    private static GoogleServices zzay;
    private final String zzaz;
    private final Status zzba;
    private final boolean zzbb;
    private final boolean zzbc;

    @VisibleForTesting
    @KeepForSdk
    GoogleServices(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
        boolean z = true;
        if (identifier != 0) {
            z = resources.getInteger(identifier) == 0 ? false : z;
            this.zzbc = !z;
        } else {
            this.zzbc = false;
        }
        this.zzbb = z;
        String zzc = zzp.zzc(context);
        zzc = zzc == null ? new StringResourceValueReader(context).getString("google_app_id") : zzc;
        if (TextUtils.isEmpty(zzc)) {
            this.zzba = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzaz = null;
            return;
        }
        this.zzaz = zzc;
        this.zzba = Status.RESULT_SUCCESS;
    }

    @KeepForSdk
    private static GoogleServices checkInitialized(String str) {
        GoogleServices googleServices;
        synchronized (sLock) {
            if (zzay != null) {
                googleServices = zzay;
            } else {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34);
                sb.append("Initialize must be called before ");
                sb.append(str);
                sb.append(".");
                throw new IllegalStateException(sb.toString());
            }
        }
        return googleServices;
    }

    @VisibleForTesting
    @KeepForSdk
    static void clearInstanceForTest() {
        synchronized (sLock) {
            zzay = null;
        }
    }

    @KeepForSdk
    public static String getGoogleAppId() {
        return checkInitialized("getGoogleAppId").zzaz;
    }

    @KeepForSdk
    public static Status initialize(Context context, String str, boolean z) {
        Preconditions.checkNotNull(context, "Context must not be null.");
        Preconditions.checkNotEmpty(str, "App ID must be nonempty.");
        synchronized (sLock) {
            if (zzay != null) {
                return zzay.checkGoogleAppId(str);
            }
            GoogleServices googleServices = new GoogleServices(str, z);
            zzay = googleServices;
            return googleServices.zzba;
        }
    }

    @KeepForSdk
    public static boolean isMeasurementEnabled() {
        GoogleServices checkInitialized = checkInitialized("isMeasurementEnabled");
        return checkInitialized.zzba.isSuccess() && checkInitialized.zzbb;
    }

    @KeepForSdk
    public static boolean isMeasurementExplicitlyDisabled() {
        return checkInitialized("isMeasurementExplicitlyDisabled").zzbc;
    }

    @VisibleForTesting
    @KeepForSdk
    final Status checkGoogleAppId(String str) {
        String str2 = this.zzaz;
        if (str2 != null && !str2.equals(str)) {
            String str3 = this.zzaz;
            return new Status(10, GeneratedOutlineSupport1.outline30(GeneratedOutlineSupport1.outline6(str3, 97), "Initialize was called with two different Google App IDs.  Only the first app ID will be used: '", str3, "'."));
        }
        return Status.RESULT_SUCCESS;
    }

    @KeepForSdk
    public static Status initialize(Context context) {
        Status status;
        Preconditions.checkNotNull(context, "Context must not be null.");
        synchronized (sLock) {
            if (zzay == null) {
                zzay = new GoogleServices(context);
            }
            status = zzay.zzba;
        }
        return status;
    }

    @VisibleForTesting
    @KeepForSdk
    GoogleServices(String str, boolean z) {
        this.zzaz = str;
        this.zzba = Status.RESULT_SUCCESS;
        this.zzbb = z;
        this.zzbc = !z;
    }
}

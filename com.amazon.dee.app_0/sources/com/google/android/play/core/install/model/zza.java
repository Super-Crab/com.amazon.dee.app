package com.google.android.play.core.install.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zza {
    private static final Map zza = new HashMap();
    private static final Map zzb = new HashMap();

    static {
        zza.put(-2, "An unknown error occurred.");
        zza.put(-3, "The API is not available on this device.");
        zza.put(-4, "The request that was sent by the app is malformed.");
        zza.put(-5, "The install is unavailable to this user or device.");
        zza.put(-6, "The download/install is not allowed, due to the current device state (e.g. low battery, low disk space, ...).");
        zza.put(-7, "The install/update has not been (fully) downloaded yet.");
        zza.put(-8, "The install is already in progress and there is no UI flow to resume.");
        zza.put(-9, "The Play Store app is either not installed or not the official version.");
        zza.put(-10, "The app is not owned by any user on this device. An app is \"owned\" if it has been acquired from Play.");
        zza.put(-100, "An internal error happened in the Play Store.");
        zzb.put(-2, "ERROR_UNKNOWN");
        zzb.put(-3, "ERROR_API_NOT_AVAILABLE");
        zzb.put(-4, "ERROR_INVALID_REQUEST");
        zzb.put(-5, "ERROR_INSTALL_UNAVAILABLE");
        zzb.put(-6, "ERROR_INSTALL_NOT_ALLOWED");
        zzb.put(-7, "ERROR_DOWNLOAD_NOT_PRESENT");
        zzb.put(-8, "ERROR_INSTALL_IN_PROGRESS");
        zzb.put(-100, "ERROR_INTERNAL_ERROR");
        zzb.put(-9, "ERROR_PLAY_STORE_NOT_FOUND");
        zzb.put(-10, "ERROR_APP_NOT_OWNED");
        zzb.put(-100, "ERROR_INTERNAL_ERROR");
    }

    public static String zza(@InstallErrorCode int i) {
        Map map = zza;
        Integer valueOf = Integer.valueOf(i);
        if (!map.containsKey(valueOf) || !zzb.containsKey(valueOf)) {
            return "";
        }
        String str = (String) zza.get(valueOf);
        String str2 = (String) zzb.get(valueOf);
        return GeneratedOutlineSupport1.outline93(new StringBuilder(String.valueOf(str).length() + 103 + String.valueOf(str2).length()), str, " (https://developer.android.com/reference/com/google/android/play/core/install/model/InstallErrorCode#", str2, ")");
    }
}

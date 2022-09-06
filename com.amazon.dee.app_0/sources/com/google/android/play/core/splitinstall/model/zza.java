package com.google.android.play.core.splitinstall.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zza {
    private static final Map zza = new HashMap();
    private static final Map zzb = new HashMap();
    private static final Map zzc;

    static {
        zza.put(-1, "Too many sessions are running for current app, existing sessions must be resolved first.");
        zza.put(-2, "A requested module is not available (to this user/device, for the installed apk).");
        zza.put(-3, "Request is otherwise invalid.");
        zza.put(-4, "Requested session is not found.");
        zza.put(-5, "Split Install API is not available.");
        zza.put(-6, "Network error: unable to obtain split details.");
        zza.put(-7, "Download not permitted under current device circumstances (e.g. in background).");
        zza.put(-8, "Requested session contains modules from an existing active session and also new modules.");
        zza.put(-9, "Service handling split install has died.");
        zza.put(-10, "Install failed due to insufficient storage.");
        zza.put(-11, "Signature verification error when invoking SplitCompat.");
        zza.put(-12, "Error in SplitCompat emulation.");
        zza.put(-13, "Error in copying files for SplitCompat.");
        zza.put(-14, "The Play Store app is either not installed or not the official version.");
        zza.put(-15, "The app is not owned by any user on this device. An app is \"owned\" if it has been acquired from Play.");
        zza.put(-100, "Unknown error processing split install.");
        zzb.put(-1, "ACTIVE_SESSIONS_LIMIT_EXCEEDED");
        zzb.put(-2, "MODULE_UNAVAILABLE");
        zzb.put(-3, "INVALID_REQUEST");
        zzb.put(-4, "DOWNLOAD_NOT_FOUND");
        zzb.put(-5, "API_NOT_AVAILABLE");
        zzb.put(-6, "NETWORK_ERROR");
        zzb.put(-7, "ACCESS_DENIED");
        zzb.put(-8, "INCOMPATIBLE_WITH_EXISTING_SESSION");
        zzb.put(-9, "SERVICE_DIED");
        zzb.put(-10, "INSUFFICIENT_STORAGE");
        zzb.put(-11, "SPLITCOMPAT_VERIFICATION_ERROR");
        zzb.put(-12, "SPLITCOMPAT_EMULATION_ERROR");
        zzb.put(-13, "SPLITCOMPAT_COPY_ERROR");
        zzb.put(-14, "PLAY_STORE_NOT_FOUND");
        zzb.put(-15, "APP_NOT_OWNED");
        zzb.put(-100, "INTERNAL_ERROR");
        zzc = new HashMap();
        for (Map.Entry entry : zzb.entrySet()) {
            zzc.put((String) entry.getValue(), (Integer) entry.getKey());
        }
    }

    @SplitInstallErrorCode
    public static int zza(String str) {
        Integer num = (Integer) zzc.get(str);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException(String.valueOf(str).concat(" is unknown error."));
    }

    public static String zzb(@SplitInstallErrorCode int i) {
        Map map = zza;
        Integer valueOf = Integer.valueOf(i);
        if (!map.containsKey(valueOf) || !zzb.containsKey(valueOf)) {
            return "";
        }
        String str = (String) zza.get(valueOf);
        String str2 = (String) zzb.get(valueOf);
        return GeneratedOutlineSupport1.outline93(new StringBuilder(String.valueOf(str).length() + 118 + String.valueOf(str2).length()), str, " (https://developer.android.com/reference/com/google/android/play/core/splitinstall/model/SplitInstallErrorCode.html#", str2, ")");
    }
}

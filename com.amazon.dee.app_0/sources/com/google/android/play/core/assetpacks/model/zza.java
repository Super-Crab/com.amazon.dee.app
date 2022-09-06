package com.google.android.play.core.assetpacks.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zza {
    private static final Map zza = new HashMap();
    private static final Map zzb = new HashMap();

    static {
        zza.put(-1, "The requesting app is unavailable (e.g. unpublished, nonexistent version code).");
        zza.put(-2, "The requested pack is not available.");
        zza.put(-3, "The request is invalid.");
        zza.put(-4, "The requested download is not found.");
        zza.put(-5, "The Asset Delivery API is not available.");
        zza.put(-6, "Network error. Unable to obtain the asset pack details.");
        zza.put(-7, "Download not permitted under current device circumstances (e.g. in background).");
        zza.put(-10, "Asset pack download failed due to insufficient storage.");
        zza.put(-11, "The Play Store app is either not installed or not the official version.");
        zza.put(-12, "Tried to show the cellular data confirmation but no asset packs are waiting for Wi-Fi.");
        zza.put(-13, "The app is not owned by any user on this device. An app is \"owned\" if it has been acquired from Play.");
        zza.put(-100, "Unknown error downloading an asset pack.");
        zzb.put(-1, "APP_UNAVAILABLE");
        zzb.put(-2, "PACK_UNAVAILABLE");
        zzb.put(-3, "INVALID_REQUEST");
        zzb.put(-4, "DOWNLOAD_NOT_FOUND");
        zzb.put(-5, "API_NOT_AVAILABLE");
        zzb.put(-6, "NETWORK_ERROR");
        zzb.put(-7, "ACCESS_DENIED");
        zzb.put(-10, "INSUFFICIENT_STORAGE");
        zzb.put(-11, "PLAY_STORE_NOT_FOUND");
        zzb.put(-12, "NETWORK_UNRESTRICTED");
        zzb.put(-13, "APP_NOT_OWNED");
        zzb.put(-100, "INTERNAL_ERROR");
    }

    public static String zza(@AssetPackErrorCode int i) {
        Map map = zza;
        Integer valueOf = Integer.valueOf(i);
        if (!map.containsKey(valueOf)) {
            return "";
        }
        String str = (String) zza.get(valueOf);
        String str2 = (String) zzb.get(valueOf);
        return GeneratedOutlineSupport1.outline93(new StringBuilder(String.valueOf(str).length() + 113 + String.valueOf(str2).length()), str, " (https://developer.android.com/reference/com/google/android/play/core/assetpacks/model/AssetPackErrorCode.html#", str2, ")");
    }
}

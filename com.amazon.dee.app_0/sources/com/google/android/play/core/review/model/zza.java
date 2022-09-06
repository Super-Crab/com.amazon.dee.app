package com.google.android.play.core.review.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zza {
    private static final Map zza = new HashMap();
    private static final Map zzb = new HashMap();

    static {
        zza.put(-1, "The Play Store app is either not installed or not the official version.");
        zza.put(-2, "Call first requestReviewFlow to get the ReviewInfo.");
        zzb.put(-1, "PLAY_STORE_NOT_FOUND");
        zzb.put(-2, "INVALID_REQUEST");
    }

    public static String zza(int i) {
        Map map = zza;
        Integer valueOf = Integer.valueOf(i);
        if (!map.containsKey(valueOf)) {
            return "";
        }
        String str = (String) zza.get(valueOf);
        String str2 = (String) zzb.get(valueOf);
        return GeneratedOutlineSupport1.outline93(new StringBuilder(String.valueOf(str).length() + 106 + String.valueOf(str2).length()), str, " (https://developer.android.com/reference/com/google/android/play/core/review/model/ReviewErrorCode.html#", str2, ")");
    }
}

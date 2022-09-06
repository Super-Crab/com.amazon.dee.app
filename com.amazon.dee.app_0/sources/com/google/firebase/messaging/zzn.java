package com.google.firebase.messaging;

import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.device.messaging.ADMConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.MissingFormatArgumentException;
import org.json.JSONArray;
import org.json.JSONException;
/* compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
/* loaded from: classes3.dex */
public final class zzn {
    @NonNull
    private final Bundle zza;

    public zzn(@NonNull Bundle bundle) {
        if (bundle != null) {
            this.zza = new Bundle(bundle);
            return;
        }
        throw new NullPointerException("data");
    }

    @Nullable
    private final JSONArray zzg(String str) {
        String zza = zza(str);
        if (!TextUtils.isEmpty(zza)) {
            try {
                return new JSONArray(zza);
            } catch (JSONException unused) {
                String zzh = zzh(str);
                StringBuilder outline106 = GeneratedOutlineSupport1.outline106(GeneratedOutlineSupport1.outline6(zza, GeneratedOutlineSupport1.outline6(zzh, 50)), "Malformed JSON for key ", zzh, RealTimeTextConstants.COLON_SPACE, zza);
                outline106.append(", falling back to default");
                Log.w("NotificationParams", outline106.toString());
                return null;
            }
        }
        return null;
    }

    private static String zzh(String str) {
        return str.startsWith("gcm.n.") ? str.substring(6) : str;
    }

    private static String zzi(String str) {
        return !str.startsWith("gcm.n.") ? str : str.replace("gcm.n.", "gcm.notification.");
    }

    public final String zza(String str) {
        Bundle bundle = this.zza;
        if (!bundle.containsKey(str) && str.startsWith("gcm.n.")) {
            String zzi = zzi(str);
            if (this.zza.containsKey(zzi)) {
                str = zzi;
            }
        }
        return bundle.getString(str);
    }

    public final boolean zzb(String str) {
        String zza = zza(str);
        return "1".equals(zza) || Boolean.parseBoolean(zza);
    }

    public final Integer zzc(String str) {
        String zza = zza(str);
        if (!TextUtils.isEmpty(zza)) {
            try {
                return Integer.valueOf(Integer.parseInt(zza));
            } catch (NumberFormatException unused) {
                String zzh = zzh(str);
                StringBuilder outline106 = GeneratedOutlineSupport1.outline106(GeneratedOutlineSupport1.outline6(zza, GeneratedOutlineSupport1.outline6(zzh, 38)), "Couldn't parse value of ", zzh, "(", zza);
                outline106.append(") into an int");
                Log.w("NotificationParams", outline106.toString());
                return null;
            }
        }
        return null;
    }

    public final Long zzd(String str) {
        String zza = zza(str);
        if (!TextUtils.isEmpty(zza)) {
            try {
                return Long.valueOf(Long.parseLong(zza));
            } catch (NumberFormatException unused) {
                String zzh = zzh(str);
                StringBuilder outline106 = GeneratedOutlineSupport1.outline106(GeneratedOutlineSupport1.outline6(zza, GeneratedOutlineSupport1.outline6(zzh, 38)), "Couldn't parse value of ", zzh, "(", zza);
                outline106.append(") into a long");
                Log.w("NotificationParams", outline106.toString());
                return null;
            }
        }
        return null;
    }

    @Nullable
    public final String zze(String str) {
        String valueOf = String.valueOf(str);
        return zza("_loc_key".length() != 0 ? valueOf.concat("_loc_key") : new String(valueOf));
    }

    @Nullable
    public final Object[] zzf(String str) {
        String valueOf = String.valueOf(str);
        JSONArray zzg = zzg("_loc_args".length() != 0 ? valueOf.concat("_loc_args") : new String(valueOf));
        if (zzg == null) {
            return null;
        }
        String[] strArr = new String[zzg.length()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = zzg.optString(i);
        }
        return strArr;
    }

    public final Bundle zze() {
        Bundle bundle = new Bundle(this.zza);
        for (String str : this.zza.keySet()) {
            if (str.startsWith("google.c.") || str.startsWith("gcm.n.") || str.startsWith("gcm.notification.")) {
                bundle.remove(str);
            }
        }
        return bundle;
    }

    @Nullable
    public final String zzb() {
        String zza = zza("gcm.n.sound2");
        return TextUtils.isEmpty(zza) ? zza("gcm.n.sound") : zza;
    }

    public final Bundle zzf() {
        Bundle bundle = new Bundle(this.zza);
        for (String str : this.zza.keySet()) {
            if (!(str.startsWith("google.c.a.") || str.equals(ADMConstants.EXTRA_FROM))) {
                bundle.remove(str);
            }
        }
        return bundle;
    }

    @Nullable
    private final String zzb(Resources resources, String str, String str2) {
        String zze = zze(str2);
        if (TextUtils.isEmpty(zze)) {
            return null;
        }
        int identifier = resources.getIdentifier(zze, "string", str);
        if (identifier == 0) {
            String valueOf = String.valueOf(str2);
            String zzh = zzh("_loc_key".length() != 0 ? valueOf.concat("_loc_key") : new String(valueOf));
            Log.w("NotificationParams", GeneratedOutlineSupport1.outline31(GeneratedOutlineSupport1.outline6(str2, GeneratedOutlineSupport1.outline6(zzh, 49)), zzh, " resource not found: ", str2, " Default value will be used."));
            return null;
        }
        Object[] zzf = zzf(str2);
        if (zzf == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, zzf);
        } catch (MissingFormatArgumentException e) {
            String zzh2 = zzh(str2);
            String arrays = Arrays.toString(zzf);
            StringBuilder outline106 = GeneratedOutlineSupport1.outline106(GeneratedOutlineSupport1.outline6(arrays, GeneratedOutlineSupport1.outline6(zzh2, 58)), "Missing format argument for ", zzh2, RealTimeTextConstants.COLON_SPACE, arrays);
            outline106.append(" Default value will be used.");
            Log.w("NotificationParams", outline106.toString(), e);
            return null;
        }
    }

    @Nullable
    public final Uri zza() {
        String zza = zza("gcm.n.link_android");
        if (TextUtils.isEmpty(zza)) {
            zza = zza("gcm.n.link");
        }
        if (!TextUtils.isEmpty(zza)) {
            return Uri.parse(zza);
        }
        return null;
    }

    @Nullable
    public final long[] zzc() {
        JSONArray zzg = zzg("gcm.n.vibrate_timings");
        if (zzg == null) {
            return null;
        }
        try {
            if (zzg.length() > 1) {
                long[] jArr = new long[zzg.length()];
                for (int i = 0; i < jArr.length; i++) {
                    jArr[i] = zzg.optLong(i);
                }
                return jArr;
            }
            throw new JSONException("vibrateTimings have invalid length");
        } catch (NumberFormatException | JSONException unused) {
            String valueOf = String.valueOf(zzg);
            StringBuilder sb = new StringBuilder(valueOf.length() + 74);
            sb.append("User defined vibrateTimings is invalid: ");
            sb.append(valueOf);
            sb.append(". Skipping setting vibrateTimings.");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final int[] zzd() {
        JSONArray zzg = zzg("gcm.n.light_settings");
        if (zzg == null) {
            return null;
        }
        int[] iArr = new int[3];
        try {
            if (zzg.length() == 3) {
                int parseColor = Color.parseColor(zzg.optString(0));
                if (parseColor != -16777216) {
                    iArr[0] = parseColor;
                    iArr[1] = zzg.optInt(1);
                    iArr[2] = zzg.optInt(2);
                    return iArr;
                }
                throw new IllegalArgumentException("Transparent color is invalid");
            }
            throw new JSONException("lightSettings don't have all three fields");
        } catch (IllegalArgumentException e) {
            String valueOf = String.valueOf(zzg);
            String message = e.getMessage();
            StringBuilder outline106 = GeneratedOutlineSupport1.outline106(GeneratedOutlineSupport1.outline6(message, valueOf.length() + 60), "LightSettings is invalid: ", valueOf, ". ", message);
            outline106.append(". Skipping setting LightSettings");
            Log.w("NotificationParams", outline106.toString());
            return null;
        } catch (JSONException unused) {
            String valueOf2 = String.valueOf(zzg);
            StringBuilder sb = new StringBuilder(valueOf2.length() + 58);
            sb.append("LightSettings is invalid: ");
            sb.append(valueOf2);
            sb.append(". Skipping setting LightSettings");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
    }

    public final String zza(Resources resources, String str, String str2) {
        String zza = zza(str2);
        return !TextUtils.isEmpty(zza) ? zza : zzb(resources, str, str2);
    }

    public static boolean zza(Bundle bundle) {
        return "1".equals(bundle.getString("gcm.n.e")) || "1".equals(bundle.getString(zzi("gcm.n.e"))) || bundle.getString("gcm.n.icon") != null || bundle.getString(zzi("gcm.n.icon")) != null;
    }
}

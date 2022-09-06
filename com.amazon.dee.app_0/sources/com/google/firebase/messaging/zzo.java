package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider;
import com.amazon.deecomms.common.Constants;
import com.amazon.device.messaging.ADMConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirelogAnalyticsEvent;
import com.lwansbrough.RCTCamera.RCTCameraModule;
/* compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
/* loaded from: classes3.dex */
public final class zzo {
    private static final DataEncoder zza = new JsonDataEncoderBuilder().registerEncoder(FirelogAnalyticsEvent.zzc.class, (ObjectEncoder) new FirelogAnalyticsEvent.zzb()).registerEncoder(FirelogAnalyticsEvent.class, (ObjectEncoder) new FirelogAnalyticsEvent.zza()).build();

    public static void zza(Intent intent, @Nullable Transport<String> transport) {
        zza("_nr", intent);
        if (transport != null) {
            try {
                transport.send(Event.ofTelemetry(zza.encode(new FirelogAnalyticsEvent.zzc(new FirelogAnalyticsEvent("MESSAGE_DELIVERED", intent)))));
            } catch (EncodingException unused) {
            }
        }
    }

    public static void zzb(Intent intent) {
        zza("_nd", intent);
    }

    public static void zzc(Intent intent) {
        zza("_nf", intent);
    }

    public static boolean zzd(Intent intent) {
        if (intent == null || zzn(intent)) {
            return false;
        }
        return "1".equals(intent.getStringExtra("google.c.a.e"));
    }

    public static boolean zze(Intent intent) {
        if (intent == null || zzn(intent)) {
            return false;
        }
        return zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static int zzf(Intent intent) {
        Object obj = intent.getExtras().get("google.ttl");
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (!(obj instanceof String)) {
            return 0;
        }
        try {
            return Integer.parseInt((String) obj);
        } catch (NumberFormatException unused) {
            String valueOf = String.valueOf(obj);
            StringBuilder sb = new StringBuilder(valueOf.length() + 13);
            sb.append("Invalid TTL: ");
            sb.append(valueOf);
            Log.w("FirebaseMessaging", sb.toString());
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static String zzg(Intent intent) {
        return intent.getStringExtra("collapse_key");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static String zzh(Intent intent) {
        return intent.getStringExtra("google.c.a.c_l");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static String zzi(Intent intent) {
        return intent.getStringExtra("google.c.a.m_l");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static String zzj(Intent intent) {
        String stringExtra = intent.getStringExtra(Constants.GCM_MESSAGE_ID_KEY);
        return stringExtra == null ? intent.getStringExtra("message_id") : stringExtra;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static String zzk(Intent intent) {
        return (intent.getExtras() == null || !zzn.zza(intent.getExtras())) ? "DATA_MESSAGE" : "DISPLAY_NOTIFICATION";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static String zzl(Intent intent) {
        String stringExtra = intent.getStringExtra(ADMConstants.EXTRA_FROM);
        if (stringExtra == null || !stringExtra.startsWith("/topics/")) {
            return null;
        }
        return stringExtra;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static int zzm(Intent intent) {
        String stringExtra = intent.getStringExtra("google.delivered_priority");
        if (stringExtra == null) {
            if ("1".equals(intent.getStringExtra("google.priority_reduced"))) {
                return 2;
            }
            stringExtra = intent.getStringExtra("google.priority");
        }
        if (RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_HIGH.equals(stringExtra)) {
            return 1;
        }
        return BaseTemplateProvider.NON_INVITATION_KEY.equals(stringExtra) ? 2 : 0;
    }

    private static boolean zzn(Intent intent) {
        return "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT".equals(intent.getAction());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static String zzb() {
        return FirebaseApp.getInstance().getApplicationContext().getPackageName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static String zzc() {
        return FirebaseInstanceId.getInstance(FirebaseApp.getInstance()).getId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static String zzd() {
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        String gcmSenderId = firebaseApp.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        String applicationId = firebaseApp.getOptions().getApplicationId();
        if (!applicationId.startsWith("1:")) {
            return applicationId;
        }
        String[] split = applicationId.split(":");
        if (split.length < 2) {
            return null;
        }
        String str = split[1];
        if (!str.isEmpty()) {
            return str;
        }
        return null;
    }

    public static void zza(Intent intent) {
        if (intent != null) {
            if ("1".equals(intent.getStringExtra("google.c.a.tc"))) {
                AnalyticsConnector analyticsConnector = (AnalyticsConnector) FirebaseApp.getInstance().get(AnalyticsConnector.class);
                Log.isLoggable("FirebaseMessaging", 3);
                if (analyticsConnector != null) {
                    String stringExtra = intent.getStringExtra("google.c.a.c_id");
                    analyticsConnector.setUserProperty("fcm", "_ln", stringExtra);
                    Bundle bundle = new Bundle();
                    bundle.putString("source", "Firebase");
                    bundle.putString(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_MEDIUM, "notification");
                    bundle.putString("campaign", stringExtra);
                    analyticsConnector.logEvent("fcm", "_cmp", bundle);
                } else {
                    Log.w("FirebaseMessaging", "Unable to set user property for conversion tracking:  analytics library is missing");
                }
            } else {
                Log.isLoggable("FirebaseMessaging", 3);
            }
        }
        zza("_no", intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zza() {
        ApplicationInfo applicationInfo;
        try {
            FirebaseApp.getInstance();
            Context applicationContext = FirebaseApp.getInstance().getApplicationContext();
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
            if (sharedPreferences.contains("export_to_big_query")) {
                return sharedPreferences.getBoolean("export_to_big_query", false);
            }
            try {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager != null && (applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128)) != null && applicationInfo.metaData != null && applicationInfo.metaData.containsKey("delivery_metrics_exported_to_big_query_enabled")) {
                    return applicationInfo.metaData.getBoolean("delivery_metrics_exported_to_big_query_enabled", false);
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            return false;
        } catch (IllegalStateException unused2) {
            Log.i("FirebaseMessaging", "FirebaseApp has not being initialized. Device might be in direct boot mode. Skip exporting delivery metrics to Big Query");
            return false;
        }
    }

    @VisibleForTesting
    private static void zza(String str, Intent intent) {
        Bundle bundle = new Bundle();
        String stringExtra = intent.getStringExtra("google.c.a.c_id");
        if (stringExtra != null) {
            bundle.putString("_nmid", stringExtra);
        }
        String stringExtra2 = intent.getStringExtra("google.c.a.c_l");
        if (stringExtra2 != null) {
            bundle.putString("_nmn", stringExtra2);
        }
        String stringExtra3 = intent.getStringExtra("google.c.a.m_l");
        if (!TextUtils.isEmpty(stringExtra3)) {
            bundle.putString("label", stringExtra3);
        }
        String stringExtra4 = intent.getStringExtra("google.c.a.m_c");
        if (!TextUtils.isEmpty(stringExtra4)) {
            bundle.putString("message_channel", stringExtra4);
        }
        String zzl = zzl(intent);
        if (zzl != null) {
            bundle.putString("_nt", zzl);
        }
        String stringExtra5 = intent.getStringExtra("google.c.a.ts");
        if (stringExtra5 != null) {
            try {
                bundle.putInt("_nmt", Integer.parseInt(stringExtra5));
            } catch (NumberFormatException e) {
                Log.w("FirebaseMessaging", "Error while parsing timestamp in GCM event", e);
            }
        }
        String stringExtra6 = intent.hasExtra("google.c.a.udt") ? intent.getStringExtra("google.c.a.udt") : null;
        if (stringExtra6 != null) {
            try {
                bundle.putInt("_ndt", Integer.parseInt(stringExtra6));
            } catch (NumberFormatException e2) {
                Log.w("FirebaseMessaging", "Error while parsing use_device_time in GCM event", e2);
            }
        }
        String str2 = (intent.getExtras() == null || !zzn.zza(intent.getExtras())) ? "data" : "display";
        if ("_nr".equals(str) || "_nf".equals(str)) {
            bundle.putString("_nmc", str2);
        }
        if (Log.isLoggable("FirebaseMessaging", 3)) {
            String valueOf = String.valueOf(bundle);
            StringBuilder sb = new StringBuilder(valueOf.length() + GeneratedOutlineSupport1.outline6(str, 37));
            sb.append("Logging to scion event=");
            sb.append(str);
            sb.append(" scionPayload=");
            sb.append(valueOf);
            sb.toString();
        }
        AnalyticsConnector analyticsConnector = (AnalyticsConnector) FirebaseApp.getInstance().get(AnalyticsConnector.class);
        if (analyticsConnector != null) {
            analyticsConnector.logEvent("fcm", str, bundle);
        } else {
            Log.w("FirebaseMessaging", "Unable to log event: analytics library is missing");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(boolean z) {
        FirebaseApp.getInstance().getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit().putBoolean("export_to_big_query", z).apply();
    }
}

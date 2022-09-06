package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.device.messaging.ADMConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.regex.Pattern;
/* compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
/* loaded from: classes3.dex */
public class FirebaseMessaging {
    public static final String INSTANCE_ID_SCOPE = "FCM";
    @Nullable
    @SuppressLint({"FirebaseUnknownNullness"})
    @VisibleForTesting
    static TransportFactory zza;
    private static final Pattern zzb = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
    private final Context zzc;
    private final FirebaseInstanceId zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirebaseMessaging(FirebaseApp firebaseApp, FirebaseInstanceId firebaseInstanceId, TransportFactory transportFactory) {
        this.zzc = firebaseApp.getApplicationContext();
        zza = transportFactory;
        this.zzd = firebaseInstanceId;
    }

    @NonNull
    public static synchronized FirebaseMessaging getInstance() {
        FirebaseMessaging firebaseMessaging;
        synchronized (FirebaseMessaging.class) {
            firebaseMessaging = getInstance(FirebaseApp.getInstance());
        }
        return firebaseMessaging;
    }

    @NonNull
    public boolean deliveryMetricsExportToBigQueryEnabled() {
        return zzo.zza();
    }

    public boolean isAutoInitEnabled() {
        return this.zzd.zzh();
    }

    public void send(@NonNull RemoteMessage remoteMessage) {
        if (!TextUtils.isEmpty(remoteMessage.getTo())) {
            Intent intent = new Intent("com.google.android.gcm.intent.SEND");
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            intent.putExtra(ADMConstants.LowLevel.EXTRA_APPLICATION_PENDING_INTENT, PendingIntent.getBroadcast(this.zzc, 0, intent2, 0));
            intent.setPackage("com.google.android.gms");
            intent.putExtras(remoteMessage.zza);
            this.zzc.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
            return;
        }
        throw new IllegalArgumentException("Missing 'to'");
    }

    public void setAutoInitEnabled(boolean z) {
        this.zzd.zzb(z);
    }

    public void setDeliveryMetricsExportToBigQuery(boolean z) {
        zzo.zza(z);
    }

    @NonNull
    public Task<Void> subscribeToTopic(@NonNull String str) {
        if (str != null && str.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in subscribeToTopic.");
            str = str.substring(8);
        }
        if (str == null || !zzb.matcher(str).matches()) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline30(GeneratedOutlineSupport1.outline6(str, 78), "Invalid topic name: ", str, " does not match the allowed format [a-zA-Z0-9-_.~%]{1,900}"));
        }
        return this.zzd.zza(str.length() != 0 ? "S!".concat(str) : new String("S!"));
    }

    @NonNull
    public Task<Void> unsubscribeFromTopic(@NonNull String str) {
        if (str != null && str.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in unsubscribeFromTopic.");
            str = str.substring(8);
        }
        if (str == null || !zzb.matcher(str).matches()) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline30(GeneratedOutlineSupport1.outline6(str, 78), "Invalid topic name: ", str, " does not match the allowed format [a-zA-Z0-9-_.~%]{1,900}"));
        }
        return this.zzd.zza(str.length() != 0 ? "U!".concat(str) : new String("U!"));
    }

    @NonNull
    @Keep
    static synchronized FirebaseMessaging getInstance(@NonNull FirebaseApp firebaseApp) {
        FirebaseMessaging firebaseMessaging;
        synchronized (FirebaseMessaging.class) {
            firebaseMessaging = (FirebaseMessaging) firebaseApp.get(FirebaseMessaging.class);
        }
        return firebaseMessaging;
    }
}

package com.google.firebase.iid;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.device.messaging.ADMConstants;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
public final class zzl {
    private final FirebaseApp zza;
    private final zzai zzb;
    private final zzao zzc;
    private final Executor zzd;
    private final UserAgentPublisher zze;
    private final HeartBeatInfo zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(FirebaseApp firebaseApp, zzai zzaiVar, Executor executor, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo) {
        this(firebaseApp, zzaiVar, executor, new zzao(firebaseApp.getApplicationContext(), zzaiVar), userAgentPublisher, heartBeatInfo);
    }

    public final Task<String> zza(String str, String str2, String str3) {
        return zzb(zza(str, str2, str3, new Bundle()));
    }

    public final Task<Void> zzb(String str, String str2, String str3) {
        return zza(zzb(zza(str, str2, str3, GeneratedOutlineSupport1.outline11("delete", "1"))));
    }

    public final Task<Void> zzc(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String valueOf = String.valueOf(str3);
        bundle.putString("gcm.topic", valueOf.length() != 0 ? "/topics/".concat(valueOf) : new String("/topics/"));
        String valueOf2 = String.valueOf(str3);
        return zza(zzb(zza(str, str2, valueOf2.length() != 0 ? "/topics/".concat(valueOf2) : new String("/topics/"), bundle)));
    }

    public final Task<Void> zzd(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String valueOf = String.valueOf(str3);
        bundle.putString("gcm.topic", valueOf.length() != 0 ? "/topics/".concat(valueOf) : new String("/topics/"));
        bundle.putString("delete", "1");
        String valueOf2 = String.valueOf(str3);
        return zza(zzb(zza(str, str2, valueOf2.length() != 0 ? "/topics/".concat(valueOf2) : new String("/topics/"), bundle)));
    }

    @VisibleForTesting
    private zzl(FirebaseApp firebaseApp, zzai zzaiVar, Executor executor, zzao zzaoVar, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo) {
        this.zza = firebaseApp;
        this.zzb = zzaiVar;
        this.zzc = zzaoVar;
        this.zzd = executor;
        this.zze = userAgentPublisher;
        this.zzf = heartBeatInfo;
    }

    private final Task<String> zzb(Task<Bundle> task) {
        return task.continueWith(this.zzd, new zzp(this));
    }

    public final Task<Void> zza(String str) {
        return zza(zzb(zza(str, "*", "*", GeneratedOutlineSupport1.outline12("iid-operation", "delete", "delete", "1"))));
    }

    private final Task<Bundle> zza(String str, String str2, String str3, final Bundle bundle) {
        bundle.putString(AuthorizationResponseParser.SCOPE, str3);
        bundle.putString("sender", str2);
        bundle.putString("subtype", str2);
        bundle.putString("appid", str);
        bundle.putString("gmp_app_id", this.zza.getOptions().getApplicationId());
        bundle.putString("gmsv", Integer.toString(this.zzb.zzd()));
        bundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
        bundle.putString("app_ver", this.zzb.zzb());
        bundle.putString("app_ver_name", this.zzb.zzc());
        String version = LibraryVersion.getInstance().getVersion("firebase-iid");
        if ("UNKNOWN".equals(version)) {
            version = GeneratedOutlineSupport1.outline27(19, "unknown_", GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        }
        String valueOf = String.valueOf(version);
        bundle.putString("cliv", valueOf.length() != 0 ? "fiid-".concat(valueOf) : new String("fiid-"));
        HeartBeatInfo.HeartBeat heartBeatCode = this.zzf.getHeartBeatCode("fire-iid");
        if (heartBeatCode != HeartBeatInfo.HeartBeat.NONE) {
            bundle.putString("Firebase-Client-Log-Type", Integer.toString(heartBeatCode.getCode()));
            bundle.putString("Firebase-Client", this.zze.getUserAgent());
        }
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzd.execute(new Runnable(this, bundle, taskCompletionSource) { // from class: com.google.firebase.iid.zzn
            private final zzl zza;
            private final Bundle zzb;
            private final TaskCompletionSource zzc;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
                this.zzb = bundle;
                this.zzc = taskCompletionSource;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(this.zzb, this.zzc);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String zza(Bundle bundle) throws IOException {
        if (bundle != null) {
            String string = bundle.getString(ADMConstants.LowLevel.EXTRA_REGISTRATION_ID);
            if (string != null) {
                return string;
            }
            String string2 = bundle.getString(ADMConstants.LowLevel.EXTRA_UNREGISTERED);
            if (string2 != null) {
                return string2;
            }
            String string3 = bundle.getString("error");
            if ("RST".equals(string3)) {
                throw new IOException("INSTANCE_ID_RESET");
            }
            if (string3 != null) {
                throw new IOException(string3);
            }
            String valueOf = String.valueOf(bundle);
            Log.w("FirebaseInstanceId", GeneratedOutlineSupport1.outline29(valueOf.length() + 21, "Unexpected response: ", valueOf), new Throwable());
            throw new IOException(ADMConstants.ERROR_SERVICE_NOT_AVAILABLE);
        }
        throw new IOException(ADMConstants.ERROR_SERVICE_NOT_AVAILABLE);
    }

    private final <T> Task<Void> zza(Task<T> task) {
        return task.continueWith(zza.zza(), new zzm(this));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Bundle bundle, TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(this.zzc.zza(bundle));
        } catch (IOException e) {
            taskCompletionSource.setException(e);
        }
    }
}

package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.device.messaging.ADMConstants;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.zzf;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.concurrent.GuardedBy;
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
final class zzao {
    private static int zza;
    private static PendingIntent zzb;
    private final Context zzd;
    private final zzai zze;
    private Messenger zzg;
    private zzf zzh;
    @GuardedBy("responseCallbacks")
    private final SimpleArrayMap<String, TaskCompletionSource<Bundle>> zzc = new SimpleArrayMap<>();
    private Messenger zzf = new Messenger(new zzar(this, Looper.getMainLooper()));

    public zzao(Context context, zzai zzaiVar) {
        this.zzd = context;
        this.zze = zzaiVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(Message message) {
        if (message != null) {
            Object obj = message.obj;
            if (obj instanceof Intent) {
                Intent intent = (Intent) obj;
                intent.setExtrasClassLoader(new zzf.zza());
                if (intent.hasExtra("google.messenger")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                    if (parcelableExtra instanceof zzf) {
                        this.zzh = (zzf) parcelableExtra;
                    }
                    if (parcelableExtra instanceof Messenger) {
                        this.zzg = (Messenger) parcelableExtra;
                    }
                }
                Intent intent2 = (Intent) message.obj;
                String action = intent2.getAction();
                if (!"com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
                    if (!Log.isLoggable("FirebaseInstanceId", 3)) {
                        return;
                    }
                    String valueOf = String.valueOf(action);
                    if (valueOf.length() != 0) {
                        "Unexpected response action: ".concat(valueOf);
                        return;
                    } else {
                        new String("Unexpected response action: ");
                        return;
                    }
                }
                String stringExtra = intent2.getStringExtra(ADMConstants.LowLevel.EXTRA_REGISTRATION_ID);
                if (stringExtra == null) {
                    stringExtra = intent2.getStringExtra(ADMConstants.LowLevel.EXTRA_UNREGISTERED);
                }
                if (stringExtra == null) {
                    String stringExtra2 = intent2.getStringExtra("error");
                    if (stringExtra2 == null) {
                        String valueOf2 = String.valueOf(intent2.getExtras());
                        StringBuilder sb = new StringBuilder(valueOf2.length() + 49);
                        sb.append("Unexpected response, no error or registration id ");
                        sb.append(valueOf2);
                        Log.w("FirebaseInstanceId", sb.toString());
                        return;
                    }
                    if (Log.isLoggable("FirebaseInstanceId", 3)) {
                        if (stringExtra2.length() != 0) {
                            "Received InstanceID error ".concat(stringExtra2);
                        } else {
                            new String("Received InstanceID error ");
                        }
                    }
                    if (stringExtra2.startsWith(AccessoryMetricsConstants.DELIMITER)) {
                        String[] split = stringExtra2.split("\\|");
                        if (split.length > 2 && "ID".equals(split[1])) {
                            String str = split[2];
                            String str2 = split[3];
                            if (str2.startsWith(":")) {
                                str2 = str2.substring(1);
                            }
                            zza(str, intent2.putExtra("error", str2).getExtras());
                            return;
                        }
                        Log.w("FirebaseInstanceId", stringExtra2.length() != 0 ? "Unexpected structured response ".concat(stringExtra2) : new String("Unexpected structured response "));
                        return;
                    }
                    synchronized (this.zzc) {
                        for (int i = 0; i < this.zzc.size(); i++) {
                            zza(this.zzc.keyAt(i), intent2.getExtras());
                        }
                    }
                    return;
                }
                Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
                if (!matcher.matches()) {
                    if (!Log.isLoggable("FirebaseInstanceId", 3)) {
                        return;
                    }
                    if (stringExtra.length() != 0) {
                        "Unexpected response string: ".concat(stringExtra);
                        return;
                    } else {
                        new String("Unexpected response string: ");
                        return;
                    }
                }
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                Bundle extras = intent2.getExtras();
                extras.putString(ADMConstants.LowLevel.EXTRA_REGISTRATION_ID, group2);
                zza(group, extras);
                return;
            }
        }
        Log.w("FirebaseInstanceId", "Dropping invalid message");
    }

    private final Bundle zzb(Bundle bundle) throws IOException {
        Bundle zzc = zzc(bundle);
        if (zzc == null || !zzc.containsKey("google.messenger")) {
            return zzc;
        }
        Bundle zzc2 = zzc(bundle);
        if (zzc2 != null && zzc2.containsKey("google.messenger")) {
            return null;
        }
        return zzc2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0086, code lost:
        if (r7.zzh != null) goto L53;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.google.android.gms.tasks.TaskCompletionSource, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00ab -> B:59:0x00b6). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x00b1 -> B:59:0x00b6). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final android.os.Bundle zzc(android.os.Bundle r8) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzao.zzc(android.os.Bundle):android.os.Bundle");
    }

    private static synchronized void zza(Context context, Intent intent) {
        synchronized (zzao.class) {
            if (zzb == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                zzb = PendingIntent.getBroadcast(context, 0, intent2, 0);
            }
            intent.putExtra(ADMConstants.LowLevel.EXTRA_APPLICATION_PENDING_INTENT, zzb);
        }
    }

    private final void zza(String str, Bundle bundle) {
        synchronized (this.zzc) {
            TaskCompletionSource<Bundle> remove = this.zzc.remove(str);
            if (remove == null) {
                String valueOf = String.valueOf(str);
                Log.w("FirebaseInstanceId", valueOf.length() != 0 ? "Missing callback for ".concat(valueOf) : new String("Missing callback for "));
                return;
            }
            remove.setResult(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Bundle zza(Bundle bundle) throws IOException {
        if (this.zze.zzd() >= 12000000) {
            try {
                return (Bundle) Tasks.await(zzv.zza(this.zzd).zzb(1, bundle));
            } catch (InterruptedException | ExecutionException e) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String valueOf = String.valueOf(e);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 22);
                    sb.append("Error making request: ");
                    sb.append(valueOf);
                    sb.toString();
                }
                if ((e.getCause() instanceof zzag) && ((zzag) e.getCause()).zza() == 4) {
                    return zzb(bundle);
                }
                return null;
            }
        }
        return zzb(bundle);
    }

    private static synchronized String zza() {
        String num;
        synchronized (zzao.class) {
            int i = zza;
            zza = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }
}

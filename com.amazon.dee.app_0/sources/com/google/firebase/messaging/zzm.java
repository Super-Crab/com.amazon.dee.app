package com.google.firebase.messaging;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
/* compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
/* loaded from: classes3.dex */
final class zzm implements Closeable {
    private final URL zza;
    @Nullable
    private Task<Bitmap> zzb;
    @Nullable
    private volatile InputStream zzc;

    private zzm(URL url) {
        this.zza = url;
    }

    @Nullable
    public static zzm zza(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new zzm(new URL(str));
        } catch (MalformedURLException unused) {
            String valueOf = String.valueOf(str);
            Log.w("FirebaseMessaging", valueOf.length() != 0 ? "Not downloading image, bad URL: ".concat(valueOf) : new String("Not downloading image, bad URL: "));
            return null;
        }
    }

    private final byte[] zzc() throws IOException {
        URLConnection openConnection = this.zza.openConnection();
        if (openConnection.getContentLength() <= 1048576) {
            InputStream inputStream = openConnection.getInputStream();
            try {
                this.zzc = inputStream;
                byte[] zza = com.google.android.gms.internal.firebase_messaging.zzj.zza(com.google.android.gms.internal.firebase_messaging.zzj.zza(inputStream, 1048577L));
                inputStream.close();
                if (Log.isLoggable("FirebaseMessaging", 2)) {
                    int length = zza.length;
                    String valueOf = String.valueOf(this.zza);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 34);
                    sb.append("Downloaded ");
                    sb.append(length);
                    sb.append(" bytes from ");
                    sb.append(valueOf);
                    sb.toString();
                }
                if (zza.length > 1048576) {
                    throw new IOException("Image exceeds max size of 1048576");
                }
                return zza;
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th2) {
                        com.google.android.gms.internal.firebase_messaging.zzm.zza(th, th2);
                    }
                }
                throw th;
            }
        }
        throw new IOException("Content-Length exceeds max size of 1048576");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        try {
            com.google.android.gms.internal.firebase_messaging.zzk.zza(this.zzc);
        } catch (NullPointerException e) {
            Log.e("FirebaseMessaging", "Failed to close the image download stream.", e);
        }
    }

    public final Bitmap zzb() throws IOException {
        String valueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(valueOf.length() + 22);
        sb.append("Starting download of: ");
        sb.append(valueOf);
        Log.i("FirebaseMessaging", sb.toString());
        byte[] zzc = zzc();
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(zzc, 0, zzc.length);
        if (decodeByteArray != null) {
            if (Log.isLoggable("FirebaseMessaging", 3)) {
                String valueOf2 = String.valueOf(this.zza);
                StringBuilder sb2 = new StringBuilder(valueOf2.length() + 31);
                sb2.append("Successfully downloaded image: ");
                sb2.append(valueOf2);
                sb2.toString();
            }
            return decodeByteArray;
        }
        String valueOf3 = String.valueOf(this.zza);
        throw new IOException(GeneratedOutlineSupport1.outline29(valueOf3.length() + 24, "Failed to decode image: ", valueOf3));
    }

    public final void zza(Executor executor) {
        this.zzb = Tasks.call(executor, new Callable(this) { // from class: com.google.firebase.messaging.zzl
            private final zzm zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zza.zzb();
            }
        });
    }

    public final Task<Bitmap> zza() {
        return (Task) Preconditions.checkNotNull(this.zzb);
    }
}

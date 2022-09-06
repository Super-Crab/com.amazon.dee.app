package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;
import com.amazon.whispercloak.KeyUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
public final class zzq {
    @Nullable
    private final zzs zzc(Context context, String str) throws zzt {
        zzs zzd;
        try {
            zzd = zzd(context, str);
        } catch (zzt e) {
            e = e;
        }
        if (zzd != null) {
            zza(context, str, zzd);
            return zzd;
        }
        e = null;
        try {
            zzs zza = zza(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
            if (zza != null) {
                zza(context, str, zza, false);
                return zza;
            }
        } catch (zzt e2) {
            e = e2;
        }
        if (e != null) {
            throw e;
        }
        return null;
    }

    @Nullable
    private final zzs zzd(Context context, String str) throws zzt {
        File zze = zze(context, str);
        if (!zze.exists()) {
            return null;
        }
        try {
            return zza(zze);
        } catch (zzt | IOException e) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(valueOf.length() + 39);
                sb.append("Failed to read ID from file, retrying: ");
                sb.append(valueOf);
                sb.toString();
            }
            try {
                return zza(zze);
            } catch (IOException e2) {
                String valueOf2 = String.valueOf(e2);
                StringBuilder sb2 = new StringBuilder(valueOf2.length() + 45);
                sb2.append("IID file exists, but failed to read from it: ");
                sb2.append(valueOf2);
                Log.w("FirebaseInstanceId", sb2.toString());
                throw new zzt(e2);
            }
        }
    }

    private static File zze(Context context, String str) {
        String sb;
        if (TextUtils.isEmpty(str)) {
            sb = "com.google.InstanceId.properties";
        } else {
            try {
                String encodeToString = Base64.encodeToString(str.getBytes("UTF-8"), 11);
                StringBuilder sb2 = new StringBuilder(String.valueOf(encodeToString).length() + 33);
                sb2.append("com.google.InstanceId_");
                sb2.append(encodeToString);
                sb2.append(".properties");
                sb = sb2.toString();
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return new File(zzb(context), sb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final zzs zza(Context context, String str) throws zzt {
        zzs zzc = zzc(context, str);
        return zzc != null ? zzc : zzb(context, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final zzs zzb(Context context, String str) {
        zzs zzsVar = new zzs(zzai.zza(zzb.zza().getPublic()), System.currentTimeMillis());
        zzs zza = zza(context, str, zzsVar, true);
        if (zza != null && !zza.equals(zzsVar)) {
            Log.isLoggable("FirebaseInstanceId", 3);
            return zza;
        }
        Log.isLoggable("FirebaseInstanceId", 3);
        zza(context, str, zzsVar);
        return zzsVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Context context) {
        File[] listFiles;
        for (File file : zzb(context).listFiles()) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    private static PublicKey zza(String str) throws zzt {
        try {
            try {
                return KeyFactory.getInstance(KeyUtils.ALGORITHM_RSA).generatePublic(new X509EncodedKeySpec(Base64.decode(str, 8)));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(valueOf.length() + 19);
                sb.append("Invalid key stored ");
                sb.append(valueOf);
                Log.w("FirebaseInstanceId", sb.toString());
                throw new zzt(e);
            }
        } catch (IllegalArgumentException e2) {
            throw new zzt(e2);
        }
    }

    private static File zzb(Context context) {
        File noBackupFilesDir = ContextCompat.getNoBackupFilesDir(context);
        if (noBackupFilesDir == null || !noBackupFilesDir.isDirectory()) {
            Log.w("FirebaseInstanceId", "noBackupFilesDir doesn't exist, using regular files directory instead");
            return context.getFilesDir();
        }
        return noBackupFilesDir;
    }

    private static long zzb(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(zzat.zza(str, "cre"), null);
        if (string != null) {
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException unused) {
                return 0L;
            }
        }
        return 0L;
    }

    @Nullable
    private final zzs zza(Context context, String str, zzs zzsVar, boolean z) {
        long j;
        Log.isLoggable("FirebaseInstanceId", 3);
        Properties properties = new Properties();
        properties.setProperty("id", zzsVar.zza());
        j = zzsVar.zzb;
        properties.setProperty("cre", String.valueOf(j));
        File zze = zze(context, str);
        try {
            zze.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(zze, "rw");
            FileChannel channel = randomAccessFile.getChannel();
            channel.lock();
            if (z && channel.size() > 0) {
                try {
                    channel.position(0L);
                    zzs zza = zza(channel);
                    channel.close();
                    randomAccessFile.close();
                    return zza;
                } catch (zzt | IOException e) {
                    if (Log.isLoggable("FirebaseInstanceId", 3)) {
                        String valueOf = String.valueOf(e);
                        StringBuilder sb = new StringBuilder(valueOf.length() + 58);
                        sb.append("Tried reading ID before writing new one, but failed with: ");
                        sb.append(valueOf);
                        sb.toString();
                    }
                }
            }
            channel.truncate(0L);
            properties.store(Channels.newOutputStream(channel), (String) null);
            channel.close();
            randomAccessFile.close();
            return zzsVar;
        } catch (IOException e2) {
            String valueOf2 = String.valueOf(e2);
            StringBuilder sb2 = new StringBuilder(valueOf2.length() + 21);
            sb2.append("Failed to write key: ");
            sb2.append(valueOf2);
            Log.w("FirebaseInstanceId", sb2.toString());
            return null;
        }
    }

    private final zzs zza(File file) throws zzt, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileChannel channel = fileInputStream.getChannel();
            channel.lock(0L, Long.MAX_VALUE, true);
            zzs zza = zza(channel);
            channel.close();
            fileInputStream.close();
            return zza;
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                com.google.android.gms.internal.firebase_messaging.zzm.zza(th, th2);
            }
            throw th;
        }
    }

    private static zzs zza(FileChannel fileChannel) throws zzt, IOException {
        Properties properties = new Properties();
        properties.load(Channels.newInputStream(fileChannel));
        try {
            long parseLong = Long.parseLong(properties.getProperty("cre"));
            String property = properties.getProperty("id");
            if (property == null) {
                String property2 = properties.getProperty("pub");
                if (property2 != null) {
                    property = zzai.zza(zza(property2));
                } else {
                    throw new zzt("Invalid properties file");
                }
            }
            return new zzs(property, parseLong);
        } catch (NumberFormatException e) {
            throw new zzt(e);
        }
    }

    @Nullable
    private static zzs zza(SharedPreferences sharedPreferences, String str) throws zzt {
        long zzb = zzb(sharedPreferences, str);
        String string = sharedPreferences.getString(zzat.zza(str, "id"), null);
        if (string == null) {
            String string2 = sharedPreferences.getString(zzat.zza(str, "|P|"), null);
            if (string2 == null) {
                return null;
            }
            string = zzai.zza(zza(string2));
        }
        return new zzs(string, zzb);
    }

    private final void zza(Context context, String str, zzs zzsVar) {
        long j;
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (zzsVar.equals(zza(sharedPreferences, str))) {
                return;
            }
        } catch (zzt unused) {
        }
        Log.isLoggable("FirebaseInstanceId", 3);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(zzat.zza(str, "id"), zzsVar.zza());
        String zza = zzat.zza(str, "cre");
        j = zzsVar.zzb;
        edit.putString(zza, String.valueOf(j));
        edit.commit();
    }
}

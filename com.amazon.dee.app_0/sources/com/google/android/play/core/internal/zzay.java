package com.google.android.play.core.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;
import androidx.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzay {
    private final com.google.android.play.core.splitcompat.zze zza;
    private final zzau zzb;
    private final Context zzc;
    private final zzax zzd;
    @Nullable
    private PackageInfo zze;

    public zzay(Context context, com.google.android.play.core.splitcompat.zze zzeVar, zzau zzauVar) {
        zzax zzaxVar = new zzax(new com.google.android.play.core.splitcompat.zza(zzeVar));
        this.zza = zzeVar;
        this.zzb = zzauVar;
        this.zzc = context;
        this.zzd = zzaxVar;
    }

    @Nullable
    private final PackageInfo zzd() {
        if (this.zze == null) {
            try {
                this.zze = this.zzc.getPackageManager().getPackageInfo(this.zzc.getPackageName(), 64);
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }
        return this.zze;
    }

    @Nullable
    private static X509Certificate zze(Signature signature) {
        try {
            return (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(signature.toByteArray()));
        } catch (CertificateException e) {
            Log.e("SplitCompat", "Cannot decode certificate.", e);
            return null;
        }
    }

    public final boolean zza(File[] fileArr) throws IOException, XmlPullParserException {
        long j;
        PackageInfo zzd = zzd();
        if (Build.VERSION.SDK_INT >= 28) {
            j = zzd.getLongVersionCode();
        } else {
            j = zzd.versionCode;
        }
        AssetManager assetManager = (AssetManager) zzbw.zzc(AssetManager.class);
        int length = fileArr.length;
        do {
            length--;
            if (length < 0) {
                return true;
            }
            this.zzd.zzb(assetManager, fileArr[length]);
        } while (j == this.zzd.zza());
        return false;
    }

    public final boolean zzb(List list) throws IOException {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            if (!this.zza.zzg(((Intent) it2.next()).getStringExtra("split_id")).exists()) {
                return false;
            }
        }
        return true;
    }

    public final boolean zzc(File[] fileArr) {
        PackageInfo zzd = zzd();
        ArrayList<X509Certificate> arrayList = null;
        if (zzd != null && zzd.signatures != null) {
            arrayList = new ArrayList();
            for (Signature signature : zzd.signatures) {
                X509Certificate zze = zze(signature);
                if (zze != null) {
                    arrayList.add(zze);
                }
            }
        }
        if (arrayList != null && !arrayList.isEmpty()) {
            int length = fileArr.length;
            loop1: while (true) {
                length--;
                if (length < 0) {
                    return true;
                }
                try {
                    String absolutePath = fileArr[length].getAbsolutePath();
                    try {
                        X509Certificate[][] zza = zzi.zza(absolutePath);
                        if (zza == null || zza.length == 0 || zza[0].length == 0) {
                            break;
                        } else if (arrayList.isEmpty()) {
                            Log.e("SplitCompat", "No certificates found for app.");
                            break;
                        } else {
                            for (X509Certificate x509Certificate : arrayList) {
                                for (X509Certificate[] x509CertificateArr : zza) {
                                    if (!x509CertificateArr[0].equals(x509Certificate)) {
                                    }
                                }
                                Log.i("SplitCompat", "There's an app certificate that doesn't sign the split.");
                            }
                        }
                    } catch (Exception e) {
                        StringBuilder sb = new StringBuilder(String.valueOf(absolutePath).length() + 32);
                        sb.append("Downloaded split ");
                        sb.append(absolutePath);
                        sb.append(" is not signed.");
                        Log.e("SplitCompat", sb.toString(), e);
                    }
                } catch (Exception e2) {
                    Log.e("SplitCompat", "Split verification error.", e2);
                    return false;
                }
            }
        } else {
            Log.e("SplitCompat", "No app certificates found.");
            return false;
        }
        Log.e("SplitCompat", "Split verification failure.");
        return false;
    }
}

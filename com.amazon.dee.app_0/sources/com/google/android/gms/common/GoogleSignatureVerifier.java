package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.wrappers.Wrappers;
import javax.annotation.CheckReturnValue;
@ShowFirstParty
@KeepForSdk
@CheckReturnValue
/* loaded from: classes2.dex */
public class GoogleSignatureVerifier {
    private static GoogleSignatureVerifier zzam;
    private final Context mContext;
    private volatile String zzan;

    private GoogleSignatureVerifier(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @KeepForSdk
    public static GoogleSignatureVerifier getInstance(Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (zzam == null) {
                zzc.zza(context);
                zzam = new GoogleSignatureVerifier(context);
            }
        }
        return zzam;
    }

    public static boolean zza(PackageInfo packageInfo, boolean z) {
        if (packageInfo != null && packageInfo.signatures != null) {
            if ((z ? zza(packageInfo, zzh.zzx) : zza(packageInfo, zzh.zzx[0])) != null) {
                return true;
            }
        }
        return false;
    }

    private final zzm zzc(String str) {
        zzm zzb;
        ApplicationInfo applicationInfo;
        if (str == null) {
            return zzm.zzb("null pkg");
        }
        if (str.equals(this.zzan)) {
            return zzm.zze();
        }
        try {
            PackageInfo packageInfo = Wrappers.packageManager(this.mContext).getPackageInfo(str, 64);
            boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
            if (packageInfo == null) {
                zzb = zzm.zzb("null pkg");
            } else {
                Signature[] signatureArr = packageInfo.signatures;
                if (signatureArr.length != 1) {
                    zzb = zzm.zzb("single cert required");
                } else {
                    zzf zzfVar = new zzf(signatureArr[0].toByteArray());
                    String str2 = packageInfo.packageName;
                    zzm zza = zzc.zza(str2, zzfVar, honorsDebugCertificates, false);
                    zzb = (!zza.zzad || (applicationInfo = packageInfo.applicationInfo) == null || (applicationInfo.flags & 2) == 0 || !zzc.zza(str2, zzfVar, false, true).zzad) ? zza : zzm.zzb("debuggable release cert app rejected");
                }
            }
            if (zzb.zzad) {
                this.zzan = str;
            }
            return zzb;
        } catch (PackageManager.NameNotFoundException unused) {
            return zzm.zzb(str.length() != 0 ? "no pkg ".concat(str) : new String("no pkg "));
        }
    }

    @KeepForSdk
    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zza(packageInfo, false)) {
            return true;
        }
        if (zza(packageInfo, true)) {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
        return false;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPackageGoogleSigned(String str) {
        zzm zzc = zzc(str);
        zzc.zzf();
        return zzc.zzad;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isUidGoogleSigned(int i) {
        zzm zzb;
        String[] packagesForUid = Wrappers.packageManager(this.mContext).getPackagesForUid(i);
        if (packagesForUid == null || packagesForUid.length == 0) {
            zzb = zzm.zzb("no pkgs");
        } else {
            zzb = null;
            for (String str : packagesForUid) {
                zzb = zza(str, i);
                if (zzb.zzad) {
                    break;
                }
            }
        }
        zzb.zzf();
        return zzb.zzad;
    }

    private final zzm zza(String str, int i) {
        ApplicationInfo applicationInfo;
        try {
            PackageInfo zza = Wrappers.packageManager(this.mContext).zza(str, 64, i);
            boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
            if (zza == null) {
                return zzm.zzb("null pkg");
            }
            Signature[] signatureArr = zza.signatures;
            if (signatureArr.length != 1) {
                return zzm.zzb("single cert required");
            }
            zzf zzfVar = new zzf(signatureArr[0].toByteArray());
            String str2 = zza.packageName;
            zzm zza2 = zzc.zza(str2, zzfVar, honorsDebugCertificates, false);
            return (!zza2.zzad || (applicationInfo = zza.applicationInfo) == null || (applicationInfo.flags & 2) == 0 || !zzc.zza(str2, zzfVar, false, true).zzad) ? zza2 : zzm.zzb("debuggable release cert app rejected");
        } catch (PackageManager.NameNotFoundException unused) {
            String valueOf = String.valueOf(str);
            return zzm.zzb(valueOf.length() != 0 ? "no pkg ".concat(valueOf) : new String("no pkg "));
        }
    }

    private static zze zza(PackageInfo packageInfo, zze... zzeVarArr) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null) {
            return null;
        }
        if (signatureArr.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzf zzfVar = new zzf(signatureArr[0].toByteArray());
        for (int i = 0; i < zzeVarArr.length; i++) {
            if (zzeVarArr[i].equals(zzfVar)) {
                return zzeVarArr[i];
            }
        }
        return null;
    }
}

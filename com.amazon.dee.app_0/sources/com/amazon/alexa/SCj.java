package com.amazon.alexa;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: TargetEvaluator.java */
@Singleton
/* loaded from: classes.dex */
public final class SCj {
    public static final String zZm = "SCj";
    public final PackageManager BIo;
    public final Dtt zQM;
    public final Context zyO;

    @Inject
    public SCj(PackageManager packageManager, Dtt dtt, Context context) {
        this.BIo = packageManager;
        this.zQM = dtt;
        this.zyO = context;
    }

    @NonNull
    public final Intent BIo(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setFlags(268435456);
        intent.addFlags(2097152);
        intent.addFlags(67108864);
        return intent;
    }

    public Afe zZm(pUc puc) {
        boolean z;
        SmX smX;
        Intent BIo;
        Intent BIo2;
        AAX aax = (AAX) puc;
        String str = aax.Qle;
        boolean z2 = false;
        if (str == null) {
            smX = SmX.UNKNOWN;
        } else {
            try {
                this.BIo.getPackageInfo(str, 0);
                z = true;
            } catch (PackageManager.NameNotFoundException unused) {
                GeneratedOutlineSupport1.outline179(C0179Pya.zZm("package not installed:"), aax.Qle, zZm);
                z = false;
            }
            if (z) {
                smX = SmX.INSTALLED;
            } else {
                smX = SmX.NOT_INSTALLED;
            }
        }
        String str2 = aax.Qle;
        String value = aax.BIo.getValue();
        int ordinal = aax.zQM.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            BIo = BIo(value);
            if (str2 != null) {
                Log.i(zZm, "set intent package name: " + str2);
                BIo.setPackage(str2);
            } else {
                Log.i(zZm, "no package name specified.");
            }
        } else if (ordinal == 2) {
            BIo = this.BIo.getLaunchIntentForPackage(value);
        } else if (ordinal != 3) {
            String str3 = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("some IdentifierType is not handled: ");
            zZm2.append(aax.zQM);
            Log.wtf(str3, zZm2.toString());
            BIo = null;
        } else {
            BIo = zZm(value);
            if (str2 != null && BIo != null && BIo.getPackage() == null) {
                Log.i(zZm, "add packageName to the parsed intent.");
                BIo.setPackage(str2);
            }
        }
        String str4 = aax.Qle;
        String value2 = aax.BIo.getValue();
        int ordinal2 = aax.zQM.ordinal();
        if (ordinal2 == 0 || ordinal2 == 1) {
            GhS ghS = aax.JTe;
            if (ghS != null && Boolean.TRUE.equals(((tIB) ghS).zZm)) {
                Log.i(zZm, "isMandatoryToLaunchTarget is true. no fallback intent");
                BIo2 = null;
            } else {
                Log.i(zZm, "set fallback intent");
                BIo2 = BIo(value2);
            }
        } else {
            if (ordinal2 == 3) {
                GhS ghS2 = aax.JTe;
                if (ghS2 != null && Boolean.TRUE.equals(((tIB) ghS2).zZm)) {
                    Log.i(zZm, "isMandatoryToLaunchTarget is true. no fallback intent");
                } else {
                    BIo2 = zZm(value2);
                    if (str4 != null && BIo2 != null) {
                        Log.i(zZm, "set package to null for fallback intent");
                        BIo2.setPackage(null);
                    }
                }
            }
            BIo2 = null;
        }
        ArrayList arrayList = new ArrayList();
        if (!zZm(BIo)) {
            if (zZm(BIo2)) {
                BIo = BIo2;
                z2 = true;
            } else {
                BIo = null;
            }
            if (SmX.INSTALLED.equals(smX)) {
                Log.i(zZm, "app exists, but identifier cannot be launched in that app.");
                arrayList.add(IJL.INCOMPAT_VERSION_INSTALLED);
            } else if (SmX.NOT_INSTALLED.equals(smX)) {
                Log.i(zZm, "app not installed.");
                arrayList.add(IJL.NOT_INSTALLED);
            } else {
                Log.i(zZm, "app not specified.");
                arrayList.add(IJL.NOT_INSTALLED);
            }
        }
        return new Aud(BIo, z2, new ArrayList(arrayList));
    }

    @Nullable
    public final Intent zZm(String str) {
        try {
            return Intent.parseUri(str, 1);
        } catch (URISyntaxException unused) {
            Log.e(zZm, "error parsing intent");
            return null;
        }
    }

    @VisibleForTesting
    public boolean zZm(@Nullable Intent intent) {
        ActivityInfo activityInfo;
        if (intent == null) {
            return false;
        }
        ResolveInfo resolveActivity = this.BIo.resolveActivity(intent, intent.getComponent() != null ? 0 : 65536);
        if (resolveActivity != null && (activityInfo = resolveActivity.activityInfo) != null) {
            String str = activityInfo.permission;
            if (str == null) {
                return true;
            }
            boolean z = this.zQM.zZm(this.zyO, str) == 0;
            String.format("permission: (%s) is granted: %b", str, Boolean.valueOf(z));
            return z;
        }
        Log.i(zZm, "No activity available to handle the intent.");
        return false;
    }
}

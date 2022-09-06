package com.bumptech.glide.signature;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.load.Key;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes9.dex */
public final class ApplicationVersionSignature {
    private static final ConcurrentMap<String, Key> PACKAGE_NAME_TO_KEY = new ConcurrentHashMap();
    private static final String TAG = "AppVersionSignature";

    private ApplicationVersionSignature() {
    }

    @Nullable
    private static PackageInfo getPackageInfo(@NonNull Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot resolve info for");
            outline107.append(context.getPackageName());
            Log.e(TAG, outline107.toString(), e);
            return null;
        }
    }

    @NonNull
    private static String getVersionCode(@Nullable PackageInfo packageInfo) {
        if (packageInfo != null) {
            return String.valueOf(packageInfo.versionCode);
        }
        return UUID.randomUUID().toString();
    }

    @NonNull
    public static Key obtain(@NonNull Context context) {
        String packageName = context.getPackageName();
        Key key = PACKAGE_NAME_TO_KEY.get(packageName);
        if (key == null) {
            Key obtainVersionSignature = obtainVersionSignature(context);
            Key putIfAbsent = PACKAGE_NAME_TO_KEY.putIfAbsent(packageName, obtainVersionSignature);
            return putIfAbsent == null ? obtainVersionSignature : putIfAbsent;
        }
        return key;
    }

    @NonNull
    private static Key obtainVersionSignature(@NonNull Context context) {
        return new ObjectKey(getVersionCode(getPackageInfo(context)));
    }

    @VisibleForTesting
    static void reset() {
        PACKAGE_NAME_TO_KEY.clear();
    }
}

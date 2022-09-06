package com.amazon.alexa;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: ApplicationMetadata.java */
@Singleton
/* loaded from: classes.dex */
public class jZN {
    public static final String zZm = "jZN";
    public final Bundle BIo;

    @Inject
    public jZN(Context context, PackageManager packageManager) {
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                this.BIo = applicationInfo.metaData;
                return;
            }
            throw new RuntimeException("Got null applicationInfo from package manager.");
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Unable to retrieve metadata from manifest", e);
        }
    }
}

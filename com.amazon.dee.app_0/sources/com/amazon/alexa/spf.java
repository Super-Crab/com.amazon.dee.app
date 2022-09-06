package com.amazon.alexa;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.drive.navigation.MappingApplication;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: PreferredNavigationAppSettingRetriever.java */
@Singleton
/* loaded from: classes.dex */
public class spf {
    public static final Uri BIo = Uri.parse("geo:37.423156,-122.084917");
    @VisibleForTesting
    public static final Intent zQM = new Intent("android.intent.action.VIEW", BIo);
    public static final String zZm = "spf";
    public final Lazy<PersistentStorage> jiA;
    public final PackageManager zyO;

    @Inject
    public spf(PackageManager packageManager, @Named("navigation_store") Lazy<PersistentStorage> lazy) {
        this.zyO = packageManager;
        this.jiA = lazy;
    }

    public final void zZm() {
        this.jiA.mo358get().edit().set("preferred_nav_app", MappingApplication.GOOGLE_MAPS_PACKAGENAME).commitAsynchronously();
    }
}

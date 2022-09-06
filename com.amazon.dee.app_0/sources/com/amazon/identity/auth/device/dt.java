package com.amazon.identity.auth.device;

import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class dt {
    public static ProviderInfo a(Uri uri, PackageManager packageManager) {
        try {
            return packageManager.resolveContentProvider(uri.getAuthority(), 0);
        } catch (Exception e) {
            ek.a(e);
            return packageManager.resolveContentProvider(uri.getAuthority(), 0);
        }
    }
}

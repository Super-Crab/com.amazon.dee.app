package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.amazon.identity.auth.device.hx;
import java.util.List;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ii {
    private static final a rj = new a() { // from class: com.amazon.identity.auth.device.ii.1
        @Override // com.amazon.identity.auth.device.ii.a
        public boolean c(Context context, Intent intent) {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            return queryIntentActivities != null && queryIntentActivities.size() > 0;
        }
    };
    private static final a rk = new a() { // from class: com.amazon.identity.auth.device.ii.2
        @Override // com.amazon.identity.auth.device.ii.a
        public boolean c(Context context, Intent intent) {
            List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
            return queryIntentServices != null && queryIntentServices.size() > 0;
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface a {
        boolean c(Context context, Intent intent);
    }

    private static Intent a(Context context, a aVar, Intent intent) {
        if (aVar.c(context, intent)) {
            return intent;
        }
        return null;
    }

    public static Intent as(String str, String str2) {
        Intent intent = new Intent();
        if (str2 != null) {
            intent.setClassName(str, str2);
        }
        return intent;
    }

    public static Intent t(Context context, String str) {
        a aVar = rj;
        hx.a am = hx.am(context);
        Intent a2 = am == null ? null : a(context, aVar, as(am.bm, str));
        return a2 != null ? a2 : a(context, aVar, as(context.getPackageName(), str));
    }
}

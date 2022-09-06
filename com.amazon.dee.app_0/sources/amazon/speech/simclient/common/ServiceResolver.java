package amazon.speech.simclient.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import java.util.List;
/* loaded from: classes.dex */
public class ServiceResolver {
    private static final String TAG = "ServiceResolver";

    public static ResolveInfo resolveBindIntent(Context context, Intent intent, String str) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> queryIntentServices = packageManager == null ? null : packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return null;
        }
        int size = queryIntentServices.size();
        if (str != null) {
            for (int i = size - 1; i >= 0; i--) {
                ResolveInfo resolveInfo = queryIntentServices.get(i);
                String str2 = resolveInfo.serviceInfo.packageName;
                if (!str.equals(resolveInfo.serviceInfo.permission) && packageManager.checkPermission(str, str2) != 0) {
                    Log.w(TAG, String.format("Package (%s) has NOT declared support for (%s)", str2, str));
                    queryIntentServices.remove(i);
                }
            }
            size = queryIntentServices.size();
        }
        if (size > 1) {
            for (int i2 = size - 1; i2 >= 0; i2--) {
                try {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(queryIntentServices.get(i2).serviceInfo.packageName, 0);
                    if ((applicationInfo.flags & 1) == 0 && (applicationInfo.flags & 128) == 0) {
                        queryIntentServices.remove(i2);
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    queryIntentServices.remove(i2);
                }
            }
        }
        if (queryIntentServices.size() > 1) {
            Log.wtf(TAG, "More than one permission-enforced system service can handle intent " + intent + " and permission " + str);
        }
        if (!queryIntentServices.isEmpty()) {
            return queryIntentServices.get(0);
        }
        return null;
    }
}

package com.amazon.identity.auth.device;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.platform.setting.PlatformSettings;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ja {
    public static void C(Context context, String str) {
        if (new ek(context).bC(str)) {
            return;
        }
        io.e("SecurityHelpers", String.format("Package is an unauthorized caller", new Object[0]));
        throw new SecurityException();
    }

    public static void a(ek ekVar) {
        ekVar.getCallingPackageName();
        if (ekVar.k(Binder.getCallingUid())) {
            return;
        }
        io.e("SecurityHelpers", "Unauthorized caller, the caller does not have permission or is signed with a different cert. Please check your manifest file and signature.");
        throw new SecurityException();
    }

    public static void aD(Context context) {
        a(new ek(context));
    }

    private static boolean aE(Context context) {
        return PlatformSettings.aU(context).f("ordered.broadcast", true).booleanValue();
    }

    public static boolean aF(Context context) {
        try {
            return (context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 1) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            io.e("SecurityHelpers", "Cannot calculate whether current app is a system app or not", e);
            return false;
        }
    }

    private static void b(Context context, Intent intent, String str, da daVar) {
        if (daVar != null) {
            c(context, intent, str, daVar);
        } else {
            a(context, intent, str);
        }
    }

    @TargetApi(17)
    private static void c(Context context, Intent intent, String str, da daVar) {
        try {
            if (aE(context)) {
                new StringBuilder("sendOrderedBroadcastAsUser ").append(intent.getAction());
                io.dm("SecurityHelpers");
                context.sendOrderedBroadcastAsUser(intent, daVar.getUserHandle(), str, null, null, -1, null, null);
                return;
            }
            new StringBuilder("sendBroadcastAsUser ").append(intent.getAction());
            io.dm("SecurityHelpers");
            context.sendBroadcastAsUser(intent, daVar.getUserHandle(), str);
        } catch (Exception e) {
            mq.incrementCounterAndRecord("MAPBroadcastException:" + intent.getAction(), new String[0]);
            io.e("SecurityHelpers", "Fail to send the broadcast. " + intent.getAction(), e);
        }
    }

    private static boolean g(Intent intent) {
        if (intent == null) {
            return false;
        }
        return (intent.getPackage() == null && intent.getComponent() == null) ? false : true;
    }

    public static void a(Activity activity) {
        ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity == null) {
            String.format("Android System called activity %s in package %s", activity.getClass().getName(), activity.getPackageName());
            io.dm("SecurityHelpers");
            return;
        }
        C(activity, callingActivity.getPackageName());
    }

    public static void a(Context context, Intent intent, String str, da daVar) {
        if ((aF(context) && mz.aY(context)) || g(intent)) {
            io.dm("SecurityHelpers");
            b(context, intent, str, daVar);
        } else if (daVar == null) {
            for (du duVar : MAPApplicationInformationQueryer.E(context).cX()) {
                String packageName = duVar.getPackageName();
                Intent intent2 = new Intent(intent);
                intent2.setPackage(packageName);
                String.format("On 3P devices, %s sends broadcast %s", context.getPackageName(), intent2.toString());
                io.dm("SecurityHelpers");
                b(context, intent2, str, null);
            }
        } else {
            throw new IllegalStateException("We can only fire a broadcast to a user if we are a system app");
        }
    }

    private static void a(Context context, Intent intent, String str) {
        try {
            if (aE(context)) {
                new StringBuilder("sendOrderedBroadcast ").append(intent.getAction());
                io.dm("SecurityHelpers");
                context.sendOrderedBroadcast(intent, str, null, null, -1, null, null);
                return;
            }
            new StringBuilder("sendBroadcast ").append(intent.getAction());
            io.dm("SecurityHelpers");
            context.sendBroadcast(intent, str);
        } catch (Exception e) {
            mq.incrementCounterAndRecord("MAPBroadcastException:" + intent.getAction(), new String[0]);
            io.e("SecurityHelpers", "Fail to send the broadcast. " + intent.getAction(), e);
        }
    }
}

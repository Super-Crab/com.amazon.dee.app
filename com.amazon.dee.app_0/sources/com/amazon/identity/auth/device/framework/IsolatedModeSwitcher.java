package com.amazon.identity.auth.device.framework;

import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.bootstrapSSO.BootstrapSSOService;
import com.amazon.identity.auth.device.cp;
import com.amazon.identity.auth.device.cq;
import com.amazon.identity.auth.device.dh;
import com.amazon.identity.auth.device.ds;
import com.amazon.identity.auth.device.ee;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.gp;
import com.amazon.identity.auth.device.hz;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.iq;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.mz;
import java.util.List;
import java.util.Locale;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class IsolatedModeSwitcher {
    private static final String TAG = "com.amazon.identity.auth.device.framework.IsolatedModeSwitcher";
    static volatile Boolean jX;
    private static volatile Boolean jY;

    private IsolatedModeSwitcher() {
    }

    private static boolean doesAppNeedToSwitchToSSOMode(Context context) {
        if (!isAppInRuntimeIsolatedMode(context) || new MAPAccountManager(context).getAccount() != null) {
            return false;
        }
        io.i(TAG, "No account detected in isolated mode.");
        return true;
    }

    public static boolean doesAppSupportRuntimeIsolatedMode(Context context) {
        if (jX == null) {
            jX = Boolean.valueOf(!TextUtils.isEmpty(iq.q(context, context.getPackageName(), "MAPRuntimeIsolateForAccountPool")));
        }
        if (jX.booleanValue()) {
            io.i(TAG, "Application supports runtime isolated mode switch.");
        }
        return jX.booleanValue();
    }

    public static synchronized boolean isAppInIsolatedMode(Context context) {
        synchronized (IsolatedModeSwitcher.class) {
            if (isAppInStaticIsolatedMode(context)) {
                io.i(TAG, "The application is in static isolated mode");
                return true;
            }
            return isAppInRuntimeIsolatedMode(context);
        }
    }

    public static boolean isAppInRuntimeIsolatedMode(Context context) {
        if (doesAppSupportRuntimeIsolatedMode(context)) {
            boolean booleanValue = new gp(context, "runtime_isolated_mode").cu("isolated").booleanValue();
            io.i(TAG, "Restoring current runtime isolated mode: ".concat(String.valueOf(booleanValue)));
            return booleanValue;
        }
        return false;
    }

    public static boolean isAppInStaticIsolatedMode(Context context) {
        if (iq.o(context, context.getPackageName(), "MAPIsolateApplication").booleanValue()) {
            return true;
        }
        if (jY != null) {
            return jY.booleanValue();
        }
        List<String> p = iq.p(context, context.getPackageName(), "MAPIsolateApplicationOnDevice");
        if (hz.isEmpty(p)) {
            return false;
        }
        ds dsVar = new ds(context);
        String str = TAG;
        io.i(str, "App needs isolation on devices: " + p.toString());
        for (String str2 : p) {
            if (str2.equalsIgnoreCase(Constants.OS_FIREOS) && mz.ba(context)) {
                return setAppInStaticIsolatedModeAndReturn(true, Constants.OS_FIREOS);
            }
            if (str2.equalsIgnoreCase("Canary") && dsVar.dw()) {
                return setAppInStaticIsolatedModeAndReturn(true, "Canary");
            }
            if (str2.equalsIgnoreCase("Grover") && dsVar.dv()) {
                return setAppInStaticIsolatedModeAndReturn(true, "Grover");
            }
            if (str2.equalsIgnoreCase("3P") && dsVar.dx()) {
                return setAppInStaticIsolatedModeAndReturn(true, "3P");
            }
        }
        return setAppInStaticIsolatedModeAndReturn(false, null);
    }

    private static void preActionOnSwitch(Context context, boolean z) {
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, BootstrapSSOService.class), z ? 2 : 1, 1);
        } catch (IllegalArgumentException unused) {
            io.a("Component Class %s not found in manifest", "BootstrapSSOService");
        }
    }

    private static boolean setAppInStaticIsolatedModeAndReturn(boolean z, String str) {
        if (z && !TextUtils.isEmpty(str)) {
            io.i(TAG, String.format(Locale.ENGLISH, "Current platform is %s, entering static isolation mode", str));
        }
        jY = Boolean.valueOf(z);
        return z;
    }

    public static synchronized void switchAppToIsolatedModeIfNecessary(Context context, String str) {
        synchronized (IsolatedModeSwitcher.class) {
            if (!doesAppSupportRuntimeIsolatedMode(context)) {
                return;
            }
            if (!TextUtils.isEmpty(str)) {
                String q = iq.q(context, context.getPackageName(), "MAPRuntimeIsolateForAccountPool");
                io.a("The account is in account pool: %s. Runtime isolated mode needed for account pool: %s.", str, q);
                if (!TextUtils.equals(str, q)) {
                    io.i(TAG, "Keep application in SSO mode.");
                    return;
                }
                io.i(TAG, "The application is entering isolated mode.");
                preActionOnSwitch(context, true);
                writeIsolatedModeStateIntoStorage(context, true);
                cp.a(new cq(context)).a(Feature.IsolateApplication, context);
                ee.N(context);
                dh.z(context).cM();
                io.a(TAG, "Finish generating local common info (version: %d) for isolated mode.", 1);
                mq.b("EnterRuntimeIsolatedMode:".concat(String.valueOf(q)), new String[0]);
            }
        }
    }

    public static synchronized void switchAppToSSOMode(Context context) {
        synchronized (IsolatedModeSwitcher.class) {
            io.i(TAG, "The application is entering SSO mode.");
            preActionOnSwitch(context, false);
            writeIsolatedModeStateIntoStorage(context, false);
            cp.a(new cq(context)).a(Feature.IsolateApplication, context);
            ee.N(context);
            mq.b("ExitRuntimeIsolatedMode", new String[0]);
        }
    }

    public static synchronized void switchAppToSSOModeIfNecessary(Context context) {
        synchronized (IsolatedModeSwitcher.class) {
            if (doesAppNeedToSwitchToSSOMode(context)) {
                switchAppToSSOMode(context);
            } else {
                io.dm(TAG);
            }
        }
    }

    private static void writeIsolatedModeStateIntoStorage(Context context, boolean z) {
        new gp(context, "runtime_isolated_mode").b("isolated", Boolean.valueOf(z));
    }
}

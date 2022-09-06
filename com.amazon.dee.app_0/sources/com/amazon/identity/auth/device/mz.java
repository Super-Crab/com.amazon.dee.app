package com.amazon.identity.auth.device;

import android.accounts.AuthenticatorDescription;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import com.amazon.device.information.contract.DeviceInformationContract;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.hx;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.fido.Fido;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class mz {
    private static final String TAG = "com.amazon.identity.auth.device.mz";
    private static final Set<String> vL;
    private static final Set<String> vM;
    private static final AtomicReference<Boolean> vN;
    private static ConcurrentHashMap<Uri, Boolean> vO;
    private static ConcurrentHashMap<String, Boolean> vP;
    private static volatile Boolean vQ;
    private static volatile Boolean vR;
    private static volatile Boolean vS;
    private static volatile Boolean vT;
    private static volatile Boolean vU;
    private static volatile Boolean vV;
    private static final Object vW;

    static {
        HashSet hashSet = new HashSet();
        vL = hashSet;
        hashSet.addAll(Arrays.asList("com.amazon.dcp", "com.amazon.sso", "com.amazon.canary", "com.amazon.fv"));
        HashSet hashSet2 = new HashSet();
        vM = hashSet2;
        hashSet2.addAll(Arrays.asList("com.amazon.imp"));
        vN = new AtomicReference<>(null);
        vO = new ConcurrentHashMap<>();
        vP = new ConcurrentHashMap<>();
        vQ = null;
        vR = null;
        vS = null;
        vT = null;
        vU = null;
        vV = null;
        vW = new Object();
    }

    private mz() {
    }

    public static boolean E(Context context, String str) {
        Boolean bool = vP.get(str);
        if (bool == null) {
            boolean z = false;
            if (new ek(context).queryIntentServices(new Intent(str), 0).size() > 0) {
                z = true;
            }
            bool = vP.putIfAbsent(str, Boolean.valueOf(z));
            if (bool == null) {
                bool = Boolean.valueOf(z);
            }
        }
        return bool.booleanValue();
    }

    private static boolean a(hx.a aVar) {
        return aVar != null && !vL.contains(aVar.bm);
    }

    public static boolean aV(Context context) {
        return hx.ak(context);
    }

    public static boolean aW(Context context) {
        return a(hx.am(context));
    }

    public static boolean aX(Context context) {
        hx.a am = hx.am(context);
        if (am == null) {
            return false;
        }
        return vL.contains(am.bm);
    }

    public static boolean aY(Context context) {
        if (vN.get() == null) {
            vN.compareAndSet(null, Boolean.valueOf(f(ed.M(context)) ? false : iQ()));
        }
        return vN.get().booleanValue();
    }

    public static boolean aZ(Context context) {
        return aY(context) && a(hx.am(context));
    }

    public static boolean aj(Context context) {
        return new ds(context).dj();
    }

    public static boolean ap(Context context) {
        return hx.ap(context);
    }

    public static boolean b(Context context, Uri uri) {
        Boolean bool = vO.get(uri);
        if (bool == null) {
            boolean z = (uri == null || new ek(context).b(uri) == null) ? false : true;
            bool = vO.putIfAbsent(uri, Boolean.valueOf(z));
            if (bool == null) {
                bool = Boolean.valueOf(z);
            }
        }
        return bool.booleanValue();
    }

    public static boolean ba(Context context) {
        return iQ() && a(hx.ao(context));
    }

    public static boolean bb(Context context) {
        return !hx.ak(context);
    }

    public static boolean bc(Context context) {
        hx.a am = hx.am(context);
        if (am == null) {
            return true;
        }
        return vM.contains(am.bm);
    }

    public static boolean bd(Context context) {
        return be(context);
    }

    public static boolean be(Context context) {
        if (aY(context)) {
            int i = Build.VERSION.SDK_INT;
            return true;
        }
        return false;
    }

    @Deprecated
    public static boolean bf(Context context) {
        String aG = jb.aG(context);
        return aG != null && aG.startsWith("D01E") && Build.MODEL.toLowerCase(Locale.US).equals("kindle fire");
    }

    @Deprecated
    public static boolean bg(Context context) {
        hx.a am;
        Integer D;
        if (vQ == null) {
            synchronized (vW) {
                if (vQ == null) {
                    boolean z = true;
                    if (aY(context) && ((am = hx.am(context)) == null || ("com.amazon.dcp".equals(am.bm) && ((D = jm.D(context, "com.amazon.dcp")) == null || 1570 > D.intValue())))) {
                        z = false;
                    }
                    vQ = Boolean.valueOf(z);
                }
            }
        }
        return vQ.booleanValue();
    }

    public static boolean bh(Context context) {
        return !aY(context) && hx.ak(context);
    }

    public static boolean bi(Context context) {
        return b(context, DeviceInformationContract.AUTHORITY_URI);
    }

    public static boolean bj(Context context) {
        AuthenticatorDescription as = hx.as(context);
        if (as == null || !as.packageName.equals("com.amazon.fv") || jm.D(context, as.packageName).intValue() < 5) {
            return false;
        }
        io.i(TAG, "Device has Grover with version 3 or later");
        return true;
    }

    public static boolean bk(Context context) {
        AuthenticatorDescription as = hx.as(context);
        return as != null && as.packageName.equals("com.amazon.canary");
    }

    public static String bl(Context context) {
        String dS = ((ea) ed.M(context).getSystemService("dcp_device_info")).dS();
        GeneratedOutlineSupport1.outline161(dS, "Device dsn: ", TAG);
        return dS;
    }

    @SuppressLint({"NewApi"})
    public static boolean bm(Context context) {
        Boolean bool = vT;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null && packageManager.hasSystemFeature("com.fireos.sdk.amazon_profile_service") && packageManager.hasSystemFeature("com.fireos.sdk.amazon_profile_service", 4)) {
            bool2 = Boolean.TRUE;
        }
        vT = bool2;
        return bool2.booleanValue();
    }

    @SuppressLint({"NewApi"})
    public static boolean bn(Context context) {
        boolean z = false;
        if (!bm(context)) {
            return false;
        }
        Boolean bool = vU;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null && packageManager.hasSystemFeature("com.fireos.sdk.amazon_profile_service", 11)) {
            if (context.checkSelfPermission("com.amazon.permission.SET_PROFILE") == 0) {
                z = true;
            }
            bool2 = Boolean.valueOf(z);
        }
        vU = bool2;
        return bool2.booleanValue();
    }

    public static synchronized boolean bo(Context context) {
        Boolean bool;
        synchronized (mz.class) {
            Boolean bool2 = vV;
            if (bool2 != null) {
                return bool2.booleanValue();
            }
            try {
                Class.forName("com.google.android.gms.fido.fido2.Fido2ApiClient");
                if (Fido.getFido2ApiClient(context) == null) {
                    io.i(TAG, "Could not initialize FidoAuthenticatorJSBridge because Fido client could not be initialized");
                    mq.incrementCounterAndRecord("FidoClientInitialization:Error", new String[0]);
                    bool = Boolean.FALSE;
                } else {
                    mq.incrementCounterAndRecord("FidoClientInitialization:Success", new String[0]);
                    bool = Boolean.TRUE;
                }
            } catch (Exception unused) {
                io.i(TAG, "Could not initialize FidoAuthenticatorJSBridge because Fido client could not be initialized");
                mq.incrementCounterAndRecord("FidoClientInitialization:Error", new String[0]);
                bool = Boolean.FALSE;
            }
            vU = bool;
            return bool.booleanValue();
        }
    }

    public static boolean f(ed edVar) {
        return edVar.dW().a(Feature.IsolateApplication);
    }

    public static boolean iQ() {
        if (Build.MANUFACTURER.equalsIgnoreCase("Amazon")) {
            return true;
        }
        try {
            Class.forName("com.amazon.acos.util.AmazonBuild");
            io.dm(TAG);
            return true;
        } catch (ClassNotFoundException unused) {
            io.dm(TAG);
            return false;
        }
    }

    public static boolean iR() {
        Boolean bool = vR;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        try {
            Class<?> cls = Class.forName("com.amazon.android.os.MultipleProfileHelper");
            cls.getMethod("myProfileId", new Class[0]);
            cls.getMethod("getCallingProfileId", new Class[0]);
            cls.getMethod("getForegroundProfileId", new Class[0]);
            cls.getMethod("isSameApp", Integer.TYPE, Integer.TYPE);
            bool2 = Boolean.TRUE;
            io.dm(TAG);
        } catch (ClassNotFoundException unused) {
            io.dm(TAG);
        } catch (NoSuchMethodException unused2) {
            io.dm(TAG);
        }
        vR = bool2;
        return bool2.booleanValue();
    }

    public static boolean iS() {
        Boolean bool = vS;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        try {
            Class.forName("amazon.os.Build.VERSION");
            bool2 = Boolean.TRUE;
            io.dm(TAG);
        } catch (ClassNotFoundException unused) {
            io.dm(TAG);
        }
        vS = bool2;
        return bool2.booleanValue();
    }

    public static synchronized boolean iT() {
        synchronized (mz.class) {
            int i = Build.VERSION.SDK_INT;
        }
        return true;
    }
}

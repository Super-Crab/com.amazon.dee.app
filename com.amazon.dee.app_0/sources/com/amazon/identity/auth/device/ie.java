package com.amazon.identity.auth.device;

import amazon.os.Build;
import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.platform.setting.PlatformSettings;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ie {
    private static final String TAG = "com.amazon.identity.auth.device.ie";
    private static volatile String rf;

    private ie() {
    }

    public static String aA(Context context) {
        String eV = PlatformSettings.aU(context).eV("ro.product.package_name");
        if (eV == null && mz.aY(context)) {
            io.e(TAG, "Central Software Component Id is null.  DCP setting -device-/os_package_name not set for this device.");
        }
        return eV;
    }

    public static String au(Context context) {
        if (IsolatedModeSwitcher.isAppInStaticIsolatedMode(context)) {
            String v = iq.v(context, "MAPDeviceType");
            if (TextUtils.isEmpty(v)) {
                return "A1MPSLFC7L5AFK";
            }
            GeneratedOutlineSupport1.outline161(v, "Overridden device type for the isolated app is ", TAG);
            return v;
        }
        return aw(context);
    }

    public static boolean av(Context context) {
        return mz.f(ed.M(context)) && !TextUtils.isEmpty(iq.v(context, "MAPDeviceType"));
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x00df, code lost:
        if (com.amazon.identity.auth.device.jk.gR() != false) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String aw(android.content.Context r4) {
        /*
            Method dump skipped, instructions count: 296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.ie.aw(android.content.Context):java.lang.String");
    }

    public static String ax(Context context) {
        return r(context, context.getPackageName());
    }

    public static Map<String, mb> ay(Context context) {
        Collection<du> cX;
        ed M = ed.M(context);
        if (((ds) M.getSystemService("sso_platform")).ds()) {
            cX = Collections.unmodifiableCollection(Collections.EMPTY_SET);
        } else {
            cX = MAPApplicationInformationQueryer.E(M).cX();
        }
        HashMap hashMap = new HashMap();
        String c = iw.c(M, DeviceAttribute.CentralDeviceType);
        Long valueOf = Long.valueOf(((ea) M.getSystemService("dcp_device_info")).cr());
        String aA = aA(context);
        io.i(TAG, String.format("Using the central device type: %s, software version: %s, and software component id: %s", c, valueOf, aA));
        hashMap.put(c, new mb(valueOf, aA));
        for (du duVar : cX) {
            Long dD = duVar.dD();
            String packageName = duVar.getPackageName();
            try {
                String deviceType = duVar.getDeviceType();
                String dE = duVar.dE();
                String str = TAG;
                String.format("The RemoteMapInfo returns softwareComponentId: %s, deviceType: %s, softwareVersion: %s, overrideDSN: %s", packageName, deviceType, dD, dE);
                io.dm(str);
                if (!TextUtils.isEmpty(dE)) {
                    String str2 = TAG;
                    String.format("%s is using override DSN. Skipping it.", duVar.getPackageName());
                    io.dm(str2);
                } else {
                    if (dD == null) {
                        String str3 = TAG;
                        String.format("%s is using null software version. Replacing the null with 0.", duVar.getPackageName());
                        io.dm(str3);
                        dD = 0L;
                    }
                    if (TextUtils.isEmpty(deviceType)) {
                        String str4 = TAG;
                        String.format("%s is using null or empty device type. This should be an integration error.", duVar.getPackageName());
                        io.dm(str4);
                    } else if (TextUtils.equals(deviceType, c)) {
                        String str5 = TAG;
                        String.format("%s is using central device type.", duVar.getPackageName());
                        io.dm(str5);
                    } else if (hashMap.containsKey(deviceType)) {
                        io.i(TAG, String.format("Multiple apps are using the device type %s. Choosing the app with the greatest software version.", deviceType));
                        if (((mb) hashMap.get(deviceType)).iw().longValue() < dD.longValue()) {
                            hashMap.put(deviceType, new mb(dD, packageName));
                        }
                    } else {
                        hashMap.put(deviceType, new mb(dD, packageName));
                    }
                }
            } catch (RemoteMAPException e) {
                io.w(TAG, String.format("Failed to query device type/override DSN for remoteMAPApp Package.Skipping it.Error Message : %s", e.getMessage()));
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            io.i(TAG, String.format("Using the deviceType-softwareVersion,softwareComponentId: %s - %s, %s", entry.getKey(), ((mb) entry.getValue()).iw(), ((mb) entry.getValue()).ix()));
        }
        return hashMap;
    }

    public static String az(Context context) {
        if (mz.aY(context)) {
            if (mz.iS()) {
                return Build.VERSION.DEVICE_TYPE_ID;
            }
            String str = new eg().get("ro.product.config.type");
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return str;
        }
        return null;
    }

    static Integer dh(String str) {
        if (str == null || !str.matches("[0-9A-F]{4}[0-9A-Z]{12}")) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(str.substring(2, 4), 16));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static boolean l(Context context, String str, String str2) {
        return TextUtils.equals(s(context, str), s(context, str2));
    }

    static String m(Context context, String str, String str2) {
        String q = iq.q(context, str2, "App_Device_Type_For_Central_Device_Type_".concat(String.valueOf(str)));
        if (q != null) {
            io.a("Package: %s device type: %s picked from metadata (manifest)", str2, q);
            return q;
        }
        String q2 = iq.q(context, str2, "MAPDeviceType");
        if (q2 != null) {
            io.a("Package: %s device type: %s picked from metadata (manifest)", str2, q2);
            return q2;
        }
        io.a("No device type override found for the app %s. Will use the central device type %s", str2, str);
        return str;
    }

    public static String n(Context context, String str, String str2) {
        return p(context, str) ? aA(context) : str2;
    }

    public static boolean p(Context context, String str) {
        return TextUtils.equals(iw.c(context, DeviceAttribute.CentralDeviceType), str);
    }

    public static boolean q(Context context, String str) {
        String s = s(context, str);
        if (s == null) {
            return false;
        }
        return p(context, s);
    }

    public static String r(Context context, String str) {
        return m(context, aw(context), str);
    }

    public static String s(Context context, String str) {
        String str2 = TAG;
        String.format("%s is trying to get device type", str);
        io.dm(str2);
        if (str == null) {
            io.i(TAG, "Not specify package name, get central device type.");
            return iw.c(context, DeviceAttribute.CentralDeviceType);
        }
        du bm = MAPApplicationInformationQueryer.E(context).bm(str);
        if (bm == null) {
            io.e(TAG, "Cannot get remote map information even including the calling app itself! This could happen only the calling app is IMP!");
            return iw.c(context, DeviceAttribute.CentralDeviceType);
        }
        try {
            return bm.getDeviceType();
        } catch (RemoteMAPException e) {
            io.e(TAG, String.format("Failed to get device type for the Package. Error Message: %s", e.getMessage()));
            return null;
        }
    }
}

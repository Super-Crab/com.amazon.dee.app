package com.amazon.identity.auth.device.framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.identity.auth.device.ab;
import com.amazon.identity.auth.device.api.MAPInformationProvider;
import com.amazon.identity.auth.device.du;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.ek;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.jk;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.mz;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class MAPApplicationInformationQueryer {
    private static final String TAG = "com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer";
    private static final Comparator<du> jZ = new Comparator<du>() { // from class: com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer.1
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(du duVar, du duVar2) {
            return du.a(duVar, duVar2) * (-1);
        }
    };
    private static MAPApplicationInformationQueryer ka;
    private final ek cp;
    private Map<String, du> kb;
    private Map<String, Integer> kc;
    private boolean kd;
    private final ed o;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class MAPApplicationCacheInvalidator extends BroadcastReceiver {
        private static final AtomicReference<MAPApplicationCacheInvalidator> ke = new AtomicReference<>();

        public static void F(Context context) {
            MAPApplicationCacheInvalidator mAPApplicationCacheInvalidator = new MAPApplicationCacheInvalidator();
            if (!ke.compareAndSet(null, mAPApplicationCacheInvalidator)) {
                io.a(MAPApplicationInformationQueryer.TAG, "%s is already registered", "MAPApplicationCacheInvalidator");
                return;
            }
            String unused = MAPApplicationInformationQueryer.TAG;
            io.a("Initializing MAPCache cleaner %s.", "MAPApplicationCacheInvalidator");
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
            intentFilter.addDataScheme("package");
            try {
                context.getApplicationContext().registerReceiver(mAPApplicationCacheInvalidator, intentFilter);
            } catch (Exception e) {
                io.w(MAPApplicationInformationQueryer.TAG, "Failed to register receiver", e);
            }
        }

        public static boolean isRegistered() {
            return ke.get() != null;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Uri data = intent.getData();
            if (data == null) {
                io.e(MAPApplicationInformationQueryer.TAG, "The system broadcast contains null data. Ignoring the broadcast");
                return;
            }
            String schemeSpecificPart = data.getSchemeSpecificPart();
            if (schemeSpecificPart == null) {
                return;
            }
            String action = intent.getAction();
            String str = MAPApplicationInformationQueryer.TAG;
            String.format("Notified by action %s to invalidate app cache for %s", action, schemeSpecificPart);
            io.dm(str);
            if (TextUtils.equals(action, "android.intent.action.PACKAGE_ADDED")) {
                MAPApplicationInformationQueryer.E(context).cZ();
            } else if (TextUtils.equals(action, "android.intent.action.PACKAGE_REMOVED")) {
                MAPApplicationInformationQueryer.E(context).bp(schemeSpecificPart);
                String str2 = MAPApplicationInformationQueryer.TAG;
                "Package just removed from the device: ".concat(schemeSpecificPart);
                io.dm(str2);
                ab.g(context).O();
            } else if (!TextUtils.equals(action, "android.intent.action.PACKAGE_CHANGED") && !TextUtils.equals(action, "android.intent.action.PACKAGE_REPLACED")) {
            } else {
                MAPApplicationInformationQueryer.E(context).bp(schemeSpecificPart);
            }
        }
    }

    MAPApplicationInformationQueryer(Context context) {
        this(context, new ek(context));
    }

    public static synchronized MAPApplicationInformationQueryer E(Context context) {
        MAPApplicationInformationQueryer mAPApplicationInformationQueryer;
        synchronized (MAPApplicationInformationQueryer.class) {
            if (ka == null || jk.gR()) {
                ka = new MAPApplicationInformationQueryer(context);
            }
            mAPApplicationInformationQueryer = ka;
        }
        return mAPApplicationInformationQueryer;
    }

    private synchronized void bo(String str) {
        PackageInfo bE;
        ProviderInfo[] providerInfoArr;
        try {
            try {
                bE = this.cp.bE(str);
            } catch (SecurityException e) {
                io.w(TAG, String.format("Tried to get MAP info for untrusted package. Error message : %s", e.getMessage()));
                mq.b("MAPPackageIncorrectlySigned", str);
            }
        } catch (PackageManager.NameNotFoundException e2) {
            io.w(TAG, String.format("Tried to get MAP info for non-existant package. Error message : %s", e2.getMessage()));
            mq.b("MAPPackageNameNotFound", str);
        }
        if (bE.providers == null) {
            String str2 = TAG;
            "Cannot get package information for ".concat(String.valueOf(str));
            io.dm(str2);
            this.kb.remove(str);
            return;
        }
        for (ProviderInfo providerInfo : bE.providers) {
            if (!ek.a(providerInfo)) {
                String str3 = TAG;
                String.format("Content Provider for %s is not enabled", providerInfo.packageName);
                io.dm(str3);
            } else {
                String str4 = providerInfo.authority;
                if (str4 != null && str4.startsWith(MAPInformationProvider.TOKEN_PROVIDER_AUTHORITY_PREFIX)) {
                    du duVar = new du(this.o, providerInfo);
                    this.kb.put(str, duVar);
                    String str5 = null;
                    try {
                        str5 = duVar.getDeviceType();
                    } catch (RemoteMAPException unused) {
                        io.w(TAG, "Couldn't determine override device type/DSN for remoteMAPInfo Package");
                    }
                    String str6 = TAG;
                    String.format("Get map info for %s, device type: %s", duVar.getPackageName(), str5);
                    io.dm(str6);
                    return;
                }
            }
        }
        this.kb.remove(str);
    }

    private synchronized boolean bq(String str) {
        boolean z;
        if (this.kc.containsKey(str)) {
            if (this.kc.get(str).intValue() > 0) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void cZ() {
        this.kd = true;
    }

    private synchronized Map<String, du> da() {
        if (this.kb == null || this.kd) {
            if (!MAPApplicationCacheInvalidator.isRegistered()) {
                io.dm(TAG);
                MAPApplicationCacheInvalidator.F(this.o);
            }
            HashMap hashMap = new HashMap();
            if (mz.f(this.o)) {
                String packageName = this.o.getPackageName();
                bo(packageName);
                du duVar = this.kb.get(packageName);
                if (duVar != null) {
                    hashMap.put(packageName, duVar);
                } else {
                    hashMap.put(packageName, new du(this.o));
                }
            } else {
                for (ProviderInfo providerInfo : db()) {
                    if (bq(providerInfo.packageName)) {
                        du duVar2 = this.kb.get(providerInfo.packageName);
                        if (duVar2 != null) {
                            hashMap.put(providerInfo.packageName, duVar2);
                        }
                    } else {
                        hashMap.put(providerInfo.packageName, new du(this.o, providerInfo));
                    }
                }
            }
            this.kb = hashMap;
            this.kd = false;
        }
        return this.kb;
    }

    private List<ProviderInfo> db() {
        ArrayList arrayList = new ArrayList();
        for (ProviderInfo providerInfo : this.cp.ed()) {
            String str = providerInfo.authority;
            if (str != null && str.startsWith(MAPInformationProvider.TOKEN_PROVIDER_AUTHORITY_PREFIX)) {
                arrayList.add(providerInfo);
            }
        }
        return arrayList;
    }

    public synchronized void P() {
        this.kb = new HashMap();
        this.kd = true;
        this.kc.clear();
    }

    public synchronized du bm(String str) {
        if (this.kb.get(str) == null && this.kd && !bq(str)) {
            io.i(TAG, "Populate change for remote MAP info.");
            String str2 = TAG;
            io.i(str2, "CacheContainsPartialResults? " + this.kd);
            bo(str);
        }
        return this.kb.get(str);
    }

    public synchronized String bn(String str) {
        du bm = bm(str);
        if (bm != null) {
            try {
                String dE = bm.dE();
                if (!TextUtils.isEmpty(dE)) {
                    return dE;
                }
            } catch (RemoteMAPException unused) {
                io.e(TAG, String.format("Unable to get device serial number for the calling package.", new Object[0]));
                return null;
            }
        }
        return null;
    }

    public synchronized void bp(String str) {
        String str2 = TAG;
        "Trying to invalidateCacheForSinglePackage:".concat(String.valueOf(str));
        io.dm(str2);
        if (this.kb.containsKey(str)) {
            if (bq(str)) {
                this.kb.get(str).dC();
                String str3 = TAG;
                String.format("The package info for %s is locked for usage. Will clean it later.", str);
                io.dm(str3);
                return;
            }
            String str4 = TAG;
            "Cleaning app info cache for package:".concat(String.valueOf(str));
            io.dm(str4);
            this.kb.remove(str);
            this.kd = true;
            return;
        }
        io.dm(TAG);
    }

    public synchronized void br(String str) {
        if (str == null) {
            return;
        }
        bm(str);
        int i = 1;
        if (this.kc.containsKey(str)) {
            i = 1 + this.kc.get(str).intValue();
        }
        String str2 = TAG;
        StringBuilder sb = new StringBuilder("Locking package info for ");
        sb.append(str);
        sb.append(". The locker count is:");
        sb.append(i);
        io.dm(str2);
        this.kc.put(str, Integer.valueOf(i));
    }

    public synchronized void bs(String str) {
        du duVar;
        if (str == null) {
            return;
        }
        String str2 = TAG;
        "Unlocking package info for: ".concat(str);
        io.dm(str2);
        if (this.kc.containsKey(str)) {
            int intValue = this.kc.get(str).intValue();
            String str3 = TAG;
            StringBuilder sb = new StringBuilder("Previous lock count:");
            sb.append(intValue);
            sb.append(". for package:");
            sb.append(str);
            io.dm(str3);
            int i = intValue <= 0 ? 0 : intValue - 1;
            this.kc.put(str, Integer.valueOf(i));
            String str4 = TAG;
            StringBuilder sb2 = new StringBuilder("Current lock count:");
            sb2.append(i);
            sb2.append(". for package:");
            sb2.append(str);
            io.dm(str4);
            if (i == 0 && (duVar = this.kb.get(str)) != null && duVar.dB()) {
                String str5 = TAG;
                "Remove package cache for package:".concat(str);
                io.dm(str5);
                this.kb.remove(str);
                this.kd = true;
            }
        }
    }

    public synchronized Collection<du> cX() {
        return new ArrayList(da().values());
    }

    public synchronized List<du> cY() {
        ArrayList arrayList;
        Map<String, du> da = da();
        arrayList = new ArrayList();
        arrayList.addAll(da.values());
        Collections.sort(arrayList, jZ);
        return arrayList;
    }

    MAPApplicationInformationQueryer(Context context, ek ekVar) {
        this.o = ed.M(context.getApplicationContext());
        this.cp = ekVar;
        this.kb = new HashMap();
        this.kc = new HashMap();
        this.kd = true;
    }
}

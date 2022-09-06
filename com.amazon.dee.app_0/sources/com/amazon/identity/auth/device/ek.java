package com.amazon.identity.auth.device;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.Binder;
import android.os.Process;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import com.amazon.identity.auth.device.framework.TrustedAppUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ek {
    private static final String TAG = "com.amazon.identity.auth.device.ek";
    private static volatile ex lB;
    private final String lC;
    private final PackageManager lD;
    private final boolean lE;
    private volatile Set<Signature> lF;
    private final Context mContext;

    public ek(Context context) {
        this(context, false);
    }

    public static synchronized void a(ex exVar) {
        synchronized (ek.class) {
            lB = exVar;
            String str = TAG;
            io.i(str, "Setting package trust logic as: " + exVar.getDescription());
        }
    }

    private boolean bB(String str) {
        return lB.a(this.mContext, str, false);
    }

    private boolean bF(String str) throws PackageManager.NameNotFoundException {
        return a(b(str, 64).signatures);
    }

    private int bG(String str) {
        try {
            return this.lD.checkSignatures(this.lC, str);
        } catch (Exception e) {
            a(e);
            return this.lD.checkSignatures(this.lC, str);
        }
    }

    private List<PackageInfo> ef() {
        try {
            return this.lD.getInstalledPackages(0);
        } catch (Exception e) {
            a(e);
            return this.lD.getInstalledPackages(0);
        }
    }

    public static boolean g(Context context, String str) {
        try {
            a(str, 64, context.getPackageManager());
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public ProviderInfo b(Uri uri) {
        ProviderInfo a = dt.a(uri, this.lD);
        if (a == null) {
            return null;
        }
        if (bC(a.packageName)) {
            return a;
        }
        io.e(TAG, String.format("Package does not qualify as a trusted package.", new Object[0]));
        return null;
    }

    public boolean bC(String str) {
        return lB.a(this.mContext, str, true);
    }

    public int bD(String str) {
        if (!this.lC.equals(str) || jk.gR()) {
            if (this.lE) {
                return -3;
            }
            if (bG(str) == 0) {
                return 0;
            }
            if (this.lF == null) {
                return -3;
            }
            try {
                return !bF(str) ? -3 : 0;
            } catch (PackageManager.NameNotFoundException unused) {
                return -4;
            }
        }
        return 0;
    }

    public PackageInfo bE(String str) throws PackageManager.NameNotFoundException, SecurityException {
        if (bC(str)) {
            return b(str, 8);
        }
        io.e(TAG, "Package is not trusted");
        throw new SecurityException("Package is not trusted");
    }

    public List<ProviderInfo> ed() {
        ProviderInfo[] providerInfoArr;
        ArrayList arrayList = new ArrayList();
        for (String str : ee()) {
            try {
                PackageInfo b = b(str, 8);
                if (b != null && (providerInfoArr = b.providers) != null) {
                    for (ProviderInfo providerInfo : providerInfoArr) {
                        if (a(providerInfo)) {
                            arrayList.add(providerInfo);
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                io.w(TAG, "Caught NameNotFoundException querying for package that existed a moment ago", e);
            }
        }
        return arrayList;
    }

    public Set<String> ee() {
        List<PackageInfo> ef = ef();
        HashSet hashSet = new HashSet();
        for (PackageInfo packageInfo : ef) {
            if (bB(packageInfo.packageName)) {
                hashSet.add(packageInfo.packageName);
            }
        }
        return hashSet;
    }

    public List<ResolveInfo> f(Intent intent) {
        List<ResolveInfo> queryIntentActivities;
        try {
            queryIntentActivities = this.lD.queryIntentActivities(intent, 0);
        } catch (Exception e) {
            a(e);
            queryIntentActivities = this.lD.queryIntentActivities(intent, 0);
        }
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            if (bB(resolveInfo.activityInfo.packageName)) {
                arrayList.add(resolveInfo);
            }
        }
        return arrayList;
    }

    public String getCallingPackageName() {
        return this.mContext.getPackageManager().getNameForUid(Binder.getCallingUid());
    }

    public Resources getResourcesForApplication(String str) throws PackageManager.NameNotFoundException {
        if (bB(str)) {
            try {
                return this.lD.getResourcesForApplication(str);
            } catch (PackageManager.NameNotFoundException e) {
                throw e;
            } catch (Exception e2) {
                a(e2);
                return this.lD.getResourcesForApplication(str);
            }
        }
        return null;
    }

    public boolean k(int i) {
        String[] packagesForUid;
        boolean z;
        int myUid = Process.myUid();
        if (jk.gR()) {
            return a(i, myUid) == 0;
        } else if (i == myUid) {
            return true;
        } else {
            if (this.lE) {
                io.dm(TAG);
                return false;
            }
            int a = a(i, myUid);
            if (a == 0) {
                return true;
            }
            if (this.lF == null) {
                return false;
            }
            try {
                packagesForUid = this.lD.getPackagesForUid(i);
            } catch (Exception e) {
                a(e);
                packagesForUid = this.lD.getPackagesForUid(i);
            }
            if (packagesForUid == null) {
                io.e(TAG, "Package name not found for the uid");
                return false;
            }
            int length = packagesForUid.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z = false;
                    break;
                }
                try {
                } catch (PackageManager.NameNotFoundException unused) {
                    io.e(TAG, "Package name not found");
                }
                if (bF(packagesForUid[i2])) {
                    z = true;
                    break;
                }
                i2++;
            }
            if (!z) {
                io.e(TAG, String.format("Other uid and my uid are do not have matching signatures (result: %d). The trusted app check also failed.", Integer.valueOf(a)));
            }
            return z;
        }
    }

    public List<ResolveInfo> queryIntentServices(Intent intent, int i) {
        List<ResolveInfo> queryIntentServices;
        try {
            queryIntentServices = this.lD.queryIntentServices(intent, i);
        } catch (Exception e) {
            a(e);
            queryIntentServices = this.lD.queryIntentServices(intent, i);
        }
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentServices) {
            if (bB(resolveInfo.serviceInfo.packageName)) {
                arrayList.add(resolveInfo);
            }
        }
        return arrayList;
    }

    public ek(Context context, boolean z) {
        if (context != null) {
            if (lB == null) {
                io.i(TAG, "Trying to use default signature based package trust logic. This should be on 3P device");
                a(new ez());
            }
            this.mContext = context;
            this.lC = context.getPackageName();
            this.lD = context.getPackageManager();
            if (z) {
                this.lF = TrustedAppUtils.b(context, this.lD);
                this.lE = false;
                return;
            }
            this.lF = TrustedAppUtils.a(context, this.lD);
            this.lE = IsolatedModeSwitcher.isAppInStaticIsolatedMode(context);
            return;
        }
        throw new IllegalArgumentException("context cannot be null");
    }

    public ServiceInfo a(ComponentName componentName) throws PackageManager.NameNotFoundException {
        ServiceInfo serviceInfo;
        try {
            serviceInfo = this.lD.getServiceInfo(componentName, 128);
        } catch (PackageManager.NameNotFoundException e) {
            throw e;
        } catch (Exception e2) {
            a(e2);
            serviceInfo = this.lD.getServiceInfo(componentName, 128);
        }
        if (serviceInfo == null) {
            return null;
        }
        if (bC(serviceInfo.packageName)) {
            return serviceInfo;
        }
        io.c(TAG, "Cannot get ServiceInfo from package since it is not signed with the Amazon Cert.", new Object[0]);
        return null;
    }

    private PackageInfo b(String str, int i) throws PackageManager.NameNotFoundException {
        return a(str, i, this.lD);
    }

    public static boolean b(Uri uri, PackageManager packageManager) {
        return dt.a(uri, packageManager) != null;
    }

    public static ProviderInfo a(Uri uri, PackageManager packageManager) {
        return dt.a(uri, packageManager);
    }

    public XmlResourceParser a(PackageItemInfo packageItemInfo) {
        if (packageItemInfo == null) {
            io.e(TAG, "PackageItemInfo cannot be null in getParserForPackage");
            return null;
        }
        return packageItemInfo.loadXmlMetaData(this.lD, "com.amazon.dcp.sso.AccountSubAuthenticator");
    }

    private boolean a(Signature[] signatureArr) {
        Set<Signature> set = this.lF;
        if (set == null) {
            return false;
        }
        for (Signature signature : signatureArr) {
            if (set.contains(signature)) {
                return true;
            }
        }
        return false;
    }

    public static PackageInfo a(String str, int i, PackageManager packageManager) throws PackageManager.NameNotFoundException {
        try {
            return packageManager.getPackageInfo(str, i);
        } catch (PackageManager.NameNotFoundException e) {
            throw e;
        } catch (Exception e2) {
            a(e2);
            return packageManager.getPackageInfo(str, i);
        }
    }

    public static boolean a(ProviderInfo providerInfo) {
        return providerInfo != null && providerInfo.enabled && providerInfo.applicationInfo != null && providerInfo.applicationInfo.enabled;
    }

    private int a(int i, int i2) {
        try {
            return this.lD.checkSignatures(i, i2);
        } catch (Exception e) {
            a(e);
            return this.lD.checkSignatures(i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Exception exc) {
        io.w(TAG, String.format("PackageManager call failed; retrying. Error Message : %s", exc.getMessage()));
        mq.b("PackageManagerError", new String[0]);
    }
}

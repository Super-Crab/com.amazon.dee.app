package com.amazon.alexa.utils.security;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes10.dex */
public final class ComponentEnabler {
    private static final String TAG = "ComponentEnabler";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class a extends b {
        private a(boolean z, boolean z2) {
            super(z, z2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static abstract class b {
        private final boolean a;
        private final boolean b;

        private b(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }

        public boolean a() {
            return this.a;
        }

        public boolean b() {
            return this.b;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class c extends b {
        private c(boolean z, boolean z2) {
            super(z, z2);
        }
    }

    private ComponentEnabler() {
        throw new UnsupportedOperationException();
    }

    public static boolean checkIfComponentExists(Context context, Class<?> cls) {
        return checkIfComponentExists(context.getPackageManager(), new ComponentName(context, cls));
    }

    public static boolean checkIfComponentExists(PackageManager packageManager, ComponentName componentName) {
        return getManifestEnabledStateForComponent(packageManager, componentName).a();
    }

    public static boolean checkIfComponentIsEnabled(Context context, Class<?> cls) {
        return checkIfComponentIsEnabled(context.getPackageManager(), new ComponentName(context, cls));
    }

    public static boolean checkIfComponentIsEnabled(PackageManager packageManager, ComponentName componentName) {
        return getActualEnabledStateForComponent(packageManager, componentName).b();
    }

    public static boolean checkIfServiceExists(Context context, Class<?> cls) {
        return checkIfServiceExists(context.getPackageManager(), new ComponentName(context, cls));
    }

    public static boolean checkIfServiceExists(PackageManager packageManager, ComponentName componentName) {
        return getManifestEnabledStateForService(packageManager, componentName).a();
    }

    public static boolean checkIfServiceIsEnabled(Context context, Class<?> cls) {
        return checkIfServiceIsEnabled(context.getPackageManager(), new ComponentName(context, cls));
    }

    public static boolean checkIfServiceIsEnabled(PackageManager packageManager, ComponentName componentName) {
        return getActualEnabledStateForComponent(packageManager, componentName).b();
    }

    private static a getActualEnabledStateForComponent(PackageManager packageManager, ComponentName componentName) {
        return getActualEnabledStateForComponent(packageManager, componentName, getManifestEnabledStateForComponent(packageManager, componentName));
    }

    private static a getActualEnabledStateForComponent(PackageManager packageManager, ComponentName componentName, c cVar) {
        boolean z = false;
        if (!cVar.a()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Component was not found: ");
            outline107.append(componentName.flattenToString());
            outline107.toString();
            return new a(false, false);
        }
        int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
        if (componentEnabledSetting == 1) {
            z = true;
        } else if (componentEnabledSetting != 2 && componentEnabledSetting != 3 && componentEnabledSetting != 4) {
            z = cVar.b();
        }
        return new a(true, z);
    }

    private static c getManifestEnabledStateForActivity(PackageManager packageManager, ComponentName componentName) {
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 512);
            if (activityInfo != null) {
                return new c(true, activityInfo.isEnabled());
            }
        } catch (PackageManager.NameNotFoundException unused) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Name not found exception: ");
            outline107.append(componentName.getClassName());
            Log.e(str, outline107.toString());
        } catch (Exception e) {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Exception while checking components within: ");
            outline1072.append(componentName.getPackageName());
            Log.e(str2, outline1072.toString(), e);
        }
        return new c(false, false);
    }

    private static c getManifestEnabledStateForComponent(PackageManager packageManager, ComponentName componentName) {
        c manifestEnabledStateForService = getManifestEnabledStateForService(packageManager, componentName);
        if (manifestEnabledStateForService.a()) {
            return manifestEnabledStateForService;
        }
        c manifestEnabledStateForActivity = getManifestEnabledStateForActivity(packageManager, componentName);
        if (manifestEnabledStateForActivity.a()) {
            return manifestEnabledStateForActivity;
        }
        c manifestEnabledStateForReceiver = getManifestEnabledStateForReceiver(packageManager, componentName);
        if (manifestEnabledStateForReceiver.a()) {
            return manifestEnabledStateForReceiver;
        }
        c manifestEnabledStateForProvider = getManifestEnabledStateForProvider(packageManager, componentName);
        return manifestEnabledStateForProvider.a() ? manifestEnabledStateForProvider : new c(false, false);
    }

    private static c getManifestEnabledStateForProvider(PackageManager packageManager, ComponentName componentName) {
        try {
            ProviderInfo providerInfo = packageManager.getProviderInfo(componentName, 512);
            if (providerInfo != null) {
                return new c(true, providerInfo.isEnabled());
            }
        } catch (PackageManager.NameNotFoundException unused) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Name not found exception: ");
            outline107.append(componentName.getClassName());
            Log.e(str, outline107.toString());
        } catch (Exception e) {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Exception while checking components within: ");
            outline1072.append(componentName.getPackageName());
            Log.e(str2, outline1072.toString(), e);
        }
        return new c(false, false);
    }

    private static c getManifestEnabledStateForReceiver(PackageManager packageManager, ComponentName componentName) {
        try {
            ActivityInfo receiverInfo = packageManager.getReceiverInfo(componentName, 512);
            if (receiverInfo != null) {
                return new c(true, receiverInfo.isEnabled());
            }
        } catch (PackageManager.NameNotFoundException unused) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Name not found exception: ");
            outline107.append(componentName.getClassName());
            Log.e(str, outline107.toString());
        } catch (Exception e) {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Exception while checking components within: ");
            outline1072.append(componentName.getPackageName());
            Log.e(str2, outline1072.toString(), e);
        }
        return new c(false, false);
    }

    private static c getManifestEnabledStateForService(PackageManager packageManager, ComponentName componentName) {
        try {
            ServiceInfo serviceInfo = packageManager.getServiceInfo(componentName, 512);
            if (serviceInfo != null) {
                return new c(true, serviceInfo.isEnabled());
            }
        } catch (PackageManager.NameNotFoundException unused) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Name not found exception: ");
            outline107.append(componentName.getClassName());
            Log.e(str, outline107.toString());
        } catch (Exception e) {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Exception while checking components within: ");
            outline1072.append(componentName.getPackageName());
            Log.e(str2, outline1072.toString(), e);
        }
        return new c(false, false);
    }

    public static void setComponentEnabled(Context context, Class<?> cls, boolean z) {
        setComponentEnabled(context.getPackageManager(), new ComponentName(context, cls), z);
    }

    private static void setComponentEnabled(PackageManager packageManager, ComponentName componentName, a aVar, boolean z) {
        if (!aVar.a()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Attempting to change enablement status of a component that doesn't exist: ");
            outline107.append(componentName.flattenToString());
            outline107.toString();
        } else if (z != (!aVar.b())) {
        } else {
            int i = 2;
            if (z) {
                i = 1;
            }
            String str = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Setting ");
            outline1072.append(componentName.getClassName());
            outline1072.append(" enabled: ");
            outline1072.append(z);
            Log.i(str, outline1072.toString());
            packageManager.setComponentEnabledSetting(componentName, i, 1);
        }
    }

    public static void setComponentEnabled(PackageManager packageManager, ComponentName componentName, boolean z) {
        setComponentEnabled(packageManager, componentName, getActualEnabledStateForComponent(packageManager, componentName), z);
    }

    public static void setServiceEnabled(Context context, Class<?> cls, boolean z) {
        setServiceEnabled(context.getPackageManager(), new ComponentName(context, cls), z);
    }

    public static void setServiceEnabled(PackageManager packageManager, ComponentName componentName, boolean z) {
        setComponentEnabled(packageManager, componentName, getActualEnabledStateForComponent(packageManager, componentName), z);
    }
}

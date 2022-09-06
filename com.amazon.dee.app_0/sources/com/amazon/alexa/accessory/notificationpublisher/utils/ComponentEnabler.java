package com.amazon.alexa.accessory.notificationpublisher.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public final class ComponentEnabler {
    private static final String TAG = "ComponentEnabler";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class ActualEnabledResult extends EnabledResult {
        private ActualEnabledResult(boolean z, boolean z2) {
            super(z, z2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class EnabledResult {
        private final boolean isEnabled;
        private final boolean wasFound;

        public boolean isEnabled() {
            return this.isEnabled;
        }

        public boolean wasFound() {
            return this.wasFound;
        }

        private EnabledResult(boolean z, boolean z2) {
            this.wasFound = z;
            this.isEnabled = z2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class ManifestEnabledResult extends EnabledResult {
        private ManifestEnabledResult(boolean z, boolean z2) {
            super(z, z2);
        }
    }

    private ComponentEnabler() {
        throw new UnsupportedOperationException();
    }

    public static boolean checkIfComponentExists(Context context, Class<?> cls) {
        return checkIfComponentExists(context.getPackageManager(), new ComponentName(context, cls));
    }

    public static boolean checkIfComponentIsEnabled(Context context, Class<?> cls) {
        return checkIfComponentIsEnabled(context.getPackageManager(), new ComponentName(context, cls));
    }

    public static boolean checkIfServiceExists(Context context, Class<?> cls) {
        return checkIfServiceExists(context.getPackageManager(), new ComponentName(context, cls));
    }

    public static boolean checkIfServiceIsEnabled(Context context, Class<?> cls) {
        return checkIfServiceIsEnabled(context.getPackageManager(), new ComponentName(context, cls));
    }

    private static ActualEnabledResult getActualEnabledStateForComponent(PackageManager packageManager, ComponentName componentName) {
        return getActualEnabledStateForComponent(packageManager, componentName, getManifestEnabledStateForComponent(packageManager, componentName));
    }

    private static ManifestEnabledResult getManifestEnabledStateForActivity(PackageManager packageManager, ComponentName componentName) {
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 512);
            if (activityInfo != null) {
                return new ManifestEnabledResult(true, activityInfo.isEnabled());
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
        return new ManifestEnabledResult(false, false);
    }

    private static ManifestEnabledResult getManifestEnabledStateForComponent(PackageManager packageManager, ComponentName componentName) {
        ManifestEnabledResult manifestEnabledStateForService = getManifestEnabledStateForService(packageManager, componentName);
        if (manifestEnabledStateForService.wasFound()) {
            return manifestEnabledStateForService;
        }
        ManifestEnabledResult manifestEnabledStateForActivity = getManifestEnabledStateForActivity(packageManager, componentName);
        if (manifestEnabledStateForActivity.wasFound()) {
            return manifestEnabledStateForActivity;
        }
        ManifestEnabledResult manifestEnabledStateForReceiver = getManifestEnabledStateForReceiver(packageManager, componentName);
        if (manifestEnabledStateForReceiver.wasFound()) {
            return manifestEnabledStateForReceiver;
        }
        ManifestEnabledResult manifestEnabledStateForProvider = getManifestEnabledStateForProvider(packageManager, componentName);
        return manifestEnabledStateForProvider.wasFound() ? manifestEnabledStateForProvider : new ManifestEnabledResult(false, false);
    }

    private static ManifestEnabledResult getManifestEnabledStateForProvider(PackageManager packageManager, ComponentName componentName) {
        try {
            ProviderInfo providerInfo = packageManager.getProviderInfo(componentName, 512);
            if (providerInfo != null) {
                return new ManifestEnabledResult(true, providerInfo.isEnabled());
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
        return new ManifestEnabledResult(false, false);
    }

    private static ManifestEnabledResult getManifestEnabledStateForReceiver(PackageManager packageManager, ComponentName componentName) {
        try {
            ActivityInfo receiverInfo = packageManager.getReceiverInfo(componentName, 512);
            if (receiverInfo != null) {
                return new ManifestEnabledResult(true, receiverInfo.isEnabled());
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
        return new ManifestEnabledResult(false, false);
    }

    private static ManifestEnabledResult getManifestEnabledStateForService(PackageManager packageManager, ComponentName componentName) {
        try {
            ServiceInfo serviceInfo = packageManager.getServiceInfo(componentName, 512);
            if (serviceInfo != null) {
                return new ManifestEnabledResult(true, serviceInfo.isEnabled());
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
        return new ManifestEnabledResult(false, false);
    }

    public static void setComponentEnabled(Context context, Class<?> cls, boolean z) {
        setComponentEnabled(context.getPackageManager(), new ComponentName(context, cls), z);
    }

    public static void setServiceEnabled(Context context, Class<?> cls, boolean z) {
        setServiceEnabled(context.getPackageManager(), new ComponentName(context, cls), z);
    }

    public static boolean checkIfComponentExists(PackageManager packageManager, ComponentName componentName) {
        return getManifestEnabledStateForComponent(packageManager, componentName).wasFound();
    }

    public static boolean checkIfComponentIsEnabled(PackageManager packageManager, ComponentName componentName) {
        return getActualEnabledStateForComponent(packageManager, componentName).isEnabled();
    }

    public static boolean checkIfServiceExists(PackageManager packageManager, ComponentName componentName) {
        return getManifestEnabledStateForService(packageManager, componentName).wasFound();
    }

    public static boolean checkIfServiceIsEnabled(PackageManager packageManager, ComponentName componentName) {
        return getActualEnabledStateForComponent(packageManager, componentName).isEnabled();
    }

    public static void setComponentEnabled(PackageManager packageManager, ComponentName componentName, boolean z) {
        setComponentEnabled(packageManager, componentName, getActualEnabledStateForComponent(packageManager, componentName), z);
    }

    public static void setServiceEnabled(PackageManager packageManager, ComponentName componentName, boolean z) {
        setComponentEnabled(packageManager, componentName, getActualEnabledStateForComponent(packageManager, componentName), z);
    }

    private static ActualEnabledResult getActualEnabledStateForComponent(PackageManager packageManager, ComponentName componentName, ManifestEnabledResult manifestEnabledResult) {
        boolean z = false;
        if (!manifestEnabledResult.wasFound()) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Component was not found: ");
            outline107.append(componentName.flattenToString());
            Log.d(str, outline107.toString());
            return new ActualEnabledResult(false, false);
        }
        int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
        if (componentEnabledSetting == 1) {
            z = true;
        } else if (componentEnabledSetting != 2 && componentEnabledSetting != 3 && componentEnabledSetting != 4) {
            z = manifestEnabledResult.isEnabled();
        }
        return new ActualEnabledResult(true, z);
    }

    private static void setComponentEnabled(PackageManager packageManager, ComponentName componentName, ActualEnabledResult actualEnabledResult, boolean z) {
        if (!actualEnabledResult.wasFound()) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Attempting to change enablement status of a component that doesn't exist: ");
            outline107.append(componentName.flattenToString());
            Log.d(str, outline107.toString());
        } else if (z != (!actualEnabledResult.isEnabled())) {
        } else {
            int i = 2;
            if (z) {
                i = 1;
            }
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Setting ");
            outline1072.append(componentName.getClassName());
            outline1072.append(" enabled: ");
            outline1072.append(z);
            Log.i(str2, outline1072.toString());
            packageManager.setComponentEnabledSetting(componentName, i, 1);
        }
    }
}

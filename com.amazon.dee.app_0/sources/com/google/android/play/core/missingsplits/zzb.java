package com.google.android.play.core.missingsplits;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.google.android.play.core.internal.zzag;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzb implements MissingSplitsManager {
    private static final zzag zza = new zzag("MissingSplitsManagerImpl");
    private final Context zzb;
    private final Runtime zzc;
    private final zza zzd;
    private final AtomicReference zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(Context context, Runtime runtime, zza zzaVar, AtomicReference atomicReference) {
        this.zzb = context;
        this.zzc = runtime;
        this.zzd = zzaVar;
        this.zze = atomicReference;
    }

    @TargetApi(21)
    private final List zza() {
        List<ActivityManager.AppTask> appTasks = ((ActivityManager) this.zzb.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)).getAppTasks();
        return appTasks != null ? appTasks : Collections.emptyList();
    }

    @Override // com.google.android.play.core.missingsplits.MissingSplitsManager
    public final boolean disableAppIfMissingRequiredSplits() {
        boolean booleanValue;
        boolean z;
        Class<?> cls;
        boolean z2;
        ApplicationInfo applicationInfo;
        Set emptySet;
        String[] strArr;
        int i = Build.VERSION.SDK_INT;
        synchronized (this.zze) {
            if (((Boolean) this.zze.get()) == null) {
                AtomicReference atomicReference = this.zze;
                int i2 = Build.VERSION.SDK_INT;
                try {
                    applicationInfo = this.zzb.getPackageManager().getApplicationInfo(this.zzb.getPackageName(), 128);
                } catch (PackageManager.NameNotFoundException unused) {
                    zza.zze("App '%s' is not found in the PackageManager", this.zzb.getPackageName());
                }
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    if (Boolean.TRUE.equals(applicationInfo.metaData.get("com.android.vending.splits.required"))) {
                        int i3 = Build.VERSION.SDK_INT;
                        try {
                            PackageInfo packageInfo = this.zzb.getPackageManager().getPackageInfo(this.zzb.getPackageName(), 0);
                            emptySet = new HashSet();
                            if (packageInfo != null && (strArr = packageInfo.splitNames) != null) {
                                Collections.addAll(emptySet, strArr);
                            }
                        } catch (PackageManager.NameNotFoundException unused2) {
                            zza.zze("App '%s' is not found in PackageManager", this.zzb.getPackageName());
                            emptySet = Collections.emptySet();
                        }
                        if (emptySet.isEmpty() || (emptySet.size() == 1 && emptySet.contains(""))) {
                            z2 = true;
                            atomicReference.set(Boolean.valueOf(z2));
                        }
                    }
                }
                z2 = false;
                atomicReference.set(Boolean.valueOf(z2));
            }
            booleanValue = ((Boolean) this.zze.get()).booleanValue();
        }
        if (booleanValue) {
            Iterator it2 = zza().iterator();
            while (true) {
                if (it2.hasNext()) {
                    ActivityManager.AppTask appTask = (ActivityManager.AppTask) it2.next();
                    if (appTask.getTaskInfo() != null && appTask.getTaskInfo().baseIntent != null && appTask.getTaskInfo().baseIntent.getComponent() != null && PlayCoreMissingSplitsActivity.class.getName().equals(appTask.getTaskInfo().baseIntent.getComponent().getClassName())) {
                        break;
                    }
                } else {
                    loop1: for (ActivityManager.AppTask appTask2 : zza()) {
                        ActivityManager.RecentTaskInfo taskInfo = appTask2.getTaskInfo();
                        if (taskInfo != null && taskInfo.baseIntent != null && taskInfo.baseIntent.getComponent() != null) {
                            ComponentName component = taskInfo.baseIntent.getComponent();
                            String className = component.getClassName();
                            try {
                                cls = Class.forName(className);
                            } catch (ClassNotFoundException unused3) {
                                zza.zze("ClassNotFoundException when scanning class hierarchy of '%s'", className);
                                try {
                                    if (this.zzb.getPackageManager().getActivityInfo(component, 0) != null) {
                                    }
                                } catch (PackageManager.NameNotFoundException unused4) {
                                    continue;
                                }
                            }
                            while (cls != null) {
                                if (cls.equals(Activity.class)) {
                                    z = true;
                                    break;
                                }
                                Class<? super Object> superclass = cls.getSuperclass();
                                cls = superclass != cls ? superclass : null;
                            }
                            continue;
                        }
                    }
                    z = false;
                    this.zzd.zza();
                    for (ActivityManager.AppTask appTask3 : zza()) {
                        appTask3.finishAndRemoveTask();
                    }
                    if (z) {
                        this.zzb.getPackageManager().setComponentEnabledSetting(new ComponentName(this.zzb, PlayCoreMissingSplitsActivity.class), 1, 1);
                        this.zzb.startActivity(new Intent(this.zzb, PlayCoreMissingSplitsActivity.class).addFlags(884998144));
                    }
                    this.zzc.exit(0);
                }
            }
            return true;
        }
        if (this.zzd.zzc()) {
            this.zzd.zzb();
            this.zzc.exit(0);
        }
        return false;
    }

    @Override // com.google.android.play.core.missingsplits.MissingSplitsManager
    public final boolean isMissingRequiredSplits() {
        boolean booleanValue;
        Set emptySet;
        String[] strArr;
        synchronized (this.zze) {
            if (((Boolean) this.zze.get()) == null) {
                AtomicReference atomicReference = this.zze;
                int i = Build.VERSION.SDK_INT;
                boolean z = false;
                try {
                    ApplicationInfo applicationInfo = this.zzb.getPackageManager().getApplicationInfo(this.zzb.getPackageName(), 128);
                    if (applicationInfo != null && applicationInfo.metaData != null) {
                        if (Boolean.TRUE.equals(applicationInfo.metaData.get("com.android.vending.splits.required"))) {
                            int i2 = Build.VERSION.SDK_INT;
                            try {
                                PackageInfo packageInfo = this.zzb.getPackageManager().getPackageInfo(this.zzb.getPackageName(), 0);
                                emptySet = new HashSet();
                                if (packageInfo != null && (strArr = packageInfo.splitNames) != null) {
                                    Collections.addAll(emptySet, strArr);
                                }
                            } catch (PackageManager.NameNotFoundException unused) {
                                zza.zze("App '%s' is not found in PackageManager", this.zzb.getPackageName());
                                emptySet = Collections.emptySet();
                            }
                            if (emptySet.isEmpty() || (emptySet.size() == 1 && emptySet.contains(""))) {
                                z = true;
                            }
                        }
                    }
                } catch (PackageManager.NameNotFoundException unused2) {
                    zza.zze("App '%s' is not found in the PackageManager", this.zzb.getPackageName());
                }
                atomicReference.set(Boolean.valueOf(z));
            }
            booleanValue = ((Boolean) this.zze.get()).booleanValue();
        }
        return booleanValue;
    }
}

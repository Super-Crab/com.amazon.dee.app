package com.amazon.alexa.voice.permissions;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.Arrays;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class VoicePermissionsAuthority implements ApplicationLifecycleObserver {
    private static final String TAG = "VoicePermissionsAuthority";
    private final BehaviorSubject<Boolean> allPermissionsBehaviorSubject;
    private final ApplicationLifecycleService applicationLifecycleService;
    private final Context context;
    private final BehaviorSubject<Boolean> minimumPermissionsBehaviorSubject;
    private static final String[] MINIMUM_PERMISSIONS = {"android.permission.RECORD_AUDIO"};
    private static final String[] ALL_PERMISSIONS = {"android.permission.RECORD_AUDIO", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};

    @Inject
    public VoicePermissionsAuthority(Context context, ApplicationLifecycleService applicationLifecycleService) {
        this.context = context;
        this.minimumPermissionsBehaviorSubject = BehaviorSubject.createDefault(Boolean.valueOf(PermissionsUtils.hasPermissions(context, MINIMUM_PERMISSIONS)));
        this.allPermissionsBehaviorSubject = BehaviorSubject.createDefault(Boolean.valueOf(PermissionsUtils.hasPermissions(context, ALL_PERMISSIONS)));
        this.applicationLifecycleService = applicationLifecycleService;
        this.applicationLifecycleService.addObserver(this);
    }

    public static String[] getAllPermissions() {
        String[] strArr = ALL_PERMISSIONS;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public static String[] getMinimumPermissions() {
        String[] strArr = MINIMUM_PERMISSIONS;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    private boolean hasPermissions(@NonNull String[] strArr) {
        int i = Build.VERSION.SDK_INT;
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(this.context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    public BehaviorSubject<Boolean> allPermissions() {
        return this.allPermissionsBehaviorSubject;
    }

    public boolean hasAllPermissions() {
        return hasPermissions(ALL_PERMISSIONS);
    }

    public boolean hasMinimumPermissions() {
        return hasPermissions(MINIMUM_PERMISSIONS);
    }

    public BehaviorSubject<Boolean> minimumPermissions() {
        return this.minimumPermissionsBehaviorSubject;
    }

    @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
    public void onStart() {
        update();
    }

    @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
    public void onStop() {
    }

    public synchronized void release() {
        this.applicationLifecycleService.removeObserver(this);
    }

    public synchronized void update() {
        boolean hasMinimumPermissions = hasMinimumPermissions();
        if (this.minimumPermissionsBehaviorSubject.getValue().booleanValue() != hasMinimumPermissions) {
            String str = TAG;
            Log.i(str, "minimumPermissionsBehaviorSubject?: " + hasMinimumPermissions);
            this.minimumPermissionsBehaviorSubject.onNext(Boolean.valueOf(hasMinimumPermissions));
        }
        boolean hasAllPermissions = hasAllPermissions();
        if (this.allPermissionsBehaviorSubject.getValue().booleanValue() != hasAllPermissions) {
            String str2 = TAG;
            Log.i(str2, "allPermissionsBehaviorSubject?: " + hasAllPermissions);
            this.allPermissionsBehaviorSubject.onNext(Boolean.valueOf(hasAllPermissions));
        }
    }
}

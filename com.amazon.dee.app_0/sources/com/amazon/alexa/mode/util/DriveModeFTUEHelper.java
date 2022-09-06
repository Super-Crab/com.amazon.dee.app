package com.amazon.alexa.mode.util;

import android.util.Log;
import com.amazon.alexa.mode.statemachine.StateContext;
/* loaded from: classes9.dex */
public class DriveModeFTUEHelper {
    private static final String TAG = LogTag.forClass(DriveModeFTUEHelper.class);
    private final PermissionChecker mPermissionChecker;
    private PreferredNavigationAppContentResolver mPreferredNavigationAppContentResolver;
    private PrefsDialogHelper mPrefsDialogHelper;

    /* loaded from: classes9.dex */
    public enum FTUEType {
        AccessoryFTUE,
        AutoBluetoothFTUE,
        DefaultFTUE
    }

    public DriveModeFTUEHelper(PreferredNavigationAppContentResolver preferredNavigationAppContentResolver, PrefsDialogHelper prefsDialogHelper, PermissionChecker permissionChecker) {
        this.mPreferredNavigationAppContentResolver = preferredNavigationAppContentResolver;
        this.mPrefsDialogHelper = prefsDialogHelper;
        this.mPermissionChecker = permissionChecker;
    }

    public FTUEType getFTUEType(StateContext stateContext) {
        if (stateContext.isManualIngressMode()) {
            return FTUEType.DefaultFTUE;
        }
        if (stateContext.isAccessoryConnected()) {
            return FTUEType.AccessoryFTUE;
        }
        return FTUEType.AutoBluetoothFTUE;
    }

    public boolean shouldEnterDriveModeFTUE() {
        boolean z = this.mPreferredNavigationAppContentResolver.getPreferredNavigationApp() != null;
        boolean z2 = this.mPrefsDialogHelper.getDriveModeConfiguredPref() == 1;
        String str = TAG;
        Log.i(str, "shouldEnterDriveModeFTUE | hasPreferredNavApp: " + z + " | isDriveModeConfigured: " + z2);
        return !z || !z2;
    }

    public boolean shouldEnterStandAloneModeFTUE() {
        boolean z = this.mPreferredNavigationAppContentResolver.getPreferredNavigationApp() != null;
        boolean hasMicrophonePermission = this.mPermissionChecker.hasMicrophonePermission();
        boolean z2 = this.mPrefsDialogHelper.getStandAloneModeConfiguredPref() == 1;
        String str = TAG;
        Log.i(str, "shouldEnterStandAloneModeFTUE | hasPreferredNavApp: " + z + " | isDriveModeConfigured: " + z2 + " | hasMicrophonePermission: " + hasMicrophonePermission);
        return !z || !z2 || !hasMicrophonePermission;
    }
}

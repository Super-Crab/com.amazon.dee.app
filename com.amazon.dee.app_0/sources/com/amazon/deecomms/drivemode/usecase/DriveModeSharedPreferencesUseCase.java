package com.amazon.deecomms.drivemode.usecase;

import com.amazon.deecomms.auth.CommsSharedPreferences;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class DriveModeSharedPreferencesUseCase {
    public static final String KEY_CURRENT_APP_MODE = "current_mode";
    public static final String MODE_DRIVEMODE = "mode_drivemode";
    public static final String MODE_MAIN = "mode_main";
    CommsSharedPreferences mCommsPrefs;

    @Inject
    public DriveModeSharedPreferencesUseCase(CommsSharedPreferences commsSharedPreferences) {
        this.mCommsPrefs = commsSharedPreferences;
    }

    public boolean isInDriveMode() {
        if (this.mCommsPrefs.containsKey(KEY_CURRENT_APP_MODE)) {
            return this.mCommsPrefs.getString(KEY_CURRENT_APP_MODE, MODE_MAIN).equals(MODE_DRIVEMODE);
        }
        return false;
    }

    public void persistMode(boolean z) {
        if (z) {
            this.mCommsPrefs.putString(KEY_CURRENT_APP_MODE, MODE_DRIVEMODE);
        } else {
            this.mCommsPrefs.putString(KEY_CURRENT_APP_MODE, MODE_MAIN);
        }
    }
}

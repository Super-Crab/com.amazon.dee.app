package com.amazon.alexa.voiceui.driveMode;

import com.amazon.alexa.api.DriveModeState;
/* loaded from: classes11.dex */
public interface DriveModeListener {
    void onAutoModeEnabled(boolean z);

    void onAutoModeState(DriveModeState driveModeState);

    void onThemeChanged(boolean z);
}
